package com.example.namebattler.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.namebattler.R
import com.example.namebattler.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityHomeBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_home
        )

        binding.btnEditCharacter.setOnClickListener{
            val setIntentCharacterList = Intent(this, CharacterEditActivity::class.java)
            startActivity(setIntentCharacterList)
        }

        binding.btnBattle.setOnClickListener {
            val setIntentCharacterList = Intent(this, BattleActivity::class.java)
            startActivity(setIntentCharacterList)
        }
    }
}

