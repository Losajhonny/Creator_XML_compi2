importar ( "/FuncionesEvaluacion.fs" );

////////////////////////////////////////////////////////////////
//                   INICIO VentanaPrincipal
////////////////////////////////////////////////////////////////
var VentanaPrincipal = crearVentana( "#000000", 900, 900, "VentanaPrincipal") ;
var Contenedor1_VentanaPrincipal = VentanaPrincipal.crearContenedor( 400, 400, "#000000", verdadero, 10, 10 ) ;

Contenedor1_VentanaPrincipal.crearTexto( "Arial", 14, "#000000", 10, 10, verdadero, falso, "Haga clic en el siguiente boton para iniciar la evaluacion " );

var btnEvaluacion_VentanaPrincipal = Contenedor1_VentanaPrincipal.crearBoton( "Arial", 14, "#000000", 60, 40, Cargar_ventana_VentanaAritmetica(), "Iniciar Evaluacion", 100, 100 ) ;
btnEvaluacion_VentanaPrincipal.alClic( Bienvenido() ) ;

Contenedor1_VentanaPrincipal.crearTexto( "Arial", 14, "#000000", 10, 250, falso, verdadero, "Haga clic en el siguiente boton para iniciar el area de reportes" );

var btnReportes_VentanaPrincipal = Contenedor1_VentanaPrincipal.crearBoton( "Arial", 14, "#000000", 60, 300, Cargar_ventana_VentanaReportes(), "Iniciar Reportes", 100, 100 ) ;
btnReportes_VentanaPrincipal.alClic( BienvenidoReporte() ) ;

var btnEnviar_VentanaPrincipal = Contenedor1_VentanaPrincipal.crearBoton( "Arial", 14, "#000000", 60, 350, nulo, "Sin funcionalidad", 100, 100 ) ;
btnEnviar_VentanaPrincipal.alClic( Guardar_VentanaPrincipal() ) ;

///////////////////////////////////////////////////////////////
//                 FIN VentanaPrincipal
///////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////
//                   INICIO VentanaAritmetica
////////////////////////////////////////////////////////////////
var VentanaAritmetica = crearVentana( "#2E2EFE", 900, 900, "VentanaAritmetica") ;
var ContenedorAritmeticas_VentanaAritmetica = VentanaAritmetica.crearContenedor( 1000, 1000, "#2E2EFE", verdadero, 10, 10 ) ;

ContenedorAritmeticas_VentanaAritmetica.crearTexto( "Times New Roman", 18, "#000000", 450, 20, verdadero, verdadero, "Bienvenido a la prueba de Aritmetica, responda las siguientes preguntas" );

ContenedorAritmeticas_VentanaAritmetica.crearTexto( "Arial", 14, "#000000", 10, 50, falso, falso, "Ingrese su Nombre:" );

ContenedorAritmeticas_VentanaAritmetica.crearCajaTexto ( 20, 100, "Arial", 14, "#000000", 100, 50, falso, falso, "Ingrese aqui su nombre", "CTNombre" ) ;

ContenedorAritmeticas_VentanaAritmetica.crearTexto( "Arial", 14, "#000000", 10, 150, falso, falso, "Ingrese la potencia de 5 a la 5:" );

ContenedorAritmeticas_VentanaAritmetica.crearControlNumerico ( 50, 100, 5000, 0, 150, 150, nulo, "CPotencia" ) ;

var btnPotencia_VentanaAritmetica = ContenedorAritmeticas_VentanaAritmetica.crearBoton( "Arial", 14, "#000000", 300, 150, nulo, "Ver Respuesta", 50, 100 ) ;
btnPotencia_VentanaAritmetica.alClic( VerPotencia(5,5) ) ;

ContenedorAritmeticas_VentanaAritmetica.crearTexto( "Arial", 14, "#000000", 10, 250, falso, falso, "Ingrese el Factorial de 7:" );

ContenedorAritmeticas_VentanaAritmetica.crearControlNumerico ( 50, 100, 6000, 0, 150, 250, nulo, "CFactorial" ) ;

var btnFactorial_VentanaAritmetica = ContenedorAritmeticas_VentanaAritmetica.crearBoton( "Arial", 14, "#000000", 300, 250, nulo, "Ver Respuesta", 50, 100 ) ;
btnFactorial_VentanaAritmetica.alClic( VerFactorial(7) ) ;

ContenedorAritmeticas_VentanaAritmetica.crearTexto( "Arial", 14, "#000000", 10, 350, falso, falso, "Ingrese el numero invertido de 351230347:" );

ContenedorAritmeticas_VentanaAritmetica.crearControlNumerico ( 50, 100, nulo, 0, 150, 350, nulo, "CInvertido" ) ;

var btnInvertido_VentanaAritmetica = ContenedorAritmeticas_VentanaAritmetica.crearBoton( "Arial", 14, "#000000", 300, 350, nulo, "Ver Respuesta", 50, 100 ) ;
btnInvertido_VentanaAritmetica.alClic( VerInvertido(351230347) ) ;

ContenedorAritmeticas_VentanaAritmetica.crearTexto( "Arial", 14, "#000000", 10, 450, falso, falso, "Ingrese el mcd de 240,506 con 10:" );

ContenedorAritmeticas_VentanaAritmetica.crearControlNumerico ( 50, 100, nulo, 0, 150, 450, nulo, "CMCD" ) ;

var btnMCD_VentanaAritmetica = ContenedorAritmeticas_VentanaAritmetica.crearBoton( "Arial", 14, "#000000", 300, 450, nulo, "Ver Respuesta", 50, 100 ) ;
btnMCD_VentanaAritmetica.alClic( VerMCD(240,506, 10) ) ;

ContenedorAritmeticas_VentanaAritmetica.crearTexto( "Arial", 14, "#000000", 10, 550, falso, falso, "Ingrese el Fibonacci de 19:" );

ContenedorAritmeticas_VentanaAritmetica.crearControlNumerico ( 50, 100, 6000, 0, 150, 550, nulo, "CFibonacci" ) ;

var btnFibonacci_VentanaAritmetica = ContenedorAritmeticas_VentanaAritmetica.crearBoton( "Arial", 14, "#000000", 300, 550, nulo, "Ver Respuesta", 50, 100 ) ;
btnFibonacci_VentanaAritmetica.alClic( VerFibonacci(19) ) ;
var ContEnviarAritmetica_VentanaAritmetica = VentanaAritmetica.crearContenedor( 100, 200, "#2E2EFE", falso, 10, 1010 ) ;

var btnEnviar_VentanaAritmetica = ContEnviarAritmetica_VentanaAritmetica.crearBoton( "Arial", 14, "#000000", 75, 30, Cargar_ventana_VentanaHistoria(), "Contestar", 70, 40 ) ;
btnEnviar_VentanaAritmetica.alClic( Guardar_VentanaAritmetica() ) ;

///////////////////////////////////////////////////////////////
//                 FIN VentanaAritmetica
///////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////
//                   INICIO VentanaHistoria
////////////////////////////////////////////////////////////////
var VentanaHistoria = crearVentana( "#2E2EFE", 900, 900, "VentanaHistoria") ;
var ContenedorHistoria_VentanaHistoria = VentanaHistoria.crearContenedor( 1000, 1000, "#2E2EFE", verdadero, 10, 10 ) ;

ContenedorHistoria_VentanaHistoria.crearTexto( "Times New Roman", 18, "#000000", 450, 20, verdadero, verdadero, "Bienvenido a la prueba de Historia, responda las siguientes preguntas" );

ContenedorHistoria_VentanaHistoria.crearTexto( "Arial", 14, "#000000", 10, 20, falso, falso, "Ingrese su Nombre:" );

ContenedorHistoria_VentanaHistoria.crearCajaTexto ( 10, 100, "Arial", 14, "#000000", 100, 20, falso, falso, "Ingrese aqui su nombre", "CTNombre" ) ;

ContenedorHistoria_VentanaHistoria.crearTexto( "Arial", 14, "#000000", 10, 250, falso, falso, "Ingrese el paisaje de la foto" );

var CDPaisaje1_VentanaHistoria = [ "Playa", "Luna", "Selva", "Desierto", "Oceano" ] ;

ContenedorHistoria_VentanaHistoria.crearDesplegable ( 50, 100, CDPaisaje1_VentanaHistoria , 150, 250, nulo, "CDPaisaje1" ) ;

ContenedorHistoria_VentanaHistoria.crearImagen( "playa.jpg", 300, 250, 100, 100 ) ;

var btnPlaya_VentanaHistoria = ContenedorHistoria_VentanaHistoria.crearBoton( "Arial", 14, "#000000", 500, 250, nulo, "Ver Respuesta", 50, 100 ) ;
btnPlaya_VentanaHistoria.alClic( Paisaje(10) ) ;

ContenedorHistoria_VentanaHistoria.crearTexto( "Arial", 14, "#000000", 10, 400, falso, falso, "Ingrese el paisaje de la foto" );

var CDPaisaje2_VentanaHistoria = [ "Playa", "Luna", "Selva", "Desierto", "Oceano" ] ;

ContenedorHistoria_VentanaHistoria.crearDesplegable ( 50, 100, CDPaisaje2_VentanaHistoria , 150, 400, nulo, "CDPaisaje2" ) ;

ContenedorHistoria_VentanaHistoria.crearImagen( "luna.jpg", 300, 400, 100, 100 ) ;

var btnLuna_VentanaHistoria = ContenedorHistoria_VentanaHistoria.crearBoton( "Arial", 14, "#000000", 500, 400, nulo, "Ver Respuesta", 50, 100 ) ;
btnLuna_VentanaHistoria.alClic( Paisaje(20) ) ;

ContenedorHistoria_VentanaHistoria.crearTexto( "Arial", 14, "#000000", 10, 550, falso, falso, "Ingrese el paisaje de la foto" );

var CDPaisaje3_VentanaHistoria = [ "Playa", "Luna", "Selva", "Desierto", "Oceano" ] ;

ContenedorHistoria_VentanaHistoria.crearDesplegable ( 50, 100, CDPaisaje3_VentanaHistoria , 150, 550, nulo, "CDPaisaje3" ) ;

ContenedorHistoria_VentanaHistoria.crearImagen( "selva.jpg", 300, 550, 100, 100 ) ;

var btnSelva_VentanaHistoria = ContenedorHistoria_VentanaHistoria.crearBoton( "Arial", 14, "#000000", 500, 550, nulo, "Ver Respuesta", 50, 100 ) ;
btnSelva_VentanaHistoria.alClic( Paisaje(30) ) ;

ContenedorHistoria_VentanaHistoria.crearTexto( "Arial", 14, "#000000", 10, 700, falso, falso, "Ingrese el paisaje de la foto" );

var CDPaisaje4_VentanaHistoria = [ "Playa", "Luna", "Selva", "Desierto", "Oceano" ] ;

ContenedorHistoria_VentanaHistoria.crearDesplegable ( 50, 100, CDPaisaje4_VentanaHistoria , 150, 700, nulo, "CDPaisaje4" ) ;

ContenedorHistoria_VentanaHistoria.crearImagen( "Desierto.jpg", 300, 700, 100, 100 ) ;

var btnDesierto_VentanaHistoria = ContenedorHistoria_VentanaHistoria.crearBoton( "Arial", 14, "#000000", 500, 700, nulo, "Ver Respuesta", 50, 100 ) ;
btnDesierto_VentanaHistoria.alClic( Paisaje(40) ) ;

ContenedorHistoria_VentanaHistoria.crearTexto( "Arial", 14, "#000000", 10, 850, falso, falso, "Ingrese el paisaje de la foto" );

var CDPaisaje5_VentanaHistoria = [ "Playa", "Luna", "Selva", "Desierto", "Oceano" ] ;

ContenedorHistoria_VentanaHistoria.crearDesplegable ( 50, 100, CDPaisaje5_VentanaHistoria , 150, 850, nulo, "CDPaisaje5" ) ;

ContenedorHistoria_VentanaHistoria.crearImagen( "oceano.jpg", 300, 850, 100, 100 ) ;

var btnOceano_VentanaHistoria = ContenedorHistoria_VentanaHistoria.crearBoton( "Arial", 14, "#000000", 500, 850, nulo, "Ver Respuesta", 50, 100 ) ;
btnOceano_VentanaHistoria.alClic( Paisaje(50) ) ;
var ContEnviarHistoria_VentanaHistoria = VentanaHistoria.crearContenedor( 100, 200, "#2E2EFE", falso, 10, 1010 ) ;

var btnEnviar_VentanaHistoria = ContEnviarHistoria_VentanaHistoria.crearBoton( "Arial", 14, "#000000", 75, 30, Cargar_ventana_VentanaIngles(), "Contestar", 70, 40 ) ;
btnEnviar_VentanaHistoria.alClic( Guardar_VentanaHistoria() ) ;

///////////////////////////////////////////////////////////////
//                 FIN VentanaHistoria
///////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////
//                   INICIO VentanaIngles
////////////////////////////////////////////////////////////////
var VentanaIngles = crearVentana( "#2E2EFE", 900, 900, "VentanaIngles") ;
var ContenedorIngles_VentanaIngles = VentanaIngles.crearContenedor( 1000, 1000, "#2E2EFE", verdadero, 10, 10 ) ;

ContenedorIngles_VentanaIngles.crearTexto( "Times New Roman", 18, "#000000", 450, 20, verdadero, verdadero, "Bienvenido a la prueba de Ingles, responda las siguientes preguntas" );

ContenedorIngles_VentanaIngles.crearTexto( "Arial", 14, "#000000", 10, 20, falso, falso, "Ingrese su Nombre:" );

ContenedorIngles_VentanaIngles.crearCajaTexto ( 10, 100, "Arial", 14, "#000000", 100, 20, falso, falso, "Ingrese aqui su nombre", "CTNombre" ) ;

ContenedorIngles_VentanaIngles.crearReproductor( "Ackermann.mp3", 450, 50, falso, 50, 100 ) ;

ContenedorIngles_VentanaIngles.crearTexto( "Arial", 14, "#000000", 10, 250, falso, falso, "What algorithm is the audio talking about?" );

ContenedorIngles_VentanaIngles.crearCajaTexto ( 50, 150, "Arial", 14, "#000000", 100, 250, falso, falso, "Ingrese aqui su respuesta", "CTPregunta" ) ;

var btnPregunta_VentanaIngles = ContenedorIngles_VentanaIngles.crearBoton( "Arial", 14, "#000000", 300, 250, nulo, "Ver Respuesta", 50, 100 ) ;
btnPregunta_VentanaIngles.alClic( Pregunta("Tipo") ) ;

ContenedorIngles_VentanaIngles.crearTexto( "Arial", 14, "#000000", 10, 350, falso, falso, "Ingrese el ackerman de 3,11:" );

ContenedorIngles_VentanaIngles.crearControlNumerico ( 50, 100, nulo, 0, 150, 350, nulo, "CAckerman" ) ;

var btnAckerman_VentanaIngles = ContenedorIngles_VentanaIngles.crearBoton( "Arial", 14, "#000000", 300, 350, nulo, "Ver Respuesta", 50, 100 ) ;
btnAckerman_VentanaIngles.alClic( Pregunta("Resultado") ) ;
var ContEnviarIngles_VentanaIngles = VentanaIngles.crearContenedor( 100, 200, "#2E2EFE", falso, 10, 1010 ) ;

var btnEnviar_VentanaIngles = ContEnviarIngles_VentanaIngles.crearBoton( "Arial", 14, "#000000", 75, 30, Cargar_ventana_VentanaLogica(), "Contestar", 70, 40 ) ;
btnEnviar_VentanaIngles.alClic( Guardar_VentanaIngles() ) ;

///////////////////////////////////////////////////////////////
//                 FIN VentanaIngles
///////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////
//                   INICIO VentanaLogica
////////////////////////////////////////////////////////////////
var VentanaLogica = crearVentana( "#2E2EFE", 900, 900, "VentanaLogica") ;
var ContenedorLogica_VentanaLogica = VentanaLogica.crearContenedor( 1000, 1000, "#2E2EFE", verdadero, 10, 10 ) ;

ContenedorLogica_VentanaLogica.crearTexto( "Times New Roman", 18, "#000000", 450, 20, verdadero, verdadero, "Bienvenido a la prueba de Logica, responda las siguientes preguntas" );

ContenedorLogica_VentanaLogica.crearTexto( "Arial", 14, "#000000", 10, 20, falso, falso, "Ingrese su Nombre:" );

ContenedorLogica_VentanaLogica.crearCajaTexto ( 10, 100, "Arial", 14, "#000000", 100, 20, falso, falso, "Ingrese aqui su nombre", "CTNombre" ) ;

ContenedorLogica_VentanaLogica.crearTexto( "Arial", 14, "#000000", 10, 150, falso, falso, "Resuelva las torres de Hanoi con 3 discos, origen 1, destino 3 y auxiliar 2" );

ContenedorLogica_VentanaLogica.crearAreaTexto ( 150, 50, "Arial", 14, "#000000", 150, 150, falso, falso, nulo, "CHanoi" ) ;

var btnHanoi_VentanaLogica = ContenedorLogica_VentanaLogica.crearBoton( "Arial", 14, "#000000", 300, 150, nulo, "Ver Respuesta", 50, 100 ) ;
btnHanoi_VentanaLogica.alClic( hanoi(3,1,2,3) ) ;

ContenedorLogica_VentanaLogica.crearTexto( "Arial", 14, "#000000", 10, 350, falso, falso, "Ingrese el Hofstader Femenina 10:" );

ContenedorLogica_VentanaLogica.crearCajaTexto ( 50, 100, "Arial", 14, "#000000", 150, 350, falso, falso, nulo, "CTHF" ) ;

var btnHF_VentanaLogica = ContenedorLogica_VentanaLogica.crearBoton( "Arial", 14, "#000000", 300, 350, nulo, "Ver Respuesta", 50, 100 ) ;
btnHF_VentanaLogica.alClic( VerFemenina() ) ;

ContenedorLogica_VentanaLogica.crearTexto( "Arial", 14, "#000000", 10, 450, falso, falso, "Ingrese el Hofstader Maculino 10:" );

ContenedorLogica_VentanaLogica.crearCajaTexto ( 50, 100, "Arial", 14, "#000000", 150, 450, falso, falso, nulo, "CTHM" ) ;

var btnHM_VentanaLogica = ContenedorLogica_VentanaLogica.crearBoton( "Arial", 14, "#000000", 300, 450, nulo, "Ver Respuesta", 50, 100 ) ;
btnHM_VentanaLogica.alClic( VerMasculino() ) ;

ContenedorLogica_VentanaLogica.crearTexto( "Arial", 14, "#000000", 10, 550, falso, falso, "Ingrese si 26 es par o impar" );

ContenedorLogica_VentanaLogica.crearCajaTexto ( 50, 100, "Arial", 14, "#000000", 150, 550, falso, falso, nulo, "CTPar" ) ;

var btnPar_VentanaLogica = ContenedorLogica_VentanaLogica.crearBoton( "Arial", 14, "#000000", 300, 550, nulo, "Ver Respuesta", 50, 100 ) ;
btnPar_VentanaLogica.alClic( VerPar(26) ) ;

ContenedorLogica_VentanaLogica.crearTexto( "Arial", 14, "#000000", 10, 650, falso, falso, "Ingrese si 27 es par o impar" );

ContenedorLogica_VentanaLogica.crearCajaTexto ( 50, 100, "Arial", 14, "#000000", 150, 650, falso, falso, nulo, "CTImpar" ) ;

var btnImpar_VentanaLogica = ContenedorLogica_VentanaLogica.crearBoton( "Arial", 14, "#000000", 300, 650, nulo, "Ver Respuesta", 50, 100 ) ;
btnImpar_VentanaLogica.alClic( VerImpar(27) ) ;
var ContEnviarLogicas_VentanaLogica = VentanaLogica.crearContenedor( 100, 200, "#2E2EFE", falso, 10, 1010 ) ;

var btnEnviar_VentanaLogica = ContEnviarLogicas_VentanaLogica.crearBoton( "Arial", 14, "#000000", 75, 30, Cargar_ventana_VentanaReportes(), "Contestar", 70, 40 ) ;
btnEnviar_VentanaLogica.alClic( Guardar_VentanaLogica() ) ;

///////////////////////////////////////////////////////////////
//                 FIN VentanaLogica
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



funcion Cargar_ventana_VentanaAritmetica() {
	VentanaAritmetica.alCargar() ;
}

funcion Cargar_ventana_VentanaReportes() {
	VentanaReportes.alCargar() ;
}

funcion Guardar_VentanaPrincipal() {
	VentanaPrincipal.crearArrayDesdeArchivo() ;
	EnviarSinFuncionalidad() ;
}

funcion Guardar_VentanaAritmetica() {
	VentanaAritmetica.crearArrayDesdeArchivo() ;
}

funcion Cargar_ventana_VentanaHistoria() {
	VentanaHistoria.alCargar() ;
}

funcion Guardar_VentanaHistoria() {
	VentanaHistoria.crearArrayDesdeArchivo() ;
}

funcion Cargar_ventana_VentanaIngles() {
	VentanaIngles.alCargar() ;
}

funcion Guardar_VentanaIngles() {
	VentanaIngles.crearArrayDesdeArchivo() ;
}

funcion Cargar_ventana_VentanaLogica() {
	VentanaLogica.alCargar() ;
}

funcion Guardar_VentanaLogica() {
	VentanaLogica.crearArrayDesdeArchivo() ;
}

funcion Guardar_VentanaReportes() {
	VentanaReportes.crearArrayDesdeArchivo() ;
	EnviarSinFuncionalidad() ;
}

VentanaPrincipal.alCargar() ;
