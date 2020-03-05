package com.example.namebattler.characters

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.namebattler.R
import com.example.namebattler.data.Characters
import com.example.namebattler.memu.MainViewModel
import com.example.namebattler.util.ScopedAppActivity
import kotlinx.android.synthetic.main.character_new_create.*

class CharacterNewCreateActivity : ScopedAppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_new_create)

        //DBへのインサート処理 + 画面遷移
        btn_order_character_creation.setOnClickListener {

            val insertData = Characters(set_input_name.text.toString(),2,120,140,160,180,200,220,240)
            val model = ViewModelProviders.of(this)[MainViewModel::class.java]
            model.insert(insertData)

            val setGeneratedCharacterCompletion = Intent(this, GeneratedCharacterCompletionActivity::class.java)
            startActivity(setGeneratedCharacterCompletion)
        }
    }

/*    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            finish()
        }
    }*/

}