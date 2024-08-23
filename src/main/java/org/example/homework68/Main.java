package org.example.homework68;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.example.homework68.entity_classes.Order;
import org.example.homework68.entity_classes.OrderProduct;
import org.example.homework68.entity_classes.Product;
import org.example.homework68.entity_classes.User;
import org.example.homework68.enums.Status;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    static EntityManager manager = Persistence.createEntityManagerFactory("default").createEntityManager();
    static Scanner scanner = new Scanner(System.in);
    private static void order(User user) {
        Map<Product, Integer> basket = new HashMap<>();

        TypedQuery<Product> query = manager.createQuery("select p from Product p", Product.class);
        List<Product> products = query.getResultList();

        products.forEach(product -> System.out.println(product.getId() + ". " + product.getName()));

        while (true) {
            System.out.print("Введите id: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (id == 0) {
                break;
            }
            Product product = manager.find(Product.class, id);

            System.out.print("Введите кол-во: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            basket.put(product, quantity);
        }

        System.out.print("Введите адрес доставки: ");
        String address = scanner.nextLine();

        try {
            manager.getTransaction().begin();

            Order order = Order.builder()
                    .user(user)
                    .status(Status.CREATED)
                    .address(address)
                    .dateOrder(LocalDateTime.now())
                    .build();

            manager.persist(order);

            for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
                OrderProduct orderProduct = OrderProduct.builder()
                        .order(order)
                        .product(entry.getKey())
                        .quantity(entry.getValue())
                        .build();

                manager.persist(orderProduct);
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
}
