package com.orchid.orchidbe.dto;

public interface OrchidDTO {

    record OrchidReq(
            boolean isNatural,
            String description,
            String name,
            String url,
            Double price,
            String categoryId // Only pass the category ID
    ) {

    }

}