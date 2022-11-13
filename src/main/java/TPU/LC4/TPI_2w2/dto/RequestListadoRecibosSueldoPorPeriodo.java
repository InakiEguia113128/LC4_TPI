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
public class RequestListadoRecibosSueldoPorPeriodo {
    public Date fecha_recibo;
    public int mes_recibo;
    public int anio_recibo;
}
