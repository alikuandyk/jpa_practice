package org.example.homework68.entity_classes;

import jakarta.persistence.*;
import lombok.*;
import org.example.homework68.enums.Role;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String login;
    private String password;
    private String name;
    private String lastname;
    @Enumerated(value = EnumType.ORDINAL)
    private Role role;
    private LocalDateTime created;
}
