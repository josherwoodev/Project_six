package swf.army.mil.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swf.army.mil.backend.entity.CarEntity;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
}
