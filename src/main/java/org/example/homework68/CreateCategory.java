package org.example.homework68;

import jakarta.persistence.*;

import java.util.Scanner;

public class CreateCategory {
    static EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
    static EntityManager manager = managerFactory.createEntityManager();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите название категории: ");
        String name = scanner.nextLine();

        isThereCategory(name);
    }

    private static void isThereCategory(String name) {
        try {
            TypedQuery<Category> query = manager.createQuery("select c from Category c where c.name = :name", Category.class);
            Category category = query.setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            System.out.println("Категория с таким названием существует");
            createCategory(name);
        }
    }

    private static void createCategory(String name) {
        try {
            manager.getTransaction().begin();

            Category category = Category.builder()
                    .name(name)
                    .build();

            manager.persist(category);
            System.out.println("Категория с названием " + name + " создана");

            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
        }
    }
}
