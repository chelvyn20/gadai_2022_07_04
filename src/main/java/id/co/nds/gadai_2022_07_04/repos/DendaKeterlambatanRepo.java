package id.co.nds.gadai_2022_07_04.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.DendaKeterlambatanEntity;

@Repository
public interface DendaKeterlambatanRepo extends JpaRepository<DendaKeterlambatanEntity, String>{
    
}