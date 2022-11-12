/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.commands;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostReciboSueldo {
    public int legajo; 
    public String fechaRecibo;
    public float porcentajeIncrementoAntiguedad;
    public float porcentajeDescuentoJubilacion;
    public float porcentajDescuentObraSocial;
    public float porcentajeDescuentFondoComplejidad;
}
