package com.example.namebattler.battle.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.namebattler.R
import com.example.namebattler.data.battleData.OutputInformationHolder


//enemy
private const val ARG_FIRST_ENEMY_NAME = "arg_first_enemy_name" //..............name
private const val ARG_SECOND_ENEMY_NAME = "arg_second_enemy_name"
private const val ARG_THIRD_ENEMY_NAME = "arg_third_enemy_name"
private const val ARG_FIRST_ENEMY_MAX_HP = "arg_first_enemy_max_hp" //..........max_hp
private const val ARG_SECOND_ENEMY_MAX_HP = "arg_second_enemy_max_hp"
private const val ARG_THIRD_ENEMY_MAX_HP = "arg_third_enemy_max_hp"
private const val ARG_FIRST_ENEMY_CURRENT_HP = "arg_first_enemy_current_hp" //..current_hp
private const val ARG_SECOND_ENEMY_CURRENT_HP = "arg_second_enemy_current_hp"
private const val ARG_THIRD_ENEMY_CURRENT_HP = "arg_third_enemy_current_hp"
private const val ARG_FIRST_ENEMY_MAX_MP = "arg_first_enemy_max_mp" //..........max_mp
private const val ARG_SECOND_ENEMY_MAX_MP = "arg_second_enemy_max_mp"
private const val ARG_THIRD_ENEMY_MAX_MP = "arg_third_enemy_max_mp"
private const val ARG_FIRST_ENEMY_CURRENT_MP = "arg_first_enemy_current_mp" //..current_mp
private const val ARG_SECOND_ENEMY_CURRENT_MP = "arg_second_enemy_current_mp"
private const val ARG_THIRD_ENEMY_CURRENT_MP = "arg_third_enemy_current_mp"
private const val ARG_FIRST_ENEMY_COND = "arg_first_enemy_cond" //..............cond
private const val ARG_SECOND_ENEMY_COND = "arg_second_enemy_cond"
private const val ARG_THIRD_ENEMY_COND = "arg_third_enemy_cond"

//party
private const val ARG_FIRST_PARTY_NAME = "arg_first_party_name" //..............name
private const val ARG_SECOND_PARTY_NAME = "arg_second_party_name"
private const val ARG_THIRD_PARTY_NAME = "arg_third_party_name"
private const val ARG_FIRST_PARTY_MAX_HP = "arg_first_party_max_hp" //..........max_hp
private const val ARG_SECOND_PARTY_MAX_HP = "arg_second_party_max_hp"
private const val ARG_THIRD_PARTY_MAX_HP = "arg_third_party_max_hp"
private const val ARG_FIRST_PARTY_CURRENT_HP = "arg_first_party_current_hp" //..current_hp
private const val ARG_SECOND_PARTY_CURRENT_HP = "arg_second_party_current_hp"
private const val ARG_THIRD_PARTY_CURRENT_HP = "arg_third_party_current_hp"
private const val ARG_FIRST_PARTY_MAX_MP = "arg_first_party_max_mp" //..........max_mp
private const val ARG_SECOND_PARTY_MAX_MP = "arg_second_party_max_mp"
private const val ARG_THIRD_PARTY_MAX_MP = "arg_third_party_max_mp"
private const val ARG_FIRST_PARTY_CURRENT_MP = "arg_first_party_current_mp" //..current_mp
private const val ARG_SECOND_PARTY_CURRENT_MP = "arg_second_party_current_mp"
private const val ARG_THIRD_PARTY_CURRENT_MP = "arg_third_party_current_mp"
private const val ARG_FIRST_PARTY_COND = "arg_first_party_cond" //..............cond
private const val ARG_SECOND_PARTY_COND = "arg_second_party_cond"
private const val ARG_THIRD_PARTY_COND = "arg_third_party_cond"

private const val ARG_FIRST_ENEMY = "arg_first_enemy"
private const val ARG_SECOND_ENEMY = "arg_second_enemy"
private const val ARG_THIRD_ENEMY = "arg_third_enemy"

private const val ARG_FIRST_PARTY = "arg_first_party"
private const val ARG_SECOND_PARTY = "arg_second_party"
private const val ARG_THIRD_PARTY = "arg_third_party"

//バトルメイン画面：ステータス情報
class StatusInformationFragment : Fragment() {

    var firstEnemyName : String? = null         //name
    var secondEnemyName : String? = null
    var thirdEnemyName : String ? = null
    var firstEnemyMaxHp : String? = null        //max_hp
    var secondEnemyMaxHp : String? = null
    var thirdEnemyMaxHp : String ? = null
    var firstEnemyCurrentHp : String? = null    //current_hp
    var secondEnemyCurrentHp : String? = null
    var thirdEnemyCurrentHp : String ? = null
    var firstEnemyMaxMp : String? = null        //max_mp
    var secondEnemyMaxMp : String? = null
    var thirdEnemyMaxMp : String ? = null
    var firstEnemyCurrentMp : String? = null    //current_mp
    var secondEnemyCurrentMp : String? = null
    var thirdEnemyCurrentMp : String ? = null
    var firstEnemyCond : String? = null         //Cond
    var secondEnemyCond : String? = null
    var thirdEnemyCond : String ? = null

    //Party
    var firstPartyName : String ? = null    //name
    var secondPartyName : String ? = null
    var thirdPartyName : String ? = null
    var firstPartyMaxHp : String? = null    //max_hp
    var secondPartyMaxHp : String? = null
    var thirdPartyMaxHp : String ? = null
    var firstPartyCurrentHp : String? = null    //current_hp
    var secondPartyCurrentHp : String? = null
    var thirdPartyCurrentHp : String ? = null
    var firstPartyMaxMp : String? = null        //max_mp
    var secondPartyMaxMp : String? = null
    var thirdPartyMaxMp : String ? = null
    var firstPartyCurrentMp : String? = null    //current_mp
    var secondPartyCurrentMp : String? = null
    var thirdPartyCurrentMp : String ? = null
    var firstPartyCond : String? = null         //Cond
    var secondPartyCond : String? = null
    var thirdPartyCond : String ? = null


    private var firstEnemyHolder : OutputInformationHolder? = null
    private var secondEnemyHolder : OutputInformationHolder? = null
    private var thirdEnemyHolder : OutputInformationHolder? = null

    private var firstPartyHolder : OutputInformationHolder? = null
    private var secondPartyHolder : OutputInformationHolder? = null
    private var thirdPartyHolder : OutputInformationHolder? = null

    companion object {

        @JvmStatic
        fun newInstance(outputInformationHolder : ArrayList<OutputInformationHolder>) =
            StatusInformationFragment().apply {

                firstEnemyHolder = outputInformationHolder[0]
                secondEnemyHolder = outputInformationHolder[1]
                thirdEnemyHolder = outputInformationHolder[2]

                firstPartyHolder = outputInformationHolder[3]
                secondPartyHolder = outputInformationHolder[4]
                thirdPartyHolder = outputInformationHolder[5]

                arguments = Bundle().apply {
                    putSerializable(ARG_FIRST_ENEMY , firstEnemyHolder)
                    putSerializable(ARG_SECOND_ENEMY, secondEnemyHolder)
                    putSerializable(ARG_THIRD_ENEMY, thirdEnemyHolder)
                    putSerializable(ARG_FIRST_PARTY, firstPartyHolder)
                    putSerializable(ARG_SECOND_PARTY, secondPartyHolder)
                    putSerializable(ARG_THIRD_PARTY, thirdPartyHolder)
//                    putString(ARG_FIRST_ENEMY_NAME,firstEnemyName)
//                    putString(ARG_SECOND_ENEMY_NAME,secondEnemyName)
//                    putString(ARG_THIRD_ENEMY_NAME,thirdEnemyName)
//
//                    putString(ARG_FIRST_ENEMY_MAX_HP, firstEnemyMaxHp)
//                    putString(ARG_SECOND_ENEMY_MAX_HP, secondEnemyMaxHp)
//                    putString(ARG_THIRD_ENEMY_MAX_HP, thirdEnemyMaxHp)

                }
            }

/*        @JvmStatic
        fun newInstance(informationData : InformationHolder) =
            StatusInformationFragment().apply {

                firstEnemyName = informationData.firstEnemyName
                secondEnemyName = informationData.secondEnemyName
                thirdEnemyName = informationData.thirdEnemyName

                firstEnemyMaxHp = informationData.firstEnemyMaxHp
                secondEnemyMaxHp = informationData.secondEnemyMaxHp
                thirdEnemyMaxHp = informationData.thirdEnemyMaxHp

                firstEnemyCurrentHp = informationData.firstEnemyCurrentHp
                secondEnemyCurrentHp = informationData.secondEnemyCurrentHp
                thirdEnemyCurrentHp = informationData.thirdEnemyCurrentHp





                arguments = Bundle().apply {
                    putString(ARG_FIRST_ENEMY_NAME,firstEnemyName)
                    putString(ARG_SECOND_ENEMY_NAME,secondEnemyName)
                    putString(ARG_THIRD_ENEMY_NAME,thirdEnemyName)

                    putString(ARG_FIRST_ENEMY_MAX_HP, firstEnemyMaxHp)
                    putString(ARG_SECOND_ENEMY_MAX_HP, secondEnemyMaxHp)
                    putString(ARG_THIRD_ENEMY_MAX_HP, thirdEnemyMaxHp)

                }
            }*/
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            firstEnemyHolder = it.getSerializable(ARG_FIRST_ENEMY) as OutputInformationHolder
            secondEnemyHolder = it.getSerializable(ARG_SECOND_ENEMY) as OutputInformationHolder
            thirdEnemyHolder = it.getSerializable(ARG_THIRD_ENEMY) as OutputInformationHolder
            //party
            firstPartyHolder = it.getSerializable(ARG_FIRST_PARTY) as OutputInformationHolder
            secondPartyHolder = it.getSerializable(ARG_SECOND_PARTY) as OutputInformationHolder
            thirdPartyHolder = it.getSerializable(ARG_THIRD_PARTY) as OutputInformationHolder


//            firstEnemyName = it.getString(ARG_FIRST_ENEMY_NAME)
//            secondEnemyName = it.getString(ARG_SECOND_ENEMY_NAME)
//            thirdEnemyName = it.getString(ARG_THIRD_ENEMY_NAME)
//
//            firstEnemyMaxHp = it.getString(ARG_FIRST_ENEMY_MAX_HP)
//            secondEnemyMaxHp = it.getString(ARG_SECOND_ENEMY_MAX_HP)
//            thirdEnemyMaxHp = it.getString(ARG_THIRD_ENEMY_MAX_HP)
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
        val args : Bundle? = arguments

        //var firstEnemy = args?.getSerializable(ARG_FIRST_ENEMY) as OutputInformationHolder
        var firstEnemy = firstEnemyHolder!!
        var secondEnemy = secondEnemyHolder!!
        var thirdEnemy = thirdEnemyHolder!!

        var firstPlayer = firstPartyHolder!!
        val secondPlayer = secondPartyHolder!!
        val thirdPlayer = thirdPartyHolder!!


        //
//        var secondEnemy = args?.getSerializable(ARG_SECOND_ENEMY) as OutputInformationHolder
//        var thirdEnemy = args.getSerializable(ARG_THIRD_ENEMY) as OutputInformationHolder
//
//        var firstParty = args.getSerializable(ARG_FIRST_PARTY) as OutputInformationHolder
//        var secondParty = args.getSerializable(ARG_SECOND_PARTY) as OutputInformationHolder
//        var thirdParty = args.getSerializable(ARG_THIRD_PARTY) as OutputInformationHolder


        if (savedInstanceState == null) {
            // FragmentManagerのインスタンス生成
            val fragmentManager = parentFragmentManager
            // FragmentTransactionのインスタンスを取得

            val fragmentTransactionSecondPlayer: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する
            fragmentTransactionSecondPlayer.replace(R.id.second_player, InformationViewFragment.newInstance(secondPlayer))
            // 張り付けを実行
            fragmentTransactionSecondPlayer.commit()

            val fragmentTransactionThirdPlayer: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する
            fragmentTransactionThirdPlayer.replace(R.id.third_player, InformationViewFragment.newInstance(thirdPlayer))
            // 張り付けを実行
            fragmentTransactionThirdPlayer.commit()

        }


//        val firstEnemyName : TextView = view.findViewById(R.id.txt_value_name)
//        val secondEnemyName : TextView = view.findViewById(R.id.txt_second_enemy_name)
//        val thirdEnemyName  : TextView = view.findViewById(R.id.txt_third_enemy_name)
//
//        val firstEnemyHp : TextView = view.findViewById(R.id.txt_value_max_hp)
//        val secondEnemyHp : TextView = view.findViewById(R.id.txt_second_enemy_hp)
//        val thirdEnemyHp : TextView = view.findViewById(R.id.txt_third_enemy_hp)
//
//        val setFirstEnemyName = args?.getString(ARG_FIRST_ENEMY_NAME)
//        val setSecondEnemyName = args?.getString(ARG_SECOND_ENEMY_NAME)
//        val setThirdEnemyName = args?.getString(ARG_THIRD_ENEMY_NAME)
//
//        val setFirstEnemyMaxHp =  args?.getString(ARG_FIRST_ENEMY_MAX_HP)
//        val setSecondEnemyMaxHp = args?.getString(ARG_SECOND_ENEMY_MAX_HP)
//        val setThirdEnemyMaxHp = args?.getString(ARG_THIRD_ENEMY_MAX_HP)

//
//        firstEnemyName.text = setFirstEnemyName
//        secondEnemyName.text = setSecondEnemyName
//        thirdEnemyName.text = setThirdEnemyName
//
//        val outputFirstEnemyHp = "HP  $setFirstEnemyMaxHp / $setFirstEnemyMaxHp"
//
//
//
//        firstEnemyHp.text = outputFirstEnemyHp
//        secondEnemyHp.text = setSecondEnemyMaxHp
//        thirdEnemyHp.text = setThirdEnemyMaxHp










    }



}


