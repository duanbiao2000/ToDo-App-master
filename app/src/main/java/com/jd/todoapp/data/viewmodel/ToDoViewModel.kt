package com.jd.todoapp.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.jd.todoapp.data.ToDoDatabase
import com.jd.todoapp.data.models.ToDoData
import com.jd.todoapp.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 用于管理待办事项数据操作的 ViewModel。
 * 继承自 AndroidViewModel，以便访问应用程序上下文。
 *
 * @param application 应用程序上下文，用于访问数据库。
 */
class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    // 待办事项数据库的数据访问对象。
    private val toDoDao = ToDoDatabase.getDataBase(
        application
    ).toDoDao()
    // 用于处理数据操作的仓库实例。
    private val repository: ToDoRepository

    // 用于观察所有待办事项列表的 LiveData。
    val getAllData: LiveData<List<ToDoData>>
    // 用于观察按高优先级排序的待办事项列表的 LiveData。
    val sortByHighPriority: LiveData<List<ToDoData>>
    // 用于观察按低优先级排序的待办事项列表的 LiveData。
    val sortByLowPriority: LiveData<List<ToDoData>>

    init {
        // 使用待办事项 DAO 初始化仓库。
        repository = ToDoRepository(toDoDao)
        // 初始化用于观察数据变化的 LiveData。
        getAllData = repository.getAllData
        sortByHighPriority = repository.sortByHighPriority
        sortByLowPriority = repository.sortByLowPriority
    }

    /**
     * 将一条待办事项插入数据库。
     *
     * @param toDoData 要插入的待办事项数据。
     */
    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }

    /**
     * 更新数据库中的待办事项。
     *
     * @param toDoData 要更新的待办事项数据。
     */
    fun updateData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(toDoData)
        }
    }

    /**
     * 从数据库中删除一条待办事项。
     *
     * @param toDoData 要删除的待办事项数据。
     */
    fun deleteItem(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(toDoData)
        }
    }

    /**
     * 从数据库中删除所有待办事项。
     */
    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    /**
     * 根据查询字符串在数据库中搜索待办事项。
     *
     * @param searchQuery 搜索查询字符串。
     * @return 用于观察搜索结果的 LiveData。
     */
    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>> {
        return repository.searchDatabase(searchQuery)
    }
}