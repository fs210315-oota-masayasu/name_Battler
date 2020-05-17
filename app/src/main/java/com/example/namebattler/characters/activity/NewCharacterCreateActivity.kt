package com.example.namebattler.characters.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.R
import com.example.namebattler.characters.MainViewModel
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.data.characterData.Player
import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.jobData.JobManager
import com.example.namebattler.util.ScopedAppActivity
import kotlinx.android.synthetic.main.character_new_create.*
import kotlin.concurrent.thread

//キャラクター作成画面
class NewCharacterCreateActivity : ScopedAppActivity(), TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_new_create)

        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //名前
        val inputName = findViewById<EditText>(R.id.set_input_name)
        inputName.addTextChangedListener(this)

        //ラジオボタン
        val radioGroup = findViewById<RadioGroup>(R.id.grp_select_job)
        radioGroup.clearCheck()
        var radioButton : RadioButton? = null

            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                // checkedIdから、選択されたRadioButtonを取得
                radioButton = findViewById(checkedId)
                //setRadioButton = radioButton
            }

        //DBへのインサート処理 + 画面遷移
        btn_order_character_creation.setOnClickListener {

            val checked = radioGroup.checkedRadioButtonId

            val chkEmpty = inputName.text.toString().trim()

            //バリデーションチェック
            when {
                chkEmpty.isEmpty() -> {
                    inputName.error = "名前を入力してください"
                }
                checked != -1  -> {
                    Log.d("tag", inputName.text.toString())

                    Log.d("tag", "ラジオボタン名：" + radioButton?.text.toString())


                    val jobName = radioButton?.text.toString()
                    val job = JobManager()
                        .getJobList(jobName)

                    //Playerクラスよりパラメータを取得
                    val character = Player(inputName.text.toString() ,job).getParam()

                    thread{
                        //名前の重複チェック（重複=update、not=insert）
                        when (val cnt = mainViewModel.countOverlap(inputName.text.toString())) {
                            0 -> {
                                mainViewModel.insert(character!!)
                            }
                            1 -> {
                                mainViewModel.update(character!!)
                            }
                            else -> {
                                println("《出力テスト:null》")
                                Log.d("tag", cnt.toString())
                                println("《出力テスト:null》")
                            }
                        }

                    }

                    val setGeneratedCharacterCompletion =
                        Intent(this, GeneratedCharacterCompletionActivity::class.java)
                    setGeneratedCharacterCompletion.putExtra(CharacterHolder.EXTRA_DATA, setCharacterHolder(character))
                    startActivity(setGeneratedCharacterCompletion)

                }
                else -> {
                    val ms= Toast.makeText(this, "職業を選択してください", Toast.LENGTH_LONG)
                    ms.setGravity(Gravity.CENTER, 0, 0)
                    ms.show()
                }
            }

        }
    }

    private fun setCharacterHolder(characters : Characters?): CharacterHolder? {
        return if (characters != null) {
            CharacterHolder(
                characters.NAME,
                JobManager().getJobList(characters.JOB),
                characters.HP,
                characters.MP,
                characters.STR,
                characters.DEF,
                characters.AGI,
                characters.LUCK,
                characters.CREATE_AT
            )
        }else{
            throw IllegalArgumentException("The value is not set correctly.(characters is null !!)")
        }
    }

    override fun afterTextChanged(s: Editable?) {
        val inputStr = s.toString()

        if (inputStr.length > 20){
            //名前
            val inputName = findViewById<EditText>(R.id.set_input_name)
            inputName.error = "20文字以内で入力してください。"
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

}