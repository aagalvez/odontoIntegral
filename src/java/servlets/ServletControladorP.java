
package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import modelo.BeanPaciente;

public class ServletControladorP extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
                
            HttpSession ses = request.getSession(true);
            BeanPaciente paciente  = (BeanPaciente) ses.getAttribute("paciente");

            if(paciente == null)
            {
        	paciente = new BeanPaciente();
        	ses.setAttribute("paciente",paciente);
            }

            String accion 	= request.getParameter("submit");
            String id     	= request.getParameter("id");
            String nombre	= request.getParameter("nombre");
            String telefono	= request.getParameter("telefono");
            String dir = request.getParameter("dir");
	    if(accion==null) accion="";
	    if(nombre==null) nombre="";
            if(telefono==null) telefono="";
            if(dir==null) dir="";
            
            if(accion.equals("Consulta"))
		paciente.read(id);
            else if(accion.equals("Actualiza"))
		paciente.update(id,nombre,telefono,dir);
            else if(accion.equals("Alta"))
		paciente.add(id,nombre,telefono,dir);
            else if(accion.equals("Baja"))
        	paciente.delete(id);

        RequestDispatcher rd = request.getRequestDispatcher("vista.jsp");
        rd.forward(request, response);
        }
    }


