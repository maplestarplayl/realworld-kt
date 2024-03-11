package com.example.ktworldbackend.entity

import jakarta.persistence.*
import lombok.Data

@Table(name = "profile")
@Entity
data class Profile(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_id_generator")
    @SequenceGenerator(name = "profile_id_generator", sequenceName = "profile_id_seq", allocationSize = 1)
    val id: Int?,
    val username:String,
    val userId: Int,
    val bio: String?,
    val image: String?)