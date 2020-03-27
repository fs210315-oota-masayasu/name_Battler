package com.example.namebattler.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.data.Characters

class CharaListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CharaListAdapter.CharaListViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var character = emptyList<Characters>() // Cached copy of character

    inner class CharaListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val charaNameView : TextView = itemView.findViewById(R.id.value_name)
        val charaAgiView :  TextView = itemView.findViewById(R.id.value_status_agi)
        val charaJobView : TextView = itemView.findViewById(R.id.value_job)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CharaListViewHolder {
        val nameView = inflater.inflate(R.layout.list_view, parent, false)
        return CharaListViewHolder(nameView)
    }

    override fun onBindViewHolder(holder: CharaListViewHolder, position: Int){
        val current = character[position]
        holder.charaNameView.text = current.NAME
        holder.charaAgiView.text = current.AGI.toString()
    }

    internal fun setCharacter(character: List<Characters>){
        this.character = character
        notifyDataSetChanged()
    }

    override fun getItemCount() = character.size
}


/*
class CharaListAdapter(private val data: IntArray): RecyclerView.Adapter<CharaListViewHolder>()
{
    */
/** 表示用データの要素数（ここでは IntArray のサイズ） *//*

    //override fun getItemCount(): Int = data.size
    override fun getItemCount(): Int = 8

    */
/** 新しく ViewHolder オブジェクトを生成するための実装 *//*

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharaListViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        return CharaListViewHolder(
            inflater.inflate(
                R.layout.list_view,
                parent,
                false
            )
        )



    }

    */
/** position の位置のデータを使って、表示内容を適切に設定（更新）する *//*

    override fun onBindViewHolder(holder: CharaListViewHolder, position: Int) {
        val num = data[position]
        val strings = arrayOf("ナイト", "アーチャー", "クレリック","ナイト2", "アーチャー2", "クレリック2","ナイト3", "アーチャー3", "クレリック3")
        //holder.label?.text = "Element-$num"
        holder.jobName?.text = strings[position]
        holder.button?.text = "Button-$num"

    }
}
*/

