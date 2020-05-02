package com.example.namebattler.characters.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.R
import com.example.namebattler.characters.MainViewModel
import com.example.namebattler.data.CharaState
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
        var sendToCompleat: CharaState? = null


        //ラジオボタン

        val radioGroup = findViewById<RadioGroup>(R.id.grp_select_job)
        radioGroup.clearCheck()
        var setRadioButton: RadioButton? = null


            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                // checkedIdから、選択されたRadioButtonを取得
                val radioButton = findViewById<RadioButton>(checkedId)
                setRadioButton = radioButton
            }


        //DBへのインサート処理 + 画面遷移
        btn_order_character_creation.setOnClickListener {

            val checked = radioGroup.checkedRadioButtonId

            Log.d("tag", "ラジオボタンID：$checked")



            val chkEmpty = inputName.text.toString().trim()

            when {
                chkEmpty.isEmpty() -> {
                    Toast.makeText(this, "名前を入力してください", Toast.LENGTH_SHORT).show()
                }
                checked != -1  -> {
                    Log.d("tag", inputName.text.toString())

                    Log.d("tag", "ラジオボタン名：" + setRadioButton?.text.toString())


                    val name = setRadioButton?.text.toString()
                    val job = JobList().getJobList(name)

                    //名前重複チェック




                    // TODO 後で修正
                    // キャラクタークラス（もしくは職業クラス）から値を取得する
                    val hp = 11
                    val mp = 22
                    val str = 33
                    val def = 44
                    val agi = 55
                    val luck = 66

                    //作成日時を取得
                    val currentDate = DateConverter().getCurrentDate()

                    // TODO 後で消す②
                    val currentDateAtString = DateConverter().convertLongToString(currentDate)


                    val characters = Characters(
                        inputName.text.toString(),
                        job,
                        hp,
                        mp,
                        str,
                        def,
                        agi,
                        luck,
                        currentDate
                    )

                    sendToCompleat = CharaState(
                        inputName.text.toString(),
                        JobList().getJobList(job),
                        hp,
                        mp,
                        str,
                        def,
                        agi,
                        luck,
                        currentDate
                    )

                    mainViewModel.insert(characters)

                    val setGeneratedCharacterCompletion =
                        Intent(this, GeneratedCharacterCompletionActivity::class.java)
                    setGeneratedCharacterCompletion.putExtra(CharaState.EXTRA_DATA, sendToCompleat)
                    startActivity(setGeneratedCharacterCompletion)

                }
                else -> {
                    Toast.makeText(this, "職業を選択してください", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}





            // TODO 後で消す④
            //名前チェック すでにインサートされている場合はエラー表示

            //if (inputName.text != null || setRadioButton?.text != null){
/*                Log.d("tag", inputName.text.toString())

                Log.d("tag","ラジオボタン名：" + setRadioButton?.text.toString())


                val name = setRadioButton?.text.toString()
                val job = JobList().getJobList(name)

                // TODO 後で修正
                // キャラクタークラス（もしくは職業クラス）から値を取得する
                val hp = 11
                val mp = 22
                val str = 33
                val def = 44
                val agi = 55
                val luck = 66

                //作成日時を取得
                val currentDate = DateConverter().getCurrentDate()

                // TODO 後で消す②
                val currentDateAtString = DateConverter().convertLongToString(currentDate)


                val characters = Characters(inputName.text.toString(), job,hp,mp,str,def,agi,luck,currentDate)

                sendToCompleat = CharaState(inputName.text.toString(), JobList().getJobList(job),hp,mp,str,def,agi,luck,currentDate)

                mainViewModel.insert(characters)

                val setGeneratedCharacterCompletion = Intent(this, GeneratedCharacterCompletionActivity::class.java)
                setGeneratedCharacterCompletion.putExtra(CharaState.EXTRA_DATA,sendToCompleat)
                startActivity(setGeneratedCharacterCompletion)*/



//            }else{
//                Log.d("tag", "名前か職業が入力されていないよ")

//            }



            //if (set_input_name.text != null){
              //  Log.d("tag", inputName.text.toString())
            //}



//        }
//    }

//}