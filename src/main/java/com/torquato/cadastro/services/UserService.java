package com.torquato.cadastro.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.torquato.cadastro.models.User;
import com.torquato.cadastro.repositories.UserRepository;
import com.torquato.cadastro.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

   
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));

    }

    public User insert(User obj) {
        String encoder = this.passwordEncoder.encode(obj.getPassword());
        obj.setPassword(encoder);
        obj = repository.save(obj);
        return obj;
        }
    

    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        String encoder = this.passwordEncoder.encode(obj.getPassword());
        obj.setPassword(encoder);
    
        return repository.save(newObj);

    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);

    }

    public Boolean validarSenha(User obj) {
        String password = repository.getReferenceById(obj.getId()).getPassword();
        boolean valid = passwordEncoder.matches(obj.getPassword(), password);
        return valid;
    }

     
}
