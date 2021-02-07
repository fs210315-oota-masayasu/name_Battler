package com.example.namebattler.party

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.characters.CharaListAdapter
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.jobData.JobManager
import com.example.namebattler.databinding.EnemyListViewBinding
import com.example.namebattler.databinding.ListViewBinding
import com.example.namebattler.util.Belong
import com.example.namebattler.viewModel.EnemyViewModel


//BattleLobbyActivity：敵パーティリスト
class EnemyListAdapter(private val enemyViewModel : EnemyViewModel,
                       private val parentLifecycleOwner: LifecycleOwner
                       ) : RecyclerView.Adapter<EnemyListAdapter.EnemyListViewHolder>(){

//    private var character = emptyList<Characters>() // Cached copy of character

    private var sendToComplete : CharacterHolder = CharacterHolder()
//    var list  = arrayListOf<CharacterHolder?>()


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

        val list = enemyViewModel.enemyFormation.value ?: listOf()

        holder.apply {
            binding.enemyViewModel = enemyViewModel
            binding.position = position

            //LifecycleOwnerをViewHolderにセット
            binding.lifecycleOwner = parentLifecycleOwner
        }
    }



/*    internal fun setCharacter(character: List<Characters>){
        this.character = character
        notifyDataSetChanged()
    }*/

    override fun getItemCount() = enemyViewModel.enemyFormation.value?.size?: 0

}


/*
//BattleLobbyActivity：敵パーティリスト
class EnemyListAdapter internal constructor(context : Context
) : RecyclerView.Adapter<EnemyListAdapter.EnemyListViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var character = emptyList<Characters>() // Cached copy of character

    private var sendToComplete : CharacterHolder = CharacterHolder()
    var list  = arrayListOf<CharacterHolder?>()

//    inner class EnemyListViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer

    inner class EnemyListViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView)


//    inner class EnemyListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
//        val charaNameView : TextView = itemView.findViewById(R.id.value_name)
//        val charaJobView : TextView = itemView.findViewById(R.id.value_job)
//        val charaHpView : TextView = itemView.findViewById(R.id.value_status_hp)
//        val charaMpView : TextView = itemView.findViewById(R.id.value_status_mp)
//        val charaStrView : TextView = itemView.findViewById(R.id.value_status_str)
//        val charaDefView : TextView = itemView.findViewById(R.id.value_status_def)
//        val charaAgiView : TextView = itemView.findViewById(R.id.value_status_agi)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnemyListViewHolder {
        val enemyListView = inflater.inflate(R.layout.list_view , parent , false)

        return EnemyListViewHolder(enemyListView)
    }

    override fun onBindViewHolder(holder: EnemyListViewHolder, position: Int) {
        val current = character[position]

        holder.itemView.findViewById<TextView>(R.id.value_name).text = current.NAME
        //職業ごとに割り振っているIDから名前を取得
        holder.itemView.findViewById<TextView>(R.id.value_job).text = JobManager().getJobList(current.JOB)
        holder.itemView.findViewById<TextView>(R.id.value_status_hp).text = current.HP.toString()
        holder.itemView.findViewById<TextView>(R.id.value_status_mp).text = current.MP.toString()
        holder.itemView.findViewById<TextView>(R.id.value_status_str).text = current.STR.toString()
        holder.itemView.findViewById<TextView>(R.id.value_status_def).text = current.DEF.toString()
        holder.itemView.findViewById<TextView>(R.id.value_status_agi).text = current.AGI.toString()



*/
/*
        holder.itemView.value_name.text = current.NAME
        //職業ごとに割り振っているIDから名前を取得
        holder.itemView.value_job.text = JobManager().getJobList(current.JOB)
        holder.itemView.value_status_hp.text = current.HP.toString()
        holder.itemView.value_status_mp.text = current.MP.toString()
        holder.itemView.value_status_str.text = current.STR.toString()
        holder.itemView.value_status_def.text = current.DEF.toString()
        holder.itemView.value_status_agi.text = current.AGI.toString()
*//*



        sendToComplete =
            CharacterHolder(
                Belong.ENEMY.name,
                current.NAME,
                JobManager().getJobList(current.JOB),
                current.HP,
                current.MP,
                current.STR,
                current.DEF,
                current.AGI,
                current.LUCK,
                current.CREATE_AT
            )
        list.add(sendToComplete)
    }

    internal fun setCharacter(character: List<Characters>){
        this.character = character
        notifyDataSetChanged()
    }

    override fun getItemCount() = character.size

}
*/
