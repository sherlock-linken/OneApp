package com.witbit.sherlock.single_picker


// WheelPickerAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.witbit.sherlock.oneapp.R

class SingleWheelPickerAdapter(
    private var items: List<String>,
    private var textSize: Float,
    private var itemHeight: Float,
    private var isCircular: Boolean
) : RecyclerView.Adapter<SingleWheelPickerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tv_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wheel_picker, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item
        holder.textView.textSize = textSize

        // 设置项目高度
        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = itemHeight.toInt()
        holder.itemView.layoutParams = layoutParams
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<String>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun setTextSize(size: Float) {
        textSize = size
        notifyDataSetChanged()
    }

    fun setItemHeight(height: Float) {
        itemHeight = height
        notifyDataSetChanged()
    }
}
