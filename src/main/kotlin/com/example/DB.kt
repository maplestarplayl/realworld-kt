package com.example

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import org.ktorm.database.Database
import java.io.File

object DB {
    lateinit var  database: Database

    fun init(config: ApplicationConfig)  {
        val driverClassName = config.property("storage.driverClassName").getString()
        val jdbcURL = config.property("storage.jdbcURL").getString() +
                (config.propertyOrNull("storage.dbFilePath")?.getString()?.let {
                    File(it).canonicalFile.absolutePath
                } ?: "")
        database = Database.connect(createHikariDataSource(url = jdbcURL, driver = driverClassName))
    }
    private fun createHikariDataSource(
        url: String,
        driver: String
    ) = HikariDataSource(HikariConfig().apply {
        driverClassName = driver
        jdbcUrl = url
        maximumPoolSize = 10
        isAutoCommit = true
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
        username = "postgres"
    })
}