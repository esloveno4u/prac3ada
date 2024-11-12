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
			pago = new int[estimacion.length];
		//***Completar Implementación***
		for(int i = estimacion.length-1; i>=0;i--){
			if(i==estimacion.length-1){
				pago[i]=tarifaMegas * estimacion[i];
			}else if(i+permanencia>estimacion.length-1){
				pago[i] = pago[i + 1] + tarifaMegas * estimacion[i];
			}else{
				if(pago[i + 1] + tarifaMegas * estimacion[i]<tarifaPlana*permanencia+pago[i+permanencia]){
					pago[i] = pago[i + 1] + tarifaMegas * estimacion[i];
				}else{
					for(int j=i+permanencia; j>=i;j--){
						if(i+permanencia==estimacion.length-1){
							pago[j]=tarifaPlana;
						}else{
							pago[j]=tarifaPlana+pago[j+1];
						}
					}
				}
			}
		}
		return pago[0];
	}

	public int[] reconstruirSol() {
		if (pago == null) {
			throw new RuntimeException("Se debe resolver el problema primero");
		}
		int [] sol= new int[pago.length];
		for(int i = 0 ; i<pago.length-1;i++){
			if(pago[i]==pago[i+1]+tarifaMegas * estimacion[i]){
				sol[i]=0;
			}else{
				sol[i]=1;
			}
		}
		return sol;
	}

	public void imprimeVectorSolucion() {
		System.out.println(Arrays.toString(pago));
	}

}