package com.example.namebattler.battle.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.namebattler.data.battleData.CharacterInformationHolder
import com.example.namebattler.databinding.FragmentInformationViewBinding

private const val ARG_PARAM1 = "param1"

class InformationViewFragment : Fragment() {

    private lateinit var binding: FragmentInformationViewBinding
    private var informationData = CharacterInformationHolder()

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
            informationData = it.getSerializable(ARG_PARAM1) as CharacterInformationHolder
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentInformationViewBinding.inflate(inflater, container, false).apply {

            //コンディション情報
            val pairList = informationData.cond
            //condListからStringのみ抽出してListへ格納
            val condList =  pairList.flatMap { listOf(it.key) }
            //List内の値を連結
            val cond = condList.joinToString(separator = " ")


            txtValueName.text = informationData.name
            txtValueMaxHp.text = informationData.maxHp.toString()
            txtValueCurrentHp.text = informationData.currentHp.toString()
            txtValueMaxMp.text = informationData.maxMp.toString()
            txtValueCurrentMp.text = informationData.currentMp.toString()
            txtValueCondition.text = cond


        }


        return binding.root
    }
}
