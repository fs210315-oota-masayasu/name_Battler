package com.example.namebattler.party

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.data.characterData.CharacterHolder

////BattleLobbyActivity：編成済パーティリスト
class PlayerListAdapter  internal constructor(
    context : Context
) : RecyclerView.Adapter<PlayerListAdapter.PartyListViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var character = mutableListOf<CharacterHolder>()


    inner class PartyListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val charaNameView : TextView = itemView.findViewById(R.id.value_name)
        val charaJobView : TextView = itemView.findViewById(R.id.value_job)
        val charaHpView : TextView = itemView.findViewById(R.id.value_status_hp)
        val charaMpView : TextView = itemView.findViewById(R.id.value_status_mp)
        val charaStrView : TextView = itemView.findViewById(R.id.value_status_str)
        val charaDefView : TextView = itemView.findViewById(R.id.value_status_def)
        val charaAgiView : TextView = itemView.findViewById(R.id.value_status_agi)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyListViewHolder {
        val partyListView = inflater.inflate(R.layout.list_view , parent , false)

        return PartyListViewHolder(partyListView)
    }

    override fun onBindViewHolder(holder: PartyListViewHolder, position: Int) {
        val current = character[position]
        holder.charaNameView.text = current.name


        holder.charaJobView.text = current.job
        holder.charaHpView.text = current.hp.toString()
        holder.charaMpView.text = current.mp.toString()
        holder.charaStrView.text = current.str.toString()
        holder.charaDefView.text = current.def.toString()
        holder.charaAgiView.text = current.agi.toString()
    }

    internal fun setCharacter(character : ArrayList <CharacterHolder>){
        this.character = character
        notifyDataSetChanged()
    }

    override fun getItemCount() = character.size
}