package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import modelo.BeanEquipo;

public class ServletEquipo extends HttpServlet {

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
            BeanEquipo equipo  = (BeanEquipo) ses.getAttribute("equipo");

            if(equipo == null)
            {
        	equipo = new BeanEquipo();
        	ses.setAttribute("equipo",equipo);
            }

            String accion 	= request.getParameter("submit");
            String id     	= request.getParameter("id");
            String marca	= request.getParameter("marca");
            String idDoc	= request.getParameter("idDoc");
	    if(accion==null) accion="";
	    if(marca==null) marca="";
            if(idDoc==null) idDoc="";
            
            if(accion.equals("Consulta"))
		equipo.read(id);
            else if(accion.equals("Actualiza"))
		equipo.update(id,marca,idDoc);
            else if(accion.equals("Alta"))
		equipo.add(id,marca,idDoc);
            else if(accion.equals("Baja"))
        	equipo.delete(id);

        RequestDispatcher rd = request.getRequestDispatcher("vista.jsp");
        rd.forward(request, response);
        }
    }


