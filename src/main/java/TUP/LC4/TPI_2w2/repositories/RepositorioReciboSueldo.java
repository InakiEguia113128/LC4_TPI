/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.repositories;

import TUP.LC4.TPI_2w2.models.Empleado;
import TUP.LC4.TPI_2w2.models.Recibo;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import javax.persistence.PersistenceContext;


/**
 *
 * @author ortiz
 */
@Repository
public class RepositorioReciboSueldo {
    @PersistenceContext
    private EntityManager em;
}
