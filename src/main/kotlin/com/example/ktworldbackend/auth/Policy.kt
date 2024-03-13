package com.example.ktworldbackend.auth

import com.example.ktworldbackend.enums.Role
import lombok.AllArgsConstructor
import lombok.Data

@Data
@AllArgsConstructor
class Policy(var role:Role?, var module: List<Module>?, var action: List<Action>?, var strategy: Strategy?) {
    fun createPolicy(block: Policy.() -> Unit) : Policy {
        return this.apply(block)
    }
}