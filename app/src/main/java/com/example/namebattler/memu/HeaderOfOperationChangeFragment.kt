package com.example.namebattler.memu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.namebattler.R
import kotlinx.android.synthetic.main.fragment_header_of_operation_change.*

private const val ARG_HEADER_TEXT = "header_text"

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
        btn_decision.setOnClickListener {
            activity?.finish()
        }

    }

}
