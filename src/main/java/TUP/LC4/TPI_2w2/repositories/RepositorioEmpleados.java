/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.repositories;


import TUP.LC4.TPI_2w2.models.Empleado;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Iñaki
 */
@Repository
public class RepositorioEmpleados {
    @PersistenceContext
    private EntityManager em;

    public List<Empleado> getEmpleados(){
        try{
            return em.createQuery("select e from Empleado e", Empleado.class).getResultList();
        }catch (Exception ex){
            throw ex;
        }
    }
    
    public Empleado findEmpleadoByLegajo(int legajo){
        try{
            String query = String.format("select e from Empleado e where legajo = %s", legajo);
            return em.createQuery(query, Empleado.class).getSingleResult();
        }catch (Exception ex){
            throw ex;
        }
    }
}
