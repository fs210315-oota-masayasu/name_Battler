package com.example.namebattler.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.namebattler.databinding.TestViewSecondBinding
import com.example.namebattler.viewModel.CharacterViewModel
import com.example.namebattler.viewModel.OperationDatabaseViewModel
import com.example.namebattler.viewModel.getViewModelFactory

class SecondTestFragment:Fragment() {

    private lateinit var binding: TestViewSecondBinding
    private val setCharacterViewModel: CharacterViewModel by viewModels { getViewModelFactory() }

    companion object{
        fun newInstance() = SecondTestFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = TestViewSecondBinding.inflate(inflater, container, false).apply {

            characterViewModel = setCharacterViewModel

            //fragmentが切り替わった時に処理が走る
//            setCharacterViewModel.isClickDecideParty.postValue(false)
//            setCharacterViewModel.buttonCounter.postValue(2)


//            Log.d("tag_begin_2", "${setCharacterViewModel.buttonCounter.value}")


            button2View.setOnClickListener {
//                Log.d("tag_cnt", "${setCharacterViewModel.buttonCounter.value}")


                Log.d("button2View", "button2View_OK")
                setCharacterViewModel.apply {
                    headerText.postValue("ボタン押したよ")

/*                    if (setCharacterViewModel.buttonCounter.value == 3){
                        isClickDecideParty.postValue(true)
                    }*/

                }
            }

            buttonSecond.setOnClickListener {

/*                setCharacterViewModel.onClickDecideParty()
                setCharacterViewModel.buttonCounter.postValue(3)

                setCharacterViewModel.isClickDecideParty.apply {
                    if (this.value == true){
                        Log.d("buttonSecond", "buttonSecond_OK")
                        val fragmentManager: FragmentManager = parentFragmentManager
                        fragmentManager.popBackStack("test",FragmentManager.POP_BACK_STACK_INCLUSIVE)

                    }
                }*/
            }
        }
        binding.lifecycleOwner = this.viewLifecycleOwner

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO onClick内で処理するとエラーになるのでやらないこと！！ （ observeが原因 ）
        // TODO java.lang.IllegalStateException: Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()

//        setCharacterViewModel.isClickDecideParty.observe(viewLifecycleOwner, Observer {
//            Log.d("***tag***", """CCCClick!!!""")
//            if (it){
//                val fragmentManager: FragmentManager = parentFragmentManager
//                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//                fragmentTransaction.replace(
//                    R.id.attach_screen,
//                    SecondTestFragment()
//                )
//                fragmentTransaction.commit()
//            }
//        })

//        binding = DataBindingUtil.setContentView(activity!!.parent, R.layout.test_view_second)
//            .inflate(inflater, container,false)
    }

}