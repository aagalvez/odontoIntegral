package modelo;
import java.sql.*;
import javax.servlet.ServletContext;

public class BeanEquipo
{
   	private String id="";
	private String nombre="";
        private String marca="";
        private String mensaje="";
        private ServletContext contexto;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

        
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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
				PreparedStatement ps = con.prepareStatement("select * from equipo where idEquipo = ?");
				ps.setString(1,id);

				ps.executeQuery();
				ResultSet rs = ps.getResultSet();
				if(rs.next())
				{
					this.mensaje = "Equipo encontrado";
					this.id = id;
					this.nombre = rs.getString("nombre");
                                        this.marca = rs.getString("marca");
                                       
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

	public void add(String id, String nombre, String marca)
	{
			this.id = id;

                        
			this.mensaje = "Se agrego exitosament el equipo " + nombre;

			try
			{

				Connection con = DataConnector.obtieneConexion(contexto);

				if(con!=null)
				{
					PreparedStatement ps = con.prepareStatement("insert dentista values(?,?,?)");
					ps.setString(1,id);
					ps.setString(2,nombre);
                                        ps.setString(3,marca);
					ps.execute();
                                        this.nombre = nombre;
                                        this.marca = marca;
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
					PreparedStatement ps = con.prepareStatement("delete from equipo where idEquipo = ?");
					ps.setString(1,id);
					ps.execute();
					this.reset();
					this.mensaje = "Equipo eliminado";
					con.close();
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				mensaje = "Acción no realizada";
			}

	}

	public void update(String id, String nombre, String marca)
	{
			try
			{

				Connection con = DataConnector.obtieneConexion(contexto);
				if(con!=null)
				{
					PreparedStatement ps = con.prepareStatement("select * from equipo  where idEquipo= ?");
					ps.setString(1,id);

					ps.executeQuery();

					ResultSet rs = ps.getResultSet();

					if(rs.next()) // Si existe el producto
					{
						PreparedStatement ps1 = con.prepareStatement("UPDATE equipo SET idDentista = ?,NombreEquipo = ?,MarcaEquipo = ? where idDentista = ?");
						ps1.setString(3,id);
                                                ps.setString(1,nombre);
                                                ps.setString(2,marca);
						ps1.execute();

                                                // Actualiza los datos del Bean para mostrarlos en la Vista
                                                
					
						this.id = id;
					        this.nombre = nombre;
                                                this.marca = marca;
						this.mensaje = "Equipo actualizado";
					}
					else
						this.mensaje = "Equipo no encontrado ";


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
                this.marca = "";
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
				st.executeQuery("select * from equipo order by idEquipo");
				ResultSet rs = st.getResultSet();
				String[][] data = null;

				if(rs.next())
				{
					rs.last();
					int rows = rs.getRow();
					if(rows > 0)
					{
						data = new String[rows][2];
						rs.beforeFirst();
						rows=0;
						while(rs.next())
						{
							data[rows][0] = rs.getString("id");
							data[rows][1] = rs.getString("nombre");
                                                        data[rows][2] = rs.getString("marca");
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
