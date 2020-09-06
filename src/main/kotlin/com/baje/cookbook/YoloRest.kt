package com.baje.cookbook

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class YoloController {
    @GetMapping("yolo")
    fun yolo(): String = "yolo"

    @GetMapping("yolo-secured")
    fun yoloSecuredGet(): String = "yolo-secured"

    @PostMapping("yolo-secured")
    fun yoloSecuredPost(@RequestBody map: Map<String, String>): String {
        println("received the following body: $map")
        return "ok, mapped received"
    }
}