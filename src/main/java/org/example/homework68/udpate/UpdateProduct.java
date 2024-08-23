package org.example.homework68.udpate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.homework68.entity_classes.Category;
import org.example.homework68.entity_classes.Option;
import org.example.homework68.entity_classes.Product;
import org.example.homework68.entity_classes.Value;

import java.util.Scanner;

public class UpdateProduct {
    static Scanner scanner = new Scanner(System.in);
    static EntityManager manager = Persistence.createEntityManagerFactory("default").createEntityManager();
    public static void main(String[] args) {
        System.out.print("Введите id товара: ");
        long id = Long.parseLong(scanner.nextLine());

        Product product = manager.find(Product.class, id);
        if (product == null) {
            System.out.println("Товар с id=" + id + " не найден");
            return;
        }

        System.out.printf("Введите название товара [%s]: ", product.getName());
        String name = scanner.nextLine();
        if (!name.isBlank()) {
            product.setName(name);
        }

        System.out.printf("Введите стоимость товара [%s]: ", product.getPrice());
        String price = scanner.nextLine();
        if (!price.isBlank()) {
            product.setPrice(Integer.parseInt(price));
        }

        try {
            manager.getTransaction().begin();

            manager.persist(product);

            Category category = product.getCategory();

            for (Option option : category.getOptions()) {
                boolean isValuePresent = false;
                Value newValue = null;
                for (Value value : product.getValues()) {
                    if (option.getId() == value.getOption().getId()) {
                        isValuePresent = true;
                        newValue = value;
                        break;
                    }
                }

                if (isValuePresent) {
                    System.out.printf("%s [%s]: ", newValue.getOption().getName(), newValue.getName());
                    String valueName = scanner.nextLine();

                    newValue.setName(valueName);
                    manager.persist(newValue);
                } else {
                    newValue = new Value();
                    System.out.print(option.getName() + " [null]: ");
                    String valueName = scanner.nextLine();

                    newValue.setOption(option);
                    newValue.setProduct(product);
                    newValue.setName(valueName);
                    manager.persist(newValue);
                }
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    private static void provideDiscount() {
        System.out.print("Введите id: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите скидку: ");
        int discount = Integer.parseInt(scanner.nextLine());

        Category category = manager.find(Category.class, id);

        try {
            manager.getTransaction().begin();

            for (Product product : category.getProducts()) {
                product.setPrice(product.getPrice() * (1 - (discount / 100)));
                manager.merge(product);
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
        }
    }
}
