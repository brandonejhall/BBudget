package BBudget.userAuthService.service;
import BBudget.userAuthService.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> findAllUsers();
    User updateUser(User user);
    void deleteUser(User user);
    User getUserById(String email);
}
