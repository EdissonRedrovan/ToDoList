package est.ups.edu.ec.database.service;

import est.ups.edu.ec.database.entity.TaskEntity;
import est.ups.edu.ec.database.repository.TasksRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@ApplicationScoped
@Transactional
public class TasksRepositoryService {

    @Inject
    TasksRepository tasksRepository;

    public List<TaskEntity> getTasks(String userEmail) {
        List<TaskEntity> tasks = tasksRepository.listTasks(userEmail);

        if(tasks.isEmpty()){
            log.info("No se encuentro tareas para el usuario : " + userEmail);
        }

        return tasks;
    }

    public void addTask(TaskEntity taskEntity) {
        tasksRepository.persist(taskEntity);
    }

    public TaskEntity findById(long id) {
        return tasksRepository.findById(id);
    }
    public void updateTask(TaskEntity taskEntity) {
        tasksRepository.getEntityManager().merge(taskEntity);
    }

    public boolean deleteTask(long id) {
        return tasksRepository.deleteById(id);
    }
}
