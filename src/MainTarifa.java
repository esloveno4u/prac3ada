import java.util.Arrays;

public class MainTarifa {
	public static void main(String[] args) {
		int[] estimacion= {20, 22, 21, 15, 18, 15, 15, 12, 13, 16};

		TarifaTelefonica p=new TarifaTelefonica(7,6,2,estimacion);
		System.out.println("El pago acumulado es: " + p.resolverBottomUp());
		System.out.println("El plan que el estudiante seguirá es: ");
		System.out.println(Arrays.toString(p.reconstruirSol()));
		p.imprimeVectorSolucion();
	}

}
