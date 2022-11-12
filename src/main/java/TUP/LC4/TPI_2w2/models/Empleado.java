/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Iñaki
 */
@Entity
@Table(name = "empleado")
@Getter @Setter
public class Empleado implements Serializable{ 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id_empleado;
    
    @Column
    public int legajo;
    
    @Column
    public String nombre;
    
    @Column
    public String apellido;
    
    @Column
    public Date fecha_nac;
    
    @Column
    public Date fecha_ingreso;
    
    @ManyToOne(targetEntity= Area.class)
    @JoinColumn(name="id_area")
    @JsonManagedReference
    private Area area ;
    
    @OneToMany(mappedBy="empleado", fetch=FetchType.EAGER)
    @JsonManagedReference
    private List<Recibo> recibos; 

    public Empleado() {}

    public Empleado(int id_empleado, int legajo, String nombre, String apellido, Date fechaNacimiento, Date fecha_ingreso, Area area) {
        this.id_empleado = id_empleado;
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nac = fechaNacimiento;
        this.fecha_ingreso = fecha_ingreso;
        this.area = area;
    }
    
}
