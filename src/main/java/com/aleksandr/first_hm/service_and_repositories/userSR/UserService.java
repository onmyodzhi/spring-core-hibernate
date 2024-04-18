package com.aleksandr.first_hm.service_and_repositories.userSR;

import com.aleksandr.first_hm.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User user){
        userRepository.addUser(user);
    }

    public void read(Long id){
        userRepository.readUserById(id);
    }

    public List<User> readAll(){
        return userRepository.readAllUsers();
    }

    public void update(Long id, String name, int age){
        userRepository.updateUserById(id,name,age);
    }

    public void delete(Long id){
        userRepository.deleteUserById(id);
    }
}
