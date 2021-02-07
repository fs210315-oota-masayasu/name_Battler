package com.example.namebattler.characters.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.R
import com.example.namebattler.viewModel.OperationDatabaseViewModel
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.data.characterData.Player
import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.jobData.JobManager
import com.example.namebattler.menu.HeaderFragment
import com.example.namebattler.util.Belong
import com.example.namebattler.util.ScopedAppActivity
/*import kotlinx.android.synthetic.main.character_new_create.**/
import kotlin.concurrent.thread

//キャラクター作成画面
class NewCharacterGenerateActivity : ScopedAppActivity(), TextWatcher {
    //TODO　削除対象

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_new_create)

        //ヘッダー
        if (savedInstanceState == null){
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する
//            fragmentTransaction.replace(
//                R.id.header_area,
//                HeaderFragment.newInstance(
//                    "キャラ作成"
//                )
//            )
            // 張り付けを実行
            fragmentTransaction.commit()
        }

        val mainViewModel = ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)

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
            }

/*
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

                    val jobName = radioButton?.text.toString()
                    val job = JobManager()
                        .getJobList(jobName)

                    //Playerクラスよりパラメータを取得
                    val character = Player(inputName.text.toString() ,job).getParam()?:
                    Characters(
                        "Error:パラメータ取得失敗",
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0
                    )

                    //名前の重複チェック（重複=update、not=insert）
                    mainViewModel.apply {
                        countOverlap.observe(this@NewCharacterGenerateActivity, Observer {
                            when (it) {
                                0 -> {
                                    mainViewModel.insert(character)
                                }
                                1 -> {
                                    mainViewModel.update(character)
                                }
                                else -> {
                                    println("《出力テスト:null》")
                                }
                            }
                        })
                        thread {
                            confirm(inputName.text.toString())
                        }
                    }


                    val setGeneratedCharacterCompletion =
                        Intent(this, ConfirmGenerationCharacterActivity::class.java)
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
        */
    }

    private fun setCharacterHolder(characters : Characters?): CharacterHolder? {
        return if (characters != null) {
            CharacterHolder(
                Belong.PLAYER.name, //所属
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