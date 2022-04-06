package com.kdh.diarydodo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kdh.diarydodo.data.DiaryInfo
import com.kdh.diarydodo.databinding.DairyListListadapterBinding


class DiaryListAdapter(private val diaryInfo: ArrayList<DiaryInfo>) :
    ListAdapter<DiaryInfo, DiaryListAdapter.DiaryListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryListViewHolder {
        val binding =
            DairyListListadapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //DiaryListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dairy_list_listadapter, parent, false))
        return DiaryListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DiaryListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DiaryListViewHolder(
        private val binding: DairyListListadapterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(diaryInfo: DiaryInfo) {
            with(binding) {
                textViewName.text = diaryInfo.id
                textViewBody.text = diaryInfo.name
                //     Glide.with(imageViewAvatar.context)
//                    .load(user.avatar)
//                    .into(imageViewAvatar)
            }
        }
    }


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<DiaryInfo>() {
            override fun areItemsTheSame(oldItem: DiaryInfo, newItem: DiaryInfo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: DiaryInfo, newItem: DiaryInfo): Boolean =
                oldItem == newItem
        }
    }


}