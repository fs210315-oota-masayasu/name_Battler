package com.example.namebattler.battle.activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.namebattler.R
import com.example.namebattler.battle.fragment.BattleOperationFragment
import com.example.namebattler.battle.fragment.StatusInformationFragment
import com.example.namebattler.data.battleData.InformationManager
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.memu.HeaderOfBattleMainFragment
import com.example.namebattler.util.ScopedAppActivity

class BattleMainActivity  : ScopedAppActivity(){

    //var informationLiveData = MutableLiveData <InformationHolder>()
    var secondEnemyName = mutableListOf <String>()
    var thirdEnemyName = mutableListOf <String>()


    companion object{
        const val ENEMY_KEY_STATE = "enemy_key_state"
        const val PARTY_KEY_STATE = "party_key_state"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.battle_main)

        //enemy
        @Suppress("UNCHECKED_CAST")
        val enemyObj = intent.getSerializableExtra(ENEMY_KEY_STATE) as ArrayList <CharacterHolder>

        //party
        @Suppress("UNCHECKED_CAST")
        val partyObj = intent.getSerializableExtra(PARTY_KEY_STATE) as ArrayList <CharacterHolder>

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
        val information = InformationManager().getInstance()
        val holder = information.initOutputInformationHolder(enemyObj,partyObj)

/*        val holder = information.getInformationHolder(enemyObj,partyObj)
        information.setNotice(holder)*/

        //val information = InformationManager(enemyObj,partyObj).getInformationHolder()
        //informationLiveData.postValue(information)
/*        val information = InformationManager(enemyObj,partyObj)
        information.getInformationHolder()*/

        Log.d("tag", "inform is :" + holder[HolderIndexEnum.FIRST_ENEMY.id].name)


        //ステータス情報「status_information」
        if (savedInstanceState == null) {
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager


            information.informationNotice.observe(this, Observer {

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
            fragmentTransaction.replace(R.id.battle_operation_area, BattleOperationFragment.newInstance(enemyObj, partyObj))

            // 張り付けを実行
            fragmentTransaction.commit()

        }




    }

    enum class HolderIndexEnum(val id: Int) {
        FIRST_ENEMY(0),
        SECOND_ENEMY(1),
        THIRD_ENEMY(2),
        FIRST_PARTY(3),
        SECOND_PARTY(4),
        THIRD_PARTY_(5),

    }

}