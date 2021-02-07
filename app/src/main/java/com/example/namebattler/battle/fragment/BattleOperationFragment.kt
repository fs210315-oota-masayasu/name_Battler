package com.example.namebattler.battle.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namebattler.R
import com.example.namebattler.battle.BattleLogAdapter
import com.example.namebattler.battle.BattleManager
import com.example.namebattler.data.battleData.CharacterInformationHolder
import com.example.namebattler.databinding.FragmentBattleOperationBinding
import com.example.namebattler.util.BackStack
import com.example.namebattler.util.EndEnum
import com.example.namebattler.util.HeaderFlag
import com.example.namebattler.viewModel.BattleViewModel
import com.example.namebattler.viewModel.HeaderViewModel
import com.example.namebattler.viewModel.getViewModelFactory


class BattleOperationFragment : Fragment() {

    private lateinit var binding: FragmentBattleOperationBinding

    private val battleViewModel: BattleViewModel by viewModels { getViewModelFactory() }
    private val headerViewModel: HeaderViewModel by viewModels { getViewModelFactory() }
    private lateinit var battleManager: BattleManager
    var informText = MutableLiveData<MutableList<String>>()
    var endingInformation = MutableLiveData<String>()

    var count = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBattleOperationBinding.inflate(inflater, container, false).apply {

            /** 作戦変更 **/
            //作戦名の表示
            battleViewModel.operationRadioType.observe(viewLifecycleOwner, {
                valueCurrentOperation.text = it.text
            })

            //ヘッダー情報更新（作戦変更画面）
            headerViewModel.apply {
                headerText.postValue(getString(R.string.operation))
                outputFlag = HeaderFlag.OPERATION_CHANGE
            }

            //作戦変更画面へ遷移
            btnChangeOperation.setOnClickListener {
                val changeOperationFragmentManager = parentFragmentManager
                val changeOperationFragmentTransaction =
                    changeOperationFragmentManager.beginTransaction()
                changeOperationFragmentTransaction.addToBackStack(BackStack.BATTLE_MAIN.name)
                changeOperationFragmentTransaction.replace(
                    R.id.attach_screen,
                    OperationChangeFragment()
                )
                changeOperationFragmentTransaction.commit()
            }

            //battleManagerのインスタンス取得
            battleManager = BattleManager()
            //エネミーデータとパーティデータへ分割
            battleViewModel.toSplitCurrentList()

            //battleManagerに戦闘処理用の各変数にデータを格納
            battleManager.setCurrentInformation(
                battleViewModel.informationNotice.value ?: arrayListOf()
            )
            //Battle
            battleManager.initCharacterList(
                battleViewModel.currentEnemyList,
                battleViewModel.currentPlayerList
            )
            battleManager.initInitiative()

            //「次のターン」押下時の処理
            btnNextTurn.setOnClickListener {

                //勝敗の情報を取得
                val isEnding = battleManager.isEnding()
                //作戦を取得
                val operationName = battleViewModel.operationRadioType.value?.text ?: "ERROR"
                //ターン経過数
                count++
                battleManager.count = count

                //勝敗がついていたら「次ターン」でバトル画面を表示させる
                if (isEnding != "") {
                    endingInformation.postValue(isEnding)
                } else {
                    //出力するログ情報（addした順に出力される）
                    val setLogData = mutableListOf<String>()

                    //バトル処理
                    val setText = battleManager.battleProcess(operationName)
                    setLogData.addAll(setText)

                    val resultInformation: ArrayList<CharacterInformationHolder> =
                        battleManager.getCurrentInformation()


                    //キャラクター情報をLiveDataへ格納
                    battleViewModel.setInformationNotice(resultInformation)
                    //ログ情報をLiveDataに格納
                    informText.postValue(setLogData)
                }
            }

            //バトルログをrecyclerViewで表示
            val recyclerViewOfBattleLog = logListView
            val battleLogAdapter = BattleLogAdapter()
            recyclerViewOfBattleLog.adapter = battleLogAdapter
            recyclerViewOfBattleLog.layoutManager =
                LinearLayoutManager(this@BattleOperationFragment.context)

            val list = mutableListOf<String>()
            battleLogAdapter.setBattleLog(list)
            //バトルログをターン開始時の位置までスクロールさせる
            informText.observe(viewLifecycleOwner, { it ->
                recyclerViewOfBattleLog.scrollToPosition(battleLogAdapter.position ?: 0)
                it.forEach {
                    battleLogAdapter.addBattleLog(it)
                }
            })

            //勝敗がついていた時にバトル結果画面を表示させる
            endingInformation.observe(viewLifecycleOwner, {

                //ヘッダー情報をセット
                headerViewModel.apply {
                    headerText.postValue(getString(R.string.battle_result))
                    outputFlag = HeaderFlag.BATTLE_MAIN
                }
                val fragmentManager: FragmentManager = parentFragmentManager

                if (savedInstanceState == null) {
                    // FragmentManagerのインスタンス生成
                    if (it == EndEnum.WIN.name) {
                        //勝利
                        // FragmentTransactionのインスタンスを取得
                        val fragmentTransaction: FragmentTransaction =
                            fragmentManager.beginTransaction()
                        // インスタンスに対して張り付け方を指定する
                        fragmentTransaction.replace(R.id.battle_operation_area, WinViewFragment())
                        fragmentTransaction.commit()
                    } else if (it == EndEnum.LOSE.name) {
                        //敗北
                        // FragmentTransactionのインスタンスを取得
                        val fragmentTransaction: FragmentTransaction =
                            fragmentManager.beginTransaction()
                        // インスタンスに対して張り付け方を指定する
                        fragmentTransaction.replace(R.id.battle_operation_area, LoseViewFragment())
                        fragmentTransaction.commit()
                    }
                }
            })
        }
        return binding.root
    }
}
