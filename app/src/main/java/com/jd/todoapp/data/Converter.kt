package com.jd.todoapp.data

import androidx.room.TypeConverter
import com.jd.todoapp.data.models.Priority

/**
 * 转换器类，用于在数据库操作中转换Priority枚举和字符串之间的格式
 */
class Converter {

    /**
     * 将Priority枚举转换为字符串
     *
     * @param priority Priority枚举值
     * @return 字符串表示的优先级
     */
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    /**
     * 将字符串转换回Priority枚举
     *
     * @param priority 代表优先级的字符串
     * @return Priority枚举值
     */
    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}