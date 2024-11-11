import java.util.Arrays;

public class TarifaTelefonica {

	private int tarifaPlana, permanencia, tarifaMegas;
	private int[] estimacion;
	private int[] pago;// Pago mínimo a realizar durante los meses de i...n

	public TarifaTelefonica(int tp, int p, int tm, int[] est) {
		tarifaPlana = tp;
		permanencia = p;
		tarifaMegas = tm;
		estimacion = est;
		pago = null;
	}

	public int resolverBottomUp() {

		//***Completar Implementación***

		return pago[estimacion.length-1];
	}

	public int[] reconstruirSol() {
		if (pago == null) {
			throw new RuntimeException("Se debe resolver el problema primero");
		}
		
		//***Completar implementación***
		return null;
	}

	public void imprimeVectorSolucion() {
		System.out.println(Arrays.toString(pago));
	}

}