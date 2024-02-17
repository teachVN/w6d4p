package it.epicode.w6d4p.service;

import it.epicode.w6d4p.DTO.UserDTO;
import it.epicode.w6d4p.exception.EmailYetUsedException;
import it.epicode.w6d4p.exception.NotFoundException;
import it.epicode.w6d4p.model.User;
import it.epicode.w6d4p.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO userDTO){
        if (userRepository.findUserByEmail(userDTO.getEmail()).isPresent()){
            throw new EmailYetUsedException("Email già in uso");
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElseThrow(()->new NotFoundException("User non trovato"));
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email).orElseThrow(()->new NotFoundException("User non trovato"));
    }


}
