package com.baje.cookbook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@SpringBootApplication
class CookBookApplication

fun main(args: Array<String>) {
	runApplication<CookBookApplication>(*args)
}

@Configuration
@EnableWebSecurity
class WebSecurityConfig(disableDefaults: Boolean = false) : WebSecurityConfigurerAdapter(disableDefaults) {
	override fun configure(http: HttpSecurity?) {
		http!!.authorizeRequests()
				.antMatchers("/**").permitAll()
	}
}