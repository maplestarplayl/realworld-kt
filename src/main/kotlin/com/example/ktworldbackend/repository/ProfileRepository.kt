package com.example.ktworldbackend.repository

import com.example.ktworldbackend.entity.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository : JpaRepository<Profile, Int>{
    fun findProfileByUserId(userId: Int): Profile?
}