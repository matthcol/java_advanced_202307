package org.example.data.tu;

import org.example.data.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    static List<Person> persons;

    @BeforeAll
    static void initData() {
        persons = List.of(
                new Person("Joe"),
                new Person("Jack"),
                new Person("William"),
                new Person("Averell")
        );
    }

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

    @Test
    void testEqualsCompatibleWithList(){
        // given
        var person = persons.get(2); // reference already in the list
        var person2 = new Person("William"); // copy of a person already in the list
        // when 1: search with a reference already in the list
        var isInList = persons.contains(person);
        // then 1:
        assertTrue(isInList, "person in the list by reference");
        // when 2:
        var isInList2 = persons.contains(person2);
        // then 2:
        assertTrue(isInList2, "person in the list by copy");
    }

    @Test
    void testEqualsHashCodeCompatibleWithHashSet(){
        // when
        var personSet = new HashSet<>(persons);
        var person = persons.get(2); // reference already in the list/set
        var person2 = new Person("William"); // copy of a person already in the list/set
        // when/then
        assertAll(
                () -> assertTrue(personSet.contains(person), "person in the hashset by reference"),
                () -> assertTrue(personSet.contains(person2), "person in the hashset by copy")
        );
    }
}