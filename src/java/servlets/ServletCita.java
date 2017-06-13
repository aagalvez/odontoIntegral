
package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import modelo.BeanCita;

public class ServletCita extends HttpServlet {

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
            BeanCita cita = (BeanCita) ses.getAttribute("producto");

            if(cita == null)
            {
        	cita = new BeanCita();
        	ses.setAttribute("cita",cita);
            }

            String accion 	= request.getParameter("submit");
            String id     	= request.getParameter("id");
            String fecha	= request.getParameter("fecha");
            String hora	= request.getParameter("hora");
            String idPac = request.getParameter("idPac");
            String idDoc	= request.getParameter("idDoc");
         
            if(id==null) id="";
	    if(accion==null) accion="";
	    if(fecha==null) fecha="";
            if(hora==null) hora="";
            if(idPac==null) idPac="";
            if(idDoc==null) idDoc="";
            
            if(accion.equals("Consulta"))
		cita.read(id);
            else if(accion.equals("Actualiza"))
		cita.update(id,fecha,hora,idPac,idDoc);
            else if(accion.equals("Alta"))
		cita.add(id,fecha,hora,idPac,idDoc);
            else if(accion.equals("Baja"))
        	cita.delete(id);

        RequestDispatcher rd = request.getRequestDispatcher("vista.jsp");
        rd.forward(request, response);
        }
    }