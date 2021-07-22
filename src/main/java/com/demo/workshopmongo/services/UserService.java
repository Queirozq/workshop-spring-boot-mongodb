package com.demo.workshopmongo.services;

import com.demo.workshopmongo.domain.User;
import com.demo.workshopmongo.dto.UserDTO;
import com.demo.workshopmongo.repository.UserRepository;
import com.demo.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(String id){
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Id não existe"));
    }

    public User insert(User obj){
        return repository.insert(obj);
    }

    public User fromDTO(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }

    public User update(User user){
        User obj = findById(user.getId());
        updateData(obj,user);
        return repository.save(obj);
    }


    public void updateData(User entity, User obj){
       entity.setName(obj.getName());
       entity.setEmail(obj.getEmail());
    }

}
