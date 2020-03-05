package com.example.namebattler.characters

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import kotlinx.android.synthetic.main.activity_character_list.*

class CharacterListActivity : AppCompatActivity() {
    // RecyclerView 本体、および、LayoutManager と Adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<*>

    // Adapter にセットするデータ (1～100)
    private val data = IntArray(100) { it + 1 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        //val model = ViewModelProviders.of(this)[MainViewModel::class.java]

        //あらかじめMainViewModelにサーバからgetする処理を書いて
        //ここ以降のどこかでその処理（メソッド）を呼び出して帰ってきた値を画面表示できるようにセットすればいい


        layoutManager = LinearLayoutManager(this)
        adapter = CharaListAdapter(data)
        recyclerView = findViewById<RecyclerView>(R.id.list_view).also {
            it.layoutManager = layoutManager
            it.adapter = adapter
        }

        //新しく作成する
        btn_character_new_create.setOnClickListener {
            val setCharacterNewCreate = Intent(this, CharacterNewCreateActivity::class.java)
            startActivity(setCharacterNewCreate)
        }

    }
}
