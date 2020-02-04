package com.example.namebattler


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CharaListAdapter(private val data: IntArray): RecyclerView.Adapter<CharaListViewHolder>()
{
    /** 表示用データの要素数（ここでは IntArray のサイズ） */
    //override fun getItemCount(): Int = data.size
    override fun getItemCount(): Int = 8

    /** 新しく ViewHolder オブジェクトを生成するための実装 */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharaListViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        return CharaListViewHolder(inflater.inflate(R.layout.list_view, parent, false))



    }

    /** position の位置のデータを使って、表示内容を適切に設定（更新）する */
    override fun onBindViewHolder(holder: CharaListViewHolder, position: Int) {
        val num = data[position]
        val strings = arrayOf("ナイト", "アーチャー", "クレリック","ナイト2", "アーチャー2", "クレリック2","ナイト3", "アーチャー3", "クレリック3")
        //holder.label?.text = "Element-$num"
        holder.jobName?.text = strings[position]
        holder.button?.text = "Button-$num"






    }
}