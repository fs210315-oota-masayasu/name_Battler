package com.example.namebattler.battle.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.namebattler.R
import com.example.namebattler.data.battleData.CharacterInformationHolder
import com.example.namebattler.databinding.FragmentStatusInformationBinding
import com.example.namebattler.viewModel.BattleViewModel
import com.example.namebattler.viewModel.getViewModelFactory

//バトルメイン画面：ステータス情報
class StatusInformationFragment : Fragment() {

    private lateinit var binding: FragmentStatusInformationBinding
    private val battleViewModel: BattleViewModel by viewModels{ getViewModelFactory() }

    private var characterInformationList : ArrayList<CharacterInformationHolder> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        characterInformationList = battleViewModel.informationNotice.value ?: ArrayList()

        binding = FragmentStatusInformationBinding.inflate(inflater,container,false).apply {
            if (savedInstanceState == null) {
                // FragmentManagerのインスタンス生成
                val fragmentManager = parentFragmentManager

                //informationNotice内のステータス情報を各Fragmentへ割り振る
                if (characterInformationList.size != 0){

                    //enemy[first_enemy]
                    val fragmentTransactionFirstEnemy: FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransactionFirstEnemy.replace(R.id.first_enemy, InformationViewFragment.newInstance(characterInformationList[0]))
                    fragmentTransactionFirstEnemy.commit()

                    //enemy[second_enemy]
                    val fragmentTransactionSecondEnemy: FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransactionSecondEnemy.replace(R.id.second_enemy, InformationViewFragment.newInstance(characterInformationList[1]))
                    fragmentTransactionSecondEnemy.commit()

                    //enemy[third_enemy]
                    val fragmentTransactionThirdEnemy: FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransactionThirdEnemy.replace(R.id.third_enemy, InformationViewFragment.newInstance(characterInformationList[2]))
                    fragmentTransactionThirdEnemy.commit()

                    //player[first_player]
                    val fragmentTransactionFirstPlayer: FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransactionFirstPlayer.replace(R.id.first_player, InformationViewFragment.newInstance(characterInformationList[3]))
                    fragmentTransactionFirstPlayer.commit()

                    //player[second_player]
                    val fragmentTransactionSecondPlayer: FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransactionSecondPlayer.replace(R.id.second_player, InformationViewFragment.newInstance(characterInformationList[4]))
                    fragmentTransactionSecondPlayer.commit()

                    //player[third_player]
                    val fragmentTransactionThirdPlayer: FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransactionThirdPlayer.replace(R.id.third_player, InformationViewFragment.newInstance(characterInformationList[5]))
                    fragmentTransactionThirdPlayer.commit()
                }
            }
        }
        return binding.root
    }
}