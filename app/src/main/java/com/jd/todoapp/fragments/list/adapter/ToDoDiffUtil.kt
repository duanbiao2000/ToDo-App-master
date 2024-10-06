package com.jd.todoapp.fragments.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.jd.todoapp.data.models.ToDoData

/**
 * 使用DiffUtil回调来计算ToDoData列表之间的差异，并驱动RecyclerView的高效更新。
 */
class ToDoDiffUtil(
    private val oldList: List<ToDoData>,
    private val newList: List<ToDoData>
) : DiffUtil.Callback() {

    /**
     * 返回旧列表的大小。
     * @return 旧列表中的项数。
     */
    override fun getOldListSize(): Int {
        return oldList.size
    }

    /**
     * 返回新列表的大小。
     * @return 新列表中的项数。
     */
    override fun getNewListSize(): Int {
        return newList.size
    }

    /**
     * 检查旧列表和新列表中的项是否相同。
     * 该方法用于确定列表中的项是否是同一个项。
     * @param oldItemPosition 旧列表中项的位置。
     * @param newItemPosition 新列表中项的位置。
     * @return 如果项相同，则返回true；否则返回false。
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    /**
     * 检查旧列表和新列表中的项的内容是否相同。
     * 该方法用于确定列表中的项的内容是否相同，即使它们是同一个项。
     * @param oldItemPosition 旧列表中项的位置。
     * @param newItemPosition 新列表中项的位置。
     * @return 如果项的内容相同，则返回true；否则返回false。
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].description == newList[newItemPosition].description
                && oldList[oldItemPosition].priority == newList[newItemPosition].priority
    }
}