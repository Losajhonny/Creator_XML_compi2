
var Registrar = crearVentana( "#ffffff", 900, 900, "Registrar") ;
var ContRegistro_Registrar = Registrar.crearContenedor( 300, 200, "#ffffff", verdadero, 10, 10 ) ;

ContRegistro_Registrar.crearTexto( "Arial", 14, "#000000", 10, 20, falso, falso, "Nombre" );

ContRegistro_Registrar.crearCajaTexto ( 25, 100, "Arial", 14, "#000000", 40+50, 20, falso, falso, "Ingrese aqui su nombre", "CTNombre_Registrar" ) ;

ContRegistro_Registrar.crearTexto( "Arial", 14, "#000000", 10, 50, falso, falso, "Edad" );

ContRegistro_Registrar.crearControlNumerico ( 25, 50, nulo, 18, 40+50, 50, 18, "CEdad_Registrar" ) ;

ContRegistro_Registrar.crearTexto( "Arial", 14, "#000000", 10, 80, falso, falso, "Descripcion" );

ContRegistro_Registrar.crearAreaTexto ( 100, 100, "Arial", 14, "#000000", 40+50, 80, falso, falso, "Ingrese aqui la descripcion desu registro", "CADescripcion_Registrar" ) ;

ContRegistro_Registrar.crearTexto( "Arial", 14, "#000000", 10, 200, falso, falso, "Correo" );

ContRegistro_Registrar.crearCajaTexto ( 25, 100, "Arial", 14, "#000000", 40+50, 200, falso, falso, "Ingrese aqui su correo", "CTCorreo_Registrar" ) ;
Registrar.alCargar() ;
