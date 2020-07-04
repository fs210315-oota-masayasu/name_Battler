package com.example.namebattler.battle.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.namebattler.R
import com.example.namebattler.data.battleData.OutputInformationHolder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"



class InformationViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var informationData : OutputInformationHolder? = null
    private var param2: String? = null

    companion object {

        @JvmStatic
        fun newInstance(informationData : OutputInformationHolder) =
            InformationViewFragment().apply {
                arguments = Bundle().apply {

                    putSerializable(ARG_PARAM1,informationData)
                }
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            informationData = it.getSerializable(ARG_PARAM1) as OutputInformationHolder?
            //param2 = it.getString(ARG_PARAM2)*/
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information_view, container, false)
    }

    override fun onViewCreated(view :View, savedInstanceState: Bundle?){
        val args : Bundle? = arguments
        val informationData = args?.getSerializable(ARG_PARAM1) as OutputInformationHolder?

        val name = informationData?.name
        val maxHp = informationData?.maxHp
        val currentHp = informationData?.currentHp
        val maxMp = informationData?.maxMp
        val currentMp = informationData?.currentMp
        val cond = informationData?.cond

        val nameView : TextView = view.findViewById(R.id.txt_value_name)
        val maxHpView : TextView = view.findViewById(R.id.txt_value_max_hp)
        val currentHpView : TextView = view.findViewById(R.id.txt_value_current_hp)
        val maxMpView : TextView = view.findViewById(R.id.txt_value_max_mp)
        val currentMpView : TextView = view.findViewById(R.id.txt_value_current_mp)
        val condView : TextView = view.findViewById(R.id.txt_value_condition)

        nameView.text = name
        maxHpView.text = maxHp
        currentHpView.text = currentHp
        maxMpView.text = maxMp
        currentMpView.text = currentMp
        condView.text = cond
    }



}
