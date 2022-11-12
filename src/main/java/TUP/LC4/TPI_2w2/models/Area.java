/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ortiz
 */
@Entity
@Table(name = "area")
@Getter @Setter
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id_area;
    
    @Column
    public String descripcion;
    
    @OneToMany(mappedBy="area", fetch=FetchType.EAGER)
    @JsonManagedReference
    private List<Empleado> empleados; 

    public Area(){}
    
    public Area(int id_area, String descripcion, List<Empleado> empleados) {
        this.id_area = id_area;
        this.descripcion = descripcion;
        this.empleados = empleados;
    }
}
