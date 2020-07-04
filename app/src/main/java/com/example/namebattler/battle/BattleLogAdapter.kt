package com.example.namebattler.battle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R

class BattleLogAdapter internal constructor(
    context : Context
) : RecyclerView.Adapter<BattleLogAdapter.BattleLogViewHolder>()
{

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var battleLog = mutableListOf <String>()
    var pos :Int? = null

    inner class BattleLogViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val logView : TextView = itemView.findViewById(R.id.txt_log)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BattleLogViewHolder {
        val battleLogView = inflater.inflate(R.layout.log_list_view, parent, false)

        return BattleLogViewHolder(battleLogView)
    }

    override fun getItemCount(): Int {
         return pos?:this.battleLog.size
    }

    override fun onBindViewHolder(holder: BattleLogViewHolder, position: Int) {
        val log = battleLog[position]
        holder.logView.text = log
    }

    internal fun setBattleLog(battleLog :MutableList <String>){
        this.battleLog = battleLog
        notifyDataSetChanged()
    }

    internal fun addBattleLog(battleLog:String){

        this.battleLog.add(battleLog)

        pos = this.battleLog.size

        notifyItemInserted(pos ?:this.battleLog.size)
    }

}