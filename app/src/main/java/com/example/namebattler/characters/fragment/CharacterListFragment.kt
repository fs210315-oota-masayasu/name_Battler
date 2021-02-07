package com.example.namebattler.characters.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namebattler.R
import com.example.namebattler.characters.CharaListAdapter
import com.example.namebattler.databinding.FragmentCharacterListBinding
import com.example.namebattler.menu.HeaderFragment
import com.example.namebattler.util.BackStack
import com.example.namebattler.util.HeaderFlag
import com.example.namebattler.viewModel.CharacterViewModel
import com.example.namebattler.viewModel.HeaderViewModel
import com.example.namebattler.viewModel.OperationDatabaseViewModel
import com.example.namebattler.viewModel.getViewModelFactory

/** キャラクター一覧画面 **/
class CharacterListFragment: Fragment() {
    private lateinit var binding: FragmentCharacterListBinding

    private val setCharacterViewModel: CharacterViewModel by viewModels { getViewModelFactory() }
    private val headerViewModel: HeaderViewModel by viewModels{ getViewModelFactory() }
    private lateinit var setOperationDatabaseViewModel: OperationDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setOperationDatabaseViewModel = ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)

        setOperationDatabaseViewModel.numOfRegistrations.observe(viewLifecycleOwner, {
            val setText = "${getString(R.string.character_list)} ( $it )"

            headerViewModel.apply {
                headerText.postValue(setText)
                outputFlag = HeaderFlag.RETURN_HOME
            }
        })

        binding = FragmentCharacterListBinding.inflate(inflater,container,false).apply {

            //Liveデータからの通知をうけてFragmentやViewを設定
            setOperationDatabaseViewModel.allCharacters.observe(viewLifecycleOwner, {

                /** ヘッダー **/
                if (savedInstanceState == null) {
                    // FragmentManagerのインスタンス生成
                    val fragmentManager: FragmentManager = parentFragmentManager

                    setOperationDatabaseViewModel.apply {
                        //LiveDataの通知を受け取って処理を実行
                        numOfRegistrations.observe(viewLifecycleOwner, {
                            // FragmentTransactionのインスタンスを取得
                            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                            // インスタンスに対して張り付け方を指定する
                            fragmentTransaction.replace(
                                R.id.header_area,
                                HeaderFragment()
                            )
                            // 張り付けを実行
                            fragmentTransaction.commit()
                        })
                        //ViewModelの件数取得処理を実行（numOfRegistrationsに格納される）
                        confirmNumOfRegistrations()
                    }
                }
                /** RecyclerView **/
                val adapter = CharaListAdapter(setOperationDatabaseViewModel, setCharacterViewModel, viewLifecycleOwner)
                binding.lifecycleOwner = viewLifecycleOwner

                //adapterにlifecycleOwnerを渡す
                val recyclerViewOfCharacters = binding.listView
                recyclerViewOfCharacters.layoutManager = LinearLayoutManager(this@CharacterListFragment.context)
                recyclerViewOfCharacters.adapter = adapter

                // RecyclerViewのクリックイベント（Adapter内のインターフェース実装）
                adapter.setOnItemClickListener(object :
                    CharaListAdapter.OnItemClickListener{
                    override fun onItemClickListener(
                        view: View,
                        position: Int,
                    ){
                        val fragmentManager: FragmentManager = parentFragmentManager
                        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

                        //BackStackを設定
                        fragmentTransaction.addToBackStack(BackStack.CHARACTER_LIST.name)

                        fragmentTransaction.replace(
                            R.id.attach_screen,
                            ConfirmCharacterStatusFragment()
                        )
                        //ヘッダー情報更新
                        headerViewModel.headerText.postValue(getString(R.string.details_character))
                        headerViewModel.outputFlag = HeaderFlag.DEFAULT
                        fragmentTransaction.commit()
                    }
                })
                //キャラクター作成画面へ遷移
                btnCharacterNewCreate.setOnClickListener {
                    setCharacterViewModel.clearInputData()
                    //ヘッダー情報更新
                    headerViewModel.headerText.postValue(getString(R.string.create_character))
                    headerViewModel.outputFlag = HeaderFlag.DEFAULT

                    val fragmentManager: FragmentManager = parentFragmentManager
                    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

                    //BackStack
                    fragmentTransaction.addToBackStack(BackStack.CHARACTER_LIST.name)
                    fragmentTransaction.replace(
                        R.id.attach_screen,
                        NewCharacterGenerateFragment()
                    )
                    fragmentTransaction.commit()
                }
            })
        }
        return binding.root
    }
}