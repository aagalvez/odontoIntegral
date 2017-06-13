
package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import modelo.BeanTratamiento;

public class ServletTratamiento extends HttpServlet {

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
            BeanTratamiento tr = (BeanTratamiento) ses.getAttribute("tratamiento");

            if(tr == null)
            {
        	tr = new BeanTratamiento();
        	ses.setAttribute("tratamiento",tr);
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
		tr.read(id);
            else if(accion.equals("Actualiza"))
		tr.update(id,des,dur,pre);
            else if(accion.equals("Alta"))
		tr.add(id,des,dur,pre);
            else if(accion.equals("Baja"))
        	tr.delete(id);

        RequestDispatcher rd = request.getRequestDispatcher("vista.jsp");
        rd.forward(request, response);
        }
    }