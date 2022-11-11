/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.repositories;


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
    private EntityManager manager;   
}
