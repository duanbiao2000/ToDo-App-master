package com.jd.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.jd.todoapp.data.ToDoDao
import com.jd.todoapp.data.models.ToDoData

/**
 * ToDoRepository 类负责处理 ToDo 数据的 CRUD 操作。
 * 它通过 LiveData 对象提供对数据库中 ToDoData 的访问和操作。
 */
class ToDoRepository(private val toDoDao: ToDoDao) {

    // LiveData 对象，用于获取所有 ToDoData 列表。
    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()
    
    // LiveData 对象，用于获取按高优先级排序的 ToDoData 列表。
    val sortByHighPriority: LiveData<List<ToDoData>> = toDoDao.sortByHighPriority()
    
    // LiveData 对象，用于获取按低优先级排序的 ToDoData 列表。
    val sortByLowPriority: LiveData<List<ToDoData>> = toDoDao.sortByLowPriority()

    /**
     * 插入 ToDoData 到数据库。
     * @param toDoData 要插入的 ToDoData 实体。
     */
    suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData)
    }

    /**
     * 更新数据库中的 ToDoData。
     * @param toDoData 要更新的 ToDoData 实体。
     */
    suspend fun updateData(toDoData: ToDoData) {
        toDoDao.updateData(toDoData)
    }

    /**
     * 从数据库中删除 ToDoData。
     * @param toDoData 要删除的 ToDoData 实体。
     */
    suspend fun deleteItem(toDoData: ToDoData) {
        toDoDao.deleteItem(toDoData)
    }

    /**
     * 删除数据库中的所有 ToDoData。
     */
    suspend fun deleteAll() {
        toDoDao.deleteAll()
    }

    /**
     * 搜索数据库中的 ToDoData。
     * @param searchQuery 搜索关键词。
     * @return 返回包含搜索结果的 LiveData 对象。
     */
    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>> {
        return toDoDao.searchDatabase(searchQuery)
    }
}