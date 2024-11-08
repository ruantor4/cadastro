package com.torquato.cadastro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.torquato.cadastro.models.User;
import com.torquato.cadastro.repositories.UserRepository;

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

        return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado! Id: "
                + id + ", Tipo: " + User.class.getName()));

    }

    public User insert(User obj) {
        obj.setId(null);
        String encoder = this.passwordEncoder.encode(obj.getPassword());
        obj.setPassword(encoder);
        return obj = repository.save(obj);
    }

    public User update(User obj) {
        String encoder = this.passwordEncoder.encode(obj.getPassword());
        obj.setPassword(encoder);
        User newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);

    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
        newObj.setPassword(obj.getPassword());
        newObj.setPhone(obj.getPhone());
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