package com.example.namebattler.characters

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import kotlinx.android.synthetic.main.activity_character_list.*

//キャラクター一覧作成画面
class CharacterListActivity : AppCompatActivity() {

    private val newCharacterCreateActivityRequestCode = 1
    private lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        val recyclerViewOfCharacters = findViewById<RecyclerView>(R.id.list_view)
        val adapter = CharaListAdapter(this)

        recyclerViewOfCharacters.adapter = adapter
        recyclerViewOfCharacters.layoutManager = LinearLayoutManager(this)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.allCharacters.observe(this, Observer { character ->
            // Update the cached copy of the character in the adapter.
            character?.let{adapter.setCharacter(it)}
        })

            //キャラクター作成画面へ遷移
        btn_character_new_create.setOnClickListener {
            val setCharacterNewCreate = Intent(this, NewCharacterCreateActivity::class.java)
            //startActivity(setCharacterNewCreate)
            startActivityForResult(setCharacterNewCreate,newCharacterCreateActivityRequestCode)
          }
    }
}
