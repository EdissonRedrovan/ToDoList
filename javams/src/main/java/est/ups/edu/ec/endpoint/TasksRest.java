package est.ups.edu.ec.endpoint;

import est.ups.edu.ec.business.TasksBusiness;
import est.ups.edu.ec.database.entity.TaskEntity;
import est.ups.edu.ec.model.response.FrameOutResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Path("/task")
public class TasksRest {

    private static final Logger log = LoggerFactory.getLogger(TasksRest.class);
    @Inject
    TasksBusiness business;

    /*@GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAllTasks(Map<String, String> emailParameters) {
        return Response.ok().entity(business.getAlltasks(emailParameters)).build();
    }*/
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAllTasks(@QueryParam("userEmail") String userEmail) {
        return Response.ok().entity(business.getAlltasks(userEmail)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addTask")
    public Response addTask(TaskEntity taskEntity){
        log.info("Adding task");
        FrameOutResponse response = business.addTask(taskEntity);
        return Response.ok().entity(response).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateTask")
    public Response updateTask(TaskEntity taskEntity){
        log.info("Updating task");
        FrameOutResponse response = business.updateTask(taskEntity);
        return Response.ok().entity(response).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteTask")
    public Response deleteTask(Map<String, Long> task){
        log.info("Borrando tarea...");
        FrameOutResponse response = business.deleteTask(task);
        return Response.ok().entity(response).build();
    }
}
