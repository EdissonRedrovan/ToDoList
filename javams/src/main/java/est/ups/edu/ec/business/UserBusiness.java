package est.ups.edu.ec.business;

import est.ups.edu.ec.database.entity.InfoEntity;
import est.ups.edu.ec.database.entity.UserEntity;
import est.ups.edu.ec.database.service.InfoRepositoryService;
import est.ups.edu.ec.database.service.UserRepositoryService;
import est.ups.edu.ec.model.response.FrameOutResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.Optional;

@Slf4j
@ApplicationScoped
public class UserBusiness {

    @Inject
    UserRepositoryService serviceUser;

    @Inject
    InfoRepositoryService serviceInfo;

    public FrameOutResponse addNewUser(UserEntity user){

        FrameOutResponse frameOutResponse = new FrameOutResponse();

        try{

            UserEntity u = new UserEntity();
            u.setName(user.getName());
            u.setUserType(user.getUserType());
            u.setPassword(DigestUtils.md5Hex(user.getPassword()));
            u.setEmail(user.getEmail());
            serviceUser.createUser(u);

            InfoEntity i = new InfoEntity();
            i.setEmail(user.getEmail());
            i.setName(user.getName());
            serviceInfo.createInfo(i);
            frameOutResponse.setCode("00");
            frameOutResponse.setMessage("OK");

            return frameOutResponse;
        }catch (Exception e){
            frameOutResponse.setCode("99");
            frameOutResponse.setMessage("ERROR AL REGISTRAR USUARIO");
            e.printStackTrace();
            return frameOutResponse;
        }

    }


   public FrameOutResponse loginUser(Map<String,String> user){
       String email = user.get("email");
       String password = DigestUtils.md5Hex(user.get("password"));

       Optional<UserEntity> userFind = serviceUser.authenticateUser(email,password);

       FrameOutResponse frameOutResponse = new FrameOutResponse();
       if(userFind.isPresent()){
           UserEntity userEntity = userFind.get();
           log.info("Usuario autenticado: " + email);
           frameOutResponse.setCode("00");
           frameOutResponse.setMessage("OK");
           frameOutResponse.setFrame(userEntity);
           return frameOutResponse;
       }else{
           log.info("Credenciales incorrectas: ");
           frameOutResponse.setCode("98");
           frameOutResponse.setMessage("CREDENCIALES INCORRECTAS");
           return frameOutResponse;
       }

    }
}
