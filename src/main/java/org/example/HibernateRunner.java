package org.example;

import org.example.entity.Role;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;


import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {

        Configuration configuration = new Configuration(); // объявляем для настройки конфигурации
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy()); // для User, чтобы названия переменных совпадали с названиями полей в БД (если они отличаются)
        configuration.configure(); // запускает настройки конфигурации и ищет путь к hibernate.cfg.xml (connection)

        try (SessionFactory sessionFactory = configuration.buildSessionFactory(); // SessionFactory обвертка для Hibernate и управления его сущностями (Создаем сущность и сохраняем ее в БД)
            Session session = sessionFactory.openSession()) { // открываем сессию, для полного цикла наших сущностей. Он создает квази-сиквел запрос

            session.beginTransaction(); // получить транзакцию для выполнения следующего кода

            User user = User.builder()  // ссылаемся на нашу сущность с классом User и открываем билдел, чтобы положить туда данные для полей
                    .username("ivan@gmail.com") // инициализация для поля в БД username (с РК)
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .birthDate(LocalDate.of(2000, 1, 19))
                    .age(20)
                    .role(Role.ADMIN)
                    .build(); // закрыть билд

            session.save(user); // сохраняем сессию

            session.getTransaction().commit(); // коммитим транзакцию в БД
        }
    }
}