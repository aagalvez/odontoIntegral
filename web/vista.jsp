
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelo.*" %>
<jsp:useBean id="producto" scope="session" class="modelo.BeanCita" />

<%
    producto.setContexto(application);
%>
<!DOCTYPE html>
<html>
<head>
<title>
Vista
</title> 
    <script type="text/javascript">
        
        function limpiar()
        {
            document.getElementById("id").value="";
            document.getElementById("titulo").value="";
            document.getElementById("plataforma").value="";
            document.getElementById("clasificacion").value="";
            document.getElementById("genero").value="";
            document.getElementById("desarrollador").value="";
            document.getElementById("precio").value="";
            document.getElementById("imagen").value="";
        }
        
    </script>
</head>
<body>
<form  name="form01" method="post" action="controlador">
<table border="0">
  <tr>
    <td>Id</td>

    <%-- Accede a propiedades del bean 'producto' --%>
    <td><input type="text" id="id" name="id" value='<jsp:getProperty name="producto" property="id"/>'  /></td>
  </tr>

  <tr>
    <td>Titulo</td>
    <td><input type="text" id="titulo" name="titulo" value='<jsp:getProperty name="producto" property="titulo" />' /></td>
  </tr>
  <tr>
    <td>Plataforma</td>
    <td><input type="text" id="plataforma" name="plataforma" value='<jsp:getProperty name="producto" property="plataforma" />' /></td>
  </tr>
  <tr>
    <td>Clasificacion</td>
    <td><input type="text" id="clasificacion" name="clasificacion" value='<jsp:getProperty name="producto" property="clasificacion" />' /></td>
  </tr>
   <tr>
    <td>Genero</td>
    <td><input type="text" id="genero" name="genero" value='<jsp:getProperty name="producto" property="genero" />' /></td>
  </tr>
   <tr>
    <td>Desarrollador</td>
    <td><input type="text" id="desarrollador" name="desarrollador" value='<jsp:getProperty name="producto" property="desarrollador" />' /></td>
  </tr>
  <tr>
    <td>Precio</td>
    <td><input type="text" id="precio" name="precio" value='<jsp:getProperty name="producto" property="precio" />' /></td>
  </tr>  
  <tr>
    <td>Imagen</td>
    <td><input type="text" id="imagen" name="imagen" value='<jsp:getProperty name="producto" property="imagen" />' /></td>
  </tr>  
 
  <tr>
    <td colspan=2 align="center">
       <input type="submit" name="submit" value="Consulta"/>
       <input type="submit" name="submit" value="Actualiza"/>
       <input type="submit" name="submit" value="Alta"/>
       <input type="submit" name="submit" value="Baja"/>
       <input type="button" value="Limpiar" onClick="limpiar()"/>
    </td>
  </tr>
</table>
</form>
<h4><jsp:getProperty name="producto" property="mensaje" /></h4>
</html>
