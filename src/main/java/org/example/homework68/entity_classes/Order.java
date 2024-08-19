package org.example.homework68.entity_classes;

import jakarta.persistence.*;
import lombok.*;
import org.example.homework68.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(value = EnumType.ORDINAL)
    private Status status;

    private String address;

    @Column(name = "date_order")
    private LocalDateTime dateOrder;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private List<OrderProduct> orderProducts;
}
