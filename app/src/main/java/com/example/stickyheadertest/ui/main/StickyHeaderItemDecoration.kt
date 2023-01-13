package com.example.stickyheadertest.ui.main

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.example.stickyheadertest.databinding.ItemHeaderBinding
import java.lang.Integer.min

/**
 * Created by PS Wang on 2022/11/3
 */
class StickyHeaderItemDecoration(
    private val dataList: List<SectionData>,
    private val context: Context,
    private val headerMarginLeft: Int,
    private val headerMarginTop: Int,
    private val headerMarginRight: Int,
    private val headerMarginBottom: Int,
) :
    RecyclerView.ItemDecoration() {

    private val headerView: TextView by lazy {
        val binding = ItemHeaderBinding.inflate(LayoutInflater.from(context))

        binding.root.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        binding.root
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)

        if (dataList.isEmpty()) return

        var left = headerMarginLeft
        var right: Int = parent.width - headerMarginRight

        if (parent.clipToPadding) {

            left += parent.paddingLeft
            right -= parent.paddingRight

        }

        val childCount = parent.childCount


        // Find the second header's top in the screen
        var secondHeaderTop = parent.bottom
        val topTitle =
            dataList[parent.getChildAdapterPosition(parent.getChildAt(0))].sectionTitle
        run lit@{
            for (i in 1 until childCount) {
                if (topTitle != dataList[parent.getChildAdapterPosition(parent.getChildAt(i))].sectionTitle) {
                    secondHeaderTop = parent.getChildAt(i - 1).bottom
                    return@lit
                }
            }
        }

        measureAndLayoutHeaderView(topTitle, right, left)
        drawHeaderView(canvas,
            left.toFloat(),
            min(secondHeaderTop - headerView.measuredHeight - headerMarginTop - headerMarginBottom,
                0 + headerMarginTop + parent.paddingTop).toFloat())

        for (i in 1 until childCount) {

            val child = parent.getChildAt(i)
            val adapterPosition = parent.getChildAdapterPosition(child)

            if (adapterPosition == 0 || dataList[adapterPosition - 1].sectionTitle != dataList[adapterPosition].sectionTitle) {

                measureAndLayoutHeaderView(dataList[adapterPosition].sectionTitle, right, left)

                drawHeaderView(canvas,
                    left.toFloat(),
                    child.top.toFloat() - headerView.measuredHeight - child.marginTop - headerMarginBottom)
            }
        }
    }

    private fun measureAndLayoutHeaderView(
        title: String,
        right: Int,
        left: Int,
    ) {
        headerView.text = title
        headerView.measure(
            View.MeasureSpec.makeMeasureSpec(right - left, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.UNSPECIFIED
        )
        headerView.layout(
            left,
            0,
            right,
            headerView.measuredHeight
        )
    }

    private fun drawHeaderView(
        canvas: Canvas,
        left: Float,
        top: Float,
    ) {
        canvas.save()
        canvas.translate(left,
            top)
        headerView.draw(canvas)
        canvas.restore()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {

        val adapterPosition = parent.getChildAdapterPosition(view)

        var top = 0

        if (adapterPosition == 0 || dataList[adapterPosition - 1].sectionTitle != dataList[adapterPosition].sectionTitle) {

            headerView.text = dataList[adapterPosition].sectionTitle
            headerView.measure(
                View.MeasureSpec.makeMeasureSpec(parent.width - headerMarginRight - headerMarginLeft,
                    View.MeasureSpec.AT_MOST),
                View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED)
            )

            top = headerView.measuredHeight + headerMarginTop + headerMarginBottom
        }

        outRect.set(0, top, 0, 0)
    }
}