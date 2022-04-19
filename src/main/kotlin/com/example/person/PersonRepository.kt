package com.example.person

import Person
import java.util.*;
import org.springframework.data.mongodb.repository.MongoRepository

interface PersonRepository : MongoRepository<Person, Int> {
    override fun findById(id: Int): Optional<Person>
}