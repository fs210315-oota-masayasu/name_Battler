package com.example.namebattler.presentation.fragment.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.R
import com.example.namebattler.databinding.CharacterStatusDetailBinding
import com.example.namebattler.function.viewModel.CharacterViewModel
import com.example.namebattler.function.viewModel.OperationDatabaseViewModel
import com.example.namebattler.function.viewModel.getViewModelFactory

/** ステータス情報 **/
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

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setOperationDatabaseViewModel = ViewModelProvider(this).get(OperationDatabaseViewModel::class.java)
    }
}