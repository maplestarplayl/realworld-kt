package com.example.ktworldbackend.dto

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class ArticleDTO(
    val title: String,
    val description: String,
    val body: String,
    val tags: List<String>
)