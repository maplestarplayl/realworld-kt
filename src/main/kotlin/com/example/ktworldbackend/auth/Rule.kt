package com.example.ktworldbackend.auth

class Rule(var policy: Policy? = null) {
}


fun Rule.rule(block: Rule.() -> Unit) : Unit {
    this.apply(block)
}
fun Rule.createPolicy(block: Policy.() -> Unit) : Policy {
    return Policy(null,null,null,null).apply(block)
}