
////////////////////////////////////////////////////////////////
//                   INICIO Registrar
////////////////////////////////////////////////////////////////
var Registrar = crearVentana( "#ffffff", 900, 900, "Registrar") ;
var ContRegistro_Registrar = Registrar.crearContenedor( 300, 200, "#ffffff", falso, 10, 10 ) ;

ContRegistro_Registrar.crearTexto( "Arial", 14, "#000000", 10, 20, falso, falso, "Nombre" );

ContRegistro_Registrar.crearCajaTexto ( 30, 100, "Arial", 14, "#000000", 40, 20, falso, falso, "Ingrese aqui su nombre", "CTNombre_Registrar" ) ;

ContRegistro_Registrar.crearTexto( "Arial", 14, "#000000", 10, 50, falso, falso, "Edad" );

ContRegistro_Registrar.crearControlNumerico ( 30, 50, nulo, 18, 40, 50, 18, "CEdad_Registrar" ) ;

ContRegistro_Registrar.crearTexto( "Arial", 14, "#000000", 10, 80, falso, falso, "Descripcion" );

ContRegistro_Registrar.crearAreaTexto ( 100, 100, "Arial", 14, "#000000", 40, 80, falso, falso, "Ingrese aqui la descripcion desu registro", "CADescripcion_Registrar" ) ;

ContRegistro_Registrar.crearTexto( "Arial", 14, "#000000", 10, 200, falso, falso, "Correo" );

ContRegistro_Registrar.crearCajaTexto ( 30, 100, "Arial", 14, "#000000", 40, 200, falso, falso, "Ingrese aqui su correo", "CTCorreo_Registrar" ) ;
var ContBtn_Registrar = Registrar.crearContenedor( 100, 200, "#ffffff", falso, 10, 320 ) ;

var btnEnviar_Registrar = ContBtn_Registrar.crearBoton( "Arial", 14, "#000000", 75, 30, nulo, "Registrar", 70, 40 ) ;
btnEnviar_Registrar.alClic( Guardar_Registrar() ) ;

///////////////////////////////////////////////////////////////
//                 FIN Registrar
///////////////////////////////////////////////////////////////



funcion Guardar_Registrar() {
	Registrar.crearArrayDesdeArchivo() ;
}

Registrar.alCargar() ;
