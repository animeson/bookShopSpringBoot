package com.svistun.bookshoop.repository;


import com.svistun.bookshoop.entity.User;
import com.svistun.bookshoop.entity.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.userImage = null WHERE u.userImage = :userImage")
    void removeUserImage(@Param("userImage") UserImage userImage);


}
