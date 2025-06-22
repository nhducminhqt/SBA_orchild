package com.orchid.orchidbe.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(collection = "tokens")
public class Token {

    @Id
    private Integer id; // Use Integer for MongoDB IDs

    private String token;

    private String refreshToken;

    private String tokenType;

    private LocalDateTime expirationDate;

    private LocalDateTime refreshExpirationDate;

    private boolean revoked;

    private boolean expired;

    private Account account; // Embedded or referenced relationship
}