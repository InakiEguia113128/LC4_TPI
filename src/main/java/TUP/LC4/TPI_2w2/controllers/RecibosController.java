/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.controllers;

import TPU.LC4.TPI_2w2.dto.DTOEmpleado;
import TUP.LC4.TPI_2w2.commands.PostReciboSueldo;
import TUP.LC4.TPI_2w2.repositories.RepositorioEmpleados;
import TUP.LC4.TPI_2w2.repositories.RepositorioReciboSueldo;
import TUP.LC4.TPI_2w2.resultados.ResultadoBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ortiz
 */
@RestController
@RequestMapping("/recibo")
public class RecibosController {

    @Autowired
    private RepositorioReciboSueldo reciboRepo;

    @Autowired
    private RepositorioEmpleados empRepo;

    @PostMapping("/registrarReciboSueldo")
    public ResponseEntity<ResultadoBase> registrarReciboSueldo(@RequestBody PostReciboSueldo reciboSueldo) {
        var resultado = empRepo.findEmpleadoByLegajo(reciboSueldo.legajo);
        if (resultado.code == 200) {
            if (reciboRepo.existeReciboPorLegajoFecha(reciboSueldo.legajo, reciboSueldo.fechaRecibo).code == 400) {
                return new ResponseEntity(resultado, HttpStatus.BAD_REQUEST);
            } else {
                resultado = reciboRepo.insertReciboSueldo(reciboSueldo, (DTOEmpleado) resultado.getResultado());
                if (resultado.code == 200) {
                    return new ResponseEntity(resultado, HttpStatus.OK);
                } else {
                    return new ResponseEntity(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else if (resultado.code == 400) {
            return new ResponseEntity(resultado, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
