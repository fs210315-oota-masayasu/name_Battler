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
import com.example.namebattler.util.EndEnum
import kotlinx.android.synthetic.main.fragment_battle_operation.*

private const val ARG_ENEMY = "arg_enemy"
private const val ARG_Player = "arg_Player"

class BattleOperationFragment : Fragment() {

    private var enemyList = arrayListOf<CharacterInformationHolder>()
    private var playerList = arrayListOf<CharacterInformationHolder>()
//    private var enemyList = arrayListOf<CharacterHolder>()
//    private var playerList = arrayListOf<CharacterHolder>()

    var informText = MutableLiveData<MutableList<String>>()
    var endingInformation = MutableLiveData<String>()

    var count = 0

    companion object {
        @JvmStatic
        //fun newInstance(enemyObj : ArrayList<CharacterHolder>, playerObj : ArrayList<CharacterHolder>) =
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_battle_operation, container, false)
    }


    override fun onViewCreated(view :View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args : Bundle? = arguments

        val battleManagerInstance = BattleManager().getInstance()
        battleManagerInstance.initCharacterList(enemyList,playerList)
        battleManagerInstance.initInitiative()

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

                if (savedInstanceState == null){
                    // FragmentManagerのインスタンス生成
                    val fragmentManager: FragmentManager = parentFragmentManager

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

        //BattleManagerを呼び出してキャラクター情報を格納

        val battleManager = BattleManager().getInstance()

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

            resultInformation.forEach {
                Log.d("INFO", "[ BattleOperationFragment ][207] info :[ " + it!!.id + "]  name is : " + it!!.name)
            }

            //キャラクター情報をLiveDataへ格納
            informationManager.setInformationNotice(resultInformation)
            //ログ情報をLiveDataに格納
            informText.postValue(setLogData)

            val isEnding = battleManager.isEnding()
            Log.d("INFO","[BattleOperationFragment]  Ending Info $isEnding")

            //勝敗画面
            if (isEnding != ""){
                endingInformation.postValue(isEnding)
            }
        }
    }

}
