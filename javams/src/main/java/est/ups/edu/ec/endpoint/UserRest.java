package est.ups.edu.ec.endpoint;

import est.ups.edu.ec.business.UserBusiness;
import est.ups.edu.ec.database.entity.UserEntity;
import est.ups.edu.ec.model.response.FrameOutResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;


@Path("/")
public class UserRest {



    @Inject
    UserBusiness business;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/adduser")
    public Response addNewUser(UserEntity user){
        FrameOutResponse response = business.addNewUser(user);
        return Response.ok().entity(response).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/loginuser")
    public Response loginUser(Map<String,String> user){
        FrameOutResponse response = business.loginUser(user);
        return Response.ok().entity(response).build();
    }

}
