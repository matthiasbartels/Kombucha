package kombucha.eventstore;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OHLCRepository extends CrudRepository<OHLCEntity, Long> {

}
