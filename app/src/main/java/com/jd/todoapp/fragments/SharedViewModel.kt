package com.jd.todoapp.fragments

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jd.todoapp.R
import com.jd.todoapp.data.models.Priority
import com.jd.todoapp.data.models.ToDoData

/**
 * SharedViewModel 类是用于在多个片段之间共享数据和逻辑的 ViewModel。
 * 它包含了在 ToDo 应用中操作任务数据所需的逻辑。
 */
class SharedViewModel(application: Application) : AndroidViewModel(application) {

    // LiveData 对象，用于通知 UI 数据库是否为空
    val empyDatabase: MutableLiveData<Boolean> = MutableLiveData(false)

    /**
     * 检查数据库是否为空，并通过 LiveData 通知 UI。
     *
     * @param toDoData 从数据库获取的任务数据列表
     */
    fun checkIfDatabaseEmpty(toDoData: List<ToDoData>) {
        empyDatabase.value = toDoData.isEmpty()
    }

    // Spinner 的事件监听器
    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {}

        /**
         * 当 Spinner 选项被选择时，根据优先级设置文本颜色。
         *
         * @param parent Spinner 的父视图
         * @param view 选中的视图
         * @param position 选中的项的位置
         * @param id 选中的项的 ID
         */
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int
            , id: Long
        ) {
            when (position) {
                0 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.red
                        )
                    )
                }
                1 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.yellow
                        )
                    )
                }
                2 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.green
                        )
                    )
                }
            }
        }
    }

    /**
     * 验证用户输入的数据是否有效。
     *
     * @param title 任务的标题
     * @param description 任务的描述
     * @return 如果数据有效返回 true，否则返回 false
     */
    fun verifyDataFromUser(title: String, description: String): Boolean {
        return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            false
        } else !(title.isEmpty() || description.isEmpty())
    }

    /**
     * 根据用户选择的优先级字符串解析出对应的 Priority 枚举。
     *
     * @param priority 用户选择的优先级字符串
     * @return 对应的 Priority 枚举
     */
    fun parsePriority(priority: String): Priority {
        return when (priority) {
            "High Priority" -> {
                Priority.HIGH
            }
            "Medium Priority" -> {
                Priority.MEDIUM
            }
            "Low Priority" -> {
                Priority.LOW
            }
            else -> Priority.LOW
        }
    }
}