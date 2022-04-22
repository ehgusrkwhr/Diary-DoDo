package com.kdh.diarydodo.helper

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.kdh.diarydodo.adapter.DiaryListAdapter

class ItemTouchHelperCallback(
    private val recyclerView: RecyclerView,
    private val context: Context
) :
    ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.LEFT
    ) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        (recyclerView.adapter as DiaryListAdapter).moveItem(
            viewHolder.adapterPosition,
            target.adapterPosition
        )
        return true
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(UP or DOWN, LEFT)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//        val dialog: AlertDialog.Builder = AlertDialog.Builder(context).apply {
//            setTitle("경고")
//            setMessage("정말 삭제하시겠습니까?")
//            setPositiveButton("예") { _, _ ->
//                Log.d("dodo55 "," 예 ")
//                (recyclerView.adapter as DiaryListAdapter).removeItem(viewHolder.layoutPosition)
//            }
//            setNegativeButton("아니요") { dl, _ ->
//                Log.d("dodo55 "," 아니요 ")
//                dl.cancel()
//            }
//            create()
//        }
//        dialog.show()
        (recyclerView.adapter as DiaryListAdapter).removeItem(viewHolder.layoutPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        when (actionState) {
            ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.ACTION_STATE_SWIPE -> {
                (viewHolder as DiaryListAdapter.DiaryListViewHolder).setAlpah(0.5f)
            }
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        (viewHolder as DiaryListAdapter.DiaryListViewHolder).setAlpah(1.5f)
    }

}