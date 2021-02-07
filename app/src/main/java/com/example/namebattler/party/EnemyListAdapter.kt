package com.example.namebattler.party

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.databinding.EnemyListViewBinding
import com.example.namebattler.viewModel.EnemyViewModel

//BattleLobbyActivity：敵パーティリスト
class EnemyListAdapter(private val enemyViewModel : EnemyViewModel,
                       private val parentLifecycleOwner: LifecycleOwner
                       ) : RecyclerView.Adapter<EnemyListAdapter.EnemyListViewHolder>(){

    /** viewHolder **/
    inner class EnemyListViewHolder(val binding: EnemyListViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnemyListViewHolder {
        val binding = DataBindingUtil.inflate<EnemyListViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.enemy_list_view,
            parent,
            false
        )
        return EnemyListViewHolder(binding)
    }
    override fun onBindViewHolder(holder: EnemyListViewHolder, position: Int) {
        holder.apply {
            binding.enemyViewModel = enemyViewModel
            binding.position = position

            //LifecycleOwnerをViewHolderにセット
            binding.lifecycleOwner = parentLifecycleOwner
        }
    }
    override fun getItemCount() = enemyViewModel.enemyFormation.value?.size?: 0
}
