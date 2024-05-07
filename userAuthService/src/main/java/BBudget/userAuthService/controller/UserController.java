package BBudget.userAuthService.controller;

import BBudget.userAuthService.dto.LoginUserDto;
import BBudget.userAuthService.dto.RegisterUserDto;
import BBudget.userAuthService.model.User;
import BBudget.userAuthService.service.impl.UserServiceImpl;
import BBudget.userAuthService.service.JwtService;
import BBudget.userAuthService.service.AuthenticationService;
import BBudget.userAuthService.model.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final JwtService jwtService;
    private final UserServiceImpl userServiceImpl;
    private final AuthenticationService authenticationService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl,
                          AuthenticationService authenticationService,
                          JwtService jwtService) {
        this.userServiceImpl = userServiceImpl;
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpiration(jwtToken));

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userServiceImpl.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }



    @GetMapping("/home")
    public ResponseEntity<String> HelloWorld(){

        return new ResponseEntity<>("Hello World",HttpStatus.OK);

    }
}
