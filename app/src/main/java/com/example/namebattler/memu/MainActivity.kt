package com.example.namebattler.memu

import android.content.Intent
import android.os.Bundle
import com.example.namebattler.R
import com.example.namebattler.characters.activity.CharacterListActivity
import com.example.namebattler.party.PartyFormationActivity
import com.example.namebattler.util.ScopedAppActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ScopedAppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //キャラクター一覧画面への遷移処理
        btn_character_list.setOnClickListener {
            val setIntentCharacterList = Intent(this, CharacterListActivity::class.java)
            startActivity(setIntentCharacterList)
        }
        //パーティ編成画面への遷移処理
        btn_party_create.setOnClickListener {

            val setIntentPartyCreate = Intent(this, PartyFormationActivity::class.java)
            startActivity(setIntentPartyCreate)

        }
    }
}

