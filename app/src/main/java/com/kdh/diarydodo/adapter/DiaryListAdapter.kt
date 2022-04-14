package com.kdh.diarydodo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kdh.diarydodo.data.DiaryInfo
import com.kdh.diarydodo.data.db.DiaryEntity
import com.kdh.diarydodo.databinding.DairyListListadapterBinding
import com.kdh.diarydodo.util.DateUtil
import java.util.*


class DiaryListAdapter :
    ListAdapter<DiaryEntity, DiaryListAdapter.DiaryListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryListViewHolder {
        Log.d("dodo2" , "onCreateViewHolder")
        val binding =
            DairyListListadapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //DiaryListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dairy_list_listadapter, parent, false))
        return DiaryListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DiaryListViewHolder, position: Int) {
        Log.d("dodo2" , "onBindViewHolder")
        holder.bind(getItem(position))
    }

    inner class DiaryListViewHolder(
        private val binding: DairyListListadapterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(diaryInfo: DiaryEntity) {
            with(binding) {
                Log.d("dodo2" , diaryInfo.id.toString())
                Log.d("dodo2" , diaryInfo.memo)
                textViewName.text = diaryInfo.id.toString()
                textViewBody.text = diaryInfo.memo
                textViewDate.text = diaryInfo.date
                //     Glide.with(imageViewAvatar.context)
//                    .load(user.avatar)
//                    .into(imageViewAvatar)
            }
        }

        fun setAlpah(alpha : Float){
            with(binding){
                textViewName.alpha = alpha
                textViewBody.alpha = alpha
                textViewDate.alpha = alpha
            }
        }
    }

    fun moveItem(fromPosition :Int,toPosition : Int){
        val newList = currentList.toMutableList()
        Collections.swap(newList,fromPosition,toPosition)
        submitList(newList)
    }

    fun removeItem(position : Int){
        val newList = currentList.toMutableList()
        newList.removeAt(position)
        submitList(newList)
    }


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<DiaryEntity>() {

            override fun areItemsTheSame(oldItem: DiaryEntity, newItem: DiaryEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: DiaryEntity, newItem: DiaryEntity): Boolean =
                oldItem == newItem
        }
    }


}