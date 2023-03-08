package com.georgefms.gymsystem.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 120)
    @NotNull
    @NotBlank
    private String name;
    @Column(length = 50)
    @NotBlank
    @NotNull
    private String muscularGroup;
    // Futuramente um URL com a execução do exercicio
    @Column(length = 150)
    private String example;
}
