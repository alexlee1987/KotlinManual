package com.alexlee1987.kotlinmanual.recyclerview

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alexlee1987.kotlinmanual.R

class CustomAdapter(private val dateSet: Array<String>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view.
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(v)
    }

    override fun getItemCount() = dateSet.size // kotlin写法
//    override fun getItemCount(): Int { // 标准写法
//        return dateSet.size
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dateSet[position]
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        val layout = recyclerView.layoutManager
        if (layout != null && layout is GridLayoutManager) {
            layout.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                // 动态指定网格布局吗每一行的item数量
                override fun getSpanSize(position: Int): Int {
                    return if (position % 4 == 0) {
                        layout.spanCount
                    } else 1
                }
            }
        }
    }

    companion object {
        private val TAG = "CustomAdapter"
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener { Log.d(TAG, "Element $adapterPosition clicked.") }
            textView = v.findViewById(R.id.textView)
        }
    }
}