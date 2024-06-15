package com.example.productcatalogservice.TableInheritance.JoinedClass;

import jakarta.persistence.*;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

@Entity(name="jc_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;

    private String email;
}
