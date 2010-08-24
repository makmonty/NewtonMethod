package principal;

import principal.Complejo;

public class NewtonMethod {
	
	private int n;
	private double c;
	
	public Complejo[] soluciones;
	public int[][] gama;
	
	public static final int GRISES = 0;
	public static final int COLORES = 1;
	
	public NewtonMethod(int n, double c)
	{
		this(n, c, COLORES);
	}
	
	public NewtonMethod(int n, double c, int g)
	{
		this.n = n;
		this.c = c;
		
		// Calcula soluciones de la ecuacion z^n+c
		soluciones = new Complejo[n];
		double saltoAngulo = Math.PI*2/n;
		
		for(int i=0; i<n; i++)
		{
			if(c<0)
				soluciones[i] = new Complejo(Math.pow(c, 1/n), i*saltoAngulo, Complejo.COOR_POLARES);
			else
				soluciones[i] = new Complejo(Math.pow(c, 1/n), i*saltoAngulo + Math.PI/n, Complejo.COOR_POLARES);
		}
		
		// Genera la gama de colores
		generaGamaColores(g);
	}
	
	private void generaGamaColores(int g)
	{
		if(g == GRISES)
		{
			gama = new int[n][3];
			
			for(int i=0; i<gama.length; i++)
			{
				int color = (255/n)*i;
				gama[i][0] = color;
				gama[i][1] = color;
				gama[i][2] = color;
				System.out.println("Color " + color);
			}
		}
		else
		{
			gama = new int[10][3];
			int[] rojo = {255,0,0};
			int[] verde = {0,255,0};
			int[] azul = {0,0,255};
			int[] amarillo = {255,255,0};
			int[] morado = {255,0,255};
			int[] celeste = {0,255,255};
			int[] naranja = {255,127,0};
			int[] gris = {127,127,127};
			int[] rojoclaro = {255,0,127};
			int[] azulmarino = {127,0,255};
			
			gama[0] = rojo;
			gama[1] = verde;
			gama[2] = azul;
			gama[3] = amarillo;
			gama[4] = morado;
			gama[5] = celeste;
			gama[6] = naranja;
			gama[7] = gris;
			gama[8] = rojoclaro;
			gama[9] = azulmarino;
			
		}
	}
	
	public Complejo algoritmoNewton(Complejo z, int numIteraciones, double errorTolerable)
	{
		Complejo resultado = null;
		//Complejo zAnterior = null;
		Complejo zNuevo = null;

		/*
		// Metodo iterativo
		zAnterior = z;
		int i=0;
		while(i < numIteraciones && error > errorTolerable)
		{
			zNuevo = zAnterior.resta(funcion(zAnterior).div(derivada(zAnterior)));
			error = zNuevo.distancia(zAnterior)/zNuevo.getModulo();
			zAnterior = zNuevo;
			i++;
		}
		resultado = zNuevo;
		 */
		
		// Metodo recursivo
		if(numIteraciones == 0)
		{
			resultado = z;
		}
		else
		{
			// Operacion resultado = z - f(z)/f'(z);
			zNuevo = z.resta(funcion(z).div(derivada(z)));
			if(zNuevo.distancia(z) > errorTolerable)
			{
				resultado = algoritmoNewton(zNuevo, numIteraciones-1, errorTolerable);
			}
			else
			{
				resultado = zNuevo;
			}
		}
		
		return resultado;
	}
	
	/***
	 * Devuelve un color dependiendo del valor complejo
	 * pasado por parametro
	 * @param z Numero complejo que decide el color
	 * @return Color
	 */
	public int[] asignaColor(Complejo z)
	{
		int cercano=0;
		double distanciaCercano = soluciones[0].distancia(z);
		
		for(int i=1; i<soluciones.length; i++)
		{
			double distancia = soluciones[i].distancia(z); 
			if(distancia < distanciaCercano)
			{
				cercano = i;
				distanciaCercano = distancia;
			}
		}
		
		return gama[cercano%gama.length];
	}
	
	public Complejo funcion(Complejo z)
	{
		return z.elevar(n).suma(c);
	}
	
	public Complejo derivada(Complejo z)
	{
		return z.elevar(n-1).mult(n);
	}
}
