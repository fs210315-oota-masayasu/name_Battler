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
import com.example.namebattler.memu.MainActivity
import com.example.namebattler.party.PartyFormationActivity
import kotlinx.android.synthetic.main.fragment_win_view.*

class WinViewFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_win_view, container, false)
    }

    override fun onStart() {
        super.onStart()
        //再挑戦
        challenge_again.setOnClickListener {
            val setIntentChallengeAgain = Intent(activity, BattleMainActivity::class.java)
            setIntentChallengeAgain.putExtra(BattleMainActivity.ENEMY_KEY_STATE,enemyObj)
            setIntentChallengeAgain.putExtra(BattleMainActivity.PLAYER_KEY_STATE,playerObj)
            startActivity(setIntentChallengeAgain)
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
            WinViewFragment().apply{
                enemyObj = enemyList
                playerObj = playerList
        }

    }
}
