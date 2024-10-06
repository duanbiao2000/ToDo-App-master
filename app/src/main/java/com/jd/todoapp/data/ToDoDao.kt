package com.jd.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jd.todoapp.data.models.ToDoData

/**
 * 数据访问对象接口，用于访问ToDoData表。
 */
@Dao
interface ToDoDao {

    /**
     * 查询todo_table表中的所有数据。
     * 
     * @return 返回一个LiveData列表，包含所有ToDoData对象。
     */
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<ToDoData>>

    /**
     * 插入一个新的待办事项到todo_table表中。
     * 如果插入的待办事项已存在，则忽略该插入操作。
     * 
     * @param toDoData 要插入的ToDoData对象。
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)

    /**
     * 更新todo_table表中的一个待办事项。
     * 
     * @param toDoData 要更新的ToDoData对象。
     */
    @Update
    suspend fun updateData(toDoData: ToDoData)

    /**
     * 从todo_table表中删除一个待办事项。
     * 
     * @param toDoData 要删除的ToDoData对象。
     */
    @Delete
    suspend fun deleteItem(toDoData: ToDoData)

    /**
     * 删除todo_table表中的所有数据。
     */
    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()

    /**
     * 搜索数据库，根据标题模糊匹配搜索结果。
     * 
     * @param searchQuery 搜索查询字符串。
     * @return 返回一个LiveData列表，包含匹配搜索查询的ToDoData对象。
     */
    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>>

    /**
     * 根据优先级从高到低排序待办事项。
     * 
     * @return 返回一个LiveData列表，包含按优先级从高到低排序的ToDoData对象。
     */
    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority(): LiveData<List<ToDoData>>

    /**
     * 根据优先级从低到高排序待办事项。
     * 
     * @return 返回一个LiveData列表，包含按优先级从低到高排序的ToDoData对象。
     */
    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority(): LiveData<List<ToDoData>>
}