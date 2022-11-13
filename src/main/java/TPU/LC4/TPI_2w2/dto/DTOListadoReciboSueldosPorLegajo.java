/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPU.LC4.TPI_2w2.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ortiz
 */
@Getter
@Setter
public class DTOListadoReciboSueldosPorLegajo {
    public int legajo;
    public float sueldoNeto;
    public Date fechaRecibo;
    public int mes;
    public int anio;
    
    public DTOListadoReciboSueldosPorLegajo(){}
    
    public DTOListadoReciboSueldosPorLegajo(int legajo, float sueldoNeto, Date fechaRecibo, int mes, int anio){
        this.legajo = legajo;
        this.sueldoNeto = sueldoNeto;
        this.fechaRecibo = fechaRecibo;
        this.mes = mes;
        this.anio = anio;
    }
}
