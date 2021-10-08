package com.example.heroku.controllers;

import com.example.heroku.models.Person;
import com.example.heroku.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    PersonRepo persons;

    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return persons.findAll();
    }

    @GetMapping("/persons/{id}")
    public Person getPerson(@PathVariable Long id) {
        return persons.findById(id).get();
    }

    @PostMapping("/persons")
    public Person createPerson(@RequestBody Person person) {
        return persons.save(person);
    }

    @PutMapping("/persons/{id}")
    public String updatePerson(@PathVariable Long id, @RequestBody Person person) {
        if (persons.existsById(id)) {
            person.setId(id);
            persons.save(person);
            return "New person is made! good job";
        } else {
            return "No person found";
        }
    }


    @PatchMapping("/persons/{id}")
    public String patchAnimal(@PathVariable Long id, @RequestBody Person person) {
        return persons.findById(id).map(foundPerson -> {
            if (person.getAge() != 0) foundPerson.setAge(person.getAge());
            if (person.getName() != null) foundPerson.setName(person.getName());
            if (person.getGender() != null) foundPerson.setGender((person.getGender()));
            persons.save(foundPerson);
            return "The person is updated!";
        }).orElse("No person found");
    }

    @DeleteMapping("/persons/{id}")
    public void deleteAnimal(@PathVariable Long id) {
        persons.deleteById(id);
    }
}


