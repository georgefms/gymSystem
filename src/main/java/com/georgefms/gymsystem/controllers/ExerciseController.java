package com.georgefms.gymsystem.controllers;

import com.georgefms.gymsystem.models.Exercise;
import com.georgefms.gymsystem.repositories.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@AllArgsConstructor
public class ExerciseController {

    /*
    TODO Post
    TODO Put
    TODO Delete
    TODO Mapear Rotas e acessos
    */

    private final ExerciseRepository repository;
    /**
     * Funcao responsavel por listar todos exercicios existentes
     * retorna uma lista de Exercicios.
     */
    @GetMapping
    public List<Exercise> getAll(){
      return repository.findAll();
    };

    /**
     * Funcao responsavel por listar um exercicio especifico
     * Recebe um Id como parametro pela url
     * Retorna uma entidade, caso encotrada com um 200, ou um 404 se nao houver
     */
    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }
}
