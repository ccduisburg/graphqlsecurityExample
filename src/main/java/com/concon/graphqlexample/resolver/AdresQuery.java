package com.concon.graphqlexample.resolver;

import com.concon.graphqlexample.model.Adres;
import com.concon.graphqlexample.repo.AdresRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class AdresQuery  implements GraphQLQueryResolver {

    private final AdresRepository adresRepository;

    public Adres getAdres(Integer Id){
        if(Id!=null){
            return adresRepository.findById(Id).get();
        }
        return null;
    }

    public List<Adres> getAllAdres(){
        List<Adres> adresList=new ArrayList<>();
        adresList.addAll(adresRepository.findAll());
        if(!adresList.isEmpty()){
            return adresList;
        }

        return null;
    }
}
