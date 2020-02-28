package com.concon.graphqlexample.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Adres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String strasse;
    private String stadt;
    private String plz;
    private String no;

}
