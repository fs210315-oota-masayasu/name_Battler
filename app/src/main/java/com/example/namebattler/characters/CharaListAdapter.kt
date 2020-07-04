package com.example.namebattler.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.jobData.JobManager

//キャラクター一覧画面
class CharaListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CharaListAdapter.CharaListViewHolder>() {

    lateinit var listener: OnItemClickListener

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var character = emptyList<Characters>() // Cached copy of character

    private var sendToComplete : CharacterHolder? = null
    private var list  = mutableListOf <CharacterHolder?>()

    inner class CharaListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val charaNameView : TextView = itemView.findViewById(R.id.value_name)
        val charaJobView : TextView = itemView.findViewById(R.id.value_job)
        val charaHpView : TextView = itemView.findViewById(R.id.value_status_hp)
        val charaMpView : TextView = itemView.findViewById(R.id.value_status_mp)
        val charaStrView : TextView = itemView.findViewById(R.id.value_status_str)
        val charaDefView : TextView = itemView.findViewById(R.id.value_status_def)
        val charaAgiView :  TextView = itemView.findViewById(R.id.value_status_agi)
    }
    interface OnItemClickListener{
        fun onItemClickListener(view: View, position: Int, sendToData: CharacterHolder?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CharaListViewHolder {
        val characterListView = inflater.inflate(R.layout.list_view, parent, false)


        return CharaListViewHolder(characterListView)
    }

    override fun onBindViewHolder(holder: CharaListViewHolder, position: Int){
        val current = character[position]
        holder.charaNameView.text = current.NAME
        holder.charaJobView.text = JobManager()
            .getJobList(current.JOB) // インデックスから名前を取得
        holder.charaHpView.text = current.HP.toString()
        holder.charaMpView.text = current.MP.toString()
        holder.charaStrView.text = current.STR.toString()
        holder.charaDefView.text = current.DEF.toString()
        holder.charaAgiView.text = current.AGI.toString()

        sendToComplete =
            CharacterHolder(
                current.NAME,
                JobManager().getJobList(current.JOB)
                ,
                current.HP,
                current.MP,
                current.STR,
                current.DEF,
                current.AGI,
                current.LUCK,
                current.CREATE_AT
            )

        list.add(sendToComplete)


        //TODO クリックすると画面遷移
        holder.itemView.setOnClickListener{

            val sendToData = list[position]
            this.listener.onItemClickListener(it, position, sendToData)
        }
    }
    internal fun setCharacter(character: List<Characters>){
        this.character = character
        notifyDataSetChanged()
    }

    override fun getItemCount() = character.size


}


