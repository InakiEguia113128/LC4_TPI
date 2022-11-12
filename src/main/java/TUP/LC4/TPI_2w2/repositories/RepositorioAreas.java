/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TUP.LC4.TPI_2w2.repositories;

import TUP.LC4.TPI_2w2.commands.PostArea;
import TUP.LC4.TPI_2w2.models.Area;
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
 * @author ortiz
 */
@Repository
public class RepositorioAreas {

    private final String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&serverTimezone=UTC", "2022-api-tpi-iv-dev.mysql.database.azure.com", 3306, "recibossueldotpiiv");
    private Connection mySqlConn;

    public ResultadoBase insertArea(PostArea area) {
        var result = new ResultadoBase();
        try {
            mySqlConn = DriverManager.getConnection(url, "springboot123", "UtnFrc2022");

            PreparedStatement pst = mySqlConn.prepareStatement("insert into area (descripcion) values (?)");
            pst.setString(1, area.getDescripcion());
            pst.execute();

            pst.close();
            mySqlConn.close();

            result.code = 200;
            result.message = "La información se guardo exitosamente!.";
        } catch (SQLException ex) {
            result.code = 500;
            result.message = ex.getMessage();
            result.resultado = null;
        }

        return result;
    }

    public ResultadoBase getAreaByDescripcion(String descripcion) {
        ResultadoBase result = new ResultadoBase();
        Area area = null;

        try {
            Connection mySqlConn = DriverManager.getConnection(url, "springboot123", "UtnFrc2022");

            PreparedStatement pst = mySqlConn.prepareStatement("select a.id_area, \n"
                    + "a.descripcion \n"
                    + "from area a \n"
                    + "where LOWER(a.descripcion) = ?");

            pst.setString(1, descripcion.toLowerCase());
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                area = new Area(resultSet.getInt("id_area"), resultSet.getString("descripcion"));
            }
            
            if (area != null) {
                result.message = "Area encontrada.";
                result.code = 200;
                result.resultado = area;
                
            } else {
                result.message = "Area no encontrada.";
                result.code = 400;
                result.resultado = area;
            }

            pst.close();
            mySqlConn.close();
        } catch (SQLException ex) {
            result.code = 500;
            result.message = ex.getMessage();
            result.resultado = null;
        }

        return result;
    }
    
    
    public ResultadoBase getAreaById(int  id) {
        ResultadoBase result = new ResultadoBase();
        Area area = null;

        try {
            Connection mySqlConn = DriverManager.getConnection(url, "springboot123", "UtnFrc2022");

            PreparedStatement pst = mySqlConn.prepareStatement("select a.id_area, \n"
                    + "a.descripcion \n"
                    + "from area a \n"
                    + "where LOWER(a.id_area) = ?");

            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                area = new Area(resultSet.getInt("id_area"), resultSet.getString("descripcion"));
            }

            result.code = 200;
            result.resultado = area;
            
            if (area != null) {
                result.message = "Area encontrada.";
                result.code = 200;
                result.resultado = area;
            } else {
                result.message = "Area no encontrada.";
                result.code = 400;
                result.resultado = area;
            }

            pst.close();
            mySqlConn.close();
        } catch (SQLException ex) {
            result.code = 500;
            result.message = ex.getMessage();
            result.resultado = null;
        }

        return result;
    }
}
