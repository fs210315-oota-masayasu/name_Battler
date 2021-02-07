package com.example.namebattler.characters.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.R
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.databinding.CharacterStatusDetailBinding
import com.example.namebattler.viewModel.CharacterViewModel
import com.example.namebattler.viewModel.OperationDatabaseViewModel
import com.example.namebattler.viewModel.getViewModelFactory

//ステータス情報
class CharacterStatusDetailFragment: Fragment(){

    private lateinit var binding: CharacterStatusDetailBinding

    private val setCharacterViewModel: CharacterViewModel by viewModels { getViewModelFactory() }
    private lateinit var setOperationDatabaseViewModel: OperationDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.character_status_detail, container, false)
        binding.characterViewModel = setCharacterViewModel
        binding.operationDatabaseViewModel = setOperationDatabaseViewModel

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setOperationDatabaseViewModel = ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)
    }
}


/*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param_Name"
private const val ARG_PARAM2 = "param_Job"
private const val ARG_PARAM3 = "param_hp"
private const val ARG_PARAM4 = "param_mp"
private const val ARG_PARAM5 = "param_str"
private const val ARG_PARAM6 = "param_def"
private const val ARG_PARAM7 = "param_agi"
private const val ARG_PARAM8 = "param_luck"
//private const val ARG_PARAM9 = "param_current_at"


class CommonDisplayStatusFragment : Fragment() {

    private var paramName : String? = null
    private var paramJob : String? = null
    private var paramHp : String? = null
    private var paramMp : String? = null
    private var paramStr  : String? = null
    private var paramDef : String? = null
    private var paramAgi : String? = null
    private var paramLuck : String? = null

    companion object {
        @JvmStatic
        fun newInstance(paramCharacter: CharacterHolder) =
            CommonDisplayStatusFragment().apply {
                //Object内に格納されている値を取り出してセットする
                paramName = paramCharacter.name
                paramJob = paramCharacter.job
                paramHp = paramCharacter.hp.toString()
                paramMp = paramCharacter.mp.toString()
                paramStr = paramCharacter.str.toString()
                paramDef = paramCharacter.def.toString()
                paramAgi = paramCharacter.agi.toString()
                paramLuck = paramCharacter.luck.toString()

                arguments = Bundle().apply {
                    putString(ARG_PARAM1, paramName)
                    putString(ARG_PARAM2, paramJob)
                    putString(ARG_PARAM3, paramHp)
                    putString(ARG_PARAM4, paramMp)
                    putString(ARG_PARAM5, paramStr)
                    putString(ARG_PARAM6, paramDef)
                    putString(ARG_PARAM7, paramAgi)
                    putString(ARG_PARAM8, paramLuck)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramName = it.getString(ARG_PARAM1)
            paramJob = it.getString(ARG_PARAM2)
            paramHp = it.getString(ARG_PARAM3)
            paramMp = it.getString(ARG_PARAM4)
            paramStr = it.getString(ARG_PARAM5)
            paramDef = it.getString(ARG_PARAM6)
            paramAgi = it.getString(ARG_PARAM7)
            paramLuck = it.getString(ARG_PARAM8)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.common_display_status, container, false)
    }

    override fun onViewCreated(view :View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val charaNameView : TextView = view.findViewById(R.id.value_name)
        val charaJobView : TextView = view.findViewById(R.id.value_job)
        val charaHpView : TextView = view.findViewById(R.id.value_status_hp)
        val charaMpView : TextView = view.findViewById(R.id.value_status_mp)
        val charaStrView : TextView = view.findViewById(R.id.value_status_str)
        val charaDefView : TextView = view.findViewById(R.id.value_status_def)
        val charaAgiView : TextView = view.findViewById(R.id.value_status_agi)
        val charaLuckView : TextView = view.findViewById(R.id.value_status_luck)
        val args : Bundle? = arguments

        val setViewValueName = args?.getString(ARG_PARAM1)
        val setViewValueJob = args?.getString(ARG_PARAM2)
        val setViewValueHp = args?.getString(ARG_PARAM3)
        val setViewValueMp = args?.getString(ARG_PARAM4)
        val setViewValueStr = args?.getString(ARG_PARAM5)
        val setViewValueDef = args?.getString(ARG_PARAM6)
        val setViewValueAgi = args?.getString(ARG_PARAM7)
        val setViewValueLuck = args?.getString(ARG_PARAM8)

        charaNameView.text = setViewValueName
        charaJobView.text = setViewValueJob
        charaHpView.text = setViewValueHp
        charaMpView.text = setViewValueMp
        charaStrView.text = setViewValueStr
        charaDefView.text = setViewValueDef
        charaAgiView.text = setViewValueAgi
        charaLuckView.text = setViewValueLuck
    }
}


*/
