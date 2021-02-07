package com.example.namebattler.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.namebattler.HomeActivity
import com.example.namebattler.databinding.FragmentHeaderDefaultBinding
import com.example.namebattler.viewModel.HeaderViewModel
import com.example.namebattler.viewModel.getViewModelFactory

class HeaderOfReturnHomeFragment : Fragment() {
    private lateinit var binding : FragmentHeaderDefaultBinding
    private val headerViewModel: HeaderViewModel by viewModels { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeaderDefaultBinding.inflate(inflater, container, false).apply {
            headerViewModel = this@HeaderOfReturnHomeFragment.headerViewModel


            /**「戻る」ボタンイベント **/
            btnScreenBack.setOnClickListener {

                val intent = Intent(activity, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

        }

        return binding.root
    }
}