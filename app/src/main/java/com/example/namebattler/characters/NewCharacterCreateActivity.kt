package com.example.namebattler.characters

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.R
import com.example.namebattler.data.Characters
import com.example.namebattler.data.DateConverter
import com.example.namebattler.data.JobList
import com.example.namebattler.util.ScopedAppActivity
import kotlinx.android.synthetic.main.character_new_create.*

//キャラクター作成画面
class NewCharacterCreateActivity : ScopedAppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_new_create)

        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //名前
        val inputName = findViewById<EditText>(R.id.set_input_name)


        //ラジオボタン

        val radioGroup = findViewById<RadioGroup>(R.id.grp_select_job)
        radioGroup.clearCheck()
        var setRadioButton : RadioButton? = null
        radioGroup.setOnCheckedChangeListener{
                _, checkedId ->
            // checkedIdから、選択されたRadioButtonを取得
            val radioButton = findViewById<RadioButton>(checkedId)
            setRadioButton = radioButton
        }

        //TODO インサート処理の動作確認　名前と職業のみでインサートできるか試してみる...OK


        //DBへのインサート処理 + 画面遷移
        btn_order_character_creation.setOnClickListener {

            // TODO 後で消す①
            if (set_input_name.text != null){
                Log.d("tag", inputName.text.toString())
            }

            if(grp_select_job != null) {
                Log.d("tag","ラジオボタン名：" + setRadioButton?.text.toString())


                val name = setRadioButton?.text.toString()
                val job = JobList().getJobList(name)

                // TODO 後で修正
                // キャラクタークラス（もしくは職業クラス）から値を取得する
                val hp = 11
                val mp = 11
                val str = 11
                val def = 11
                val agi = 11
                val luck = 11

                //作成日時を取得
                val currentDate = DateConverter().getCurrentDate()

                // TODO 後で消す②
                // TODO インサートする値できちんと日時表示できるか？
                val currentDateAtString = DateConverter().convertLongToString(currentDate)

                Log.d("tag","Job val is $job")
                Log.d("tag", "create_At val long  $currentDate")
                Log.d("tag", "create_At_String $currentDateAtString")


                val characters = Characters(inputName.text.toString(), job,hp,mp,str,def,agi,luck,currentDate)

                mainViewModel.insert(characters)
            }

            val setGeneratedCharacterCompletion = Intent(this, GeneratedCharacterCompletionActivity::class.java)
            startActivity(setGeneratedCharacterCompletion)
        }
    }

}