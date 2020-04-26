package com.example.namebattler.characters

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.namebattler.R
import com.example.namebattler.data.CharaState
import com.example.namebattler.util.ScopedAppActivity

class CharacterStatusActivity : ScopedAppActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.output_screen_)

        // Serializableを使ってobjectをFragmentへ渡す
        if (savedInstanceState == null) {
            val sendObj = intent.getSerializableExtra(CharaState.EXTRA_DATA)

            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する
            fragmentTransaction.replace(
                R.id.container,
                CommonDisplayStatusFragment.newInstance(sendObj as CharaState)
            )

            fragmentTransaction.replace(
                R.id.buttonArea,
                ProcessingAfterCreationFragment.newInstance()
            )
            // 張り付けを実行
            fragmentTransaction.commit()
        }
    }

}