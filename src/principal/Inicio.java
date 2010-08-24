package principal;
import ventanas.*;
//import principal.Complejo;

public class Inicio {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		Complejo z1 = new Complejo(-0.5,0);
		Complejo z2 = new Complejo(2,0);
		Complejo z3 = new Complejo(1,1);
		Complejo z4 = new Complejo(3, Math.PI/2, Complejo.COOR_POLARES);
		
		System.out.println("Angulo " + z1.getAngulo());
		System.out.println("Modulo " + z1.getModulo());
		System.out.println("Distancia a z3: " + z1.distancia(z3));
		
		NewtonMethod nm = new NewtonMethod(3,-1);
		
		Complejo[] soluciones = nm.soluciones;
		for(int i=0; i<soluciones.length; i++)
		{
			System.out.print("" + soluciones[i]);
		}
		System.out.println("");
		
		System.out.println("Soluciones: ");
		
		Complejo c = nm.algoritmoNewton(z1, 5, 0.26666666);
		
		
		System.out.println("Resultado = " + c);
		System.out.println("Funcion: " + nm.funcion(z1));
		System.out.println("Pow: " + Math.pow(z1.getRe(), 3));
		System.out.println("Z^3: " + z1.elevar(3));
		System.out.println("Z^3-1" + z1.elevar(3).resta(1));
		System.out.println("Angulo: " + z1.getAngulo());
		System.out.println("Derivada: " + nm.derivada(z1));
		System.out.println("f(z)/f'(z): " + nm.funcion(z1).div(nm.derivada(z1)));
		
		System.out.println("z1-z2 = " + z1.resta(z2));
		System.out.println("angulo z2 " + z2.getAngulo());
		System.out.println("angulo z3 " + z3.getAngulo());
		System.out.println("suma de ambos " + (z2.getAngulo() + z3.getAngulo()));
		System.out.println("z2*z3 = " + z2.mult(z3));
		System.out.println("z3^2 = " + z3.elevar(2));
		System.out.println("z4 = " + z4);
		System.out.println("sen(pi*2/3) = " + Math.cos(Math.PI*2/3));
		*/
		VentanaPrincipal vp = new VentanaPrincipal();
		
		vp.setVisible(true);
	}

}
