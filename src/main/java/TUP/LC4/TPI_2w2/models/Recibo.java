/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.models;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Iñaki
 */
@Getter @Setter
public class Recibo {
    public int id_recibo;
            
    public float sueldo_bruto;
    
    public float monto_antiguedad;
    
    public float jubilacion;
    
    public float obra_social;
    
    public float fondo_alta_complejidad;
    
    public int id_empleado;
    
    public Date fecha_recibo;

    public Recibo(){}
    
    public Recibo(int id_recibo, float sueldo_bruto, float monto_antiguedad, float jubilacion, float obra_social, float fondo_alta_complejidad, int id_empleado, Date fecha_recibo) {
        this.id_recibo = id_recibo;
        this.sueldo_bruto = sueldo_bruto;
        this.monto_antiguedad = monto_antiguedad;
        this.jubilacion = jubilacion;
        this.obra_social = obra_social;
        this.fondo_alta_complejidad = fondo_alta_complejidad;
        this.id_empleado = id_empleado;
        this.fecha_recibo = fecha_recibo;
    }
}
