package com.example.namebattler.memu

import android.content.Intent
import android.os.Bundle
import com.example.namebattler.R
import com.example.namebattler.characters.activity.CharacterListActivity
import com.example.namebattler.party.PartyCreateActivity
import com.example.namebattler.util.ScopedAppActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ScopedAppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //キャラクター一覧画面への遷移処理
        btn_character_list.setOnClickListener {
            val setintentCharacterList = Intent(this, CharacterListActivity::class.java)
            startActivity(setintentCharacterList)
        }
        //パーティ編成画面への遷移処理
        btn_party_create.setOnClickListener {
            val setintentPartyCreate = Intent(this, PartyCreateActivity::class.java)
            startActivity(setintentPartyCreate)

        }
    }
}

