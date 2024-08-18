package est.ups.edu.ec.database.service;

import est.ups.edu.ec.database.entity.InfoEntity;
import est.ups.edu.ec.database.repository.InfoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
@Transactional
public class InfoRepositoryService {

    @Inject
    InfoRepository infoRepository;

    public void createInfo(InfoEntity info){
        infoRepository.persist(info);
    }
}
