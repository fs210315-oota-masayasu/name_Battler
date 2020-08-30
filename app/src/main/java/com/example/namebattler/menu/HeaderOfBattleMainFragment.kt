package com.example.namebattler.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.namebattler.R

private const val ARG_HEADER_TEXT = "header_text"


class HeaderOfBattleMainFragment : Fragment() {

    private var headerText : String? = null

    companion object {
        @JvmStatic
        fun newInstance(setHeaderText: String) =
            HeaderOfBattleMainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_HEADER_TEXT, setHeaderText)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            headerText = it.getString(ARG_HEADER_TEXT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_header_of_battle_main, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val headerTextView : TextView = view.findViewById(R.id.txt_header_of_battle_main)

        val args : Bundle? = arguments
        headerTextView.text = args?.getString(ARG_HEADER_TEXT)

    }
}


