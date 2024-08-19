package org.example.homework68.entity_classes;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Option> options;
    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Product> products;
}
