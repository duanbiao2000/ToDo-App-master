package com.jd.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jd.todoapp.data.models.ToDoData

// 定义一个数据库类，用于存储ToDoData实体。
// entities属性定义了数据库中包含的实体类型。
// version属性用于定义数据库的版本，当数据库结构变化时需要更新这个版本号。
// exportSchema属性设置为false是为了在测试时避免导出数据库模式。
@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
// 使用TypeConverters属性指定类型转换器，用于处理实体中可能的复杂类型。
@TypeConverters(Converter::class)
abstract class ToDoDatabase : RoomDatabase() {

    // 定义一个抽象方法，返回ToDoDao数据访问对象的实例。
    abstract fun toDoDao(): ToDoDao

    companion object {
        // 使用Volatile关键字标记INSTANCE变量，以确保多线程访问时其值的可见性。
        private var INSTANCE: ToDoDatabase? = null

        // 提供一个用于获取数据库实例的方法，采用单例模式确保只有一个数据库实例。
        fun getDataBase(context: Context): ToDoDatabase {
            // 先尝试获取已有的数据库实例。
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            // 当第一次调用时，使用同步代码块来创建新的数据库实例，
            // 这确保了在同一时间只有一个线程可以访问这个代码块。
            synchronized(this) {
                // 创建数据库实例，并将其赋值给INSTANCE变量。
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                // 返回新创建的数据库实例。
                return instance
            }
        }
    }
}