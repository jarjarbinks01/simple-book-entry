package com.tecpal.be.interview.bookentrysystem.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @NonNull
    private String uuid;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private String author;

    private String publisher;

    @NonNull
    private Date publish_date;
}
