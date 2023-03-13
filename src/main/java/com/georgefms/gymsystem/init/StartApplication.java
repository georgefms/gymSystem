package com.georgefms.gymsystem.init;

import com.georgefms.gymsystem.models.Exercise;
import com.georgefms.gymsystem.models.User;
import com.georgefms.gymsystem.repositories.ExerciseRepository;
import com.georgefms.gymsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class StartApplication implements CommandLineRunner {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        User user = repository.findByUsername("admin");
        if(user == null){
            user = new User();
            user.setName("ADMIN");
            user.setUsername("admin");
            user.setPassword("master123");
            user.getRoles().add("ADMIN");
            repository.save(user);
        }
        user = repository.findByUsername("user");
        if(user ==null){
            user = new User();
            user.setName("USER");
            user.setUsername("user");
            user.setPassword("user123");
            user.getRoles().add("USERS");
            repository.save(user);
        }
        exerciseRepository.deleteAll();
        Exercise e = new Exercise();
        e.setName("Supino Reto");
        e.setMuscularGroup("Peitoral");
        e.setExample("Exercicio com barra no banco 180 graus");
        exerciseRepository.save(e);
    }
}