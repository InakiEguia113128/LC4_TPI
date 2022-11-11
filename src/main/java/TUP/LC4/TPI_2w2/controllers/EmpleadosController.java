/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.controllers;

import TUP.LC4.TPI_2w2.models.Empleado;
import TUP.LC4.TPI_2w2.repositories.RepositorioEmpleados;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Iñaki
 */
@RestController
@RequestMapping("/empleados")
public class EmpleadosController {
    
    @Autowired
    private RepositorioEmpleados repo;
    
    @GetMapping("/getEmpleado")
    public ResponseEntity<List<Empleado>> GetListaEmpleados(){
        return ResponseEntity.ok(repo.findAll());
    }
    
    @GetMapping("/getEmpleadoByLegajo/{legajo}")
    public ResponseEntity<Empleado> GetEmpleadoByLegajo(@PathVariable int legajo){
        return ResponseEntity.ok(repo.findEmpleadoByLegajo(legajo));
    }
}
