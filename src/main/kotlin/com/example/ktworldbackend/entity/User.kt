package com.example.ktworldbackend.entity

import jakarta.persistence.*

@Entity
@Table(name="user1")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
    val id: Int?,
    val email: String,
    val password: String) {
}