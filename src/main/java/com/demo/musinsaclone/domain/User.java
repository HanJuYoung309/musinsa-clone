package com.demo.musinsaclone.domain;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length=255)
    private String email;

    @Column(nullable=false, length=255)
    private String passwordHash;

    @Column(nullable=false, length=80)
    private String name;

    @Column(nullable=false, length=20)
    private String role; // USER, ADMIN
}