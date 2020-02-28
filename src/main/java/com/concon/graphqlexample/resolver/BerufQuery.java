package com.concon.graphqlexample.resolver;


import com.concon.graphqlexample.model.Beruf;
import com.concon.graphqlexample.repo.BerufRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class BerufQuery  implements GraphQLQueryResolver {

    private final BerufRepository berufRepository;

    public Beruf getBeruf(Integer id) {
        if (id != null) {
            return berufRepository.findById(id).get();
        }
        return null;
    }

    public List<Beruf> getAllBeruf() {
        List<Beruf> berufList = new ArrayList<>();
        berufList.addAll(berufRepository.findAll());
        if (!berufList.isEmpty()) {
            return berufList;
        }

        return null;
    }
}