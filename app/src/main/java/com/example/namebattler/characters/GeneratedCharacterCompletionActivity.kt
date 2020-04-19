package com.example.namebattler.characters

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.namebattler.R
import com.example.namebattler.data.CharaState
import com.example.namebattler.util.ScopedAppActivity
import kotlinx.android.synthetic.main.generated_character_completion.*


//キャラクター作成完了画面
class GeneratedCharacterCompletionActivity : ScopedAppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.generated_character_completion)


        var setName : String? = null
        var setJob :String? = null


        // TODO Fragment生成（テスト）
        if(savedInstanceState == null){
            val state = intent.getSerializableExtra(CharaState.EXTRA_DATA)
            if(state is CharaState){
                setName = state.name
                setJob = state.job
            }



            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する
            fragmentTransaction.replace(R.id.container,  TestFragment.newInstance(setName,setJob))
            // 張り付けを実行
            fragmentTransaction.commit()
        }


        //キャラクター一覧画面へ戻る
        btn_end_to_character_create.setOnClickListener {
            val finishCharacterCreate = Intent(this, CharacterListActivity::class.java)
            finishCharacterCreate.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or  Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(finishCharacterCreate)

        }
    }
}