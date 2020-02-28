package com.concon.graphqlexample.controller;

import com.concon.graphqlexample.model.Beruf;
import com.concon.graphqlexample.repo.BerufRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins="http://localhost:8082")
@RequestMapping("/api")
public class BerufController {
    @Autowired
    private BerufRepository berufRepository;

    @GetMapping(path="/berufe")
    public List<Beruf>  getAllBeruf(){
        return berufRepository.findAll();
    }

    @PutMapping(path="{berufId}")
    public void  updateBeruf( @PathVariable("berufId") Integer berufId,  @RequestBody  @Valid Beruf beruf){
     if(berufId!=null){
         Beruf updateBeruf =null;
         updateBeruf = berufRepository.findById(berufId).get();
         if(updateBeruf!=null){
             updateBeruf.setId(updateBeruf.getId());
             updateBeruf.setName(beruf.getName());
             updateBeruf.setDescription(beruf.getDescription());
             berufRepository.save(updateBeruf);
         }
     }

    }

    @PostMapping
    public void addNewBeruf(@RequestBody @Valid Beruf beruf) {
        berufRepository.save(beruf);
    }


    @DeleteMapping("{berufid}")
    public void deleteStudent(@PathVariable("berufid") Integer berufid) {
        berufRepository.deleteById(berufid);
    }


}
