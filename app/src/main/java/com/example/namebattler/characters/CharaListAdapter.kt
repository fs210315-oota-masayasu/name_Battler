package com.example.namebattler.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.data.Characters
import com.example.namebattler.data.JobList

class CharaListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CharaListAdapter.CharaListViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var character = emptyList<Characters>() // Cached copy of character

    inner class CharaListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val charaNameView : TextView = itemView.findViewById(R.id.value_name)
        val charaJobView : TextView = itemView.findViewById(R.id.value_job)
        val charaHpView : TextView = itemView.findViewById(R.id.value_status_hp)
        val charaMpView : TextView = itemView.findViewById(R.id.value_status_mp)
        val charaStrView : TextView = itemView.findViewById(R.id.value_status_str)
        val charaDefView : TextView = itemView.findViewById(R.id.value_status_def)
        val charaAgiView :  TextView = itemView.findViewById(R.id.value_status_agi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CharaListViewHolder {
        val characterListView = inflater.inflate(R.layout.list_view, parent, false)
        return CharaListViewHolder(characterListView)
    }

    override fun onBindViewHolder(holder: CharaListViewHolder, position: Int){
        val current = character[position]
        holder.charaNameView.text = current.NAME
        holder.charaJobView.text = JobList().getJobList(current.JOB) //インデックスから名前を取得
        holder.charaHpView.text = current.HP.toString()
        holder.charaMpView.text = current.MP.toString()
        holder.charaStrView.text = current.STR.toString()
        holder.charaDefView.text = current.DEF.toString()
        holder.charaAgiView.text = current.AGI.toString()
    }

    internal fun setCharacter(character: List<Characters>){
        this.character = character
        notifyDataSetChanged()
    }

    override fun getItemCount() = character.size
}