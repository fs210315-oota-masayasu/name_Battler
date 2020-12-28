package com.example.namebattler.party

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.namebattler.R
import kotlinx.android.synthetic.main.activity_foundation.view.*

class PartyFormationFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null){
            val fragmentManager: FragmentManager = parentFragmentManager
            val fragmentTransition: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransition.replace(
                R.id.attach_screen,
                TestBattleFragment()
            )

            fragmentTransition.commit()

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_foundation, container, false)
    }
}