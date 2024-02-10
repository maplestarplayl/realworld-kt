package com.example.ktshopbackend.repository

import com.example.ktshopbackend.entity.Tag
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TagRepository : JpaRepository<Tag,Int> {
}