package com.baje.cookbook

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class YoloController {
    @GetMapping("yolo")
    fun yolo(): String = "yolo"
}