package com.example.ktworldbackend.auth

import com.example.ktworldbackend.enums.Role
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class Policy(var role:Role?, var modules: List<Module>?, var action: List<Action>?, var strategy: Strategy?)