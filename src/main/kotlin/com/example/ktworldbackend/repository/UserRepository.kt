package com.example.ktworldbackend.repository

import com.example.ktworldbackend.entity.Tag
import com.example.ktworldbackend.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
    fun findUserByEmail(email: String): User?
}