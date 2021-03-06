package com.demo.workshopmongo.config;

import com.demo.workshopmongo.domain.Post;
import com.demo.workshopmongo.domain.User;
import com.demo.workshopmongo.dto.AuthorDTO;
import com.demo.workshopmongo.dto.CommentDTO;
import com.demo.workshopmongo.repository.PostRepository;
import com.demo.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;



    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User matheus = new User(null, "Matheus Queiroz", "scririft@gmail.com");
        User jorge = new User(null, "Jorginho Pereira", "jorge@gmail.com");
        User toninho = new User(null, "Toninho Andrade", "toninho@gmail.com");

        userRepository.saveAll(Arrays.asList(matheus,jorge,toninho));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem","Vou viajar para São Paulo. Abraços!", new AuthorDTO(matheus));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(matheus));

        CommentDTO comment1 = new CommentDTO("Boa viagem bro", sdf.parse("21/03/2018"), new AuthorDTO(jorge));
        CommentDTO comment2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(toninho));
        CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(jorge));

        post1.getComments().addAll(Arrays.asList(comment1,comment2));
        post2.getComments().add(comment3);

        postRepository.saveAll(Arrays.asList(post1,post2));

        matheus.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(matheus);

    }
}
