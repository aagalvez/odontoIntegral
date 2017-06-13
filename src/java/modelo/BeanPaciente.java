/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.*;
import javax.servlet.ServletContext;

public class BeanPaciente 
{
   	private String id="";
	private String nombre="";
        private String telefono="";
        private String dir="";
        private String mensaje="";
        private ServletContext contexto;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

 


        
	public void setId(String id)
	{
		this.id = id;
	}

        
	public void setMensaje(String mensaje)
	{
		this.mensaje = mensaje;
	}

        public void setContexto(ServletContext contexto)
        {
                this.contexto = contexto;
        }

        
	public String getId()
	{
		return id;
	}


        
	public String getMensaje()
	{
		return mensaje;
	}
        
	public void read(String id)
	{
		this.reset();
		this.id = id;

		try
		{

			Connection con = DataConnector.obtieneConexion(contexto);
			if(con != null)
			{
				PreparedStatement ps = con.prepareStatement("select * from paciente where idPaciente = ?");
				ps.setString(1,id);

				ps.executeQuery();
				ResultSet rs = ps.getResultSet();
				if(rs.next())
				{
					this.mensaje = "Producto encontrado";
					this.id = id;
					this.nombre = rs.getString("nombre");
                                        this.telefono = rs.getString("telefono");
                                        this.dir = rs.getString("dir");
				}
				else this.mensaje = "Dentista no encontrado :c";
				con.close();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			mensaje = "Acción no realizada";

		}

	}

	public void add(String id, String nombre, String telefono, String dir)
	{
			this.id = id;

                        
			this.mensaje = "Se agrego exitosament al paciente " + nombre;

			try
			{

				Connection con = DataConnector.obtieneConexion(contexto);

				if(con!=null)
				{
					PreparedStatement ps = con.prepareStatement("insert paciente values(?,?,?,?)");
					ps.setString(1,id);
					ps.setString(2,nombre);
                                        ps.setString(3,telefono);
                                        ps.setString(4,dir);
					ps.execute();
                                        this.nombre = nombre;
                                        this.telefono = telefono;
                                        this.dir = dir;
					con.close();
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				mensaje = "Acción no realizada";
			}
	}


	public void delete(String id)
	{
			try
			{

				Connection con = DataConnector.obtieneConexion(contexto);
				if(con!=null)
				{
					PreparedStatement ps = con.prepareStatement("delete from paciente where idPaciente = ?");
					ps.setString(1,id);
					ps.execute();
					this.reset();
					this.mensaje = "Paciente eliminado";
					con.close();
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				mensaje = "Acción no realizada";
			}

	}

	public void update(String id, String nombre, String telefono, String dir)
	{
			try
			{

				Connection con = DataConnector.obtieneConexion(contexto);
				if(con!=null)
				{
					PreparedStatement ps = con.prepareStatement("select * from paciente  where idPaciente = ?");
					ps.setString(1,id);

					ps.executeQuery();

					ResultSet rs = ps.getResultSet();

					if(rs.next()) // Si existe el producto
					{
						PreparedStatement ps1 = con.prepareStatement("UPDATE denitsta SET idPaciente = ?,NombrePaciente = ?,TelefonoPaciente = ?,DireccionPaciente = ? where idPaciente = ?");
						ps1.setString(4,id);
                                                ps.setString(1,nombre);
                                                ps.setString(2,telefono);
                                                ps.setString(4,dir);
						ps1.execute();

                                                // Actualiza los datos del Bean para mostrarlos en la Vista
                                                
					
						this.id = id;
					        this.nombre = nombre;
                                                this.telefono = telefono;
                                                this.dir = dir;
						this.mensaje = "Paciente actualizado";
					}
					else
						this.mensaje = "Paciente no encontrado ";


					con.close();
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				mensaje = "Acción no realizada";
			}
	}


	public void reset()
	{
		this.id = "";
                this.nombre = "";                        
                this.telefono = "";
                this.dir = "";
		this.mensaje="";
	}

	public String[][] readAll()
	{


		try
		{

			Connection con = DataConnector.obtieneConexion(contexto);
			if(con != null)
			{
				Statement st = con.createStatement();
				st.executeQuery("select * from paciente order by idPaciente");
				ResultSet rs = st.getResultSet();
				String[][] data = null;

				if(rs.next())
				{
					rs.last();
					int rows = rs.getRow();
					if(rows > 0)
					{
						data = new String[rows][3];
						rs.beforeFirst();
						rows=0;
						while(rs.next())
						{
							data[rows][0] = rs.getString("id");
							data[rows][1] = rs.getString("nombre");
                                                        data[rows][2] = rs.getString("telefono");
                                                        data[rows][4] = rs.getString("dir");
                                                        rows++;
						}

					}

				}
				con.close();
				return data;
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	} 
}
