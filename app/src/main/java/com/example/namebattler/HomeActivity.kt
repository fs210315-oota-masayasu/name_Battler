package com.example.namebattler

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.characters.activity.CharacterListActivity
import com.example.namebattler.databinding.ActivityHomeBinding
import com.example.namebattler.party.PartyFormationActivity
import com.example.namebattler.util.ScopedAppActivity
import com.example.namebattler.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : ScopedAppActivity() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_home)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val binding : ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.btnEditCharacter.setOnClickListener{
            Log.d("*** tag ***", "click!!")
            val setIntentCharacterList = Intent(this, CharacterEditActivity::class.java)
            startActivity(setIntentCharacterList)
        }

        binding.btnBattle.setOnClickListener {
            Log.d("*** tag ***", "click!!")
            val setIntentCharacterList = Intent(this, BattleActivity::class.java)
            startActivity(setIntentCharacterList)
        }





//        btn_character_list.setOnClickListener {
//            val setIntentCharacterList = Intent(this, CharacterListActivity::class.java)
//            startActivity(setIntentCharacterList)
//        }
        //パーティ編成画面への遷移処理
//        btn_party_create.setOnClickListener {
//
//            val setIntentPartyCreate = Intent(this, PartyFormationActivity::class.java)
//            startActivity(setIntentPartyCreate)
//
//        }
    }
}

