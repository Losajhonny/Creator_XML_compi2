importar ( "Funciones.fs" );

////////////////////////////////////////////////////////////////
//                   INICIO Registrar
////////////////////////////////////////////////////////////////
var Registrar = crearVentana( "#ffffff", 900, 900, "Registrar") ;
Registrar.alCargar( MensajeBienvenida("Julio ") ) ;
Registrar.alCerrar( MensajeDespedida("Julio" + " Arango") ) ;
var ContRegistro_Registrar = Registrar.crearContenedor( 300, 200, "#ffffff", falso, 10, 10 ) ;

ContRegistro_Registrar.crearTexto( "Arial", 14, "#000000", 10, 20, falso, falso, "Nombre" );

ContRegistro_Registrar.crearCajaTexto ( 10, 100, "Arial", 14, "#000000", 40, 20, falso, falso, "Ingrese aqui su nombre", "CTNombre_Registrar" ) ;

ContRegistro_Registrar.crearTexto( "Arial", 14, "#000000", 10, 50, falso, falso, "Edad" );

ContRegistro_Registrar.crearControlNumerico ( 10, 50, nulo, 18, 40, 50, 18, "CEdad_Registrar" ) ;

ContRegistro_Registrar.crearTexto( "Arial", 14, "#000000", 10, 80, falso, falso, "Descripcion" );

ContRegistro_Registrar.crearAreaTexto ( 100, 100, "Arial", 14, "#000000", 40, 80, falso, falso, "Ingrese aqui la descripcion desu registro", "CADescripcion_Registrar" ) ;

ContRegistro_Registrar.crearTexto( "Arial", 14, "#000000", 10, 200, falso, falso, "Correo" );

ContRegistro_Registrar.crearCajaTexto ( 10, 100, "Arial", 14, "#000000", 40, 200, falso, falso, "Ingrese aqui su correo", "CTCorreo_Registrar" ) ;
var ContBtn_Registrar = Registrar.crearContenedor( 100, 200, "#ffffff", falso, 10, 320 ) ;

var btnEnviar_Registrar = ContBtn_Registrar.crearBoton( "Arial", 14, "#000000", 75, 30, Cargar_ventana_Inicio(), "Registrar", 70, 40 ) ;
btnEnviar_Registrar.alClic( Guardar_Registrar() ) ;

///////////////////////////////////////////////////////////////
//                 FIN Registrar
///////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////
//                   INICIO VentanaReportes
////////////////////////////////////////////////////////////////
var VentanaReportes = crearVentana( "#000000", 900, 900, "VentanaReportes") ;
var ContenedorReportes_VentanaReportes = VentanaReportes.crearContenedor( 800, 800, "#000000", verdadero, 10, 10 ) ;

var btnEvaluacion1_VentanaReportes = ContenedorReportes_VentanaReportes.crearBoton( "Arial", 14, "#000000", 60, 40, nulo, "Reporte Aritmeticos", 100, 100 ) ;
btnEvaluacion1_VentanaReportes.alClic( ReporteAritmetico() ) ;

var btnEvaluacion2_VentanaReportes = ContenedorReportes_VentanaReportes.crearBoton( "Arial", 14, "#000000", 60, 40, nulo, "Reporte Historicos", 100, 100 ) ;
btnEvaluacion2_VentanaReportes.alClic( ReporteHistorico() ) ;

var btnEvaluacion3_VentanaReportes = ContenedorReportes_VentanaReportes.crearBoton( "Arial", 14, "#000000", 60, 40, nulo, "Reporte Ingles", 100, 100 ) ;
btnEvaluacion3_VentanaReportes.alClic( ReporteIngles() ) ;

var btnEvaluacion4_VentanaReportes = ContenedorReportes_VentanaReportes.crearBoton( "Arial", 14, "#000000", 60, 40, nulo, "Reporte Logicos", 100, 100 ) ;
btnEvaluacion4_VentanaReportes.alClic( ReporteLogico() ) ;

var btnEnviar_VentanaReportes = ContenedorReportes_VentanaReportes.crearBoton( "Arial", 14, "#000000", 60, 350, nulo, "Sin funcionalidad", 100, 100 ) ;
btnEnviar_VentanaReportes.alClic( Guardar_VentanaReportes() ) ;

///////////////////////////////////////////////////////////////
//                 FIN VentanaReportes
///////////////////////////////////////////////////////////////



funcion Guardar_Registrar() {
	Registrar.crearArrayDesdeArchivo() ;
}

funcion Cargar_ventana_Inicio() {
	Inicio.alCargar() ;
}

funcion Guardar_VentanaReportes() {
	VentanaReportes.crearArrayDesdeArchivo() ;
	EnviarSinFuncionalidad() ;
}

Registrar.alCargar() ;
