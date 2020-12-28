package com.example.namebattler.party

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namebattler.R
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.databinding.PartyFormationBinding
import com.example.namebattler.menu.HeaderFragment
import com.example.namebattler.viewModel.CharacterViewModel

class PartyFormationActivity: AppCompatActivity() {

    private lateinit var characterViewModel: CharacterViewModel
//    private lateinit var operationDatabaseViewModel: OperationDatabaseViewModel

    var sendToFormation = arrayListOf<CharacterHolder?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.party_formation)

//        operationDatabaseViewModel = ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)
        characterViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)

        //キャラクター登録件数を取得（numOfRegistrationsに格納される）
        characterViewModel.confirmNumOfRegistrations()
        characterViewModel.numOfRegistrations.observe(this, Observer {
            //numOfRegistrationsに格納したキャラクター登録件数をセット
            characterViewModel.initCheckList()
        })

        characterViewModel.allCharacters.observe(this, Observer {

            /** ヘッダー **/
            if (savedInstanceState == null){
                // FragmentManagerのインスタンス生成
                val fragmentManager: FragmentManager = supportFragmentManager
                // FragmentTransactionのインスタンスを取得
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()


//            characterViewModel.allCharacters.observe(this, Observer {

                val setText = "パーティー編成"

                // インスタンスに対して張り付け方を指定する
                fragmentTransaction.replace(
                    R.id.header_area,
                    HeaderFragment.newInstance(
                        setText
                    )
                )
                // 張り付けを実行
                fragmentTransaction.commit()

//                })



//            // インスタンスに対して張り付け方を指定する
//            fragmentTransaction.replace(
//                R.id.header_area,
//                HeaderFragment.newInstance(
//                    "パーティー編成"
//                )
//            )
//            // 張り付けを実行
//            fragmentTransaction.commit()
            }

            /** RecyclerView **/

            val adapter = FormationListAdapter(characterViewModel, this)
            //Liveデータからの通知を受けてRecyclerViewが走る
//        characterViewModel.allCharacters.observe(this, Observer {

            val binding: PartyFormationBinding = DataBindingUtil.setContentView(this, R.layout.party_formation)

            binding.lifecycleOwner = this
            binding.viewModel = characterViewModel

            //adapterにlifecycleOwnerを渡す
            val recyclerViewOfCharacters = binding.formationListView
            recyclerViewOfCharacters.layoutManager = LinearLayoutManager(this)
            recyclerViewOfCharacters.adapter = adapter

            characterViewModel.selectionCharacterList.observe(this, Observer { it ->

                it.forEach {
                    Log.d("+++ Tag +++", "send to data : ${it.NAME}")

                }
            })

            characterViewModel.isClickDecideParty.observe(this, Observer {
                val intent = Intent(this, BattleLobbyActivity::class.java)
                startActivity(intent)
            })

/*
            characterViewModel.color.postValue(R.color.inactiveColor)



            characterViewModel.addCharacterList.observe(this, Observer {
                characterViewModel.color.postValue(characterViewModel.setColor)
            })
*/



/*            //デフォルトのボタン状態
            btn_decide_party.setBackgroundResource(R.color.inactiveColor)
            btn_decide_party.text = "3名選択してください"
            btn_decide_party.isEnabled = true

            btn_decide_party.setOnClickListener {

                Log.d("|| TAG ||", "@@@@@///@@@@")

                characterViewModel.selectionCharacterList.observe(this, Observer {
                    Log.d("//// TAG ////", "//// ${it[0].NAME} ////")
                })*/

//                val tete = characterViewModel.selectionCharacterList
//
//                tete.observe(this, Observer {
//                    Log.d("|| TAG ||", "$it")
//                }
//
//                )




//                val intent = Intent(this, BattleLobbyActivity::class.java)
//                intent.putExtra(BattleLobbyActivity.KEY_STATE,sendToFormation)
//                startActivity(intent)
//            }












//        })




        })











//        val recyclerViewOfFormationList = findViewById<RecyclerView>(R.id.formation_list_view)
//        val adapter = FormationListAdapter(this)

//        recyclerViewOfFormationList.adapter = adapter
//        recyclerViewOfFormationList.layoutManager = LinearLayoutManager(this)

//        sendToFormation = adapter.list



//        operationDatabaseViewModel.allCharacters.observe(this, Observer { character ->
//            // Update the cached copy of the character in the adapter.
//            character?.let {
//                adapter.setCharacter(it)
//
//            }
//        })


//        //デフォルトのボタン状態
//        btn_decide_party.setBackgroundResource(R.color.inactiveColor)
//        btn_decide_party.text = "3名選択してください"
//        btn_decide_party.isEnabled = false

//
//            adapter.noticeChangeCount.observe(this, Observer { cnt ->
//                when {
//                    //チェックカウントが3未満
//                    cnt < 3 -> {
//
//                        val message = "このパーティで開始($cnt/3)"
//                        btn_decide_party.text = message
//                        btn_decide_party.setBackgroundResource(R.color.inactiveColor)
//                        btn_decide_party.isEnabled = false
//
//                    }
//                    cnt > 3 -> {
//                        val message = "このパーティで開始($cnt/3)"
//                        btn_decide_party.text = message
//                        btn_decide_party.setBackgroundResource(R.color.excessColor)
//                        btn_decide_party.isEnabled = false
//                    }
//                    else -> {
//                        val message = "このパーティで開始($cnt/3)"
//                        btn_decide_party.text = message
//                        btn_decide_party.setBackgroundResource(R.color.buttonColor)
//                        btn_decide_party.isEnabled = true
//
//                    }
//                }
//        })

/*
            btn_decide_party.setOnClickListener {

                val tete = characterViewModel.selectionCharacterList

                tete.observe(this, Observer {
                    Log.d("|| TAG ||", "$it")
                }

                )



//                val intent = Intent(this, BattleLobbyActivity::class.java)
//                intent.putExtra(BattleLobbyActivity.KEY_STATE,sendToFormation)
//                startActivity(intent)
        }*/

    }
}


