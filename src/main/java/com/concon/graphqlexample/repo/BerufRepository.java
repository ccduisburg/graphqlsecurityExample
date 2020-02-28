package com.concon.graphqlexample.repo;

import com.concon.graphqlexample.model.Beruf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BerufRepository extends JpaRepository<Beruf,Integer> {
}
