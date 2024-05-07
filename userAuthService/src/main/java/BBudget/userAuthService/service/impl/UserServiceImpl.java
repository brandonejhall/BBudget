package BBudget.userAuthService.service.impl;

import BBudget.userAuthService.model.User;
import BBudget.userAuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import  BBudget.userAuthService.service.UserService;


import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user){
        if (user == null) {
            System.out.println("User Null");
            throw new IllegalArgumentException("User object cannot be null");
        }

        // Validate email format
        String email = user.getEmail();
        if (email == null || !isValidEmail(email)) {
            System.out.println("Bad Email");
            throw new IllegalArgumentException("Invalid email address");
        }

        // Validate password strength
        String password = user.getPassword();
        if (password == null || !isValidPassword(password)) {
            System.out.println("Bad Password");
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }

        // Check if the email is already in use
        if (userRepository.findUserByEmail(email) != null) {
            System.out.println("Email already there");
            throw new RuntimeException("Email address is already in use");
        }


        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        try {
            newUser = userRepository.save(newUser);
            if (newUser == null) {
                throw new RuntimeException("User creation failed");
            }
            return newUser;
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("User creation failed: " + e.getMessage());
        }


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
    public Optional<User> getUserById(String Email){
        return userRepository.findUserByEmail(Email);
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }


    private boolean isValidEmail(String email) {
        // Regular expression for basic email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return email != null && pattern.matcher(email).matches();
    }

    // Method to validate password strength
    private boolean isValidPassword(String password) {
        // Password must be at least 8 characters long
        // It must contain at least one digit, one uppercase letter, one lowercase letter, and one special character
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return password != null && pattern.matcher(password).matches();
    }

}
