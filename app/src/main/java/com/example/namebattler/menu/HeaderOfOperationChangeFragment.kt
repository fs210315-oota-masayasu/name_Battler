package com.example.namebattler.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.namebattler.R
import com.example.namebattler.databinding.FragmentHeaderOfOperationChangeBinding
import com.example.namebattler.util.BackStack
import com.example.namebattler.util.HeaderFlag
import com.example.namebattler.viewModel.HeaderViewModel
import com.example.namebattler.viewModel.getViewModelFactory

class HeaderOfOperationChangeFragment : Fragment() {

    private lateinit var binding: FragmentHeaderOfOperationChangeBinding
    private val headerViewModel: HeaderViewModel by viewModels{ getViewModelFactory()}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHeaderOfOperationChangeBinding.inflate(inflater, container, false).apply {
            headerViewModel = this@HeaderOfOperationChangeFragment.headerViewModel

            //ヘッダー情報更新（バトルメイン画面）
            this@HeaderOfOperationChangeFragment.headerViewModel.apply {
                headerText.postValue(getString(R.string.battle_main))
                outputFlag = HeaderFlag.BATTLE_MAIN
            }

            btnDecision.setOnClickListener {
                val fragmentManager = parentFragmentManager
                fragmentManager.popBackStack(BackStack.BATTLE_MAIN.name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }

        }
        return binding.root
    }

}

/*private const val ARG_HEADER_TEXT = "header_text"

class HeaderOfOperationChangeFragment : Fragment() {
    private var headerText: String? = null
    companion object {

        @JvmStatic
        fun newInstance(setHeaderText: String) =
            HeaderOfOperationChangeFragment().apply {
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
        return inflater.inflate(R.layout.fragment_header_of_operation_change, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val headerTextView : TextView = view.findViewById(R.id.txt_header)

        val args : Bundle? = arguments
        headerTextView.text = args?.getString(ARG_HEADER_TEXT)
    }

    override fun onStart(){
        super.onStart()
*//*
        btn_decision.setOnClickListener {
            activity?.finish()
        }
*//*

    }

}*/
