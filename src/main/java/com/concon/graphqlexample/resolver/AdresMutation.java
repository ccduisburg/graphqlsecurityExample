package com.concon.graphqlexample.resolver;


import com.concon.graphqlexample.model.Adres;
import com.concon.graphqlexample.repo.AdresRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdresMutation implements GraphQLMutationResolver {

    private final AdresRepository adresRepository;

    public Adres createAdres(Adres adres) {
        adresRepository.save(adres);
        return adres;

    }

    public Adres updateAdres(Integer id, Adres adres) {
        Adres adresUpdate = adresRepository.findById(id).get();
        adresUpdate.setId(adres.getId());
        adresUpdate.setStrasse(adres.getStrasse());
        adresUpdate.setNo(adres.getNo());
        adresUpdate.setPlz(adres.getPlz());
        adresUpdate.setStadt(adres.getStadt());
        if (adresUpdate != null) {
            adresRepository.save(adres);
            return adresUpdate;
        }
        return null;
    }

    public Boolean deleteAdres(Integer id) {
        Adres adresDelete = adresRepository.findById(id).get();
        if (adresDelete != null) {
            adresRepository.delete(adresDelete);
            return true;
        }
        return false;

    }
}
