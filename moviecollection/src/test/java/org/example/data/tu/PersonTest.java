package org.example.data.tu;

import org.example.data.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testAllArgsConstructor(){
        var person = new Person("Robert Pattinson",
                LocalDate.of(1986, 5, 13));
        System.out.println(person);
    }

    @Test
    void testRequiredArgsConstructor(){
        var person = new Person("Zoë Kravitz");
        System.out.println(person);
    }

    @Test
    void testNoArgsConstructor(){
        var person = new Person();
        System.out.println(person);
    }
}