package com.example.namebattler.party

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.characters.MainViewModel
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.memu.HeaderFragment
import kotlinx.android.synthetic.main.party_formation.*

class PartyFormationActivity: AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    var sendToFormation = arrayListOf<CharacterHolder?>()
    private val isCheckedBox = MutableLiveData<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.party_formation)

        //ヘッダー
        if (savedInstanceState == null){
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する
            fragmentTransaction.replace(
                R.id.header_area,
                HeaderFragment.newInstance(
                    "パーティー編成"
                )
            )
            // 張り付けを実行
            fragmentTransaction.commit()
        }

        val recyclerViewOfFormationList = findViewById<RecyclerView>(R.id.formation_list_view)
        val adapter = FormationListAdapter(this)

        recyclerViewOfFormationList.adapter = adapter
        recyclerViewOfFormationList.layoutManager = LinearLayoutManager(this)

        sendToFormation = adapter.list

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.allCharacters.observe(this, Observer { character ->
            // Update the cached copy of the character in the adapter.
            character?.let {
                adapter.setCharacter(it)

            }
        })


        //デフォルトのボタン状態
        btn_decide_party.setBackgroundResource(R.color.inactiveColor)
        btn_decide_party.text = "3名選択してください"
        btn_decide_party.isEnabled = false


            adapter.noticeChangeCount.observe(this, Observer { cnt ->
                when {
                    //チェックカウントが3未満
                    cnt < 3 -> {

                        val message = "このパーティで開始($cnt/3)"
                        btn_decide_party.text = message
                        btn_decide_party.setBackgroundResource(R.color.inactiveColor)
                        btn_decide_party.isEnabled = false

                    }
                    cnt > 3 -> {
                        val message = "このパーティで開始($cnt/3)"
                        btn_decide_party.text = message
                        btn_decide_party.setBackgroundResource(R.color.excessColor)
                        btn_decide_party.isEnabled = false
                    }
                    else -> {
                        val message = "このパーティで開始($cnt/3)"
                        btn_decide_party.text = message
                        btn_decide_party.setBackgroundResource(R.color.buttonColor)
                        btn_decide_party.isEnabled = true

                    }
                }
        })
            btn_decide_party.setOnClickListener {
                val intent = Intent(this, BattleLobbyActivity::class.java)
                intent.putExtra(BattleLobbyActivity.KEY_STATE,sendToFormation)
                startActivity(intent)
        }

    }
}


