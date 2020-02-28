package com.concon.graphqlexample.resolver;

import com.concon.graphqlexample.model.Beruf;
import com.concon.graphqlexample.repo.BerufRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class BerufMutation implements  GraphQLMutationResolver {

        private final BerufRepository berufRepository;

        public Beruf createBeruf(Beruf beruf) {
            berufRepository.save(beruf);
            return beruf;

        }

        public Beruf updateBeruf(Integer id, Beruf beruf) {
            Beruf berufUpdate = berufRepository.findById(id).get();
            berufUpdate.setId(beruf.getId());
            berufUpdate.setName(beruf.getName());
            berufUpdate.setDescription(beruf.getDescription());

            if (berufUpdate != null) {
                berufRepository.save(beruf);
                return berufUpdate;
            }
            return null;
        }

        public Boolean deleteBeruf(Integer id) {
            Beruf berufDelete = berufRepository.findById(id).get();
            if (berufDelete != null) {
                berufRepository.delete(berufDelete);
                return true;
            }
            return false;

        }
}
