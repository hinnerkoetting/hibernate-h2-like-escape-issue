package org.example;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<org.example.User, Long> {

    @Query("from User where name LIKE %:name% ")
    List<User> findByNameLike(@Param("name") String name);

}