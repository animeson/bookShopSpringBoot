package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

/*    @Query("SELECT w.*, b.* " +
            "FROM warehouse w " +
            "JOIN book b ON b.bookid = w.book_id" +
            "WHERE b.bookid " +
            "IN ( SELECT bc.book_id FROM book_categories bc" +
            "JOIN category c ON bc.category_id = c.categoryid  " +
            "WHERE c.name = :categoryName")
    Page<Book> findAllByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);*/

}
