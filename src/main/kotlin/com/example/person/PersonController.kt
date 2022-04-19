package com.example.person

import Person
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("person")
class PersonController(val repository: PersonRepository) {

    @PostMapping
    fun create(@RequestBody person: Person): ResponseEntity<Person> {
        var personSaved = repository.save(person)
        return ResponseEntity.ok(personSaved)
    }

    @GetMapping
    fun read(): ResponseEntity<(MutableList<Person>)> = ResponseEntity.ok(repository.findAll())

    @PutMapping("{id}")
    fun update(@PathVariable id:Int, @RequestBody person: Person): ResponseEntity<Person> {
        val personDbOptional = repository.findById(id)
        val toSave = personDbOptional
            .orElseThrow { RuntimeException("PersonID $id not found") }
            .copy(name = person.name, telephone = person.telephone)
        return ResponseEntity.ok(repository.save(toSave))
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id:Int) = repository
        .findById(id)
        .ifPresent { repository.delete(it)}



}