package com.kdh.diarydodo.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kdh.diarydodo.data.db.DiaryEntity
import com.kdh.diarydodo.databinding.DairyListListadapterBinding
import com.kdh.diarydodo.databinding.ItemCalendarListBinding
import java.util.*


class DiaryCalendarListAdapter(context: Context) :
    ListAdapter<DiaryEntity, DiaryCalendarListAdapter.DiaryListViewHolder>(diffCallback) {
    private val listAdapterContext = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryListViewHolder {
        Log.d("dodo2", "onCreateViewHolder")
        val binding = ItemCalendarListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //DiaryListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dairy_list_listadapter, parent, false))
        return DiaryListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DiaryListViewHolder, position: Int) {
        Log.d("dodo2", "onBindViewHolder")
        holder.bind(getItem(position))
    }

    inner class DiaryListViewHolder(
        private val binding: ItemCalendarListBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(diaryInfo: DiaryEntity) {
            with(binding) {
                Log.d("dodo2", diaryInfo.id.toString())
                Log.d("dodo2", diaryInfo.memo)
                tvDiaryDate.text= diaryInfo.date
                tvDiaryDescription.text = diaryInfo.memo
                tvDiaryLoaction.text = ""
            }
        }
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