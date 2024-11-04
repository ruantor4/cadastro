package com.torquato.cadastro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.torquato.cadastro.models.User;
import com.torquato.cadastro.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return (List<User>) repository.findAll();
    }

    public User findById(Long id){
        Optional<User> user = repository.findById(id);
       
        return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado! Id: " 
        + id + ", Tipo: "+ User.class.getName()));

    }

    public User insert(User obj){
        obj.setId(null);
        return obj = repository.save(obj);
    }

    public User update(User obj){
        User newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
        
    }

    private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
        newObj.setSenha(obj.getSenha());
        newObj.setPhone(obj.getPhone());
	}

    public void delete(Long id){
        findById(id);
        
    }

}