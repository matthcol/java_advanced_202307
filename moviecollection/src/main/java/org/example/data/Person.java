package org.example.data;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(force = true) // framework serialization/deserialization (XML, JSON, JPA)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Person {
    @NonNull
    private String name;

    private LocalDate birthdate;
}
