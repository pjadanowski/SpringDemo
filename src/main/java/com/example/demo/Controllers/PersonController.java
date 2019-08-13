package com.example.demo.Controllers;

import com.example.demo.Models.Person;
import com.example.demo.repository.PersonRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/persons")
    public List<Person> index() {
        return personRepository.findAll();
    }

    @GetMapping("/persons/faker")
    public String faker() {
        Faker faker = new Faker(new Locale("pl"));

        IntStream.range(0,10000).forEach(i -> {
            Person person = new Person(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.name().username(),
                    faker.date().birthday(15,30)
            );
            personRepository.save(person);
        });
        return "Done";
    }
}
