package est.ups.edu.ec.database.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="task")
@RegisterForReflection
@Data
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userEmail;
    private String name;
    private String description;
    private Timestamp createdDate;
    private Timestamp startDate;
    private Timestamp endDate;
    private Boolean completed;
}
