/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.controllers;

import TPU.LC4.TPI_2w2.dto.DTOEmpleado;
import TUP.LC4.TPI_2w2.commands.PostEmpleado;
import TUP.LC4.TPI_2w2.models.Empleado;
import TUP.LC4.TPI_2w2.repositories.RepositorioAreas;
import TUP.LC4.TPI_2w2.repositories.RepositorioEmpleados;
import TUP.LC4.TPI_2w2.resultados.ResultadoBase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Iñaki
 */
@RestController
@RequestMapping("/empleado")
public class EmpleadosController {

    @Autowired
    private RepositorioEmpleados repoEmpleado;
    @Autowired
    private RepositorioAreas repoArea;

    @GetMapping("/getEmpleados")
    public ResponseEntity<List<DTOEmpleado>> getEmpleados() {
        return ResponseEntity.ok(repoEmpleado.getEmpleados());
    }

    @GetMapping("/getEmpleadoByLegajo/{legajo}")
    public ResponseEntity<ResultadoBase> getEmpleadoByLegajo(@PathVariable int legajo) {
        ResultadoBase resultado = repoEmpleado.findEmpleadoByLegajo(legajo);

        if (resultado.code == 200) {
            return new ResponseEntity(resultado, HttpStatus.OK);
        } else if (resultado.code == 500) {
            return new ResponseEntity(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity(resultado, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/postEmpleado")
    public ResponseEntity<ResultadoBase> postEmpleado(@RequestBody PostEmpleado comando) {

        ResultadoBase resultado = this.repoEmpleado.findEmpleadoByLegajo(comando.legajo);
        if (resultado.code == 400) {
            resultado = repoArea.getAreaById(comando.id_area);
            if (resultado.code == 200) {

                resultado = repoEmpleado.postEmpleado(comando);

                if (resultado.code == 200) {

                    return new ResponseEntity(resultado, HttpStatus.OK);
                } else {
                    return new ResponseEntity(resultado, HttpStatus.BAD_REQUEST);
                }
            }
        } else {
            resultado.setCode(400);
            resultado.setMessage("Este legajo ya esta registrado");
            return new ResponseEntity(resultado, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(resultado, HttpStatus.BAD_REQUEST);
    }
}
