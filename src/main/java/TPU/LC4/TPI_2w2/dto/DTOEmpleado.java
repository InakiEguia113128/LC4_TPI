/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPU.LC4.TPI_2w2.dto;

import TUP.LC4.TPI_2w2.models.Area;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Iñaki
 */
 @Getter @Setter
public class DTOEmpleado {


    public int id_empleado;
    
    public int legajo;
    
    public String nombre;
    
    public String apellido;
    
    public Date fecha_nac;
    
    public Date fecha_ingreso;
    
    public int aniosDeAntiguedad;
    
    public float sueldo_bruto;
    
    public int id_area;
    
    public String descripcionArea;
    
    public Area area;
    
    public DTOEmpleado(){}
    
    public DTOEmpleado(int id_empleado, int legajo, String nombre, String apellido, Date fecha_nac, Date fecha_ingreso, float sueldo_bruto, int id_area, int ant, String area) {
        this.id_empleado = id_empleado;
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nac = fecha_nac;
        this.fecha_ingreso = fecha_ingreso;
        this.sueldo_bruto = sueldo_bruto;
        this.id_area = id_area;
        this.aniosDeAntiguedad = ant;
        this.descripcionArea = area;
    }
    
    public DTOEmpleado(int id_empleado, int legajo, String nombre, String apellido, Date fecha_nac, Date fecha_ingreso, float sueldo_bruto, int id_area, int ant, Area area) {
        this.id_empleado = id_empleado;
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nac = fecha_nac;
        this.fecha_ingreso = fecha_ingreso;
        this.sueldo_bruto = sueldo_bruto;
        this.id_area = id_area;
        this.aniosDeAntiguedad = ant;
        this.area = area;
    }
}
    

