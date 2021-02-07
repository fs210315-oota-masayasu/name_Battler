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

//enemy
private const val ARG_FIRST_ENEMY = "arg_first_enemy"
private const val ARG_SECOND_ENEMY = "arg_second_enemy"
private const val ARG_THIRD_ENEMY = "arg_third_enemy"

//party
private const val ARG_FIRST_PARTY = "arg_first_party"
private const val ARG_SECOND_PARTY = "arg_second_party"
private const val ARG_THIRD_PARTY = "arg_third_party"

//バトルメイン画面：ステータス情報
class StatusInformationFragment : Fragment() {

    private lateinit var binding: FragmentStatusInformationBinding
    private val battleViewModel: BattleViewModel by viewModels{ getViewModelFactory() }


    private var characterInformationList : ArrayList<CharacterInformationHolder> = arrayListOf()

    private var firstEnemyHolder= CharacterInformationHolder()
    private var secondEnemyHolder = CharacterInformationHolder()
    private var thirdEnemyHolder = CharacterInformationHolder()

    private var firstPartyHolder = CharacterInformationHolder()
    private var secondPartyHolder = CharacterInformationHolder()
    private var thirdPartyHolder = CharacterInformationHolder()

//    companion object {
//
//        @JvmStatic
//        fun newInstance() =
//            StatusInformationFragment().apply {



//                firstEnemyHolder = characterInformationHolder[0]
//                secondEnemyHolder = characterInformationHolder[1]
//                thirdEnemyHolder = characterInformationHolder[2]
//
//                firstPartyHolder = characterInformationHolder[3]
//                secondPartyHolder = characterInformationHolder[4]
//                thirdPartyHolder = characterInformationHolder[5]

/*                arguments = Bundle().apply {
                    putSerializable(ARG_FIRST_ENEMY , firstEnemyHolder)
                    putSerializable(ARG_SECOND_ENEMY, secondEnemyHolder)
                    putSerializable(ARG_THIRD_ENEMY, thirdEnemyHolder)
                    putSerializable(ARG_FIRST_PARTY, firstPartyHolder)
                    putSerializable(ARG_SECOND_PARTY, secondPartyHolder)
                    putSerializable(ARG_THIRD_PARTY, thirdPartyHolder)
                }*/
//            }
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*        arguments?.let {
            firstEnemyHolder = it.getSerializable(ARG_FIRST_ENEMY) as CharacterInformationHolder
            secondEnemyHolder = it.getSerializable(ARG_SECOND_ENEMY) as CharacterInformationHolder
            thirdEnemyHolder = it.getSerializable(ARG_THIRD_ENEMY) as CharacterInformationHolder
            //party
            firstPartyHolder = it.getSerializable(ARG_FIRST_PARTY) as CharacterInformationHolder
            secondPartyHolder = it.getSerializable(ARG_SECOND_PARTY) as CharacterInformationHolder
            thirdPartyHolder = it.getSerializable(ARG_THIRD_PARTY) as CharacterInformationHolder
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        characterInformationList = battleViewModel.informationNotice.value ?: ArrayList()

        binding = FragmentStatusInformationBinding.inflate(inflater,container,false).apply {
            if (savedInstanceState == null) {
                // FragmentManagerのインスタンス生成
                val fragmentManager = parentFragmentManager
                // FragmentTransactionのインスタンスを取得
                // インスタンスに対して張り付け方を指定する
                // 張り付けを実行

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


//TODO old
/*
//enemy
private const val ARG_FIRST_ENEMY = "arg_first_enemy"
private const val ARG_SECOND_ENEMY = "arg_second_enemy"
private const val ARG_THIRD_ENEMY = "arg_third_enemy"

//party
private const val ARG_FIRST_PARTY = "arg_first_party"
private const val ARG_SECOND_PARTY = "arg_second_party"
private const val ARG_THIRD_PARTY = "arg_third_party"

//バトルメイン画面：ステータス情報
class StatusInformationFragment : Fragment() {
    private var firstEnemyHolder= CharacterInformationHolder()
    private var secondEnemyHolder = CharacterInformationHolder()
    private var thirdEnemyHolder = CharacterInformationHolder()

    private var firstPartyHolder = CharacterInformationHolder()
    private var secondPartyHolder = CharacterInformationHolder()
    private var thirdPartyHolder = CharacterInformationHolder()

    companion object {

        @JvmStatic
        fun newInstance(characterInformationHolder: ArrayList<CharacterInformationHolder>) =
            StatusInformationFragment().apply {

                firstEnemyHolder = characterInformationHolder[0]
                secondEnemyHolder = characterInformationHolder[1]
                thirdEnemyHolder = characterInformationHolder[2]

                firstPartyHolder = characterInformationHolder[3]
                secondPartyHolder = characterInformationHolder[4]
                thirdPartyHolder = characterInformationHolder[5]

                arguments = Bundle().apply {
                    putSerializable(ARG_FIRST_ENEMY , firstEnemyHolder)
                    putSerializable(ARG_SECOND_ENEMY, secondEnemyHolder)
                    putSerializable(ARG_THIRD_ENEMY, thirdEnemyHolder)
                    putSerializable(ARG_FIRST_PARTY, firstPartyHolder)
                    putSerializable(ARG_SECOND_PARTY, secondPartyHolder)
                    putSerializable(ARG_THIRD_PARTY, thirdPartyHolder)
                }
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            firstEnemyHolder = it.getSerializable(ARG_FIRST_ENEMY) as CharacterInformationHolder
            secondEnemyHolder = it.getSerializable(ARG_SECOND_ENEMY) as CharacterInformationHolder
            thirdEnemyHolder = it.getSerializable(ARG_THIRD_ENEMY) as CharacterInformationHolder
            //party
            firstPartyHolder = it.getSerializable(ARG_FIRST_PARTY) as CharacterInformationHolder
            secondPartyHolder = it.getSerializable(ARG_SECOND_PARTY) as CharacterInformationHolder
            thirdPartyHolder = it.getSerializable(ARG_THIRD_PARTY) as CharacterInformationHolder
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status_information, container, false)
    }

    override fun onViewCreated(view :View, savedInstanceState: Bundle?){

        val firstEnemy = firstEnemyHolder
        val secondEnemy = secondEnemyHolder
        val thirdEnemy = thirdEnemyHolder

        val firstPlayer = firstPartyHolder
        val secondPlayer = secondPartyHolder
        val thirdPlayer = thirdPartyHolder

        if (savedInstanceState == null) {
            // FragmentManagerのインスタンス生成
            val fragmentManager = parentFragmentManager
            // FragmentTransactionのインスタンスを取得
            // インスタンスに対して張り付け方を指定する
            // 張り付けを実行

            //enemy[first_enemy]
            val fragmentTransactionFirstEnemy: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransactionFirstEnemy.replace(R.id.first_enemy, InformationViewFragment.newInstance(firstEnemy))
            fragmentTransactionFirstEnemy.commit()

            //enemy[second_enemy]
            val fragmentTransactionSecondEnemy: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransactionSecondEnemy.replace(R.id.second_enemy, InformationViewFragment.newInstance(secondEnemy))
            fragmentTransactionSecondEnemy.commit()

            //enemy[third_enemy]
            val fragmentTransactionThirdEnemy: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransactionThirdEnemy.replace(R.id.third_enemy, InformationViewFragment.newInstance(thirdEnemy))
            fragmentTransactionThirdEnemy.commit()

            //player[first_player]
            val fragmentTransactionFirstPlayer: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransactionFirstPlayer.replace(R.id.first_player, InformationViewFragment.newInstance(firstPlayer))
            fragmentTransactionFirstPlayer.commit()

            //player[second_player]
            val fragmentTransactionSecondPlayer: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransactionSecondPlayer.replace(R.id.second_player, InformationViewFragment.newInstance(secondPlayer))
            fragmentTransactionSecondPlayer.commit()

            //player[third_player]
            val fragmentTransactionThirdPlayer: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransactionThirdPlayer.replace(R.id.third_player, InformationViewFragment.newInstance(thirdPlayer))
            fragmentTransactionThirdPlayer.commit()

        }
    }
}

*/

