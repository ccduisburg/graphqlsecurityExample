package com.concon.graphqlexample.resolver;

import com.concon.graphqlexample.auth.AuthData;
import com.concon.graphqlexample.model.SigninPayload;
import com.concon.graphqlexample.model.User;
import com.concon.graphqlexample.repo.UserRepository;
import com.concon.graphqlexample.security.Unsecured;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.GraphQLException;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {
    private UserRepository userRepository;

    /*
   createuser(authProvider: AuthData!):User
     deleteuser(id:ID):Boolean
     updateuser(id:ID,auth:AuthData):User
     */

//    public User createUser(String email, AuthData auth) {
//        User newUser = new User(name, auth.getEmail(), auth.getPassword());
//        return userRepository.saveUser(newUser);
//    }

//    public SigninPayload signinUser(AuthData auth) {
//        User user = userRepository.findByEmail(auth.getEmail());
//        if (user.getPassword().equals(auth.getPassword())) {
//            return new SigninPayload(user.getId(), user);
//        }
//        throw new GraphQLException("Invalid credentials");
//    }
//
@Unsecured
    public User createuser(AuthData auth){
        User newUser = new User(auth.getEmail(), auth.getPassword());
        return userRepository.save(newUser);
    }
    public User updateuser(Integer id,AuthData auth){
        if(id!=null) {
            User user = userRepository.findById(id).get();
            user.setPassword(auth.getPassword());
            userRepository.save(user);
        }
        return null;
    }

    public Boolean deleteuser(Integer id){
        if(id!=null){
        userRepository.deleteById(id);
        return true;
    }
    return false;
    }


   String token="bbbbbbbb";
@Unsecured
    public SigninPayload login(AuthData auth) throws IllegalAccessException{
        if((auth.getEmail()!=null&&auth.getEmail().length()!=0)&&(auth.getPassword().length()!=0&&auth.getPassword()==null)){
        User user = userRepository.findByEmail(auth.getEmail()).get();
        if (user.getPassword().equals(auth.getPassword())) {
            return new SigninPayload(user,token);
        }}
        throw new GraphQLException("Invalid credentials");

    }
}
