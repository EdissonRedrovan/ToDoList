package est.ups.edu.ec.database.repository;

import est.ups.edu.ec.database.entity.TaskEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TasksRepository implements PanacheRepositoryBase<TaskEntity, Long> {
    public List<TaskEntity> listTasks(String userEmail) {
        return find("userEmail = :userEmail",
                Parameters.with("userEmail", userEmail.trim()))
                .list();
    }
}