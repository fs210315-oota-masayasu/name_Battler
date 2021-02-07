package com.example.namebattler.characters.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namebattler.R
import com.example.namebattler.characters.CharaListAdapter
import com.example.namebattler.viewModel.OperationDatabaseViewModel
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.databinding.ActivityCharacterListBinding
import com.example.namebattler.menu.HeaderFragment
import com.example.namebattler.viewModel.CharacterViewModel

//キャラクター一覧画面
class CharacterListActivity : AppCompatActivity() {

    //TODO　削除対象

    private val newCharacterCreateActivityRequestCode = 1

    private lateinit var operationDatabaseViewModel: OperationDatabaseViewModel
    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)



        operationDatabaseViewModel =
            ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)

        characterViewModel =
            ViewModelProvider(this).get(CharacterViewModel::class.java)



        //Liveデータからの通知をうけてFragmentやViewを設定
        operationDatabaseViewModel.allCharacters.observe(this, Observer {


            /** ヘッダー **/
            if (savedInstanceState == null) {
                // FragmentManagerのインスタンス生成
                val fragmentManager: FragmentManager = supportFragmentManager
                // FragmentTransactionのインスタンスを取得
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

                //スコープ関数で処理を簡略化
                operationDatabaseViewModel.apply {
                    //LiveDataの通知を受け取って処理を実行
                    numOfRegistrations.observe(this@CharacterListActivity, Observer {

                        val setText = "キャラ一覧 ( $it )"

                        // インスタンスに対して張り付け方を指定する
//                        fragmentTransaction.replace(
//                            R.id.header_area,
//                            HeaderFragment.newInstance(
//                                setText
//                            )
//                        )
                        // 張り付けを実行
                        fragmentTransaction.commit()

                    })
                    //ViewModelの件数取得処理を実行（numOfRegistrationsに格納される）
                    confirmNumOfRegistrations()
                }
            }

            /** RecyclerView **/

            val adapter = CharaListAdapter(operationDatabaseViewModel, characterViewModel, this)


            val binding: ActivityCharacterListBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_character_list)

            binding.lifecycleOwner = this
            binding.operationDatabaseViewModel = operationDatabaseViewModel
            binding.characterViewModel = characterViewModel

            //adapterにlifecycleOwnerを渡す
            val recyclerViewOfCharacters = binding.listView
            recyclerViewOfCharacters.layoutManager = LinearLayoutManager(this)
            recyclerViewOfCharacters.adapter = adapter

            // RecyclerViewのクリックイベント（Adapter内のインターフェース実装）

            adapter.setOnItemClickListener(object :
                CharaListAdapter.OnItemClickListener {
                override fun onItemClickListener(
                    view: View,
                    position: Int,
                ) {
                    val intent = Intent(view.context, ConfirmCharacterStatusActivity::class.java)
//                    intent.putExtra(CharacterHolder.EXTRA_DATA)
                    startActivity(intent)
                }
            })

/*            adapter.setOnItemClickListener(object :
                CharaListAdapter.OnItemClickListener {
                override fun onItemClickListener(
                    view: View,
                    position: Int,
                    sendToData: CharacterHolder?
                ) {
                    val intent = Intent(view.context, ConfirmCharacterStatusActivity::class.java)
                    intent.putExtra(CharacterHolder.EXTRA_DATA, sendToData)
                    startActivity(intent)
                }
            })*/


/*
            //キャラクター作成画面へ遷移
            btn_character_new_create.setOnClickListener {
                val setCharacterNewCreate = Intent(this, NewCharacterGenerateActivity::class.java)
                //startActivity(setCharacterNewCreate)
                startActivityForResult(setCharacterNewCreate, newCharacterCreateActivityRequestCode)
            }

*/


        })
    }
}

    /* NOT DataBinding */

    /*private lateinit var operationDatabaseViewModel: OperationDatabaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        operationDatabaseViewModel = ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)

        //ヘッダー
        if (savedInstanceState == null) {
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

            //スコープ関数で処理を簡略化
            operationDatabaseViewModel.apply {
                //LiveDataの通知を受け取って処理を実行
                numOfRegistrations.observe(this@CharacterListActivity, Observer {

                    val setText = "キャラ一覧 ( $it )"

                    // インスタンスに対して張り付け方を指定する
                    fragmentTransaction.replace(
                        R.id.header_area,
                        HeaderFragment.newInstance(
                            setText
                        )
                    )
                    // 張り付けを実行
                    fragmentTransaction.commit()

                })
                //ViewModelの件数取得処理を実行（numOfRegistrationsに格納される）
                confirmNumOfRegistrations()
            }

        }


        val recyclerViewOfCharacters = findViewById<RecyclerView>(R.id.list_view)
        val adapter = CharaListAdapter(this)

        recyclerViewOfCharacters.adapter = adapter
        recyclerViewOfCharacters.layoutManager = LinearLayoutManager(this)




        operationDatabaseViewModel.allCharacters.observe(this, Observer { character ->
            // Update the cached copy of the character in the adapter.
            character?.let {
                adapter.setCharacter(it)

            }
        })


        // RecyclerViewのクリックイベント（Adapter内のインターフェース実装）
        adapter.setOnItemClickListener(object :
            CharaListAdapter.OnItemClickListener {
            override fun onItemClickListener(view: View, position: Int, sendToData: CharacterHolder?) {
                val intent = Intent(view.context, ConfirmCharacterStatusActivity::class.java)
                intent.putExtra(CharacterHolder.EXTRA_DATA, sendToData)
                startActivity(intent)

            }

        })
        //キャラクター作成画面へ遷移
        btn_character_new_create.setOnClickListener {
            val setCharacterNewCreate = Intent(this, NewCharacterGenerateActivity::class.java)
            //startActivity(setCharacterNewCreate)
            startActivityForResult(setCharacterNewCreate, newCharacterCreateActivityRequestCode)
        }
    }*/


