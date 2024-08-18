package est.ups.edu.ec.database.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user_info")
@RegisterForReflection
@Data
public class InfoEntity {

    private String name;

    @Id
    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;

}
