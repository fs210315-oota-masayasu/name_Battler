package com.example.namebattler.characters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.namebattler.R
import kotlinx.android.synthetic.main.fragment_processing_after_creation.*

class ProcessingAfterCreationFragment() : Fragment() {

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

        fun newInstance(): ProcessingAfterCreationFragment {
            return ProcessingAfterCreationFragment()
        }

    }

    override fun onStart() {
        super.onStart()
        btn_end_to_character_create.setOnClickListener {
            val finishCharacterCreate = Intent(activity, CharacterListActivity::class.java)
            finishCharacterCreate.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or  Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(finishCharacterCreate)

        }
    }

//        fun newInstance() =
//            ProcessingAfterCreationFragment().apply{
//                arguments = Bundle().apply{
//                                    }
//            }


}
