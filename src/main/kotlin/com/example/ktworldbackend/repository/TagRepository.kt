package com.example.ktworldbackend.repository

import com.example.ktworldbackend.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository : JpaRepository<Tag,Int> {
}