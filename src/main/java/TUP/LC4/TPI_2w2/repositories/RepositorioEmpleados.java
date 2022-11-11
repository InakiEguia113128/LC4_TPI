/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.repositories;


import TUP.LC4.TPI_2w2.models.Empleado;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Iñaki
 */
@Repository
public interface RepositorioEmpleados extends JpaRepository<Empleado, Integer>{
    
    
}
