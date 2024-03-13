package com.example.ktworldbackend.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.ktworldbackend.aspect.logger
import com.example.ktworldbackend.entity.Profile
import com.example.ktworldbackend.entity.User
import com.example.ktworldbackend.repository.ProfileRepository
import com.example.ktworldbackend.repository.UserRepository
import com.example.ktworldbackend.util.JsonUtil
import org.springframework.stereotype.Service
const val secret: String = "lifeng"

@Service
class UserService(val userRepository: UserRepository , val profileRepository: ProfileRepository) {

    fun login(email: String, password: String): String {
        var user = userRepository.findUserByEmail(email) ?: return "user not found"
        var profile = profileRepository.findProfileByUserId(user.id!!)
        return  when (user.password == password) {
            true -> {
                return JWT.create().withClaim("user",JsonUtil.toJson(profile!!)).sign(Algorithm.HMAC256(secret))
            }
            false -> "password error"
        }
    }

    fun register(email: String, password: String, username:String): String {
        var user= User(null,email, password)
        userRepository.save(user)
        profileRepository.save(Profile(null,username, user.id!!,null,null))
        return "ok"
    }

    fun getCurrentUser(user:Profile): Profile {
        return profileRepository.findProfileByUserId(user.userId!!) ?: throw RuntimeException("profile not found")
    }
    fun updateCurrentUser(user:Profile, profile: Profile): Profile {
        return profileRepository.save(user.copy(username = profile.username?:user.username, bio = profile.bio?:user.bio, image = profile.image?:user.image))
    }

}