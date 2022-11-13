/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.controllers;

import TUP.LC4.TPI_2w2.commands.PostArea;
import TUP.LC4.TPI_2w2.repositories.RepositorioAreas;
import TUP.LC4.TPI_2w2.resultados.ResultadoBase;
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
@RequestMapping("/area")
public class AreasController {

    @Autowired
    private RepositorioAreas repo;

    @PostMapping("/agregarArea")
    public ResponseEntity<String> addArea(@RequestBody PostArea area) {
        var resultado = repo.getAreaByDescripcion(area.Descripcion);

        if (resultado.resultado == null && resultado.code != 500) {
            resultado = repo.insertArea(area);

            if (resultado.code == 200) {
                return new ResponseEntity(resultado.message, HttpStatus.OK);
            } else {
                return new ResponseEntity(resultado.message, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(String.format("El área %s area ya existe en la base de datos.", area.Descripcion), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAreaByDescripcion/{descripcion}")
    public ResponseEntity<ResultadoBase> getAreaByDescripcion(@PathVariable String descripcion) {
        var resultado = repo.getAreaByDescripcion(descripcion);
        if (resultado.code == 200) {
            return new ResponseEntity(resultado, HttpStatus.OK);

        } else if (resultado.code == 400) {
            return new ResponseEntity(resultado, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAreaById/{id}")
    public ResponseEntity<ResultadoBase> getAreaByDescripcion(@PathVariable int id) {
        var resultado = repo.getAreaById(id);
        if (resultado.code == 200) {
            return new ResponseEntity(resultado, HttpStatus.OK);

        } else if (resultado.code == 400) {
            return new ResponseEntity(resultado, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
