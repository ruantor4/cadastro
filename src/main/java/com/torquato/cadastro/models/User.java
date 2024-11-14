package com.torquato.cadastro.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

   
	@NotNull(message = "O nome não pode ser nulo.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre {min} e {max} caracteres.")
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    
	@NotNull(message = "O email não pode ser nulo.")
    @Size(min = 2, max = 100, message = "O email deve ter entre {min} e {max} caracteres.")
    @Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "O email deve ter um formato válido.")
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;
   
    
	@NotNull(message = "A senha não pode ser nula.")
    @Size(min = 8, max = 60, message = "A senha deve ter entre {min} e {max} caracteres.")
    @Column(name = "password", length = 60, nullable = false)
    private String password;
   
    
    @NotNull(message = "O telefone não pode ser nulo.")
    @Size(max = 15, message = "O telefone deve ter no máximo {max} caracteres.")
    @Pattern(regexp = "^\\+?[0-9]*$", message = "O telefone deve conter apenas números e pode começar com '+'.")
    @Column(name = "telefone", length = 15, nullable = false)
    private String phone;

}
