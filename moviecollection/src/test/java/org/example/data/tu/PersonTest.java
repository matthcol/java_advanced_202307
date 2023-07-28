package org.example.data.tu;

import org.example.data.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testAllArgsConstructor(){
        // given: facts
        String name = "Robert Pattinson";
        var birthdate = LocalDate.of(1986, 5, 13);
        // when
        var person = new Person(name, birthdate);
        // then: verify/check
        // System.out.println(person); // to debug only
        assertAll(
                () -> assertEquals(name, person.getName(), "name"),
                () -> assertEquals(birthdate, person.getBirthdate(), "birthdate")
        );
    }

    @Test
    void testRequiredArgsConstructor(){
        // given
        String name = "Zoë Kravitz";
        // when
        var person = new Person("Zoë Kravitz");
        // then
        //        System.out.println(person);
        assertAll(
                () -> assertEquals(name, person.getName(), "name"),
                () -> assertNull(person.getBirthdate(), "birthdate")
        );
    }

    @Test
    void testNoArgsConstructor(){
        // when
        var person = new Person();
        // then
        //        System.out.println(person);
        assertAll(
                () -> assertNull(person.getName(), "name"),
                () -> assertNull(person.getBirthdate(), "birthdate")
        );
    }
}