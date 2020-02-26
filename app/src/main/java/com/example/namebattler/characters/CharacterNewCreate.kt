package com.example.namebattler.characters

import android.os.Bundle
import com.example.namebattler.R
import com.example.namebattler.util.ScopedAppActivity

class CharacterNewCreate : ScopedAppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_new_create)
    }

}