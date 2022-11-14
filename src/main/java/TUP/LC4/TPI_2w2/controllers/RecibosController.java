/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.controllers;

import TPU.LC4.TPI_2w2.dto.DTOEmpleado;
import TPU.LC4.TPI_2w2.dto.RequestListadoRecibosSueldoPorPeriodo;
import TUP.LC4.TPI_2w2.commands.PostReciboSueldo;
import TUP.LC4.TPI_2w2.repositories.RepositorioEmpleados;
import TUP.LC4.TPI_2w2.repositories.RepositorioReciboSueldo;
import TUP.LC4.TPI_2w2.resultados.ResultadoBase;
import java.util.Date;
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
            var empleadoDto = (DTOEmpleado) resultado.getResultado();
            resultado = reciboRepo.existeReciboPorLegajoFecha(reciboSueldo.legajo, reciboSueldo.fechaRecibo);
            if (resultado.code == 400) {
                return new ResponseEntity(resultado, HttpStatus.BAD_REQUEST);
            } else {
                resultado = reciboRepo.insertReciboSueldo(reciboSueldo, empleadoDto);
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

    @GetMapping("/getDtoListadoReciboSueldosPorLegajo/{legajo}")
    public ResponseEntity<ResultadoBase> getDtoListadoReciboSueldosPorLegajo(@PathVariable int legajo) {
        var resultado = reciboRepo.GetDtoListadoReciboSueldosPorLegajo(legajo);
        if (resultado.code == 200) {
            return new ResponseEntity(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDtoListadoReciboSueldosPorFecha")
    public ResponseEntity<ResultadoBase> getDtoListadoReciboSueldosPorFecha(@RequestBody RequestListadoRecibosSueldoPorPeriodo dto) {
        var resultado = reciboRepo.GetDtoListadoReciboSueldosPorFecha(dto.getFecha_recibo());
        if (resultado.code == 200) {
            return new ResponseEntity(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDtoListadoReciboSueldosPorAnioMes")
    public ResponseEntity<ResultadoBase> getDtoListadoReciboSueldosPorAnioMes(@RequestBody RequestListadoRecibosSueldoPorPeriodo dto) {
        var resultado = reciboRepo.GetDtoListadoReciboSueldosPorAnio(dto.getAnio_recibo(), dto.getMes_recibo());

        if (resultado.code == 200) {
            return new ResponseEntity(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
