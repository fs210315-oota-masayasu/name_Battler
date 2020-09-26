package com.example.namebattler.characters.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.characters.CharaListAdapter
import com.example.namebattler.characters.MainViewModel
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.menu.HeaderFragment
import kotlinx.android.synthetic.main.activity_character_list.*

//キャラクター一覧画面
class CharacterListActivity : AppCompatActivity() {

    private val newCharacterCreateActivityRequestCode = 1
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //ヘッダー
        if (savedInstanceState == null) {
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

            //スコープ関数で処理を簡略化
            mainViewModel.apply {
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




        mainViewModel.allCharacters.observe(this, Observer { character ->
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
    }
}


