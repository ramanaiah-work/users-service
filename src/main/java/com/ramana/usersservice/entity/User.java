package com.ramana.usersservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@CompoundIndexes({
        @CompoundIndex(name = "unique_username", def = "{'username': 1}", unique = true),
        @CompoundIndex(name = "unique_email", def = "{'email': 1}", unique = true)
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private Set<Address> address;
}
