package com.witbit.sherlock.single_picker

// WheelPicker.kt
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.*
import com.witbit.sherlock.oneapp.R
import androidx.core.content.withStyledAttributes

class SingleWheelPicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var items = mutableListOf<String>()
    private var textSize = 16f // SP
    private var itemHeight = 50f // DP
    private var isCircular = false
    private var selectedItemCallback: ((String, Int) -> Unit)? = null
    private var selectedPosition = 0
    private var centerItemDecorator: CenterItemDecorator? = null


    init {
        setupRecyclerView()
        setupAttributes(attrs, defStyleAttr)
    }

    private fun setupAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        context.withStyledAttributes(
            attrs,
            R.styleable.WheelPicker,
            defStyleAttr,
            0
        ) {

            textSize = getDimension(R.styleable.WheelPicker_wheelTextSize, 16f)
            itemHeight = getDimension(R.styleable.WheelPicker_wheelItemHeight, 50f)
            isCircular = getBoolean(R.styleable.WheelPicker_wheelCircular, false)

        }
    }

    private fun setupRecyclerView() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        // 使用 SnapHelper 实现吸附效果
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(this)

        // 设置滚动监听
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val centerView = snapHelper.findSnapView(layoutManager)
                    if (centerView != null) {
                        val position = getChildAdapterPosition(centerView)
                        if (position != RecyclerView.NO_POSITION && position != selectedPosition) {
                            selectedPosition = position
                            val selectedItem = items[position]
                            selectedItemCallback?.invoke(selectedItem, position)
                        }
                    }
                }
            }
        })

        // 添加装饰器
        centerItemDecorator = CenterItemDecorator()
        addItemDecoration(centerItemDecorator!!)
    }

    fun setItems(items: List<String>) {
        this.items.clear()
        this.items.addAll(items)
        adapter?.notifyDataSetChanged()
        updateDecorator()
    }

    fun setTextSize(sizeInSp: Float) {
        this.textSize = sizeInSp
        updateDecorator()
    }

    fun setItemHeight(heightInDp: Float) {
        this.itemHeight = heightInDp
        updateDecorator()
    }

    fun setCircular(circular: Boolean) {
        this.isCircular = circular
        updateDecorator()
    }

    fun setOnItemSelectedListener(callback: (String, Int) -> Unit) {
        this.selectedItemCallback = callback
    }

    fun getSelectedItem(): String {
        return if (items.isNotEmpty() && selectedPosition < items.size) {
            items[selectedPosition]
        } else ""
    }

    fun getSelectedPosition(): Int {
        return selectedPosition
    }

    fun setSelectedPosition(position: Int) {
        if (position in 0 until items.size) {
            selectedPosition = position
            smoothScrollToPosition(position)
        }
    }

    private fun updateDecorator() {
        centerItemDecorator?.apply {
            setTextSize(textSize)
            setItemHeight(itemHeight)
            setCircular(isCircular)
        }
        adapter?.notifyDataSetChanged()
    }
}
