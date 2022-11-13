/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPU.LC4.TPI_2w2.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ortiz
 */
@Getter 
@Setter
public class DTOListadoReciboSueldosPorAnioMes {
    public float sueldo_neto;
    public String area; 
    
    public DTOListadoReciboSueldosPorAnioMes(){}
    
    public DTOListadoReciboSueldosPorAnioMes(float sueldo_neto, String area){
        this.sueldo_neto = sueldo_neto;
        this.area = area;
    }
}
