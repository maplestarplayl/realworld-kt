package com.example.ktworldbackend.auth

import com.example.ktworldbackend.exception.LocalRuntimeException
import java.lang.Module

object Verifer{
    fun verify(rule: Rule, action: Action, attribute: Attribute): Boolean {
        val selectedPolicy = rule.policies
            .firstOrNull { it.modules?.contains(attribute.module) ?: false && it.role == attribute.user.role }
            ?: throw LocalRuntimeException("No policy found for the user")
        return selectedPolicy.action?.contains(action) ?: false
    }
}