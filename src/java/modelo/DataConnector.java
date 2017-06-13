package modelo;

import java.sql.*;
import javax.servlet.ServletContext;

public class DataConnector 
{
    	public static Connection obtieneConexion(ServletContext contexto)
	{
			Connection con=null;
			try
			{
                            String mySQLHost = contexto.getInitParameter("mySQLHost");
                            String dbName = contexto.getInitParameter("dbName");
                            String user = contexto.getInitParameter("user");
                            String password = contexto.getInitParameter("password");
			
                            Class.forName("com.mysql.jdbc.Driver");
                            String urlBD = mySQLHost + dbName+ "?user=" + user + "&password=" + password;
                            con = DriverManager.getConnection(urlBD);
                            
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}

			return con;
	}
}
