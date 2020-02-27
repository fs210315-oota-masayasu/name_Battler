package com.example.namebattler.characters

import android.content.Intent
import android.os.Bundle
import com.example.namebattler.R
import com.example.namebattler.util.ScopedAppActivity
import kotlinx.android.synthetic.main.character_new_create.*

class CharacterNewCreateActivity : ScopedAppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_new_create)

        btn_order_character_creation.setOnClickListener {
            val setGeneratedCharacterCompletion = Intent(this, GeneratedCharacterCompletionActivity::class.java)
            startActivity(setGeneratedCharacterCompletion)
        }
    }

}