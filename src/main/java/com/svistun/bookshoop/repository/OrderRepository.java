package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.OrderStatus;
import com.svistun.bookshoop.entity.Orders;
import com.svistun.bookshoop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findByUserAndStatus(User user, OrderStatus status);

/*    @Query("SELECT w.*, b.* " +
            "FROM warehouse w " +
            "JOIN book b ON b.bookid = w.book_id" +
            "WHERE b.bookid " +
            "IN ( SELECT bc.book_id FROM book_categories bc" +
            "JOIN category c ON bc.category_id = c.categoryid  " +
            "WHERE c.name = :categoryName")
    Page<Book> findAllByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);*/

}
