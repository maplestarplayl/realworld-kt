package com.example.ktworldbackend.annotation

import com.example.ktworldbackend.auth.Action
import com.example.ktworldbackend.auth.Module
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class AbacAnno(val module: Module = Module.DEFAULT,
                          val action: Action = Action.DEFAULT) {

}