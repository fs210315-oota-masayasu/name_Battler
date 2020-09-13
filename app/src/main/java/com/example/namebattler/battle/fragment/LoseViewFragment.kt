package com.example.namebattler.battle.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.namebattler.R
import com.example.namebattler.battle.activity.BattleMainActivity
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.menu.MainActivity
import com.example.namebattler.party.PartyFormationActivity
import kotlinx.android.synthetic.main.fragment_lose_view.*

class LoseViewFragment : Fragment() {

    var enemyObj = arrayListOf<CharacterHolder>()
    var playerObj = arrayListOf<CharacterHolder>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lose_view, container, false)
    }

    override fun onStart() {
        super.onStart()
        //再挑戦
        challenge_again.setOnClickListener {
            val challengeAgain = Intent(activity, BattleMainActivity::class.java)
            challengeAgain.putExtra(BattleMainActivity.ENEMY_KEY_STATE,enemyObj)
            challengeAgain.putExtra(BattleMainActivity.PLAYER_KEY_STATE,playerObj)
            startActivity(challengeAgain)
        }

        //次の対戦へ
        next_battle.setOnClickListener {
            val setIntentPartyCreate = Intent(activity, PartyFormationActivity::class.java)
            setIntentPartyCreate.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(setIntentPartyCreate)
        }

        //対戦を終了する
        end_battle.setOnClickListener {
            val setIntentEndBattle = Intent(activity, MainActivity::class.java)
            setIntentEndBattle.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(setIntentEndBattle)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(enemyList :ArrayList<CharacterHolder>, playerList :ArrayList<CharacterHolder>) =
            LoseViewFragment().apply {
                enemyObj = enemyList
                playerObj = playerList
            }
    }
}
