
package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import modelo.BeanMaterial;

public class ServletMaterial extends HttpServlet {

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
            BeanMaterial material = (BeanMaterial) ses.getAttribute("material");

            if(material == null)
            {
        	material = new BeanMaterial();
        	ses.setAttribute("material",material);
            }

            String accion 	= request.getParameter("submit");
            String id     	= request.getParameter("id");
            String nombre	= request.getParameter("nombre");
            String marca	= request.getParameter("marca");
            String idDoc	= request.getParameter("idDoc");
         
            if(id==null) id="";
	    if(accion==null) accion="";
	    if(nombre==null) nombre="";
            if(marca==null) marca="";
            if(idDoc==null) idDoc="";
            
            if(accion.equals("Consulta"))
		material.read(id);
            else if(accion.equals("Actualiza"))
		material.update(id,nombre,marca,idDoc);
            else if(accion.equals("Alta"))
		material.add(id,nombre,marca,idDoc);
            else if(accion.equals("Baja"))
        	material.delete(id);

        RequestDispatcher rd = request.getRequestDispatcher("vista.jsp");
        rd.forward(request, response);
        }
    }