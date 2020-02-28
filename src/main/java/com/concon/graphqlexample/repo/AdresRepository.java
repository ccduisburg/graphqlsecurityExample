package com.concon.graphqlexample.repo;

import com.concon.graphqlexample.model.Adres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresRepository extends JpaRepository<Adres,Integer> {
}
