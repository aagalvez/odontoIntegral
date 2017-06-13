package modelo;
import java.sql.*;
import javax.servlet.ServletContext;

public class BeanMaterial
{
   	private String id="";
	private String caducidad="";
        private String tipo="";
        private String costo="";
        private String mensaje="";
        private ServletContext contexto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
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
				PreparedStatement ps = con.prepareStatement("select * from material where idMaterial = ?");
				ps.setString(1,id);

				ps.executeQuery();
				ResultSet rs = ps.getResultSet();
				if(rs.next())
				{
					this.mensaje = "Material encontrado";
					this.id = id;
					this.caducidad = rs.getString("caducidad");
                                        this.tipo = rs.getString("tipo");
                                        this.costo = rs.getString("costo");
				}
				else this.mensaje = "Material no encontrado :c";
				con.close();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			mensaje = "Acción no realizada";

		}

	}

	public void add(String id, String caducidad, String tipo, String costo)
	{
			this.id = id;

                        
			this.mensaje = "Se agrego exitosament el material " + tipo;

			try
			{

				Connection con = DataConnector.obtieneConexion(contexto);

				if(con!=null)
				{
					PreparedStatement ps = con.prepareStatement("insert material values(?,?,?,?)");
					ps.setString(1,id);
					ps.setString(2,caducidad);
                                        ps.setString(3,tipo);
                                        ps.setString(4,costo);
                                        
					ps.execute();
                                        this.caducidad = caducidad;
                                        this.tipo = tipo;
                                        this.costo = costo;
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
					PreparedStatement ps = con.prepareStatement("delete from material where idMaterial = ?");
					ps.setString(1,id);
					ps.execute();
					this.reset();
					this.mensaje = "Material eliminado";
					con.close();
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				mensaje = "Acción no realizada";
			}

	}

	public void update(String id, String caducidad, String tipo, String costo)
	{
			try
			{

				Connection con = DataConnector.obtieneConexion(contexto);
				if(con!=null)
				{
					PreparedStatement ps = con.prepareStatement("select * from material  where idMaterial= ?");
					ps.setString(1,id);

					ps.executeQuery();

					ResultSet rs = ps.getResultSet();

					if(rs.next()) // Si existe el producto
					{
						PreparedStatement ps1 = con.prepareStatement("UPDATE Material SET idMaterial = ?,CaducidadMaterial = ?,Tipo = ?,CostoMaterial = ? where idDentista = ?");
						ps1.setString(4,id);
                                                ps.setString(1,caducidad);
                                                ps.setString(2,tipo);
                                                ps.setString(3,costo);
						ps1.execute();

                                                // Actualiza los datos del Bean para mostrarlos en la Vista
                                                
					
						this.id = id;
					        this.caducidad = caducidad;
                                                this.tipo = tipo;
                                                this.costo = costo;
                                                
						this.mensaje = "Material actualizado";
					}
					else
						this.mensaje = "Material no encontrado ";


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
                this.caducidad = "";                        
                this.tipo = "";
                this.costo = "";
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
