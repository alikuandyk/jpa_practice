package org.example.homework67;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Example {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        // Получение кота по id
        /*Cat cat = manager.find(Cat.class, 999);
        System.out.println(cat);*/

        // Получение всех котов
        /*TypedQuery<Cat> query = manager.createQuery("select c from Cat c", Cat.class);
        List<Cat> cats = query.getResultList();

        cats.forEach(System.out::println);*/

        // Создание кота
        /*Cat cat = Cat.builder()
                .name("1")
                .color("Серый")
                .build();

        try {
            manager.getTransaction().begin();

            manager.persist(cat);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }*/

        // Обновление кота
        /*Cat cat = manager.find(Cat.class, 7);
        cat.setName("Мурзик");

        try {
            manager.getTransaction().begin();

            manager.merge(cat);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }*/


        // Удаление кота
        /*Cat cat = manager.find(Cat.class, 0);
        try {
            manager.getTransaction().begin();

            manager.remove(cat);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }*/

        // Получение кота по имени
        TypedQuery<Cat> query = manager.createQuery("select c from Cat c where c.name = :name", Cat.class);
        query.setParameter("name", "Мурзик");

        /*query.getResultStream()
                .findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Кот не найден"));*/

        /*if (optional.isPresent()) {
            System.out.println(optional.get());
        } else {
            System.out.println("Кот не найден");
        }*/


        /*List<Cat> cats = query.getResultList();

        if (cats.isEmpty()) {
            System.out.println("Кот не найден");
        } else {
            System.out.println(cats.get(0));
        }*/

        // TODO: создайте метод для получения событий которые опубликованы
        // TODO: создать метод для получения событий по названию
    }
}
