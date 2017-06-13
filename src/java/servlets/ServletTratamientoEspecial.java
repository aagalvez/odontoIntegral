
package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import modelo.BeanTratamientoEspecial;

public class ServletTratamientoEspecial extends HttpServlet {

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
            BeanTratamientoEspecial te = (BeanTratamientoEspecial) ses.getAttribute("tratamiento especial");

            if(te == null)
            {
        	te = new BeanTratamientoEspecial();
        	ses.setAttribute("tratamiento especial",te);
            }

            String accion 	= request.getParameter("submit");
            String id     	= request.getParameter("id");
            String des	= request.getParameter("des");
            String dur	= request.getParameter("dur");
            String pre	= request.getParameter("pre");
         
            if(id==null) id="";
	    if(accion==null) accion="";
	    if(des==null) des="";
            if(dur==null) dur="";
            if(pre==null) pre="";
            if(accion.equals("Consulta"))
		te.read(id);
            else if(accion.equals("Actualiza"))
		te.update(id,des,dur,pre);
            else if(accion.equals("Alta"))
		te.add(id,des,dur,pre);
            else if(accion.equals("Baja"))
        	te.delete(id);

        RequestDispatcher rd = request.getRequestDispatcher("vista.jsp");
        rd.forward(request, response);
        }
    }