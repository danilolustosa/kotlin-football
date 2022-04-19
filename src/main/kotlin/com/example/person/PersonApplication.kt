package com.example.person

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PersonMain

fun main(args: Array<String>) {
	runApplication<PersonMain>(*args)
}