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
import java.util.*


class DiaryListAdapter(context: Context) :
    ListAdapter<DiaryEntity, DiaryListAdapter.DiaryListViewHolder>(diffCallback) {
    private val listAdapterContext = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryListViewHolder {
        Log.d("dodo2", "onCreateViewHolder")
        val binding =
            DairyListListadapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //DiaryListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dairy_list_listadapter, parent, false))
        return DiaryListViewHolder(binding, listAdapterContext)
    }


    override fun onBindViewHolder(holder: DiaryListViewHolder, position: Int) {
        Log.d("dodo2", "onBindViewHolder")
        holder.bind(getItem(position))
    }

    inner class DiaryListViewHolder(
        private val binding: DairyListListadapterBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(diaryInfo: DiaryEntity) {
            with(binding) {
                Log.d("dodo2", diaryInfo.id.toString())
                Log.d("dodo2", diaryInfo.memo)
                textViewName.text = diaryInfo.id.toString()
                textViewBody.text = diaryInfo.memo
                textViewDate.text = diaryInfo.date
                imageViewAvatar.setOnClickListener {
                    Toast.makeText(context, "아이템클릭!!", Toast.LENGTH_SHORT).show()
                }
                //     Glide.with(imageViewAvatar.context)
//                    .load(user.avatar)
//                    .into(imageViewAvatar)
            }
        }

        fun setAlpah(alpha: Float) {
            with(binding) {
                textViewName.alpha = alpha
                textViewBody.alpha = alpha
                textViewDate.alpha = alpha
            }
        }
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val newList = currentList.toMutableList()
        Collections.swap(newList, fromPosition, toPosition)
        submitList(newList)
    }

    fun removeItem(position: Int) {
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