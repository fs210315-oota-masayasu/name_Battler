package com.example.namebattler.party.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namebattler.R
import com.example.namebattler.battle.fragment.BattleMainFragment
import com.example.namebattler.data.battleData.InformationManager
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.enemyData.EnemyManager
import com.example.namebattler.databinding.BattleLobbyBinding
import com.example.namebattler.menu.HeaderFragment
import com.example.namebattler.party.EnemyListAdapter
import com.example.namebattler.party.PlayerListAdapter
import com.example.namebattler.util.Belong
import com.example.namebattler.util.HeaderFlag
import com.example.namebattler.viewModel.*

class BattleLobbyFragment:Fragment() {

    private lateinit var binding: BattleLobbyBinding

    private val enemyViewModel: EnemyViewModel by viewModels { getViewModelFactory() }
    private val partyFormationViewModel: PartyFormationViewModel by viewModels { getViewModelFactory() }
    private val headerViewModel: HeaderViewModel by viewModels{ getViewModelFactory() }

    private val battleViewModel: BattleViewModel by viewModels{ getViewModelFactory()  }

    var sendToEnemy = arrayListOf<CharacterHolder?>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        enemyViewModel.enemyFormation.value = EnemyManager().getEnemyData()

        binding = BattleLobbyBinding.inflate(inflater,container,false).apply {



            /** ヘッダー **/
            if (savedInstanceState == null) {
                // FragmentManagerのインスタンス生成
                val fragmentManager: FragmentManager = parentFragmentManager
                // FragmentTransactionのインスタンスを取得
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

                // インスタンスに対して張り付け方を指定する
                fragmentTransaction.replace(
                    R.id.header_area,
                    HeaderFragment()
                )
                // 張り付けを実行
                fragmentTransaction.commit()
            }


            /** エネミーリスト **/
            lifecycleOwner = viewLifecycleOwner


            enemyViewModel.enemyFormation.observe(viewLifecycleOwner,{

                //RecyclerViewの取得
                val recyclerViewOfEnemyList = enemyListView
                val enemyAdapter = EnemyListAdapter(enemyViewModel,viewLifecycleOwner)
                //adapterにlifecycleOwnerを渡す
                recyclerViewOfEnemyList.layoutManager = LinearLayoutManager(this@BattleLobbyFragment.context)
                recyclerViewOfEnemyList.adapter = enemyAdapter

            })




            /** 編成済みパーティリスト **/
            val recyclerViewOfPartyList = partyListView
            val partyAdapter = PlayerListAdapter(partyFormationViewModel, viewLifecycleOwner)
            recyclerViewOfPartyList.layoutManager = LinearLayoutManager(this@BattleLobbyFragment.context)
            recyclerViewOfPartyList.adapter = partyAdapter

            //バトルメイン画面へ遷移
            btnBattleStart.setOnClickListener {



                //val pList = partyFormationViewModel.selectionCharacterList.value ?: mutableListOf()
//                val eList :MutableList<Characters> = (enemyViewModel.enemyFormation.value ?: listOf(
//                    Characters()
//                )) as MutableList<Characters>

                //CharactersからCharacterHolderへのデータ変換
                val partyInformationList = InformationManager().setCharacterHolderList(
                    Belong.PLAYER,
                    partyFormationViewModel.selectionCharacterList.value ?: mutableListOf())

                val enemyInformationList = InformationManager().setCharacterHolderList(
                    Belong.ENEMY,
                    (enemyViewModel.enemyFormation.value ?: mutableListOf(Characters())) as MutableList<Characters>)


                //battleViewModelの各変数にCharacterHolderを格納
                battleViewModel.sendFromLobbyToMainPartyList = partyInformationList
                battleViewModel.sendFromLobbyToMainEnemyList = enemyInformationList


                /** ステータスの初期情報の取得とLiveData（informationNotice）への格納 **/
                battleViewModel.setInformationNotice()







                //ヘッダー情報をセット
                headerViewModel.apply {
                    headerText.postValue(getString(R.string.battle_main))
                    outputFlag = HeaderFlag.BATTLE_MAIN
                }


                // FragmentManagerのインスタンス生成
                val fragmentManager: FragmentManager = parentFragmentManager
                // FragmentTransactionのインスタンスを取得
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()



                fragmentTransaction.replace(
                    R.id.attach_screen,
                    BattleMainFragment()
                )
                fragmentTransaction.commit()


            }

            //敵編成を変更する
            btnReselectEnemy.setOnClickListener {
                enemyViewModel.enemyFormation.postValue(EnemyManager().getEnemyData())
            }

        }
        return binding.root
    }

}