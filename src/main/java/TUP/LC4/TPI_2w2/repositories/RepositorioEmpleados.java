/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.repositories;


import TUP.LC4.TPI_2w2.models.Empleado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Iñaki
 */
@Repository
public class RepositorioEmpleados {
    private final String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&serverTimezone=UTC", "2022-api-tpi-iv-dev.mysql.database.azure.com", 3306, "recibossueldotpiiv");
    private Connection mySqlConn;
    
    public List<Empleado> getEmpleados(){
        try{
            mySqlConn = DriverManager.getConnection(url, "springboot123", "UtnFrc2022");
            /*return em.createQuery("select e from Empleado e", Empleado.class).getResultList();*/
            var results = new ArrayList<Empleado>();
            Statement st = mySqlConn.createStatement();
            ResultSet resultSet = st.executeQuery("select e.id_empleado, \n" +
                    "e.legajo, \n" +
                    "e.nombre, \n" +
                    "e.apellido, \n" +
                    "e.fecha_nac, \n" + 
                    "e.fecha_ingreso, \n" +
                    "e.id_area, \n" +
                    "e.sueldo_bruto \n" +
                    "from empleado e \n");

            while (resultSet.next()){
                results.add(new Empleado(resultSet.getInt("id_empleado"), 
                        resultSet.getInt("legajo"), 
                        resultSet.getString("nombre"), 
                        resultSet.getString("apellido"), 
                        new Date(resultSet.getDate("fecha_nac").getTime()), 
                        new Date(resultSet.getDate("fecha_ingreso").getTime()), 
                        resultSet.getFloat("sueldo_bruto"),
                        resultSet.getInt("id_area")));
            }
            
            st.close();
            mySqlConn.close();
            return results;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }
    
    public Empleado findEmpleadoByLegajo(int legajo){
        try{
            Empleado result = null;
            mySqlConn = DriverManager.getConnection(url, "springboot123", "UtnFrc2022");
            PreparedStatement pst = mySqlConn.prepareStatement("select e.id_empleado, \n" +
                    "e.legajo, \n" +
                    "e.nombre, \n" +
                    "e.apellido, \n" +
                    "e.fecha_nac, \n" + 
                    "e.fecha_ingreso, \n" +
                    "e.id_area, \n" +
                    "e.sueldo_bruto \n" +
                    "from empleado e \n" + 
                    "where legajo = ?");
            
            pst.setInt(1, legajo);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()){
                result = new Empleado(resultSet.getInt("id_empleado"), 
                        resultSet.getInt("legajo"), 
                        resultSet.getString("nombre"), 
                        resultSet.getString("apellido"), 
                        new Date(resultSet.getDate("fecha_nac").getTime()), 
                        new Date(resultSet.getDate("fecha_ingreso").getTime()), 
                        resultSet.getFloat("sueldo_bruto"),
                        resultSet.getInt("id_area"));
            }
            
            pst.close();
            mySqlConn.close();
            
            return result;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
