package com.alex.AuthLab.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@ToString(exclude = "password")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class User {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String email;
    private String password;

}
