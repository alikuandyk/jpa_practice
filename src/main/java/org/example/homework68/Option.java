package org.example.homework68;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "options")

public class Option {
    @Id
    private int id;
    private String name;
    @Column(name = "category_id")
    private int categoryId;
}
