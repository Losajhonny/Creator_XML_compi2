importar ( "FuncionesAritmeticas.fs" );

////////////////////////////////////////////////////////////////
//                   INICIO ventana
////////////////////////////////////////////////////////////////
var ventana = crearVentana( "#ffffff", 900, 900, "ventana") ;
var cont1_ventana = ventana.crearContenedor( 500, 500, "#ffffff", falso, 10, 10 ) ;

var btn_nombre_ventana = cont1_ventana.crearBoton( "Arial", 14, "#000000", 20, 20, nulo, "Factorial", 100, 100 ) ;
btn_nombre_ventana.alClic(  RealizarFactorial()  ) ;

///////////////////////////////////////////////////////////////
//                 FIN ventana
///////////////////////////////////////////////////////////////



ventana.alCargar() ;
