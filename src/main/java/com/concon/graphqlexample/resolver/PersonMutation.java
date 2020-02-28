package com.concon.graphqlexample.resolver;

import com.concon.graphqlexample.model.Person;
import com.concon.graphqlexample.repo.PersonRepository;
import com.concon.graphqlexample.security.Unsecured;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonMutation implements GraphQLMutationResolver {
    private final PersonRepository personRepository;
    @Unsecured
    public Person createPerson(Person person){
        personRepository.save(person);
        return person;
    }

    public Person updatePerson(Integer id,Person person){
        Person personUpdate=personRepository.findById(id).get();
        personUpdate.setId(person.getId());
        personUpdate.setName(person.getName());
        personUpdate.setVorname(person.getName());
//        personUpdate.setAdres(person.getAdres());
//        personUpdate.setBeruf(person.getBeruf());
        personUpdate.setFallows(person.getFallows());
//        personUpdate.setFalovers(person.getFallows());
    if(personUpdate!=null){
        personRepository.save(person);
        return personUpdate;
    }
    return null;
    }

    public Boolean deletePerson(Integer id){
        Person personDelete=personRepository.findById(id).get();
        if(personDelete!=null){
            personRepository.delete(personDelete);
            return true;
        }
        return false;
    }
}
