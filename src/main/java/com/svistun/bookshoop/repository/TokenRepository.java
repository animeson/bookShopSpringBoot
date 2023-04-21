package com.svistun.bookshoop.repository;

import com.svistun.bookshoop.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = """
      select t from Token t inner join User u\s
      on t.user.userID = u.userID\s
      where u.userID = :id and (t.expired = false or t.revoked = false)\s
      """)
    Collection<Token> findAllValidTokenByUser(Long id);

    Optional<Token> findByToken(String token);
}
