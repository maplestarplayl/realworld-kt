package com.example.plugins

import io.ktor.server.application.*

val DataTransformationPlugin = createApplicationPlugin(name = "DataTransformationPlugin") {

//    onCallRespond { call ->
//        transformBody { data ->
//            if (data is String) {
//                (data + 1).toString()
//            } else if (data is HttpResponse<*>) {
//                data
//            } else {
//                HttpResponse<Any>(true, 0, null, data)
//            }
//        }
//    }

}
fun Application.configureDataTransformation() {
    install(DataTransformationPlugin)
}