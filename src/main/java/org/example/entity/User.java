package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data // Это POJO сущность (геттеры, сеттеры, хеш-код, эквалс, ту-стринг, конструкторы и т.д.)
@NoArgsConstructor
@AllArgsConstructor
@Builder // для SessionFactory чтобы при вызове сессии добавлять в этот класс нужные данные для записи в БД
@Entity // каждая сущность в hibernate должна иметь PK
@Table(name = "users", schema = "public") // чтобы название таблицы совпадала с названием сущности (DB 'user' = .class 'user'), а не названием класса User
public class User {

    @Id // PK
    private String username;
    private String firstname;
    private String lastname;

//    @Column(name = "birth_date")  добавляем, чтобы название нашей переменной совпадало с названием поля в БД, но т.к. мы определили setPhysicalNamingStrategy(), то это делать необязательно
    private LocalDate birthDate;
    private Integer age;
    private Role role;
}
