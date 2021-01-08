package com.example.namebattler.characters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.R
import com.example.namebattler.data.characterData.CharacterHolder
import com.example.namebattler.databinding.ListViewBinding
import com.example.namebattler.viewModel.CharacterViewModel
import com.example.namebattler.viewModel.OperationDatabaseViewModel

//キャラクター一覧画面
class CharaListAdapter (private val viewModel: OperationDatabaseViewModel,
                        private val characterViewModel: CharacterViewModel,
                        private val parentLifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<CharaListAdapter.CharaListViewHolder>() {

    /** Viewのクリックイベント **/
    lateinit var listener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClickListener(view: View, position: Int, sendToData: CharacterHolder?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    /** viewHolder **/
    class CharaListViewHolder(val binding: ListViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CharaListViewHolder {

        val binding = DataBindingUtil.inflate<ListViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_view,
            parent,
            false
        )
        return CharaListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return viewModel.allCharacters.value?.size ?:0
    }

    override fun onBindViewHolder(holder: CharaListViewHolder, position: Int){

        val list = viewModel.allCharacters.value?: listOf()

        holder.binding.viewModel = viewModel
        holder.binding.characterViewModel = characterViewModel

        holder.binding.position = position

        //LifecycleOwnerをViewHolderにセット
        holder.binding.lifecycleOwner = parentLifecycleOwner


        /** クリックすると画面遷移 **/
        holder.itemView.setOnClickListener{

            val sendToData = list[position]
            this.listener.onItemClickListener(it, position, characterViewModel.onrClickListData(sendToData))
        }
    }
}
