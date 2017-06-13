
package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import modelo.BeanReceta;

public class ServletReceta extends HttpServlet {

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
            BeanReceta receta = (BeanReceta) ses.getAttribute("receta");

            if(receta == null)
            {
        	receta = new BeanReceta();
        	ses.setAttribute("receta",receta);
            }

            String accion 	= request.getParameter("submit");
            String id     	= request.getParameter("id");
            String des	= request.getParameter("des");
            String dur	= request.getParameter("dur");
            String pre	= request.getParameter("pre");
         
            if(id==null) id="";
	    if(accion==null) accion="";
	    if(des==null) des="";
            if(accion.equals("Consulta"))
		receta.read(id);
            else if(accion.equals("Actualiza"))
		receta.update(id,des);
            else if(accion.equals("Alta"))
		receta.add(id,des);
            else if(accion.equals("Baja"))
        	receta.delete(id);

        RequestDispatcher rd = request.getRequestDispatcher("vista.jsp");
        rd.forward(request, response);
        }
    }