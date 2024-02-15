package BBudget.userAuthService.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("User")
public class User {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}


