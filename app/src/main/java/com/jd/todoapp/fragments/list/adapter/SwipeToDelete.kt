package com.jd.todoapp.fragments.list.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * 用于删除列表中项目的滑动删除助手类
 * 继承自ItemTouchHelper.SimpleCallback，仅支持向左滑动删除
 */
abstract class SwipeToDelete : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    /**
     * 重写onMove方法，禁止项目移动
     * 因为滑动删除不需要移动项目，所以这里直接返回false
     *
     * @param recyclerView RecyclerView实例，正在进行触摸操作
     * @param viewHolder 当前触摸的ViewHolder
     * @param target 目标ViewHolder，即滑动后的位置
     * @return 总是返回false，禁止项目移动
     */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }
}