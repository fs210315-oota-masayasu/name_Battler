package com.example.namebattler.party.fragment

import android.content.Intent
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
import com.example.namebattler.databinding.PartyFormationBinding
import com.example.namebattler.menu.HeaderFragment
import com.example.namebattler.party.FormationListAdapter
import com.example.namebattler.party.activity.BattleLobbyActivity
import com.example.namebattler.util.BackStack
import com.example.namebattler.util.HeaderFlag
import com.example.namebattler.viewModel.*

class PartyFormationFragment: Fragment() {

    private lateinit var binding: PartyFormationBinding


    /** ViewModel **/
    private val partyFormationViewModel: PartyFormationViewModel by viewModels{ getViewModelFactory() }
    private val headerViewModel: HeaderViewModel by viewModels{ getViewModelFactory() }
    private lateinit var operationDatabaseViewModel: OperationDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        //キャラクター登録件数を取得（numOfRegistrationsに格納される）
        operationDatabaseViewModel.confirmNumOfRegistrations()
        operationDatabaseViewModel.numOfRegistrations.observe(viewLifecycleOwner,{

            //ヘッダー情報をセット
            headerViewModel.apply {
                headerText.postValue(getString(R.string.party_formation))
                outputFlag = HeaderFlag.RETURN_HOME
            }


            //numOfRegistrationsに格納したキャラクター登録件数をセット
            operationDatabaseViewModel.initCheckList()
            //PartyFormationViewModelにもせっと
            partyFormationViewModel.isCheckedPartyFormation  = operationDatabaseViewModel.isCheckedPartyFormation

        })



        //bindingで画面を生成 スコープ関数でbindingの指定を省略
        binding = PartyFormationBinding.inflate(inflater,container,false).apply {

            //Liveデータからの通知をうけてFragmentやViewを設定
            operationDatabaseViewModel.allCharacters.observe(viewLifecycleOwner,{

                /** ヘッダー **/
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






                /** RecyclerView **/
                val adapter = FormationListAdapter(operationDatabaseViewModel,partyFormationViewModel,viewLifecycleOwner)

                lifecycleOwner = viewLifecycleOwner
                viewModel = partyFormationViewModel

                //adapterにlifecycleOwnerを渡す
                val recyclerViewOfCharacters = formationListView
                recyclerViewOfCharacters.layoutManager = LinearLayoutManager(this@PartyFormationFragment.context)
                recyclerViewOfCharacters.adapter = adapter


                btnDecideParty.setOnClickListener {
                    partyFormationViewModel.onClickDecideParty()

                    partyFormationViewModel.isClickDecideParty.observe(viewLifecycleOwner, {

                        //ヘッダー情報をセット
                        headerViewModel.apply {
                            headerText.postValue("バトル開始")
                            outputFlag = HeaderFlag.PARTY_FORMATION
                        }

                        //初期化処理
                        partyFormationViewModel.initPartyFormationData()


                        val sentToBattleLobbyManager: FragmentManager = parentFragmentManager
                        val sentToBattleLobbyTransaction: FragmentTransaction = sentToBattleLobbyManager.beginTransaction()

                        sentToBattleLobbyTransaction.addToBackStack(BackStack.PARTY_FORMATION.name)



                        sentToBattleLobbyTransaction.replace(
                            R.id.attach_screen,
                            BattleLobbyFragment()
                        )

                        sentToBattleLobbyTransaction.commit()

                    })

                }





            })
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        operationDatabaseViewModel = ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)





    }
}