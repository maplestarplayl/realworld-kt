package com.example.ktworldbackend.service

import com.example.ktworldbackend.entity.Tag
import com.example.ktworldbackend.repository.TagRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class TagService(val tagRepository: TagRepository){
    fun createNewTag(tagName: String): Tag{
        val tag = Tag(null,tagName)
        return tagRepository.save(tag);
    }
    fun findTagById(Id:Int): Optional<Tag> {
        return tagRepository.findById(Id)
    }
    fun findTagPage(size:Int): Page<Tag>{
        return tagRepository.findAll(Pageable.ofSize(size))
    }
}