package com.concon.graphqlexample.repo;

import com.concon.graphqlexample.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface PersonRepository  extends JpaRepository<Person,Integer> {

    //@Query("SELECT p FROM Person p WHERE p.name LIKE %?1% ")
    List<Person> findByName(Optional<String> name);

  //  @Query("SELECT p FROM Person p WHERE p.vorname LIKE %?1% ")
    List<Person> findByVorname(Optional<String> vorname);
}
