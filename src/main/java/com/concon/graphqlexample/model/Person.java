package com.concon.graphqlexample.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Person {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
        private String vorname;
        @ManyToOne(fetch = FetchType.LAZY)
        private Adres adres;
        @ManyToOne(fetch = FetchType.LAZY)
        private Beruf beruf;
        @ManyToMany(fetch = FetchType.EAGER)
        private Set<Person> falovers;
        @ManyToMany(fetch = FetchType.EAGER)
        private Set<Person> fallows;

        public Person() {
        }


}
