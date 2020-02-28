package com.concon.graphqlexample.resolver;

import com.concon.graphqlexample.model.SigninPayload;
import com.concon.graphqlexample.model.User;
import com.coxautodev.graphql.tools.GraphQLResolver;

public class SigninResolver implements GraphQLResolver<SigninPayload> {

public User user(SigninPayload payload){
        return payload.getUser();
        }
        }
