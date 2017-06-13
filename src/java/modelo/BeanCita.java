package modelo;
import java.sql.*;
import javax.servlet.ServletContext;

public class BeanCita 
{
   	private String id="";
	private String nombre="";
        private String telefono="";
        private String perfil="";
        private String correo="";
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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
				PreparedStatement ps = con.prepareStatement("select * from dentista where idDentista = ?");
				ps.setString(1,id);

				ps.executeQuery();
				ResultSet rs = ps.getResultSet();
				if(rs.next())
				{
					this.mensaje = "Dentista encontrado";
					this.id = id;
					this.nombre = rs.getString("nombre");
                                        this.telefono = rs.getString("telefono");
                                        this.perfil = rs.getString("perfil");
                                        this.correo = rs.getString("correo");
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

	public void add(String id, String nombre, String telefono, String perfil, String correo)
	{
			this.id = id;

                        
			this.mensaje = "Se agrego exitosament al dentista " + nombre;

			try
			{

				Connection con = DataConnector.obtieneConexion(contexto);

				if(con!=null)
				{
					PreparedStatement ps = con.prepareStatement("insert dentista values(?,?,?,?,?)");
					ps.setString(1,id);
					ps.setString(2,nombre);
                                        ps.setString(3,telefono);
                                        ps.setString(4,perfil);
                                        ps.setString(5,correo);
					ps.execute();
                                        this.nombre = nombre;
                                        this.telefono = telefono;
                                        this.perfil = perfil;
                                        this.correo = correo;
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
					PreparedStatement ps = con.prepareStatement("delete from dentista where idDentista = ?");
					ps.setString(1,id);
					ps.execute();
					this.reset();
					this.mensaje = "Dentista eliminado";
					con.close();
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				mensaje = "Acción no realizada";
			}

	}

	public void update(String id, String nombre, String telefono, String perfil, String correo)
	{
			try
			{

				Connection con = DataConnector.obtieneConexion(contexto);
				if(con!=null)
				{
					PreparedStatement ps = con.prepareStatement("select * from dentista  where idDentista= ?");
					ps.setString(1,id);

					ps.executeQuery();

					ResultSet rs = ps.getResultSet();

					if(rs.next()) // Si existe el producto
					{
						PreparedStatement ps1 = con.prepareStatement("UPDATE denitsta SET idDentista = ?,NombreDentista = ?,TelefonoDentista = ?,PerfitlProfDentista = ?,CorreoDentista=? where idDentista = ?");
						ps1.setString(5,id);
                                                ps.setString(1,nombre);
                                                ps.setString(2,telefono);
                                                ps.setString(3,perfil);
                                                ps.setString(4,correo);
						ps1.execute();

                                                // Actualiza los datos del Bean para mostrarlos en la Vista
                                                
					
						this.id = id;
					        this.nombre = nombre;
                                                this.telefono = telefono;
                                                this.perfil = perfil;
                                                this.correo = correo;
						this.mensaje = "Dentista actualizado";
					}
					else
						this.mensaje = "Dentista no encontrado ";


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
                this.perfil = "";
                this.correo = "";
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
				st.executeQuery("select * from dentista order by idDentista");
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
                                                        data[rows][4] = rs.getString("perfil");
                                                        data[rows][5] = rs.getString("correo");
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
