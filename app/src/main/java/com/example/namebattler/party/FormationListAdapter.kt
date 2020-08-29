package com.example.namebattler.party

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.jobData.JobManager
import com.example.namebattler.util.Belong

class FormationListAdapter  internal constructor(
    context: Context
) : RecyclerView.Adapter<FormationListAdapter.FormationListViewHolder>() {

    private lateinit var listener: OnItemClickListener

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var character = emptyList<Characters>() // Cached copy of character

    private var checkedCharacter : CharacterHolder? = null
    var list  = arrayListOf <CharacterHolder?>()

    //チェックカウント
    private var cnt = 0
    //チェックカウントを格納する
    var noticeChangeCount = MutableLiveData<Int>()

    inner class FormationListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val charaNameView : TextView = itemView.findViewById(R.id.value_name)
        val charaJobView : TextView = itemView.findViewById(R.id.value_job)
        val charaHpView : TextView = itemView.findViewById(R.id.value_status_hp)
        val charaMpView : TextView = itemView.findViewById(R.id.value_status_mp)
        val charaStrView : TextView = itemView.findViewById(R.id.value_status_str)
        val charaDefView : TextView = itemView.findViewById(R.id.value_status_def)
        val charaAgiView : TextView = itemView.findViewById(R.id.value_status_agi)

    }
    interface OnItemClickListener{
        fun onItemClickListener(view: View, position: Int, sendToData: CharacterHolder?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : FormationListViewHolder {
        val partyListView = inflater.inflate(R.layout.formation_list_view, parent, false)


        return FormationListViewHolder(partyListView)
    }

    override fun onBindViewHolder(holder: FormationListViewHolder, position: Int){
        val current = character[position]
        holder.charaNameView.text = current.NAME
        holder.charaJobView.text = JobManager()
            .getJobList(current.JOB) // インデックスから名前を取得
        holder.charaHpView.text = current.HP.toString()
        holder.charaMpView.text = current.MP.toString()
        holder.charaStrView.text = current.STR.toString()
        holder.charaDefView.text = current.DEF.toString()
        holder.charaAgiView.text = current.AGI.toString()
        val checkBox : CheckBox = holder.itemView.findViewById(R.id.checkBox_list)

        //初期化
        checkBox.isChecked = false

        // チェックボックス以外をタップした際にチェックがついたり外れたりする処理
        holder.itemView.setOnClickListener{

            //チェック：ON  → チェック：OFF Listをremove
            //チェック：OFF → チェック：ON Listへadd
            if(checkBox.isChecked){
                //チェックを外す
                checkBox.isChecked = false

                checkedCharacter =
                    CharacterHolder(
                        Belong.PLAYER.name,
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
                list.remove(checkedCharacter)

                //チェックカウントを-1
                cnt -= 1
            }else if (!checkBox.isChecked){

                //チェックをつける
                checkBox.isChecked = true

                checkedCharacter =
                    CharacterHolder(
                        Belong.PLAYER.name,
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

                //チェックカウントを+1
                cnt += 1
                list.add(checkedCharacter)

            }
            //チェックカウントをLiveDataに格納してActivityで受け取る
            noticeChangeCount.postValue(cnt)
        }

        checkBox.setOnClickListener{
            if(checkBox.isChecked){

                checkedCharacter =
                    CharacterHolder(
                        Belong.PLAYER.name,
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
                //チェックカウントを+1
                cnt += 1

                list.add(checkedCharacter)

            }else if (!checkBox.isChecked){

                checkedCharacter =
                    CharacterHolder(
                        Belong.PLAYER.name,
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

                list.remove(checkedCharacter)

                //チェックカウントを-1
                cnt -= 1

            }
            //チェックカウントをLiveDataに格納してActivityで受け取る
            noticeChangeCount.postValue(cnt)
        }
    }

    internal fun setCharacter(character: List<Characters>){
        this.character = character
        notifyDataSetChanged()
    }

    override fun getItemCount() = character.size

}