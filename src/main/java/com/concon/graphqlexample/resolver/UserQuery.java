package com.concon.graphqlexample.resolver;

import com.concon.graphqlexample.model.User;
import com.concon.graphqlexample.repo.UserRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserQuery implements GraphQLQueryResolver {
    private UserRepository userRepository;

    public User getUser(Integer id){
        if(id!=null) {
         return userRepository.findById(id).get();
        }
        return null;
    }

    public User getUserByEmail(String email){
       if(email!=null&&email.length()!=0){
           return userRepository.findByEmail(email).orElseThrow(() ->
                   new UsernameNotFoundException(String.format("Username %s not found", email)));
       }
        return null;
    }
}
