package com.itis.template.entity

import androidx.room.*
import java.util.*


@Entity(tableName = "task")
data class Task(
        @PrimaryKey(autoGenerate = true)
        var id: Int?,
        @ColumnInfo(name = "title")
        var title: String?,
        @ColumnInfo(name = "description")
        var description: String?,
        @field: TypeConverters(DateConverter::class)
        @ColumnInfo(name = "date")
        var date: Date?,
        @ColumnInfo(name = "longitude")
        var longitude: Double?,
        @ColumnInfo(name = "latitude")
        var latitude: Double?
)

class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?) = timestamp?.let { Date(it) }

    @TypeConverter
    fun toTimestamp(date: Date?) = date?.time
}
