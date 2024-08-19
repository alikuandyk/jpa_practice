package org.example.homework68.create;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.example.homework68.entity_classes.Category;
import org.example.homework68.entity_classes.Option;
import org.example.homework68.entity_classes.Product;
import org.example.homework68.entity_classes.Value;

import java.util.List;
import java.util.Scanner;

public class CreateProduct {
    static EntityManager manager = Persistence.createEntityManagerFactory("default").createEntityManager();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        createValueAndProduct();
    }

    private static List<Category> getAllCategories() {
        return manager.createQuery("select c from Category c", Category.class).getResultList();
    }

    private static void printAllCategories() {
        List<Category> categories = getAllCategories();
        categories.forEach(category -> System.out.println(category.getId() + ". " + category.getName()));
    }

    private static void createValueAndProduct() {
        printAllCategories();

        System.out.print("Выбери категорию: ");
        int id = Integer.parseInt(scanner.nextLine());
        Category category = manager.find(Category.class, id);

        System.out.print("Название товара: ");
        String productName = scanner.nextLine();

        System.out.print("Стоимость товара: ");
        int productPrice = Integer.parseInt(scanner.nextLine());

        try {
            manager.getTransaction().begin();

            Product product = Product.builder()
                    .name(productName)
                    .price(productPrice)
                    .category(category)
                    .build();

            manager.persist(product);

            manager.getTransaction().commit();

            List<Option> options = category.getOptions();
            for (Option option : options) {
                System.out.println(option.getName());
                String valueName = scanner.nextLine();

                try {
                    manager.getTransaction().begin();

                    Value value = Value.builder()
                            .name(valueName)
                            .product(product)
                            .option(option)
                            .build();

                    manager.persist(value);

                    manager.getTransaction().commit();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    manager.getTransaction().rollback();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
        }
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
            System.out.println("Товар создан");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
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
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    private static void addOptions(String name, Category category) {
        List<String> names = List.of(name.split(", "));

        names.forEach(string -> addOption(string, category));
    }

    private static void addOption(String name, Category category) {
        try {
            manager.getTransaction().begin();

            Option newOption = Option.builder()
                    .name(name)
                    .category(category)
                    .build();

            manager.persist(newOption);
            System.out.println("Возожности добавлены в категорию");

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
}





//public static void main(String[] args) {
//    System.out.print("Введите id категории: ");
//    int id = Integer.parseInt(scanner.nextLine());
//
//    for (Category category : categories) {
//        if (category.getId() == id) {
//            System.out.print("Введите название товара: ");
//            String name = scanner.nextLine();
//
//            System.out.print("Введите стоимость товара: ");
//            int price = Integer.parseInt(scanner.nextLine());
//
//            createProduct(name, price, category);
//            return;
//        }
//    }
//    System.out.println("Категория с таким id не существует");
//}
