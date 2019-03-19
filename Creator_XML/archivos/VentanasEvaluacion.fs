
////////////////////////////////////////////////////////////////
//                   INICIO VentanaHistoria
////////////////////////////////////////////////////////////////
var VentanaHistoria = crearVentana( "#2E2EFE", 900, 900, "VentanaHistoria") ;
var ContenedorHistoria_VentanaHistoria = VentanaHistoria.crearContenedor( 1000, 1000, "#2E2EFE", verdadero, 10, 10 ) ;

ContenedorHistoria_VentanaHistoria.crearTexto( "Times New Roman", 18, "#000000", 450, 20, verdadero, verdadero, "Bienvenido a la prueba de Historia, responda las siguientes preguntas" );

ContenedorHistoria_VentanaHistoria.crearTexto( "Arial", 14, "#000000", 10, 20, falso, falso, "Ingrese su Nombre:" );

ContenedorHistoria_VentanaHistoria.crearCajaTexto ( 10, 100, "Arial", 14, "#000000", 100, 20, falso, falso, "Ingrese aqui su nombre", "CTNombre_VentanaHistoria" ) ;

ContenedorHistoria_VentanaHistoria.crearTexto( "Arial", 14, "#000000", 10, 250, falso, falso, "Ingrese el paisaje de la foto" );

var CDPaisaje1VentanaHistoria = [ "Playa", "Luna", "Selva", "Desierto", "Oceano" ] ;

ContenedorHistoria_VentanaHistoria.crearDesplegable ( 50, 100, CDPaisaje1_VentanaHistoria , 150, 250, nulo, "CDPaisaje1_VentanaHistoria" ) ;

///////////////////////////////////////////////////////////////
//                 FIN VentanaHistoria
///////////////////////////////////////////////////////////////



