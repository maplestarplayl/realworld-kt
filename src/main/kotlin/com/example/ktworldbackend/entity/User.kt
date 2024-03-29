package com.example.ktworldbackend.entity

import com.example.ktworldbackend.enums.Role
import jakarta.persistence.*

@Entity
@Table(name="account")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
    val id: Int?,
    val email: String,
    val password: String,
    @Enumerated(EnumType.ORDINAL)
    val role: Role) {
}