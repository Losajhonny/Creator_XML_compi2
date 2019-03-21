importar ( "v1.fs" );

////////////////////////////////////////////////////////////////
//                   INICIO v1
////////////////////////////////////////////////////////////////
var v1 = crearVentana( "#ffffff", 900, 900, "v1") ;
v1.alCargar( mostrar("Bienvenido a v1") ) ;
v1.alCerrar( final() ) ;
var c1_v1 = v1.crearContenedor( 250, 300, "#00bb2d", falso, 10, 20 ) ;

c1_v1.crearTexto( "Arial", 14, "#000000", 10, 20, falso, falso, "Ingrese su usuario:" );

c1_v1.crearCajaTexto ( 30, 100, "Arial", 14, "#000000", 160, 15, falso, falso, nulo, "cont1" ) ;

c1_v1.crearTexto( "Arial", 14, "#000000", 10, 50, falso, falso, "Ingrese un codigo:" );

c1_v1.crearControlNumerico ( 30, 100, 100, 0, 160, 45, nulo, "cont2" ) ;

c1_v1.crearTexto( "Arial", 14, "#000000", 10, 80, falso, falso, "Ingrese un password:" );

c1_v1.crearCajaTexto ( 30, 100, "Arial", 14, "#000000", 160, 75, falso, falso, nulo, "cont3" ) ;

var btn1_v1 = c1_v1.crearBoton( "Arial", 14, "#000000", 160, 105, nulo, "Enviar...", 40, 100 ) ;
btn1_v1.alClic( Guardar_v1() ) ;

var btn2_v1 = c1_v1.crearBoton( "Arial", 14, "#000000", 160, 155, nulo, "Obtener reporte", 40, 150 ) ;
btn2_v1.alClic( reporte() ) ;

///////////////////////////////////////////////////////////////
//                 FIN v1
///////////////////////////////////////////////////////////////



funcion Guardar_v1() {
	v1.crearArrayDesdeArchivo() ;
}

v1.alCargar() ;
