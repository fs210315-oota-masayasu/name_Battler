package com.example.namebattler.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.namebattler.R
import com.example.namebattler.databinding.HeaderScreenBinding
import com.example.namebattler.util.HeaderFlag
import com.example.namebattler.viewModel.HeaderViewModel
import com.example.namebattler.viewModel.getViewModelFactory

class HeaderFragment : Fragment() {

    private lateinit var binding: HeaderScreenBinding
    private val headerViewModel: HeaderViewModel by viewModels { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HeaderScreenBinding.inflate(inflater, container, false)

        val fragmentManager: FragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        when(headerViewModel.outputFlag){
            HeaderFlag.NONE ->{
                fragmentTransaction.replace(
                    R.id.output_header_screen,
                    HeaderOfAbnormalityFragment()
                )
            }
            HeaderFlag.DEFAULT ->{
                fragmentTransaction.replace(
                    R.id.output_header_screen,
                    HeaderOfDefaultFragment()
                )
            }

            HeaderFlag.BATTLE_MAIN ->{
                fragmentTransaction.replace(
                    R.id.output_header_screen,
                    HeaderOfBattleMainFragment()
                )
            }
            HeaderFlag.OPERATION_CHANGE ->{
                fragmentTransaction.replace(
                    R.id.output_header_screen,
                    HeaderOfOperationChangeFragment()
                )
            }
            HeaderFlag.RETURN_HOME ->{
                fragmentTransaction.replace(
                    R.id.output_header_screen,
                    HeaderOfReturnHomeFragment()
                )
            }

            HeaderFlag.PARTY_FORMATION ->{
                fragmentTransaction.replace(
                    R.id.output_header_screen,
                    HeaderOfReturnPartyFormationFragment()
                )
            }

        }


        fragmentTransaction.commit()



        return binding.root
    }
/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textOfHeaderView : TextView = view.findViewById(R.id.txt_header)

        val args : Bundle? = arguments
        textOfHeaderView.text = args?.getString(ARG_PARAM)
    }

    *//**「戻る」ボタンイベント（現在表示しているActivityを終了する） **//*
    override fun onStart(){
        super.onStart()

        var btn_screen_back = view?.findViewById<Button>(R.id.btn_screen_back)

        btn_screen_back?.setOnClickListener {

            activity?.finish()
//            val intent = Intent(activity, MainActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//            startActivity(intent)
        }
    }*/

}

/*private const val ARG_PARAM = "text_of_header"

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
        var view = inflater.inflate(R.layout.fragment_header, container, false)

        return inflater.inflate(R.layout.fragment_header, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textOfHeaderView : TextView = view.findViewById(R.id.txt_header)

        val args : Bundle? = arguments
        textOfHeaderView.text = args?.getString(ARG_PARAM)
    }

    *//**「戻る」ボタンイベント（現在表示しているActivityを終了する） **//*
    override fun onStart(){
        super.onStart()

        var btn_screen_back = view?.findViewById<Button>(R.id.btn_screen_back)

        btn_screen_back?.setOnClickListener {

            activity?.finish()
//            val intent = Intent(activity, MainActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//            startActivity(intent)
        }
    }

}*/
