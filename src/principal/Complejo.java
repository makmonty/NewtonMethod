package principal;

public class Complejo {
	
	public static final int COOR_POLARES = 0;
	public static final int COOR_CARTESIANAS = 1;
	
	private double re;
	private double im;
	
	private Double modulo = null;
	private Double angulo = null;
	
	public Complejo(double real, double imaginario)
	{
		this.re = real;
		this.im = imaginario;
	}
	
	public Complejo(double c1, double c2, int tipo)
	{
		// c1 es parte real y c2 imaginaria
		if(tipo == COOR_CARTESIANAS)
		{
			this.re = c1;
			this.im = c2;
		}
		// c1 es modulo y c2 angulo
		else if(tipo == COOR_POLARES)
		{
			double[] coor = polaresACartesianas(c1, c2);
			
			this.re = coor[0];
			this.im = coor[1];
		}
	}
	
	public static double[] polaresACartesianas(double modulo, double angulo)
	{
		double[] c = new double[2];
		
		c[0] = modulo*Math.cos(angulo);
		c[1] = modulo*Math.sin(angulo);
		
		return c;
	}

	public double getRe() {
		return re;
	}

	public double getIm() {
		return im;
	}

	public double getModulo() {
		if(modulo == null)
			modulo = Math.sqrt(Math.pow(re, 2) + Math.pow(im, 2));
		
		return modulo;
	}

	public double getAngulo() {
		if(angulo == null)
		{
			if(re == 0)
			{
				if(im < 0)
					angulo = Math.PI*3/4;
				else
					angulo = Math.PI/4;
			}
			else
				angulo = Math.atan2(im,re);
		}
		
		return angulo;
	}
	
	public Complejo suma(Complejo c)
	{
		return new Complejo(re + c.getRe(), im + c.getIm());
	}
	
	public Complejo suma(double c)
	{
		return new Complejo(re + c, im);
	}
	
	public Complejo resta(Complejo c)
	{
		return new Complejo(re - c.getRe(), im - c.getIm());
	}
	
	public Complejo resta(double c)
	{
		return new Complejo(re - c, im);
	}
	
	public Complejo mult(Complejo c)
	{
		double a = c.getRe();
		double b = c.getIm();
		
		return new Complejo(re*a - im*b, re*b + im*a);
	}
	
	public Complejo mult(double c)
	{
		return new Complejo(re*c, im*c);
	}
	
	public Complejo div(Complejo c)
	{
		double mod1 = this.getModulo();
		double ang1 = this.getAngulo();
		double mod2 = c.getModulo();
		double ang2 = c.getAngulo();
		
		return new Complejo(mod1/mod2, ang1-ang2, COOR_POLARES);
	}
	
	public Complejo div(double c)
	{
		return new Complejo(re/c, im/c);
	}
	
	public Complejo elevar(int n)
	{
		return new Complejo(Math.pow(getModulo(), n), getAngulo()*n, COOR_POLARES);
	}
	
	public double distancia(Complejo c)
	{
		return (Math.sqrt(Math.pow(re-c.getRe(), 2) + Math.pow(im-c.getIm(), 2)));
	}
	
	public Complejo truncar(int numDecimales)
	{
		int n = (int)Math.pow(10,numDecimales);
		double reTrunc = Math.floor(re*n)/n;
		double imTrunc = Math.floor(im*n)/n;
	    
	    return new Complejo(reTrunc, imTrunc);
	}
	
	public boolean equals(Object o)
	{
		if(o instanceof Complejo)
		{
			Complejo c = (Complejo)o;
			return re == c.getRe() && im == c.getIm();
		}
		else
			return false;
	}
	
	public Object clone()
	{
		return new Complejo(re, im);
	}
	
	public String toString()
	{
		if(im==0)
			return re+"";
		if(re==0)
			return im+"i";
		if(im>0)
			return re+"+"+im+"i"; 
				
		return re+""+im+"i";
	}
}
