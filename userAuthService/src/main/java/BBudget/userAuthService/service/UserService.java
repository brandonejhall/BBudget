package BBudget.userAuthService.service;
import BBudget.userAuthService.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    List<User> findAllUsers();
    User updateUser(User user);
    void deleteUser(User user);
    Optional<User> getUserById(String email);
    User getUserByEmail(String email);
}
