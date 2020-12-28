package com.example.namebattler.party

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.battle.activity.BattleMainActivity
import com.example.namebattler.viewModel.OperationDatabaseViewModel
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.data.enemyData.EnemyManager
import com.example.namebattler.menu.HeaderFragment
import kotlinx.android.synthetic.main.battle_lobby.*

class BattleLobbyActivity : AppCompatActivity() {

    private lateinit var operationDatabaseViewModel: OperationDatabaseViewModel
    private var nameList = mutableListOf <String?>()
    private var secondaryList = mutableListOf <String?>()

    var sendToEnemy = arrayListOf<CharacterHolder?>()


    companion object {
        val KEY_STATE = "key_state"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.battle_lobby)

        //ヘッダー
        if (savedInstanceState == null){
            // FragmentManagerのインスタンス生成
            val fragmentManager: FragmentManager = supportFragmentManager
            // FragmentTransactionのインスタンスを取得
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            // インスタンスに対して張り付け方を指定する
            fragmentTransaction.replace(
                R.id.header_area,
                HeaderFragment.newInstance(
                    "バトル開始"
                )
            )
            // 張り付けを実行
            fragmentTransaction.commit()
        }

        val states = intent.getSerializableExtra(KEY_STATE) as ArrayList<*>


        //敵パーティリスト
        val recyclerViewOfEnemyList = findViewById<RecyclerView>(R.id.enemy_list_view)
        val enemyAdapter = EnemyListAdapter(this)

        recyclerViewOfEnemyList.adapter = enemyAdapter
        recyclerViewOfEnemyList.layoutManager = LinearLayoutManager(this)

        val getEnemyList = EnemyManager().getEnemyData()

        sendToEnemy = enemyAdapter.list

        enemyAdapter.setCharacter(getEnemyList)


        //編成済みパーティリスト
        val recyclerViewOfPartyList = findViewById<RecyclerView>(R.id.party_list_view)
        val partyAdapter = PlayerListAdapter(this)
        val itemDecoration =  DividerItemDecoration(this, LinearLayoutManager(this).orientation)

        recyclerViewOfPartyList.adapter = partyAdapter
        recyclerViewOfPartyList.layoutManager = LinearLayoutManager(this)
        recyclerViewOfPartyList.addItemDecoration(itemDecoration)

        @Suppress("UNCHECKED_CAST")
        partyAdapter.setCharacter(states as ArrayList<CharacterHolder>)

        //バトルメイン画面へ遷移
        btn_battle_start.setOnClickListener {
            val intent = Intent(this, BattleMainActivity::class.java)
            intent.putExtra(BattleMainActivity.ENEMY_KEY_STATE,sendToEnemy)
            intent.putExtra(BattleMainActivity.PLAYER_KEY_STATE,states)
            startActivity(intent)
        }

        //パーティ編成画面へ戻る
        btn_reselect_enemy.setOnClickListener {
            val intent = Intent(this, PartyFormationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}