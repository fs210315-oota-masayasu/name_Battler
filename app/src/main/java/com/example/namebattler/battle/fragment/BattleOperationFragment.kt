package com.example.namebattler.battle.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.battle.BattleLogAdapter
import com.example.namebattler.data.battleData.InformationManager
import com.example.namebattler.data.characterData.CharacterHolder
import kotlinx.android.synthetic.main.fragment_battle_operation.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private const val ARG_ENEMY = "arg_enemy"
private const val ARG_PARTY = "arg_party"

/*
private const val ARG_FIRST_ENEMY = "arg_first_enemy"
private const val ARG_SECOND_ENEMY = "arg_second_enemy"
private const val ARG_THIRD_ENEMY = "arg_third_enemy"

private const val ARG_FIRST_PARTY = "arg_first_party"
private const val ARG_SECOND_PARTY = "arg_second_party"
private const val ARG_THIRD_PARTY = "arg_third_party"
*/

class BattleOperationFragment : Fragment() {

    private var enemyList = arrayListOf<CharacterHolder>()
    private var partyList = arrayListOf<CharacterHolder>()


/*

    private var firstEnemy : CharacterHolder? = null
    private var secondEnemy : CharacterHolder? = null
    private var thirdEnemy : CharacterHolder? = null

    private var firstParty : CharacterHolder? = null
    private var secondParty : CharacterHolder? = null
    private var thirdParty : CharacterHolder? = null


*/


    //private var informationManager : InformationManager? = null



    private var cnt : Int? = 0

    var informText = MutableLiveData<String>()

    var firstEnemyName = MutableLiveData <String>()
    var secondEnemyName = MutableLiveData <String>()
    var thirdEnemyName = MutableLiveData <String>()

    companion object {
        @JvmStatic
        fun newInstance(enemyObj : ArrayList<CharacterHolder>, partyObj : ArrayList<CharacterHolder>) =
            BattleOperationFragment().apply {


/*

                    firstEnemy = enemy[0]
                    secondEnemy = enemy[1]
                    thirdEnemy = enemy[2]

                    firstParty = party[0]
                    secondParty = party[1]
                    thirdParty = party[2]

*/

                //informationManager = informationData

                this.enemyList = enemyObj
                this.partyList = partyObj


                arguments = Bundle().apply {

//                    putSerializable(ARG_FIRST_ENEMY,firstEnemy)
//                    putSerializable(ARG_SECOND_ENEMY, secondEnemy)
//                    putSerializable(ARG_THIRD_ENEMY,thirdEnemy)
//
//                    putSerializable(ARG_FIRST_PARTY, firstParty)
//                    putSerializable(ARG_SECOND_PARTY, secondParty)
//                    putSerializable(ARG_THIRD_PARTY, thirdParty)

                    //putSerializable(ARG_INFORMATION, informationManager)

                    putSerializable(ARG_ENEMY, enemyList)
                    putSerializable(ARG_PARTY, partyList)


                }

            }
/*
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BattleOperationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/
    }



    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

            enemyList = it.getSerializable(ARG_ENEMY) as ArrayList<CharacterHolder>
            partyList = it.getSerializable(ARG_PARTY) as ArrayList<CharacterHolder>



/*
            firstEnemy = it.getSerializable(ARG_FIRST_ENEMY) as CharacterHolder?
            secondEnemy = it.getSerializable(ARG_SECOND_ENEMY) as CharacterHolder?
            thirdEnemy = it.getSerializable(ARG_THIRD_ENEMY) as CharacterHolder?

            firstParty = it.getSerializable(ARG_FIRST_PARTY) as CharacterHolder?
            secondParty = it.getSerializable(ARG_SECOND_PARTY) as CharacterHolder?
            thirdParty = it.getSerializable(ARG_THIRD_PARTY) as CharacterHolder?
*/


            //informationManager = it.getSerializable(ARG_INFORMATION) as InformationManager


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

        var firstEnemy = enemyList[0]
        var secondEnemy = enemyList[1]
        var thirdEnemy = enemyList[2]

        var firstParty  = partyList[0]
        var secondParty = partyList[1]
        var thirdParty  = partyList[2]



        //val setFirstEnemy =  args?.getSerializable(ARG_FIRST_ENEMY) as CharacterHolder?
        Log.d("tag", "first Enemy is " + firstEnemy?.name)


        //StatusInformationFragment().firstEnemyNameTest.postValue(setFirstEnemy?.name)
        //StatusInformationFragment().secondEnemyNameTest.postValue(secondEnemy?.name)
        //StatusInformationFragment().thirdEnemyNameTest.postValue(thirdEnemy?.name)



        //バトルログをrecyclerViewで表示
        val recyclerViewOfBattleLog = view.findViewById<RecyclerView>(R.id.log_list_view)
        val getContext = activity
        if(getContext != null){
            val adapter = BattleLogAdapter(getContext)
            recyclerViewOfBattleLog.adapter = adapter
            recyclerViewOfBattleLog.layoutManager = LinearLayoutManager(getContext)


            val list = mutableListOf<String>()
            adapter.setBattleLog(list)

            informText.observe(viewLifecycleOwner, Observer {
                //Log.d("tag", "log is $it")
                //list.add(it)
                recyclerViewOfBattleLog.scrollToPosition(adapter.pos?:0)
                adapter.addBattleLog(it)

                //Log.d("tag", "log is $list")

            })





        }
    }

    override fun onStart() {
        super.onStart()

/*        val information = InformationManager.inform
        val holder = information.getInformationHolder()*/

        @Suppress("UNCHECKED_CAST")
//        val test = informationManager?.getInformationHolder(enemyList as ArrayList<CharacterHolder>,
//            partyList as ArrayList<CharacterHolder>
//        )
        val temp = InformationManager().getInstance()
        val test = temp.getOutputInformationHolder(enemyList, partyList)




        btn_next_turn.setOnClickListener {
            cnt = cnt?.plus(1)
            var log = "テスト$cnt"

            //Log.d("tag", "before check :" + test?.firstEnemyName)

            //Log.d("tag", "wo is " + firstEnemy?.name)
            //firstEnemyName.postValue(firstEnemy?.name)




            test[HolderIndexEnum.THIRD_PARTY_.id].name = "テスト太郎"


            temp.setInformationNotice(test)



            //Log.d("tag", "button listen :" + informationManager?.firstEnemyName)
            Log.d("tag", "inform check :" + test[HolderIndexEnum.THIRD_PARTY_.id].name)

            informText.postValue(log)

        }
    }

    enum class HolderIndexEnum(val id: Int) {
        FIRST_ENEMY(0),
        SECOND_ENEMY(1),
        THIRD_ENEMY(2),
        FIRST_PARTY(3),
        SECOND_PARTY(4),
        THIRD_PARTY_(5),

    }


}
