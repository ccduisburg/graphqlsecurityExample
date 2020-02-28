package com.concon.graphqlexample.resolver;

import com.concon.graphqlexample.model.Person;
import com.concon.graphqlexample.repo.PersonRepository;
import com.concon.graphqlexample.security.Unsecured;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonQuery implements GraphQLQueryResolver {

    private final PersonRepository personRepository;

    public Person getPerson(Integer Id){
        if(Id!=null){
            return personRepository.findById(Id).get();
        }
        return null;
    }
    @Unsecured
    public List<Person> getAllPerson(){
        List<Person> personList=new ArrayList<>();
        personList.addAll(personRepository.findAll());
        if(!personList.isEmpty()){
            return personList;
        }

        return null;
    }

    public List<Person> getPersonBy(Optional<String> name,Optional<String> vorname){

        if(name.isPresent()){return personRepository.findByName(name);}
        if(name.isPresent()){return personRepository.findByVorname(vorname);}
        return personRepository.findAll();
    }

}
