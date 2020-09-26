package com.example.namebattler.characters.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.namebattler.R
import com.example.namebattler.characters.activity.CharacterListActivity
import com.example.namebattler.characters.activity.NewCharacterGenerateActivity
import kotlinx.android.synthetic.main.fragment_processing_after_creation.*

class AfterGenerationMenuFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_processing_after_creation, container, false)
    }
    companion object {
        @JvmStatic
        fun newInstance(): AfterGenerationMenuFragment {
            return AfterGenerationMenuFragment()
        }
    }

    override fun onStart() {
        super.onStart()

        //続けて作成する
        btn_continue_character_create.setOnClickListener {
            val intentOfContinue = Intent(activity,
                NewCharacterGenerateActivity::class.java)
            intentOfContinue.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or  Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intentOfContinue)
        }


        //作成を終了する
        btn_end_to_character_create.setOnClickListener {
            val intentOfEnd = Intent(activity, CharacterListActivity::class.java)
            intentOfEnd.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or  Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intentOfEnd)
        }
    }
}
