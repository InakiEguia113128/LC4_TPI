/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.repositories;

import TPU.LC4.TPI_2w2.dto.DTOEmpleado;
import TUP.LC4.TPI_2w2.commands.PostEmpleado;
import TUP.LC4.TPI_2w2.models.Empleado;
import TUP.LC4.TPI_2w2.models.Recibo;
import TUP.LC4.TPI_2w2.resultados.ResultadoBase;
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

    public List<DTOEmpleado> getEmpleados() {
        try {
            Date date = new Date();
            mySqlConn = DriverManager.getConnection(url, "springboot123", "UtnFrc2022");
            /*return em.createQuery("select e from Empleado e", Empleado.class).getResultList();*/
            var results = new ArrayList<DTOEmpleado>();
            Statement st = mySqlConn.createStatement();
            ResultSet resultSet = st.executeQuery("select e.id_empleado, \n"
                    + "e.legajo, \n"
                    + "e.nombre, \n"
                    + "e.apellido, \n"
                    + "e.fecha_nac, \n"
                    + "e.fecha_ingreso, \n"
                    + "e.id_area, \n"
                    + "e.sueldo_bruto \n"
                    + "from empleado e \n");

            while (resultSet.next()) {
                results.add(new DTOEmpleado(resultSet.getInt("id_empleado"),
                        resultSet.getInt("legajo"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        new Date(resultSet.getDate("fecha_nac").getTime()),
                        new Date(resultSet.getDate("fecha_ingreso").getTime()),
                        resultSet.getFloat("sueldo_bruto"),
                        resultSet.getInt("id_area"),
                        date.getYear() - resultSet.getDate("fecha_ingreso").getYear()));
            }

            st.close();
            mySqlConn.close();
            return results;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    public ResultadoBase findEmpleadoByLegajo(int legajo) {
        ResultadoBase resultado = new ResultadoBase();
        Date date = new Date();
        try {
            DTOEmpleado empleado = null;
            mySqlConn = DriverManager.getConnection(url, "springboot123", "UtnFrc2022");
            PreparedStatement pst = mySqlConn.prepareStatement("select e.id_empleado, \n"
                    + "e.legajo, \n"
                    + "e.nombre, \n"
                    + "e.apellido, \n"
                    + "e.fecha_nac, \n"
                    + "e.fecha_ingreso, \n"
                    + "e.id_area, \n"
                    + "e.sueldo_bruto \n"
                    + "from empleado e \n"
                    + "where legajo = ?");

            pst.setInt(1, legajo);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                empleado = new DTOEmpleado(resultSet.getInt("id_empleado"),
                        resultSet.getInt("legajo"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        new Date(resultSet.getDate("fecha_nac").getTime()),
                        new Date(resultSet.getDate("fecha_ingreso").getTime()),
                        resultSet.getFloat("sueldo_bruto"),
                        resultSet.getInt("id_area"),
                        date.getYear() - resultSet.getDate("fecha_ingreso").getYear());

            }

            if (empleado != null) {
                resultado.setCode(200);
                resultado.setMessage("Empleado encontrado");
                resultado.resultado = empleado;
            } else {
                resultado.setCode(400);
                resultado.setMessage("No se encontro el empleado");
                resultado.resultado = null;
            }
            pst.close();
            mySqlConn.close();

            return resultado;
        } catch (SQLException ex) {

            resultado.setCode(500);
            resultado.setMessage(ex.getMessage());
            resultado.resultado = null;

            return resultado;
        }
    }

    public ResultadoBase postEmpleado(PostEmpleado empleado) {
        ResultadoBase resultado = new ResultadoBase();
        try {

            mySqlConn = DriverManager.getConnection(url, "springboot123", "UtnFrc2022");
            PreparedStatement pst = mySqlConn.prepareStatement("INSERT INTO `recibossueldotpiiv`.`empleado`"
                    + " (`legajo`, `nombre`, `apellido`, `fecha_nac`, `fecha_ingreso`, `id_area`, `sueldo_bruto`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);");

            pst.setInt(1, empleado.legajo);
            pst.setString(2, empleado.nombre);
            pst.setString(3, empleado.apellido);
            pst.setString(4, empleado.fecha_nac);
            pst.setString(5, empleado.fecha_ingreso);
            pst.setInt(6, empleado.id_area);
            pst.setFloat(7, empleado.sueldo_bruto);

            pst.execute();

            resultado.code = 200;
            resultado.message = "Se ingreso un nuevo empleado";
            resultado.resultado = null;

            mySqlConn.close();

            return resultado;
        } catch (SQLException ex) {

            resultado.setCode(500);
            resultado.setMessage(ex.getMessage());
            resultado.resultado = null;

            return resultado;
        }
    }
     
    public ResultadoBase getRecibosByLegajo(int legajo){
           ResultadoBase resultado =  new ResultadoBase();
           
        try{
            mySqlConn = DriverManager.getConnection(url, "springboot123", "UtnFrc2022");
            
            PreparedStatement pst = mySqlConn.prepareStatement("select * from recibossueldotpiiv.recibo_sueldo rs join recibossueldotpiiv.empleado e \n" +
                                                                                                                        "on rs.id_empleado = e.id_empleado \n" +
                                                                                                                           "where legajo   = ?");         
            pst.setInt(1, legajo);
            ResultSet resultSet = pst.executeQuery();
              var results = new ArrayList<Recibo>();
            while(resultSet.next()){
                    results.add(new Recibo(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getInt(3),
                            resultSet.getInt(4),
                            resultSet.getInt(5),
                            resultSet.getInt(6),
                            resultSet.getInt(7),
                            resultSet.getString(8)
                    ));
           }
                         resultado.setCode(200);
                        resultado.setMessage("Empleado encontrado");
                        resultado.resultado  = results;
        
            
          
            pst.close();
            mySqlConn.close();
            
            return resultado;
        }
        

            catch (SQLException ex){

              resultado.setCode(500);
              resultado.setMessage(ex.getMessage());
              resultado.resultado  = null; 
              
              return resultado;
        }
    }
}
      
     
     
    

