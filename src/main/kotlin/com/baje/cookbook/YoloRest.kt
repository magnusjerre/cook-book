package com.baje.cookbook

import com.baje.cookbook.models.CookBookUser
import com.baje.cookbook.repository.CookBookUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class YoloController {
    @Autowired lateinit var cookBookUserRepository: CookBookUserRepository

    @GetMapping("yolo")
    fun yolo(): String = "yolo"

    @GetMapping("yolo-secured")
    fun yoloSecuredGet(): String = "yolo-secured"

    @PostMapping("yolo-secured")
    fun yoloSecuredPost(@RequestBody map: Map<String, String>): String {
        println("received the following body: $map")
        return "ok, mapped received"
    }

    @GetMapping("user")
    fun user(): List<CookBookUser> = cookBookUserRepository.findAll()
}