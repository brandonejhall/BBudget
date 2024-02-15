package BBudget.userAuthService.service;

import BBudget.userAuthService.model.User;
import BBudget.userAuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
       return  userRepository.save(user);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();

    }

    public User updateUser(User user){
        return
                userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public User getUserById(String Email){
        return userRepository.findUserByEmail(Email);
    }

}
