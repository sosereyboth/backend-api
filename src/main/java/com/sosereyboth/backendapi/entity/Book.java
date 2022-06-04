package com.sosereyboth.backendapi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "BOOK_TBL")
public class Book {

    @Id
    private String id;
    private String author;
    private String title;
    private String category;
    private String description;
    private String reservedBy;

}
