package com.example.namebattler.battle.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.namebattler.R
import com.example.namebattler.battle.fragment.BattleOperationFragment
import com.example.namebattler.battle.fragment.StatusInformationFragment
import com.example.namebattler.data.battleData.InformationManager
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.menu.HeaderOfBattleMainFragment

class BattleMainActivity  : AppCompatActivity(){

    companion object{
        const val ENEMY_KEY_STATE = "enemy_key_state"
        const val PLAYER_KEY_STATE = "Player_key_state"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.battle_main)

        //enemy
        @Suppress("UNCHECKED_CAST")
        val enemyObj = intent.getSerializableExtra(ENEMY_KEY_STATE) as ArrayList <CharacterHolder>

        //Player
        @Suppress("UNCHECKED_CAST")
        val playerObj = intent.getSerializableExtra(PLAYER_KEY_STATE) as ArrayList <CharacterHolder>

         //ヘッダー
        //ヘッダーに表示させる文字
        val setText :String = getString(R.string.battle_main)
        if (savedInstanceState == null) {
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する
            fragmentTransaction.replace(R.id.header_area, HeaderOfBattleMainFragment.newInstance(setText))

            // 張り付けを実行
            fragmentTransaction.commit()
        }

        //val information = InformationManager()
        val informationManager = InformationManager().getInstance()

        //ステータスの初期情報の取得とLiveData（informationNotice）への格納
        val enemyStatusList = informationManager.initOutputInformationList(enemyObj)
        val playerStatusList = informationManager.initOutputInformationList(playerObj)
        val initInformation = informationManager.getOutputInformationList(enemyStatusList, playerStatusList)
        informationManager.setInformationNotice(initInformation)

        //ステータス情報「status_information」
        if (savedInstanceState == null) {
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager

            //LiveData（informationNotice）に変化があったときにFragmentが再生成される
            informationManager.informationNotice.observe(this, Observer {

            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.status_infomation_area,
                    StatusInformationFragment.newInstance(it))
                // 張り付けを実行
                fragmentTransaction.commitNow()
            })
        }
        //バトル操作「battle_operation」
        if  (savedInstanceState == null){
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            //ステータスの初期情報をFragmentへ送る
            fragmentTransaction.replace(R.id.battle_operation_area, BattleOperationFragment.newInstance(enemyStatusList, playerStatusList))

            // 張り付けを実行
            fragmentTransaction.commit()
        }
    }
}