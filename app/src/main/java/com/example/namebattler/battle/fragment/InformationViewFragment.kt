package com.example.namebattler.battle.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.namebattler.R
import com.example.namebattler.data.battleData.CharacterInformationHolder

private const val ARG_PARAM1 = "param1"

class InformationViewFragment : Fragment() {
    private var informationData : CharacterInformationHolder? = null

    companion object {
        @JvmStatic
        fun newInstance(informationData : CharacterInformationHolder) =
            InformationViewFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1,informationData)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            informationData = it.getSerializable(ARG_PARAM1) as CharacterInformationHolder?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_information_view, container, false)
    }

    override fun onViewCreated(view :View, savedInstanceState: Bundle?){
        val args : Bundle? = arguments
        val informationData = args?.getSerializable(ARG_PARAM1) as CharacterInformationHolder?

        val name = informationData?.name
        val maxHp = informationData?.maxHp
        val currentHp = informationData?.currentHp
        val maxMp = informationData?.maxMp
        val currentMp = informationData?.currentMp

        val pairList = informationData?.cond?: mutableMapOf()
        //condListからStringのみ抽出してListへ格納
        val condList =  pairList.flatMap { listOf(it.key) }
        //List内の値を連結
        val cond = condList.joinToString(separator = " ")

        val nameView : TextView = view.findViewById(R.id.txt_value_name)
        val maxHpView : TextView = view.findViewById(R.id.txt_value_max_hp)
        val currentHpView : TextView = view.findViewById(R.id.txt_value_current_hp)
        val maxMpView : TextView = view.findViewById(R.id.txt_value_max_mp)
        val currentMpView : TextView = view.findViewById(R.id.txt_value_current_mp)
        val condView : TextView = view.findViewById(R.id.txt_value_condition)

        nameView.text = name
        maxHpView.text = maxHp.toString()
        currentHpView.text = currentHp.toString()
        maxMpView.text = maxMp.toString()
        currentMpView.text = currentMp.toString()
        condView.text = cond
    }

}
