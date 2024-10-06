package com.jd.todoapp.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jd.todoapp.data.models.ToDoData
import com.jd.todoapp.databinding.RowLayoutBinding

/**
 * ListAdapter 类用于处理 RecyclerView 的数据绑定和更新。
 * 它继承自 RecyclerView.Adapter，并使用 ToDoData 类作为其数据类型。
 */
class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    // 定义适配器的数据源
    var dataList = emptyList<ToDoData>()

    /**
     * MyViewHolder 类持有视图并管理与之关联的数据。
     * 它内部使用 DataBinding 来简化数据的绑定操作。
     */
    class MyViewHolder(private val binding: RowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * 将 ToDoData 对象绑定到视图。
         * @param toDoData 要绑定的数据对象
         */
        fun bind(toDoData: ToDoData) {
            binding.toDoData = toDoData
            binding.executePendingBindings()
        }

        /**
         * 创建并返回一个新的 MyViewHolder 实例。
         * @param parent 父 ViewGroup，用于 inflate 布局
         * @return 新的 MyViewHolder 实例
         */
        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(
                    binding
                )
            }
        }
    }

    /**
     * 创建并返回 ViewHolder。这是 RecyclerView 需要覆盖的三个方法之一。
     * @param parent 父 ViewGroup，用于 inflate 布局
     * @param viewType 视图类型，此处未使用
     * @return 新创建的 ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(
            parent
        )
    }

    /**
     * 返回数据集的大小。这是 RecyclerView 需要覆盖的三个方法之一。
     * @return 数据集的大小
     */
    override fun getItemCount(): Int {
        return dataList.size
    }

    /**
     * 将数据项绑定到指定位置的 ViewHolder。这是 RecyclerView 需要覆盖的三个方法之一。
     * @param holder ViewHolder 实例
     * @param position ViewHolder 在 RecyclerView 中的位置
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    /**
     * 更新适配器的数据集，并使用 DiffUtil 计算和提交变更。
     * @param toDoData 新的数据集
     */
    fun setData(toDoData: List<ToDoData>) {
        val toDoDiffUtil = ToDoDiffUtil(dataList, toDoData)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.dataList = toDoData
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}