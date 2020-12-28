package com.example.namebattler

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.namebattler.characters.fragment.CharacterListFragment
import com.example.namebattler.party.PartyFormationFragment

class BattleActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foundation)

        //ヘッダー
        if (savedInstanceState == null){
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する
            fragmentTransaction.replace(
                R.id.attach_screen,
                PartyFormationFragment()
            )
            // 張り付けを実行
            fragmentTransaction.commit()
        }
    }
}