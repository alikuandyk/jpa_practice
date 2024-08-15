package org.example.homework68;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "options")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Option {
    @Id
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "option")
    private List<Value> values;
}
