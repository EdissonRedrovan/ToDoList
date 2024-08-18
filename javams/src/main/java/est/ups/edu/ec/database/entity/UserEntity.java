package est.ups.edu.ec.database.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user_form")
@RegisterForReflection
@Data
public class UserEntity {

    private String name;

    @Id
    private String email;

    private String password;

    private String userType;
}

