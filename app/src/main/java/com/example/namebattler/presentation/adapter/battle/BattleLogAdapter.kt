package com.example.namebattler.presentation.adapter.battle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.databinding.LogListViewBinding
import com.example.namebattler.function.viewModel.BattleViewModel

class BattleLogAdapter(private val battleViewModel: BattleViewModel,
                       private val parentLifecycleOwner: LifecycleOwner):
    RecyclerView.Adapter<BattleLogAdapter.BattleLogViewHolder>(){
    private var battleLog = mutableListOf <String>()
    var position :Int? = null

    /** viewHolder **/
    class BattleLogViewHolder(val binding: LogListViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BattleLogViewHolder {
        val binding = DataBindingUtil.inflate<LogListViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.log_list_view,
            parent, false
        )
        return  BattleLogViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return battleViewModel.battleLogData.value?.size?:0
//        return position?:this.battleLog.size
    }

    override fun onBindViewHolder(holder: BattleLogViewHolder, position: Int) {

        holder.binding.lifecycleOwner = parentLifecycleOwner
        val list = battleViewModel.battleLogData.value?: mutableListOf()
        val log = list[position]

//        val log = battleLog[position]

        holder.binding.txtLog.text = log

    }

//    internal fun setBattleLog(battleLog :MutableList <String>){
//        this.battleLog = battleLog
//        notifyDataSetChanged()
//    }

//    internal fun addBattleLog(battleLog:String){
//
//        this.battleLog.add(battleLog)
//
//        position = this.battleLog.size
//
//        notifyItemInserted(position ?:this.battleLog.size)
//    }

}