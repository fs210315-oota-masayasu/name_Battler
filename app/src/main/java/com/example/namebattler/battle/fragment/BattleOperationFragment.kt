package com.example.namebattler.battle.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.battle.BattleLogAdapter
import com.example.namebattler.battle.BattleManager
import com.example.namebattler.battle.activity.OperationChangeActivity
import com.example.namebattler.data.battleData.CharacterInformationHolder
import com.example.namebattler.data.battleData.InformationManager
import com.example.namebattler.data.battleData.Operation
import com.example.namebattler.databinding.FragmentBattleOperationBinding
import com.example.namebattler.util.BackStack
import com.example.namebattler.util.Belong
import com.example.namebattler.util.EndEnum
import com.example.namebattler.util.HeaderFlag
import com.example.namebattler.viewModel.BattleViewModel
import com.example.namebattler.viewModel.HeaderViewModel
import com.example.namebattler.viewModel.getViewModelFactory

private const val ARG_ENEMY = "arg_enemy"
private const val ARG_Player = "arg_Player"

class BattleOperationFragment : Fragment() {

    private lateinit var binding: FragmentBattleOperationBinding

    private val battleViewModel: BattleViewModel by viewModels{ getViewModelFactory() }
    private val headerViewModel: HeaderViewModel by viewModels{ getViewModelFactory() }

//    private var enemyList = arrayListOf<CharacterInformationHolder>()
//    private var playerList = arrayListOf<CharacterInformationHolder>()

    var informText = MutableLiveData<MutableList<String>>()

    var endingInformation = MutableLiveData<String>()

    var count = 0


    private lateinit var battleManager: BattleManager

/*    companion object {
        @JvmStatic
        fun newInstance(enemyObj: ArrayList<CharacterInformationHolder>, playerObj: ArrayList<CharacterInformationHolder>) =
            BattleOperationFragment().apply {
                this.enemyList = enemyObj
                this.playerList = playerObj

                arguments = Bundle().apply {
                    putSerializable(ARG_ENEMY, enemyList)
                    putSerializable(ARG_Player, playerList)
                }
            }
    }*/

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            enemyList = it.getSerializable(ARG_ENEMY) as ArrayList<CharacterInformationHolder>
//            playerList = it.getSerializable(ARG_Player) as ArrayList<CharacterInformationHolder>
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        binding = FragmentBattleOperationBinding.inflate(inflater, container, false).apply {

            /** 作戦変更 **/
            //作戦名の表示
            battleViewModel.operationRadioType.observe(viewLifecycleOwner,{
                valueCurrentOperation.text = it.text
            })

            //ヘッダー情報更新（作戦変更画面）
            headerViewModel.apply {
                headerText.postValue(getString(R.string.operation))
                outputFlag = HeaderFlag.OPERATION_CHANGE
            }

            //作戦変更画面へ遷移
            btnChangeOperation.setOnClickListener {
                val changeOperationFragmentManager  = parentFragmentManager
                val changeOperationFragmentTransaction = changeOperationFragmentManager.beginTransaction()


                changeOperationFragmentTransaction.addToBackStack(BackStack.BATTLE_MAIN.name)

                changeOperationFragmentTransaction.replace(
                    R.id.attach_screen,
                    OperationChangeFragment()
                )
                changeOperationFragmentTransaction.commit()
            }

            //TODO enemyList、playerListをViewModelから取得する

            //battleManagerのインスタンス取得
            battleManager = BattleManager()
            //エネミーデータとパーティデータへ分割
            battleViewModel.toSplitCurrentList()

            //battleManagerに戦闘処理用の各変数にデータを格納
            battleManager.setCurrentInformation(battleViewModel.informationNotice?.value?: arrayListOf())
            battleManager.initCharacterList(battleViewModel.currentEnemyList,battleViewModel.currentPlayerList)
            battleManager.initInitiative()

            //「次のターン」処理
            btnNextTurn.setOnClickListener{
                val isEnding = battleManager.isEnding()

                val operationName = battleViewModel.operationRadioType.value?.text?:"ERROR"

                count++

                battleManager.count = count

                //次ターンで勝敗画面を表示
                if (isEnding != ""){
                    endingInformation.postValue(isEnding)
                }else{
                    //出力するログ情報（addした順に出力される）
                    val setLogData = mutableListOf<String>()

                    //バトル処理
                    val setText = battleManager.battleProcess(operationName)
                    setLogData.addAll(setText)

                    val resultInformation:ArrayList<CharacterInformationHolder> = battleManager.getCurrentInformation()


                    //キャラクター情報をLiveDataへ格納
                    battleViewModel.setInformationNotice(resultInformation)
                    //ログ情報をLiveDataに格納
                    informText.postValue(setLogData)


                }
            }




            //バトルログをrecyclerViewで表示

            val recyclerViewOfBattleLog = logListView
            val battleLogAdapter = BattleLogAdapter(this@BattleOperationFragment.requireContext())
            recyclerViewOfBattleLog.adapter = battleLogAdapter
            recyclerViewOfBattleLog.layoutManager = LinearLayoutManager(this@BattleOperationFragment.context)

            val list = mutableListOf<String>()
            battleLogAdapter.setBattleLog(list)
            //バトルログをターン開始時の位置までスクロールさせる
            informText.observe(viewLifecycleOwner, { it ->
                recyclerViewOfBattleLog.scrollToPosition(battleLogAdapter.position?:0)
                it.forEach {
                    battleLogAdapter.addBattleLog(it)
                }
            })


            endingInformation.observe(viewLifecycleOwner , Observer{

                //ヘッダー情報をセット
                headerViewModel.apply {
                    headerText.postValue(getString(R.string.battle_result))
                    outputFlag = HeaderFlag.BATTLE_MAIN
                }
                val enemy = InformationManager().resetCharacterList(battleViewModel.currentEnemyList)
                val player = InformationManager().resetCharacterList(battleViewModel.currentPlayerList)
                val fragmentManager: FragmentManager = parentFragmentManager

                if (savedInstanceState == null){
                    // FragmentManagerのインスタンス生成
                    if (it == EndEnum.WIN.name){
                        //勝利
                        // FragmentTransactionのインスタンスを取得
                        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                        // インスタンスに対して張り付け方を指定する
                        fragmentTransaction.replace(R.id.battle_operation_area, WinViewFragment())
                        fragmentTransaction.commit()
                    }else if(it == EndEnum.LOSE.name){
                        //敗北
                        // FragmentTransactionのインスタンスを取得
                        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                        // インスタンスに対して張り付け方を指定する
                        fragmentTransaction.replace(R.id.battle_operation_area, LoseViewFragment())
                        fragmentTransaction.commit()
                    }
                }
            })




/*

            val recyclerViewOfBattleLog = logListView

            val getContext = activity
            if(getContext != null){
                val adapter = BattleLogAdapter(getContext)
                recyclerViewOfBattleLog.adapter = adapter
                recyclerViewOfBattleLog.layoutManager = LinearLayoutManager(getContext)

                val list = mutableListOf<String>()
                adapter.setBattleLog(list)

                //バトルログをターン開始時の位置までスクロールさせる
                informText.observe(viewLifecycleOwner, Observer { it ->
                    recyclerViewOfBattleLog.scrollToPosition(adapter.pos?:0)
                    it.forEach {
                        adapter.addBattleLog(it)
                    }
                })
                endingInformation.observe(viewLifecycleOwner , Observer{
                    val enemy = InformationManager().resetCharacterList(enemyList)
                    val player = InformationManager().resetCharacterList(playerList)
                    val fragmentManager: FragmentManager = parentFragmentManager

                    if (savedInstanceState == null){
                        // FragmentManagerのインスタンス生成
                        if (it == EndEnum.WIN.name){
                            //勝利
                            // FragmentTransactionのインスタンスを取得
                            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                            // インスタンスに対して張り付け方を指定する
                            fragmentTransaction.replace(R.id.battle_operation_area, WinViewFragment.newInstance(enemy, player))
                            fragmentTransaction.commit()
                        }else if(it == EndEnum.LOSE.name){
                            //敗北
                            // FragmentTransactionのインスタンスを取得
                            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                            // インスタンスに対して張り付け方を指定する
                            fragmentTransaction.replace(R.id.battle_operation_area, LoseViewFragment.newInstance(enemy, player))
                            fragmentTransaction.commit()
                        }
                    }
                })
            }


*/




        }

        return binding.root
    }

    override fun onViewCreated(view :View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
/*
    override fun onStart() {
        super.onStart()
        //TODO onStartから移動

        //作戦変更
        //作戦名取得用インスタンス
        val operationInstance = Operation().getInstance()
        //作戦名の取得
        val operationName = operationInstance.get()

        //TextViewへ作戦名を渡す
        binding.valueCurrentOperation.text = operationName

        //作戦変更画面へ遷移
        binding.btnChangeOperation.setOnClickListener {
            val intent = Intent(activity, OperationChangeActivity::class.java)
            startActivity(intent)
        }


        //InformationManagerのインスタンス取得
        val informationManager = InformationManager().getInstance()
        //バトル開始前の各キャラクター情報
        //player、enemyをまとめる
        val currentInformation = arrayListOf <CharacterInformationHolder>()

        currentInformation.addAll(enemyList)
        currentInformation.addAll(playerList)
        battleManager.setCurrentInformation(currentInformation)


        //「次のターン」処理
        binding.btnNextTurn.setOnClickListener {
            count++

            battleManager.count = count

            //出力するログ情報（addした順に出力される）
            val setLogData = mutableListOf<String>()

            //バトル処理
            val setText = battleManager.battleProcess(operationName)
            setLogData.addAll(setText)

            val resultInformation:ArrayList<CharacterInformationHolder> = battleManager.getCurrentInformation()

            //キャラクター情報をLiveDataへ格納
            battleViewModel.setInformationNotice(resultInformation)
            //ログ情報をLiveDataに格納
            informText.postValue(setLogData)

            val isEnding = battleManager.isEnding()
            //勝敗画面
            if (isEnding != ""){
                endingInformation.postValue(isEnding)
            }
        }
    }

*/
}


// TODO OLD
/*
private const val ARG_ENEMY = "arg_enemy"
private const val ARG_Player = "arg_Player"

class BattleOperationFragment : Fragment() {

    private var enemyList = arrayListOf<CharacterInformationHolder>()
    private var playerList = arrayListOf<CharacterInformationHolder>()

    var informText = MutableLiveData<MutableList<String>>()

    var endingInformation = MutableLiveData<String>()

    var count = 0

    private lateinit var battleManager: BattleManager

    companion object {
        @JvmStatic
        fun newInstance(enemyObj: ArrayList<CharacterInformationHolder>, playerObj: ArrayList<CharacterInformationHolder>) =
            BattleOperationFragment().apply {
                this.enemyList = enemyObj
                this.playerList = playerObj

                arguments = Bundle().apply {
                    putSerializable(ARG_ENEMY, enemyList)
                    putSerializable(ARG_Player, playerList)
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            enemyList = it.getSerializable(ARG_ENEMY) as ArrayList<CharacterInformationHolder>
            playerList = it.getSerializable(ARG_Player) as ArrayList<CharacterInformationHolder>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_battle_operation, container, false)
    }

    override fun onViewCreated(view :View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        battleManager = BattleManager()
        battleManager.initCharacterList(enemyList,playerList)
        battleManager.initInitiative()

        //バトルログをrecyclerViewで表示
        val recyclerViewOfBattleLog = view.findViewById<RecyclerView>(R.id.log_list_view)
        val getContext = activity
        if(getContext != null){
            val adapter = BattleLogAdapter(getContext)
            recyclerViewOfBattleLog.adapter = adapter
            recyclerViewOfBattleLog.layoutManager = LinearLayoutManager(getContext)

            val list = mutableListOf<String>()
            adapter.setBattleLog(list)

            //バトルログをターン開始時の位置までスクロールさせる
            informText.observe(viewLifecycleOwner, Observer { it ->
                recyclerViewOfBattleLog.scrollToPosition(adapter.pos?:0)
                it.forEach {
                    adapter.addBattleLog(it)
                }
            })
            endingInformation.observe(viewLifecycleOwner , Observer{
                val enemy = InformationManager().resetCharacterList(enemyList)
                val player = InformationManager().resetCharacterList(playerList)
                val fragmentManager: FragmentManager = parentFragmentManager

                if (savedInstanceState == null){
                    // FragmentManagerのインスタンス生成
                    if (it == EndEnum.WIN.name){
                        //勝利
                        // FragmentTransactionのインスタンスを取得
                        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                        // インスタンスに対して張り付け方を指定する
                        fragmentTransaction.replace(R.id.battle_operation_area, WinViewFragment.newInstance(enemy, player))
                        fragmentTransaction.commit()
                    }else if(it == EndEnum.LOSE.name){
                        //敗北
                        // FragmentTransactionのインスタンスを取得
                        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                        // インスタンスに対して張り付け方を指定する
                        fragmentTransaction.replace(R.id.battle_operation_area, LoseViewFragment.newInstance(enemy, player))
                        fragmentTransaction.commit()
                    }
                }
            })
        }
    }

    override fun onStart() {
        super.onStart()

        //作戦変更
        //作戦名取得用インスタンス
        val operationInstance = Operation().getInstance()
        //作戦名の取得
        val operationName = operationInstance.get()

        //TextViewへ作戦名を渡す
        val operationView : TextView = view!!.findViewById(R.id.value_current_operation)
        operationView.text = operationName

        //作戦変更画面へ遷移
        btn_change_operation.setOnClickListener {
            val intent = Intent(activity, OperationChangeActivity::class.java)
            startActivity(intent)
        }


        //InformationManagerのインスタンス取得
        val informationManager = InformationManager().getInstance()
        //バトル開始前の各キャラクター情報
         //player、enemyをまとめる
        val currentInformation = arrayListOf <CharacterInformationHolder>()

        currentInformation.addAll(enemyList)
        currentInformation.addAll(playerList)
        battleManager.setCurrentInformation(currentInformation)


        //「次のターン」処理
        btn_next_turn.setOnClickListener {
            count++

            battleManager.count = count

            //出力するログ情報（addした順に出力される）
            val setLogData = mutableListOf<String>()

            //バトル処理
            val setText = battleManager.battleProcess(operationName)
            setLogData.addAll(setText)

            val resultInformation:ArrayList<CharacterInformationHolder> = battleManager.getCurrentInformation()

            //キャラクター情報をLiveDataへ格納
            informationManager.setInformationNotice(resultInformation)
            //ログ情報をLiveDataに格納
            informText.postValue(setLogData)

            val isEnding = battleManager.isEnding()
            //勝敗画面
            if (isEnding != ""){
                endingInformation.postValue(isEnding)
            }
        }

    }
}

*/

