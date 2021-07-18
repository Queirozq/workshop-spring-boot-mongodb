package com.demo.workshopmongo.config;

import com.demo.workshopmongo.domain.User;
import com.demo.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;



    @Override
    public void run(String... args) throws Exception {

      userRepository.deleteAll();

      User matheus = new User(null, "Matheus Queiroz", "scririft@gmail.com");
      User jorge = new User(null, "Jorginho Pereira", "jorge@gmail.com");
      User toninho = new User(null, "Toninho Andrade", "toninho@gmail.com");

      userRepository.saveAll(Arrays.asList(matheus,jorge,toninho));
    }
}
