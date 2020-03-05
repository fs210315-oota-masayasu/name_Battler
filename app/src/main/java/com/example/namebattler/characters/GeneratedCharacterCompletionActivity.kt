package com.example.namebattler.characters

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import com.example.namebattler.R
import com.example.namebattler.data.Characters
import com.example.namebattler.memu.MainViewModel
import com.example.namebattler.util.ScopedAppActivity
import kotlinx.android.synthetic.main.character_new_create.*
import kotlinx.android.synthetic.main.generated_character_completion.*


class GeneratedCharacterCompletionActivity : ScopedAppActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val model = ViewModelProviders.of(this)[MainViewModel::class.java]


            if (set_input_name.text != null){
                val inputName = set_input_name.text.toString()
                model.characterAtName(inputName)
                val createName = model.getCharacterdata
            value_name.text = createName.toString()

        }

        val tenmp : LiveData<List<Characters>> = model.allCharacters

        Log.d("TAG", "@@@" + tenmp.toString() + "@@@")
        model.allCharacters



        setContentView(R.layout.generated_character_completion)

        //キャラクター一覧画面へ戻る
        btn_end_to_character_create.setOnClickListener {
            val finishCharacterCreate = Intent(this, CharacterListActivity::class.java)
            finishCharacterCreate.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or  Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(finishCharacterCreate)

        }
    }
}