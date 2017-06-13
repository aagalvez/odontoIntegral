package modelo;
import java.sql.*;
import javax.servlet.ServletContext;

public class BeanTratamientoEspecial
{
   	private String id="";
	private String descripcion="";
        private String duracion ="";
        private String presupuesto ="";
        private String mensaje="";
        private ServletContext contexto;

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }


    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ServletContext getContexto() {
        return contexto;
    }

    public void setContexto(ServletContext contexto) {
        this.contexto = contexto;
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
				PreparedStatement ps = con.prepareStatement("select * from tratamiento especial where idTratamiento especial = ?");
				ps.setString(1,id);

				ps.executeQuery();
				ResultSet rs = ps.getResultSet();
				if(rs.next())
				{
					this.mensaje = "Tratamiento encontrado";
					this.id = id;
					this.descripcion = rs.getString("decripcion");
                                        this.duracion = rs.getString("duracion");
                                        this.presupuesto = rs.getString("presupuesto");
                                                       
				}
				else this.mensaje = "Tratamiento no encontrado :c";
				con.close();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			mensaje = "Acción no realizada";

		}

	}

	public void add(String id, String descripcion, String duracion, String presupuesto)
	{
			this.id = id;

                        
			this.mensaje = "Se agrego exitosament el tratamiento " + id;

			try
			{

				Connection con = DataConnector.obtieneConexion(contexto);

				if(con!=null)
				{
					PreparedStatement ps = con.prepareStatement("insert tratamiento especial values(?,,?,?,?)");
					ps.setString(1,id);
					ps.setString(2,descripcion);
                                        ps.setString(3,duracion);
                                        ps.setString(4,presupuesto);
   					ps.execute();
                                        this.descripcion = descripcion;
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
					PreparedStatement ps = con.prepareStatement("delete from tratamiento especial where idTratamiento especial = ?");
					ps.setString(1,id);
					ps.execute();
					this.reset();
					this.mensaje = "Tratamiento eliminado";
					con.close();
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				mensaje = "Acción no realizada";
			}

	}

	public void update(String id, String descripcion, String duracion, String presupuesto)
	{
			try
			{

				Connection con = DataConnector.obtieneConexion(contexto);
				if(con!=null)
				{
					PreparedStatement ps = con.prepareStatement("select * from tratamiento especial  where idTratamiento especial= ?");
					ps.setString(1,id);

					ps.executeQuery();

					ResultSet rs = ps.getResultSet();

					if(rs.next()) // Si existe el producto
					{
						PreparedStatement ps1 = con.prepareStatement("UPDATE tratamiento especial SET idTratamiento especial = ?,descripcion = ?, duracion = ?, presupuesto = ? where idTratamiento especial = ?");
						ps1.setString(4,id);
                                                ps.setString(1,descripcion);
                                                ps.setString(2,duracion);
                                                ps.setString(3,presupuesto);
                                                ps1.execute();

                                                // Actualiza los datos del Bean para mostrarlos en la Vista
                                                
					
						this.id = id;
					        this.descripcion = descripcion;
                                                this.duracion = duracion;
                                                this.presupuesto = presupuesto;                                                
                                                this.mensaje = "tratamiento actualizado";
					}
					else
						this.mensaje = "tratamiento no encontrado ";


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
                this.descripcion = "";
                this.duracion = "";
                this.presupuesto = "";
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
				st.executeQuery("select * from tratamiento especial order by idTratamiento especial");
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
							data[rows][1] = rs.getString("descripcion");
                                                        data[rows][2] = rs.getString("duracion");
                                                        data[rows][3] = rs.getString("presupuesto");
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
