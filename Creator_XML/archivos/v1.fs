
funcion mostrar(var val)
{
	imprimir ( val );
}

funcion final()
{
	imprimir("final");
}


funcion reporte()
{
	var b = crearArrayDesdeArchivo("v1.gdato");
	b.map(valor_sumar);
}

funcion valor_sumar ( var b )
{
	imprimir ( b.cont1 + ":" );
	imprimir ( b.cont2 + 2 );
}