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
        
        <div class="box">
            <form name="formulario" class="registro" method="post" action="ServletRegistro">
                            <p align="justify">
                                <label for="correo">Correo:</label>
                                <input type="email" class="inputform" required name="email"  maxlength="20" id="correo"/><br><br>
                                <label for="contraseña">Contraseña:</label>
                                <input type="password" class="inputform" required name="pass"  maxlength="20" id="contraseña"/><br><br>
                                <label for="nombre">Nombre completo:</label>
                                <input type="text" class="inputform" required name="nombre"  maxlength="20" id="nombre"/><br><br>
                                <label for="tel">Teléfono:</label>
                                <input type="number" class="inputform" required name="telefono" value="Telefono" maxlenght="10" id="tel"/><br><br>
                                <div class="botones">
                                <input type="submit" class="btn1" value="Registrar"/>
                                <input type="reset" class="btn1" value="Borrar"/>
                                </div>
                            </p>
                    </form>
        </div>
        
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
