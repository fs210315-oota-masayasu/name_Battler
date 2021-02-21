package com.example.namebattler.presentation.adapter.party

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.database.characterDatabase.Characters
import com.example.namebattler.databinding.FormationListViewBinding
import com.example.namebattler.function.viewModel.OperationDatabaseViewModel
import com.example.namebattler.function.viewModel.PartyFormationViewModel

class FormationListAdapter(private val operationDatabaseViewModel: OperationDatabaseViewModel,
                           private val partyFormationViewModel: PartyFormationViewModel,
                           private val parentLifecycleOwner: LifecycleOwner
                           ) : RecyclerView.Adapter<FormationListAdapter.FormationListViewHolder>() {

    /** viewHolder **/
    class FormationListViewHolder(val binding: FormationListViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    //生成するリストのlayout指定
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormationListViewHolder {
        val binding = DataBindingUtil.inflate<FormationListViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.formation_list_view,
            parent,
            false
        )
        return FormationListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return operationDatabaseViewModel.allCharacters.value?.size ?: 0
    }
    //チェックカウントを格納する
    var noticeChangeCount = MutableLiveData<Int>()

    override fun onBindViewHolder(holder: FormationListViewHolder, position: Int) {
        val list = operationDatabaseViewModel.allCharacters.value ?: listOf()

        partyFormationViewModel.allCharacterList = list as MutableList<Characters>
        holder.binding.viewModel = operationDatabaseViewModel
        holder.binding.partyFormationViewModel = partyFormationViewModel
        holder.binding.position = position
        //LifecycleOwnerをViewHolderにセット
        holder.binding.lifecycleOwner = parentLifecycleOwner
    }
}