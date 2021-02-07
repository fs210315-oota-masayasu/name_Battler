package com.example.namebattler.party

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.databinding.PartyListViewBinding
import com.example.namebattler.viewModel.PartyFormationViewModel


/** 編成済パーティリスト  **/
class PlayerListAdapter(private val partyFormationViewModel: PartyFormationViewModel,
                        private val parentLifecycleOwner: LifecycleOwner
                        ) : RecyclerView.Adapter<PlayerListAdapter.PartyListViewHolder>() {

    /** viewHolder **/
    class PartyListViewHolder(val binding : PartyListViewBinding) : RecyclerView.ViewHolder(binding.root)

    //生成するリストのlayout指定
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyListViewHolder {
        val binding = DataBindingUtil.inflate<PartyListViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.party_list_view,
            parent,
            false
        )
        return PartyListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PartyListViewHolder, position: Int) {
//        val current = character[position]
        holder.apply {
            binding.partyFormationViewModel = partyFormationViewModel
            binding.position = position

            binding.lifecycleOwner = parentLifecycleOwner
        }
    }
    override fun getItemCount() = partyFormationViewModel.selectionCharacterList.value?.size?:0
}