package com.demo.workshopmongo.resources;

import com.demo.workshopmongo.domain.User;
import com.demo.workshopmongo.dto.UserDTO;
import com.demo.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

       @Autowired
       private UserService service;

       @GetMapping
       public ResponseEntity<List<UserDTO>> findAll(){
              List<User> list = service.findAll();
              List<UserDTO> listDto = list.stream().map(UserDTO::new).collect(Collectors.toList());
              return ResponseEntity.ok().body(listDto);
       }

       @GetMapping(value = "/{id}")
       public ResponseEntity<UserDTO> findById(@PathVariable String id){
              User user = service.findById(id);
              UserDTO userDTO = new UserDTO(user);
              return ResponseEntity.ok().body(userDTO);
       }

       @PostMapping
       public ResponseEntity<User> insert(@RequestBody UserDTO objDto){
         User user = service.fromDTO(objDto);
         service.insert(user);
         URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
         return ResponseEntity.created(uri).body(user);
       }

       @DeleteMapping(value = "/{id}")
       public ResponseEntity<Void> deleteById(@PathVariable String id){
              service.deleteById(id);
              return ResponseEntity.noContent().build();
       }
}
