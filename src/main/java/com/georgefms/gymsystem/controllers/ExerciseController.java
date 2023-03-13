package com.georgefms.gymsystem.controllers;

import com.georgefms.gymsystem.models.Exercise;
import com.georgefms.gymsystem.repositories.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@AllArgsConstructor
public class ExerciseController {

    /*
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

    /**
     * Funcao responsavel por criar um novo exercicio
     * Recebe via Post uma entidade do tipo exercicio
     * Retorna um codigo http em caso de sucesso
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Exercise post(@RequestBody @Valid Exercise exercise){
        return repository.save(exercise);
    };

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> update(@PathVariable @NotNull @Positive Long id, @RequestBody Exercise exercise ){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(exercise.getName());
                    recordFound.setMuscularGroup(exercise.getMuscularGroup());
                    recordFound.setExample(exercise.getExample());
                    Exercise updated = repository.save(recordFound);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id)
                .map(recordFound -> {
                    repository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
