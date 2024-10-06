package com.jd.todoapp.fragments

import android.view.View
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jd.todoapp.R
import com.jd.todoapp.data.models.Priority
import com.jd.todoapp.data.models.ToDoData
import com.jd.todoapp.fragments.list.ListFragmentDirections

/**
 * BindingAdapters 类用于处理数据绑定中的自定义属性适配器
 * 这些适配器将数据绑定表达式的结果转换为适当的操作或UI更新
 */
class BindingAdapters {

    companion object {
        /**
         * 处理导航到 AddFragment 的属性适配器
         * 当 navigate 参数为 true 时，点击 FloatingActionButton 将导航到 AddFragment
         *
         * @param view FloatingActionButton 实例，用于点击导航
         * @param navigate Boolean 值，决定是否启用导航
         */
        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if (navigate) {
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }

        /**
         * 根据数据库是否为空来控制视图的可见性
         * 当 emptyDatabase MutableLiveData 的值为 true 时，视图可见；否则，视图不可见
         *
         * @param view 需要控制可见性的 View 实例
         * @param emptyDatabase MutableLiveData<Boolean> 类型的数据，表示数据库是否为空
         */
        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>) {
            when (emptyDatabase.value) {
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
            }
        }

        /**
         * 根据 Priority 将 Spinner 的选中项设置为高、中、低之一
         *
         * @param view Spinner 实例，用于显示优先级选项
         * @param priority Priority 枚举值，表示当前项的优先级
         */
        @BindingAdapter("android:parsePriorityToInt")
        @JvmStatic
        fun parsePriorityToInt(view: Spinner, priority: Priority) {
            when (priority) {
                Priority.HIGH -> view.setSelection(0)
                Priority.MEDIUM -> view.setSelection(1)
                Priority.LOW -> view.setSelection(2)
            }
        }

        /**
         * 根据 Priority 为 CardView 设置相应的背景颜色
         *
         * @param cardView CardView 实例，用于显示卡片
         * @param priority Priority 枚举值，决定卡片的背景颜色
         */
        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(cardView: CardView, priority: Priority) {
            when (priority) {
                Priority.HIGH -> cardView.setBackgroundColor(cardView.context.getColor(R.color.red))
                Priority.MEDIUM -> cardView.setBackgroundColor(cardView.context.getColor(R.color.yellow))
                Priority.LOW -> cardView.setBackgroundColor(cardView.context.getColor(R.color.green))
            }
        }

        /**
         * 处理导航到 UpdateFragment 的属性适配器
         * 点击 ConstraintLayout 时，根据传入的 ToDoData 对象导航到 UpdateFragment
         *
         * @param view ConstraintLayout 实例，用于点击导航
         * @param currentItem ToDoData 对象，表示当前项的数据
         */
        @BindingAdapter("android:navigateToUpdateFragment")
        @JvmStatic
        fun navigateToUpdateFragment(view: ConstraintLayout, currentItem: ToDoData) {
            view.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                view.findNavController().navigate(action)
            }
        }
    }
}