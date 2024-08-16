package org.example.homework68;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;

public class CreateProduct {
    static EntityManager manager = Persistence.createEntityManagerFactory("default").createEntityManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Category> categories = getAllCategories();
        categories.forEach(category -> System.out.println(category.getId() + ". " + category.getName()));

        System.out.print("Введите id категории: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (Category category : categories) {
            if (category.getId() == id) {
                System.out.print("Введите название товара: ");
                String name = scanner.nextLine();

                System.out.print("Введите стоимость товара: ");
                int price = Integer.parseInt(scanner.nextLine());

                createProduct(name, price, category);
                System.out.println("Товар создан");
                return;
            }
        }
        System.out.println("Категория с таким id не существует");
    }

    private static List<Category> getAllCategories() {
        TypedQuery<Category> query = manager.createQuery("select c from Category c", Category.class);
        List<Category> categories = query.getResultList();

        return categories;
    }

    private static void createProduct(String name, int price, Category category) {
        try {
            manager.getTransaction().begin();

            Product newProduct = Product.builder()
                    .name(name)
                    .price(price)
                    .category(category)
                    .build();

            manager.persist(newProduct);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
}
