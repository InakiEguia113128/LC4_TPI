/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.controllers;

import TUP.LC4.TPI_2w2.repositories.RepositorioEmpleados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Iñaki
 */

@RestController
public class EmpleadosController {
    @Autowired
    
    private RepositorioEmpleados repo;
       
}
