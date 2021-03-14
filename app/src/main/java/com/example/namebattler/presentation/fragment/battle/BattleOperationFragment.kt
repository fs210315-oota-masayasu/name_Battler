package com.example.namebattler.presentation.fragment.battle

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
import com.example.namebattler.presentation.adapter.battle.BattleLogAdapter
import com.example.namebattler.function.battle.OverallProgressOfBattle
import com.example.namebattler.function.battle.CharacterInformationHolder
import com.example.namebattler.databinding.FragmentBattleOperationBinding
import com.example.namebattler.function.BackStack
import com.example.namebattler.function.EndEnum
import com.example.namebattler.function.HeaderFlag
import com.example.namebattler.function.viewModel.BattleViewModel
import com.example.namebattler.function.viewModel.HeaderViewModel
import com.example.namebattler.function.viewModel.getViewModelFactory


class BattleOperationFragment : Fragment() {

    private lateinit var binding: FragmentBattleOperationBinding

    private val battleViewModel: BattleViewModel by viewModels { getViewModelFactory() }
    private val headerViewModel: HeaderViewModel by viewModels { getViewModelFactory() }
    private lateinit var overallProgressOfBattle: OverallProgressOfBattle
    var endingInformation = MutableLiveData<String>()

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

            //OverallProgressOfBattleのインスタンス取得
            overallProgressOfBattle = OverallProgressOfBattle(battleViewModel)
            //エネミーデータとパーティデータへ分割
            battleViewModel.toSplitCurrentList()

            //Battle
            overallProgressOfBattle.initCharacterList(
                battleViewModel.currentEnemyList,
                battleViewModel.currentPlayerList
            )
            overallProgressOfBattle.initInitiative()


            //ログ出力
            battleViewModel.battleLogData.observe(viewLifecycleOwner,{
                //バトルログをrecyclerViewで表示
                val adapter = BattleLogAdapter(battleViewModel, viewLifecycleOwner)
                val recyclerViewOfBattleLog = logListView
                lifecycleOwner = viewLifecycleOwner

                //adapterにlifecycleOwnerを渡す
                recyclerViewOfBattleLog.layoutManager = LinearLayoutManager(this@BattleOperationFragment.context)
                recyclerViewOfBattleLog.adapter = adapter

                recyclerViewOfBattleLog.scrollToPosition(adapter.itemCount - 1)

            })


            //「次のターン」押下時の処理
            btnNextTurn.setOnClickListener {

                //勝敗の情報を取得
                val isEnding = overallProgressOfBattle.isEnding()
                //作戦を取得
                val operationName = battleViewModel.operationRadioType.value?.text ?: "ERROR"

                //勝敗がついていたら「次ターン」でバトル画面を表示させる
                if (isEnding != "") {
                    endingInformation.postValue(isEnding)
                } else {
                    //出力するログ情報（addした順に出力される）
                    var setLogData = mutableListOf<String>()

                    //バトル処理
                    overallProgressOfBattle.turnProgressOfBattle(operationName)

                }
            }

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
