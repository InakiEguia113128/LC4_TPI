/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.repositories;

import TUP.LC4.TPI_2w2.models.Empleado;
import TUP.LC4.TPI_2w2.models.ReciboSueldo;
import org.springframework.stereotype.Repository;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ortiz
 */
@Repository
public interface RepositorioReciboSueldo extends JpaRepository<ReciboSueldo, Integer>{
    
}
