package com.example.ktworldbackend.auth

import com.example.ktworldbackend.enums.Role

object Rule {
    var  policies: MutableList<Policy> = emptyList<Policy>().toMutableList()
    fun addPolicy(vararg policy: Policy) {
        policies += mutableListOf(*policy)
    }
}
interface RuleScope {
    fun policy(block: Policy.() -> Unit): Unit



}
inline fun rule(content: RuleScope.() -> Unit){
    RuleScopeInstance.content()
}
internal object RuleScopeInstance: RuleScope{
    override fun policy(block: Policy.() -> Unit): Unit {
        val policy = Policy(null, null, null, null)
        policy.block()
        Rule.addPolicy(policy)
    }
}

