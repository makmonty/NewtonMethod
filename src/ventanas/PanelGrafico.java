package ventanas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.JLabel;
import javax.swing.JPanel;

import principal.Complejo;

import principal.NewtonMethod;

public class PanelGrafico extends JPanel {

	private double izquierda = -10;
	private double derecha = 10;
	private double arriba = 5;
	private double abajo = -5;
	
	public double[][][] coordenadasRelativas;
	
	private BufferedImage imagen;
	
	private static final long serialVersionUID = 1L;
	
	private Point inicioZoom;
	private Point finZoom;
	private boolean arrastrando;
	
	NewtonMethod nm;
	private boolean verSoluciones;
	private boolean verIteraciones;
	
	private Complejo[] iteraciones;
	
	public PanelGrafico()
	{
		super();
	}
	
	public void setBounds(int x, int y, int w, int h)
	{
		super.setBounds(x, y, w, h);
		
		imagen = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		actualizarCoordenadasRelativas();
		
		blanquearImagen();
	}
	
	public void setBounds(Rectangle r)
	{
		setBounds((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
	}
	
	public void actualizarCoordenadasRelativas()
	{
		coordenadasRelativas = new double[this.getWidth()][this.getHeight()][2];
		for(int i=0; i<this.getWidth(); i++)
		{
			for(int j=0; j<this.getHeight(); j++)
			{
				coordenadasRelativas[i][j] = calcularCoordenadaRelativa(i,j);
			}
		}
	}
	
	public void blanquearImagen()
	{
		WritableRaster raster = imagen.getRaster();
		
		int[] blanco = {255,255,255};
		for(int i=0; i < raster.getWidth(); i++)
		{
			for(int j=0; j < raster.getHeight(); j++)
			{
				raster.setPixel(i, j, blanco);
			}
		}
	}
	
	public void ratonPulsa(Point p)
	{
		arrastrando = true;
		inicioZoom = p;
		finZoom = p;
		//System.out.println("Pulsando: " + arrastrando);
	}
	
	public void ratonArrastrando(Point p)
	{
		//System.out.println("Moviendo");
		if(arrastrando)
		{
			finZoom = p;
			
			this.repaint();
		}
	}
	
	public void ratonMoviendo(Point p, JLabel realField, JLabel imagField)
	{
		realField.setText(""+ coordenadasRelativas[p.x][p.y][0]);
		imagField.setText(""+ coordenadasRelativas[p.x][p.y][1]);
	}
	
	public void ratonSuelta(Point p)
	{
		
		//System.out.println("inicio: " + coordenadasRelativas[inicioZoom.x][inicioZoom.y][0] + ","+ coordenadasRelativas[inicioZoom.x][inicioZoom.y][1]);
		//System.out.println("Soltando");
		arrastrando = false;
	
		// Ahora hay que especificar los nuevos
		// arriba, abajo, izquierda y derecha
		int tempArriba;
		int tempAbajo;
		int tempIzquierda;
		int tempDerecha;
		
		if(inicioZoom.y > finZoom.y)
		{
			tempArriba = finZoom.y;
			tempAbajo = inicioZoom.y;
		}
		else
		{
			tempArriba = inicioZoom.y;
			tempAbajo = finZoom.y;
		}
		
		if(inicioZoom.x > finZoom.x)
		{
			tempIzquierda = finZoom.x;
			tempDerecha = inicioZoom.x;
		}
		else
		{
			tempIzquierda = inicioZoom.x;
			tempDerecha = finZoom.x;
		}
		
		if(tempIzquierda != tempDerecha && tempArriba != tempAbajo)
		{
			izquierda = coordenadasRelativas[tempIzquierda][tempArriba][0];
			arriba = coordenadasRelativas[tempIzquierda][tempArriba][1];
			derecha = coordenadasRelativas[tempDerecha][tempAbajo][0];
			abajo = coordenadasRelativas[tempDerecha][tempAbajo][1];
			
			actualizarCoordenadasRelativas();
			
			blanquearImagen();
			this.repaint();
		}
	}
	
	public void muestraIteraciones(Point p, int numIteraciones)
	{
		//System.out.println("Dibujando iteraciones");
		verIteraciones=true;
		iteraciones = new Complejo[numIteraciones+1];
		
		double[] cAbs = coordenadasRelativas[p.x][p.y];
		
		iteraciones[0] = new Complejo(cAbs[0], cAbs[1]);
		
		double resolucion = calculaResolucion();
		//System.out.println("Resolucion: " + resolucion);
		
		for(int i=1; i<=numIteraciones; i++)
		{
			iteraciones[i] = nm.algoritmoNewton(iteraciones[i-1], 1, resolucion);

			//System.out.println("" + iteraciones[i]);
			//System.out.println("Distancia: " + iteraciones[i].distancia(iteraciones[i-1]));
		}
		
		this.repaint();
		//System.out.println("Iteraciones dibujadas");
	}
	
	public void ocultaIteraciones()
	{
		verIteraciones = false;
		this.repaint();
	}
	
	
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		
		//blanquearImagen();
		//System.out.println("Redibujando");
		dibujaNewton(g2d);
		dibujaEjesCoordenadas(g2d);
		
		if(arrastrando)
		{
			dibujaCuadroSeleccion(g2d);
		}
		
		if(verSoluciones)
		{
			dibujaSoluciones(g2d);
		}
		
		if(verIteraciones)
		{
			dibujaIteraciones(g2d);
		}
	}
	
	private void dibujaIteraciones(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		boolean verCoordenadas;
		g2d.setColor(Color.black);
		for(int i=0; i<iteraciones.length; i++)
		{
			if(i==0 || i==iteraciones.length-1)
				verCoordenadas=true;
			else
				verCoordenadas=false;
			dibujaPunto(iteraciones[i], 8, 8, verCoordenadas, g2d);
			if(i>0)
				dibujaLinea(iteraciones[i-1], iteraciones[i], g2d);
		}
	}
	
	private void dibujaCuadroSeleccion(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		
		// Esto por si ha dibujado el cuadro al reves
		int tempArriba;
		int tempIzquierda;
		
		if(inicioZoom.y > finZoom.y)
			tempArriba = finZoom.y;
		else
			tempArriba = inicioZoom.y;
		
		if(inicioZoom.x > finZoom.x)
			tempIzquierda = finZoom.x;
		else
			tempIzquierda = inicioZoom.x;
		
		int width = Math.abs(finZoom.x - inicioZoom.x);
		int height = Math.abs(finZoom.y - inicioZoom.y);
		
		g2d.setColor(Color.red);
		g2d.drawRect(tempIzquierda, tempArriba, width, height);
	}
	
	private void dibujaNewton(Graphics g)
	{
		Graphics g2d = (Graphics2D)g;
		g2d.drawImage(imagen, 0, 0, null);
	}
	
	public void dibujaSoluciones(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;

		g2d.setColor(Color.black);
		for(int i=0; i<nm.soluciones.length; i++)
		{
			dibujaPunto(nm.soluciones[i], 8, 8, true, g2d);
		}
		
		this.repaint();
	}
	
	public void muestraSoluciones()
	{
		//System.out.println("Mostrando soluciones");
		verSoluciones = true;
		this.repaint();
	}
	
	public void ocultaSoluciones()
	{
		//System.out.println("Ocultando soluciones");
		verSoluciones = false;
		this.repaint();
	}
	
	public void dibujaPunto(Complejo centro, int anchura, int altura, boolean verCoordenadas, Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		int[] ca = coordenadaAbsoluta(centro.getRe(), centro.getIm());
		
		int x = ca[0] - anchura/2;
		int y = ca[1] - altura/2;
		
		g2d.drawOval(x, y, anchura, altura);
		
		double resolucion = calculaResolucion();
		int numDecimales = 0;
		while(resolucion<1)
		{
			resolucion *= 10;
			numDecimales++;
		}
		
		Complejo truncado = centro.truncar(numDecimales);
		
		if(verCoordenadas)
			g2d.drawString(truncado.toString(), x+anchura, y);
	}
	
	private void dibujaLinea(Complejo c1, Complejo c2, Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		int[] c1abs = coordenadaAbsoluta(c1.getRe(), c1.getIm());
		int[] c2abs = coordenadaAbsoluta(c2.getRe(), c2.getIm());
		
		g2d.drawLine(c1abs[0], c1abs[1], c2abs[0], c2abs[1]);
	}
	
	private void dibujaEjesCoordenadas(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		
		// {horizontal,vertical}
		int[] ejes = getCentroCoordenadas();
		
		// Especifica el color
		g2d.setColor(Color.BLACK);
		// Dibuja eje horizontal
		g2d.drawLine(0, ejes[1], this.getWidth(), ejes[1]);
		// Dibuja ejevertical
		g2d.drawLine(ejes[0], 0, ejes[0], this.getHeight());
		
		//TODO Dibujar marcas
	}
	
	/***
	 * Devuelve las coordenadas en las que dibujar los ejes de coordenadas
	 * @return Matriz bidimensional cuyo primer valor es la coordenada
	 *         en la que dibujar el eje horizontal y el segundo la coordenada
	 *         en la que dibujar el eje vertical
	 */
	private int[] getCentroCoordenadas()
	{
		return coordenadaAbsoluta(0,0);
	}
	
	public double[] calcularCoordenadaRelativa(int i, int j)
	{
		double[] cr = new double[2];
		
		int anchuraPanel = this.getWidth();
		int alturaPanel = this.getHeight();
		
		double anchuraGrafico = derecha - izquierda;
		double alturaGrafico = arriba - abajo;
		
		double proporcionAltura = alturaPanel/alturaGrafico;
		double proporcionAnchura = anchuraPanel/anchuraGrafico;
		
		cr[0] = izquierda + i/proporcionAnchura;
		cr[1] = abajo + (alturaPanel-j)/proporcionAltura;
		
		return cr;
	}
	
	public int[] coordenadaAbsoluta(double i, double j)
	{
		int anchuraPanel = this.getWidth();
		int alturaPanel = this.getHeight();
		
		double anchuraGrafico = derecha - izquierda;
		double alturaGrafico = arriba - abajo;
		
		double proporcionAltura = alturaPanel/alturaGrafico;
		double proporcionAnchura = anchuraPanel/anchuraGrafico;
		
		int[] coor = new int[2];
		
		// Eje horizontal
		coor[0] = (int)Math.round((i-izquierda)*proporcionAnchura);
		// Eje vertical
		coor[1] = (int)Math.round(-(j-abajo)*proporcionAltura + alturaPanel);
		
		return coor;
	}
	
	public void inicializar(int n, double c)
	{
		nm = new NewtonMethod(n, c);
	}
	
	public double calculaResolucion()
	{
		double resHorizontal = coordenadasRelativas[1][0][0] - coordenadasRelativas[0][0][0];
		double resVertical = coordenadasRelativas[0][0][1] - coordenadasRelativas[0][1][1];
		return Math.min(resHorizontal, resVertical);
	}
	
	public void newton(int numIteraciones)
	{
		System.out.println("Iniciando");
		System.out.println("Ajustando variables");
		Complejo complejo, z;
		WritableRaster raster = imagen.getRaster();
		
		int anchuraPanel = this.getWidth();
		int alturaPanel = this.getHeight();
		
		// Variables para cronometrar
		long inicio, fin;
		
		this.blanquearImagen();
		
		System.out.println("Calculando error tolerable");
		// Si toleramos un error, el algoritmo puede
		// ir mucho mas rapido.
		// Este error lo sacamos en funcion de la
		// resolucion que tienen los pixele
		double resolucion = calculaResolucion();
		System.out.println("Resolucion " + resolucion);
		System.out.println("Recorriendo plano (" + anchuraPanel + "x" + alturaPanel + ", " + (anchuraPanel*alturaPanel) + " pixeles)");
		
		inicio = System.currentTimeMillis();
		for(int i=0; i<this.getHeight(); i++)
		{
			for(int j=0; j<this.getWidth(); j++)
			{
				// Las i y j no se corresponden
				// con las coordenadas dibujadas,
				// asi que hay que transformarlas
				double[] coor = coordenadasRelativas[j][i];
				
				complejo = new Complejo(coor[0], coor[1]);
				
				z = nm.algoritmoNewton(complejo, numIteraciones, resolucion);
				
				raster.setPixel(j, i, nm.asignaColor(z));
			}
			this.dibujaNewton(this.getGraphics());
		}
		fin = System.currentTimeMillis();
		
		
		this.repaint();
		
		System.out.println("Finalizado. Tiempo total: " + (fin - inicio) + " milisegundos.");
	}
	
	public void setIzquierda(double i)
	{
		if(i < derecha)
			izquierda = i;
		else
			izquierda = derecha-1;
	}
	
	public void setDerecha(double i)
	{
		if(i > izquierda)
			derecha = i;
		else
			derecha = izquierda+1;
	}
	
	public void setAbajo(double i)
	{
		if(i < arriba)
			abajo = i;
		else
			abajo = arriba-1;
	}
	
	public void setArriba(double i)
	{
		if(i > abajo)
			arriba = i;
		else
			arriba = abajo+1;
	}
	
	public double getIzquierda()
	{
		return izquierda;
	}
	
	public double getDerecha()
	{
		return derecha;
	}
	
	public double getArriba()
	{
		return arriba;
	}
	
	public double getAbajo()
	{
		return abajo;
	}
}

