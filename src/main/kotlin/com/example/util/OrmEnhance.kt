package com.example.util

import org.ktorm.dsl.AssignmentsBuilder
import org.ktorm.dsl.eq
import org.ktorm.dsl.update
import org.ktorm.schema.Column
import org.ktorm.schema.ColumnDeclaring
import kotlin.reflect.full.declaredMemberProperties

fun < C: Any> AssignmentsBuilder.setIfValueNotNull(column: Column<C>, value: C?) {
    value?.let {
        set(column, value)
    }
}

public fun <T : org.ktorm.schema.BaseTable<*>> org.ktorm.database.Database.updateWithObject(table: T, data:Any): kotlin.Int {
    return update(table){
        val fields = table::class.declaredMemberProperties
        fields.forEach { fe ->
            val clazz = data.javaClass
            val field = clazz.getDeclaredField(fe.name)
            field.isAccessible = true
            val value = field.get(data)
            value?.let { set(table[fe.name] as Column<Any>, value as Any?) }

        }
        val tableId = table.get("id") as ColumnDeclaring<Int>
        val dataIdField = data.javaClass.getDeclaredField("id")
        dataIdField.isAccessible = true
        val dataId = dataIdField.get(data) as Int
        where {

            tableId.eq(dataId)
        }
    }
}