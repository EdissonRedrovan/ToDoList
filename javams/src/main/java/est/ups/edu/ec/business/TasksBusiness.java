package est.ups.edu.ec.business;

import est.ups.edu.ec.database.entity.TaskEntity;
import est.ups.edu.ec.database.service.TasksRepositoryService;
import est.ups.edu.ec.model.response.FrameOutResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@ApplicationScoped
public class TasksBusiness {

    @Inject
    TasksRepositoryService tasksRepositoryService;

    /*public FrameOutResponse getAlltasks(Map<String, String> emailParameters) {
        FrameOutResponse frameOutResponse = new FrameOutResponse();
        if(emailParameters.isEmpty()) {
            frameOutResponse.setCode("97");
            frameOutResponse.setMessage("NO SE HA ENVIADO EL EMAIL DEL USUARIO");
        }else if(emailParameters.get("userEmail") == null){
            frameOutResponse.setCode("96");
            frameOutResponse.setMessage("NO SE HA ENVIADO EL EMAIL DEL USUARIO");
        } else if (emailParameters.get("userEmail").isEmpty()) {
            frameOutResponse.setCode("95");
            frameOutResponse.setMessage("NO SE HA ENVIADO EL EMAIL DEL USUARIO");
        }else{
            String userEmail = emailParameters.get("userEmail");
            List<TaskEntity> tasks = tasksRepositoryService.getTasks(userEmail);
            frameOutResponse.setCode("00");
            frameOutResponse.setMessage("Ok");
            frameOutResponse.setFrame(tasks);
        }
        return frameOutResponse;
    }*/
    public FrameOutResponse getAlltasks(String userEmail) {
        FrameOutResponse frameOutResponse = new FrameOutResponse();
        List<TaskEntity> tasks = tasksRepositoryService.getTasks(userEmail);
        frameOutResponse.setCode("00");
        frameOutResponse.setMessage("Ok");
        frameOutResponse.setFrame(tasks);

        return frameOutResponse;
    }

    public FrameOutResponse addTask(TaskEntity taskEntity){
        FrameOutResponse frameOutResponse = new FrameOutResponse();
        try{
            tasksRepositoryService.addTask(taskEntity);
            frameOutResponse.setCode("00");
            frameOutResponse.setMessage("Ok");
        }catch(Exception e){
            frameOutResponse.setCode("98");
            frameOutResponse.setMessage("ERROR AL CREAR TAREA" + e.getCause());
        }
        return frameOutResponse;
    }

    public FrameOutResponse updateTask(TaskEntity taskEntity) {
        FrameOutResponse frameOutResponse = new FrameOutResponse();
            TaskEntity taskEntityAux = tasksRepositoryService.findById(taskEntity.getId());
            if(taskEntityAux == null){
                frameOutResponse.setCode("92");
                frameOutResponse.setMessage("NO SE HA ENCONTRADO LA TAREA");
            }else{
                taskEntityAux.setName(taskEntity.getName());
                taskEntityAux.setDescription(taskEntity.getDescription());
                taskEntityAux.setCreatedDate(taskEntity.getCreatedDate());
                taskEntityAux.setStartDate(taskEntity.getStartDate());
                taskEntityAux.setEndDate(taskEntity.getEndDate());
                taskEntityAux.setCompleted(taskEntity.getCompleted());
                tasksRepositoryService.updateTask(taskEntityAux);
                frameOutResponse.setCode("00");
                frameOutResponse.setMessage("Ok");
            }
        return frameOutResponse;
    }

    public FrameOutResponse deleteTask(Map<String, Long> task){
        FrameOutResponse frameOutResponse = new FrameOutResponse();
        if(task == null){
            frameOutResponse.setCode("91");
            frameOutResponse.setMessage("NO SE HA ENVIADO ID DE TAREA");
            return frameOutResponse;
        }
        if(task.isEmpty()){
            frameOutResponse.setCode("90");
            frameOutResponse.setMessage("NO SE HA ENVIADO ID DE TAREA");
            return frameOutResponse;
        }
        if(!task.containsKey("taskId")){
            frameOutResponse.setCode("89");
            frameOutResponse.setMessage("NO SE HA ENVIADO ID DE TAREA");
            return frameOutResponse;
        }
        long taskId = task.get("taskId");
        TaskEntity taskEntity = tasksRepositoryService.findById(taskId);
        if(taskEntity == null){
            frameOutResponse.setCode("88");
            frameOutResponse.setMessage("NO SE HA ENCONTRADO LA TAREA");
            return frameOutResponse;
        }
        boolean success = tasksRepositoryService.deleteTask(taskId);
        if (success){
            frameOutResponse.setCode("00");
            frameOutResponse.setMessage("Ok");
        }else{
            frameOutResponse.setCode("87");
            frameOutResponse.setMessage("NO SE HA PODIDO BORRAR LA TAREA");
        }
        return frameOutResponse;
    }
}
