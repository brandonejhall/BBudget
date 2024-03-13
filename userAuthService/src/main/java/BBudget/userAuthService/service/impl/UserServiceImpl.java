package BBudget.userAuthService.service.impl;

import BBudget.userAuthService.model.User;
import BBudget.userAuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import  BBudget.userAuthService.service.UserService;


import java.util.List;



@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user){
        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setFirstName(user.getFirstName());
        newUser.setFirstName(user.getLastName());
        return  userRepository.save(newUser);
    }
    @Override
    public List<User> findAllUsers(){
        return userRepository.findAll();

    }
    @Override
    public User updateUser(User user){
        return
                userRepository.save(user);
    }

    @Override
    public void deleteUser(User user){
        userRepository.delete(user);
    }

    @Override
    public User getUserById(String Email){
        return userRepository.findUserByEmail(Email);
    }




}
