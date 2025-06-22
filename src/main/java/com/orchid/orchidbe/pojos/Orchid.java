package com.orchid.orchidbe.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orchids")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orchid {

    @Id
    private Integer id; // Use Integer for MongoDB IDs

    private boolean isNatural;

    private String description;

    private String name;

    private String url;

    private Double price;

    private Category category;
}