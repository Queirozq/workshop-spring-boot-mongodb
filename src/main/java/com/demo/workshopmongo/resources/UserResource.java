package com.demo.workshopmongo.resources;

import com.demo.workshopmongo.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

       @GetMapping
       public ResponseEntity<List<User>> findAll(){
              User matheus = new User("1001", "Matheus Queiroz", "scririft@gmail.com");
              User suruga = new User("1002", "Kanbaru Suruga", "suruga@gmail.com");
              List<User> list = new ArrayList<>(Arrays.asList(matheus, suruga));
              return ResponseEntity.ok().body(list);
       }
}
