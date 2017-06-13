
package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import modelo.BeanDentista;

public class ServletControladorD extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
                
            HttpSession ses = request.getSession(true);
            BeanDentista dentista = (BeanDentista) ses.getAttribute("producto");

            if(dentista  == null)
            {
        	dentista = new BeanDentista();
        	ses.setAttribute("dentista",dentista);
            }

            String accion 	= request.getParameter("submit");
            String id     	= request.getParameter("id");
            String nombre	= request.getParameter("nombre");
            String telefono 	= request.getParameter("telefono");
            String perfil = request.getParameter("perfil");
            String correo	= request.getParameter("correo");
            if(id==null) id="";
	    if(accion==null) accion="";
	    if(nombre==null) nombre="";
            if(telefono==null) telefono="";
            if(perfil==null) perfil="";
            if(correo==null) correo="";
            if(accion.equals("Consulta"))
		dentista.read(id);
            else if(accion.equals("Actualiza"))
		dentista.update(id,nombre,telefono,perfil,correo);
            else if(accion.equals("Alta"))
		dentista.add(id,nombre,telefono,perfil,correo);
            else if(accion.equals("Baja"))
        	dentista.delete(id);

        RequestDispatcher rd = request.getRequestDispatcher("vista.jsp");
        rd.forward(request, response);
        }
    }


