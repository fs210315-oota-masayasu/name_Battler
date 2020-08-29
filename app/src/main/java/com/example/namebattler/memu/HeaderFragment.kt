package com.example.namebattler.memu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.namebattler.R
import kotlinx.android.synthetic.main.fragment_header.*

private const val ARG_PARAM = "text_of_header"

class HeaderFragment : Fragment() {

    private var setText : String? = null

    companion object {
        @JvmStatic
        fun newInstance(setTextOfHeader: String) =
            HeaderFragment().apply {
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
        return inflater.inflate(R.layout.fragment_header, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textOfHeaderView : TextView = view.findViewById(R.id.txt_header)

        val args : Bundle? = arguments
        textOfHeaderView.text = args?.getString(ARG_PARAM)
    }


    override fun onStart(){
        super.onStart()
        btn_screen_back.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

}
