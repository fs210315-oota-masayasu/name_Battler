package com.example.namebattler.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.R
import com.example.namebattler.databinding.TestViewFirstBinding
import com.example.namebattler.viewModel.CharacterViewModel
import com.example.namebattler.viewModel.OperationDatabaseViewModel
import com.example.namebattler.viewModel.getViewModelFactory
import kotlinx.android.synthetic.main.test_view_first.*

class TestFragment: Fragment() {

    private lateinit var binding: TestViewFirstBinding

    companion object{
        fun newInstance() = TestFragment()
    }

    private val setCharacterViewModel: CharacterViewModel by viewModels { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  =  TestViewFirstBinding.inflate(inflater, container, false).apply {

            characterViewModel = setCharacterViewModel

            //fragmentが切り替わった時に処理が走る
            setCharacterViewModel.isClickDecideParty.postValue(false)
            setCharacterViewModel.buttonCounter.postValue(2)

            Log.d("tag_begin", "${setCharacterViewModel.buttonCounter.value}")

            button1View.setOnClickListener {

                Log.d("tag_cnt", "${setCharacterViewModel.buttonCounter.value}")

                setCharacterViewModel.apply {
                    headerText.postValue("初期値_2")

                    if (setCharacterViewModel.buttonCounter.value == 3){
                        isClickDecideParty.postValue(true)
                    }else{
                        headerText.postValue("上のボタンを押して！")
                    }

                }
            }

            buttonFirst.setOnClickListener {
//                setCharacterViewModel.onClickDecideParty()

                setCharacterViewModel.buttonCounter.postValue(3)

                setCharacterViewModel.isClickDecideParty.apply {
                    Log.d("buttonSecond", "buttonSecond_check: ${this.value}}")


                    if (this.value == true){
                        Log.d("buttonSecond", "buttonSecond_OK")
                        val fragmentManager: FragmentManager = parentFragmentManager
                        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

                        // BackStackを設定 タグは高度な指定
                        fragmentTransaction.addToBackStack("test")

                        fragmentTransaction.replace(
                            R.id.attach_screen,
                            SecondTestFragment()
                        )

                        fragmentTransaction.commit()
                    }else{
                        setCharacterViewModel.headerText.postValue("下のボタンを押して！")
                    }
                }
            }
        }
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

}