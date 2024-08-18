package est.ups.edu.ec.database.repository;

import est.ups.edu.ec.database.entity.InfoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InfoRepository implements PanacheRepositoryBase<InfoEntity,Long> {
}
