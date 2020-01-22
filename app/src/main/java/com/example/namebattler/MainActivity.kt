package com.example.namebattler

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendToCharacterList: Button = findViewById(R.id.btn_character_list)

        val intent = Intent(this, CharacterListActivity::class.java)
        sendToCharacterList.setOnClickListener { startActivity(intent) }
    }
}
