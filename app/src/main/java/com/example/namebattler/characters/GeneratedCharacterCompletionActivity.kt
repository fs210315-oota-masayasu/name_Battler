package com.example.namebattler.characters

import android.content.Intent
import android.os.Bundle
import com.example.namebattler.R
import com.example.namebattler.util.ScopedAppActivity
import kotlinx.android.synthetic.main.generated_character_completion.*

//キャラクター作成完了画面
class GeneratedCharacterCompletionActivity : ScopedAppActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.generated_character_completion)

        //キャラクター一覧画面へ戻る
        btn_end_to_character_create.setOnClickListener {
            val finishCharacterCreate = Intent(this, CharacterListActivity::class.java)
            finishCharacterCreate.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or  Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(finishCharacterCreate)

        }
    }
}