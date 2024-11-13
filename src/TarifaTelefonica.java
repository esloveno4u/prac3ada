import java.util.Arrays;

public class TarifaTelefonica {

	private int tarifaPlana, permanencia, tarifaMegas;
	private int[] estimacion;
	private int[] pago;// Pago mínimo a realizar durante los meses de i...n
	public TarifaTelefonica(int tp, int p, int tm, int[] est) {
		tarifaPlana = tp;
		permanencia = p-1;
		tarifaMegas = tm;
		estimacion = est;
		pago = null;
	}

	public int resolverBottomUp() {
		//**Completar Implementación**
		int n=estimacion.length;
		pago=new int[n+1];
		pago[n]=0;
		for(int j=n-1; j>=0; j--) {
			if(j+permanencia > n){
				pago[j]=tarifaMegas * estimacion[j] + pago[j+1];
			}else {
				pago[j]=Math.min(tarifaMegas * estimacion[j] + pago[j+1], permanencia * tarifaPlana + pago[j+permanencia]);
			}
		}
		return pago[0];

	}

	public int[] reconstruirSol() {
		if (pago == null) {
			throw new RuntimeException("Se debe resolver el problema primero");
		}
		// **Completar implementación**
		int n=estimacion.length;
		int[] pago1 = new int[n];
		int mes=0;
		while(mes<n){
			if(pago[mes]== tarifaMegas * estimacion[mes] + pago[mes+1]){
				pago1[mes]=0;
				mes++;
			}else{
				for(int j=0; j<permanencia && mes+j <n; j++){
					pago1[mes+j]=1;
				}
				mes+=permanencia;
			}
		}
		return pago1;
	}


	public void imprimeVectorSolucion() {
		System.out.println(Arrays.toString(pago));
	}
	//casoLIM
	private boolean casoLim(int i,int cant){
		boolean lim = i==estimacion.length-1;
		if(lim){
			pago[i]=cant;
		}else{
			pago[i]=cant+1;
		}
		return lim;
	}
	private boolean casoLimite(int i){
		return i>=estimacion.length-1;
	}
}