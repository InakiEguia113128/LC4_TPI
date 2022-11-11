/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.models;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Iñaki
 */
@Entity
@Table(name = "empleados")

public class Empleado {
    
    @Id @Basic
    public int legajo;
    @Basic
    public String nombre;
    @Basic
    public String apellido;
    @Basic
    public Date fechaNacimiento;
    @Basic
    public int antiguedad;
    @Basic
    public String area;
    @Basic
    public float sueldoBruto;

    public Empleado() {
    }

    public Empleado(int legajo, String nombre, String apellido, Date fechaNacimiento, int antiguedad, String area, float sueldoBruto) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.antiguedad = antiguedad;
        this.area = area;
        this.sueldoBruto = sueldoBruto;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public float getSueldoBruto() {
        return sueldoBruto;
    }

    public void setSueldoBruto(float sueldoBruto) {
        this.sueldoBruto = sueldoBruto;
    }

    @Override
    public String toString() {
        return "Legajo " + legajo + ", nombre " + nombre + ", apellido " + apellido + ", Nacimiento " + fechaNacimiento + ", antiguedad " + antiguedad + ", area " + area + ", sueldoBruto" + sueldoBruto;
    }
}
