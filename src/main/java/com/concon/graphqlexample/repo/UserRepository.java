package com.concon.graphqlexample.repo;

import com.concon.graphqlexample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Integer> {

    //@Query("SELECT p FROM Person p WHERE p.name LIKE %?1% ")
    Optional<User> findByEmail(String email);

}