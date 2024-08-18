package est.ups.edu.ec.database.service;

import est.ups.edu.ec.database.entity.UserEntity;
import est.ups.edu.ec.database.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@ApplicationScoped
@Slf4j
@Transactional
public class UserRepositoryService {


    @Inject
    UserRepository userRepository;

    public void createUser(UserEntity user) {
        userRepository.persist(user);
    }

    public Optional<UserEntity> authenticateUser(String email, String password) {
        Optional<UserEntity> userOptional = userRepository.loginUser(email, password);

        if(userOptional.isEmpty()){
            log.info("No se encuentro usuario : " + email);
        }

        return userOptional;
    }
}
