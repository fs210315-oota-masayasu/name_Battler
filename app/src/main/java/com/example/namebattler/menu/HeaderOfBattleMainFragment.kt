package com.example.namebattler.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.namebattler.R
import com.example.namebattler.databinding.FragmentHeaderOfBattleMainBinding
import com.example.namebattler.viewModel.HeaderViewModel
import com.example.namebattler.viewModel.getViewModelFactory

class HeaderOfBattleMainFragment : Fragment() {

    private lateinit var binding: FragmentHeaderOfBattleMainBinding
    private val headerViewModel: HeaderViewModel by viewModels{ getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeaderOfBattleMainBinding.inflate(inflater, container, false).apply {
            headerViewModel = this@HeaderOfBattleMainFragment.headerViewModel
        }

        return binding.root
    }
}



/*
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
*/


