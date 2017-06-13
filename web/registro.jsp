
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
        <link rel="shortcut icon" href="imagenes/logo.png" type="image/png" />
        <link href="estilos/estilo-general.css" rel="stylesheet" type="text/css" />
        <link href="estilos/fuentes.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
        function cargaURL(url, titulo)
        {
            window.frames['marco-desplegado'].location = url;
            document.getElementById("titulo").innerHTML = titulo;
        }
        </script>
    </head>
    <body>
        <header class="head">
		<div class="logo">
			<img src="imagenes/logo.png" width="100" height="100">
		</div>
			<h1>Odontología integral Echegaray</h1>
        </header>

        <nav class="menubar">
        	<ul>
			<li><a href="index.html">Inicio</a></li>
			<li><a href="registro.jsp">Registro</a></li>
			<li><a href="inicio-sesion.jsp">Iniciar sesión</a></li>
			<li><a href="presentacion.html">Acerca de nosotros</a></li>
		</ul>	
        </nav>
        
        <h1 class="reg">Registro</h1>
        <h3 align="center">Seleccione el tipo de registro</h3><br>
        <ul id="opc">
		<li><a href="registroP.jsp">Paciente</a></li>
                <li><a href="registroD.jsp">Dentistas</a></li
	</ul><br>
        
        <div id="recuadro">
            <span>Visitenos en:</span><br />
            Calle:  Blvd. Magnocentro 4, San Fernando La Herradura <br/>
            Bosque de las Palmas,<br />
            52760 Huixquilucan, M&#233;xico<br />
            <br />
            <span>Telefonos:</span><br />
            0980-7787, 5678-6534
        </div>
    </body>
</html>