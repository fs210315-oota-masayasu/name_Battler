package com.example.namebattler.memu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.namebattler.R

private const val ARG_PARAM = "text_of_header"


class HeaderOfBattleMainFragment : Fragment() {

    private var setText : String? = null

    companion object {
        @JvmStatic
        fun newInstance(setTextOfHeader: String) =
            HeaderOfBattleMainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM, setTextOfHeader)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            setText = it.getString(ARG_PARAM)
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
        val textOfHeaderView : TextView = view.findViewById(R.id.txt_header_of_battle_main)

        val args : Bundle? = arguments
        textOfHeaderView.text = args?.getString(ARG_PARAM)

    }
}


