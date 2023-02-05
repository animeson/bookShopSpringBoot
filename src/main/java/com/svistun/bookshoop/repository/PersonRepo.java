package com.svistun.bookshoop.repository;


import com.svistun.bookshoop.entity.Book;
import com.svistun.bookshoop.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Long> {
    Person findByEmail(String email);
    Boolean existsByEmail(String email);

}
