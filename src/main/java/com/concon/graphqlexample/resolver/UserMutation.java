package com.concon.graphqlexample.resolver;

import com.concon.graphqlexample.auth.AuthData;
import com.concon.graphqlexample.jwt.JwtConfig;
import com.concon.graphqlexample.model.SigninPayload;
import com.concon.graphqlexample.model.User;
import com.concon.graphqlexample.repo.UserRepository;
import com.concon.graphqlexample.security.Unsecured;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.GraphQLException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;

@Component
public class UserMutation implements GraphQLMutationResolver {

    private final UserRepository userRepository;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
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
BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserMutation(UserRepository userRepository, JwtConfig jwtConfig, SecretKey secretKey) {
        this.userRepository = userRepository;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }


    @Unsecured
    public User createuser(String email,String password){
        User newUser = new User(email, password);
        System.out.println(passwordEncoder.encode(password));
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





@Unsecured
    public SigninPayload login(String email,String password) throws IllegalAccessException{
        //if((email!=null&&email.length()!=0)&&(password.length()!=0&&password==null)){
        User user = userRepository.findByEmail(email).get();
    System.out.println(user.getPassword());
        if (user.getPassword().equals(password)) {
            Claims claims = Jwts.claims()
                    .setSubject(user.getEmail());
            claims.put("password", String.valueOf(user.getPassword()));
          //  claims.put("role", jwtUser.getRole());

            String token = Jwts.builder()
//            .setSubject(authResult.getName())
        //            .claim("authorities", authResult.getAuthorities())
                    .setIssuedAt(new Date())
                    .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(10)))
                    .signWith(secretKey)
                    .compact();
            return new SigninPayload(user,token);
        }//}
        throw new GraphQLException("Invalid credentials");

    }
}
