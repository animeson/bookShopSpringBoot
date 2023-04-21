package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.UserImage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
    @Transactional
    @Modifying
    @Query("delete from UserImage where user.userID =:userID")
    void deleteByUser(@Param("userID") Long userID);
}
