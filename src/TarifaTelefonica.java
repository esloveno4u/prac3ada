import java.util.Arrays;

public class TarifaTelefonica {

	private int tarifaPlana, permanencia, tarifaMegas;
	private int[] estimacion;
	private int[] pago;// Pago mínimo a realizar durante los meses de i...n
	private int[] tarifa;
	public TarifaTelefonica(int tp, int p, int tm, int[] est) {
		tarifaPlana = tp;
		permanencia = p-1;
		tarifaMegas = tm;
		estimacion = est;
		pago = null;
	}

	public int resolverBottomUp() {
			pago = new int[estimacion.length];
			tarifa = new int [estimacion.length];
		//***Completar Implementación***
		for(int i = estimacion.length-1; i>=0;i--){
			if(i+permanencia>estimacion.length){
				if(!casoLim(i,tarifaMegas*estimacion[i])){
					pago[i] = pago[i + 1] + tarifaMegas * estimacion[i];
				}
				tarifa[i]=0;
			}else{
				if(casoLimite(i+permanencia)){
					if(pago[i + 1] + tarifaMegas * estimacion[i]< tarifaPlana*permanencia){
						pago[i] = pago[i + 1] + tarifaMegas * estimacion[i];
						tarifa[i]=0;
					}else{
							for(int j=i+permanencia-1; j>=i;j--){
								if(j==estimacion.length-1){
									pago[j]=tarifaPlana;
								}else{
									pago[j]=tarifaPlana+pago[j+1];
								}
								tarifa[j]=1;
							}

					}
				}else{
					if(tarifa[i+permanencia+1]==1 || pago[i + 1] + tarifaMegas * estimacion[i] < tarifaPlana*permanencia+pago[i+permanencia]){
						pago[i] = pago[i + 1] + tarifaMegas * estimacion[i];
						tarifa[i]=0;
					}else{
						if(tarifa[i+permanencia]==0){
							for(int j=i+permanencia; j>=i;j--){
								if(j==estimacion.length-1){
									pago[j]=tarifaPlana;
								}else{
									pago[j]=tarifaPlana+pago[j+1];
								}
								tarifa[j]=1;
							}
						}else{
							pago[i] = pago[i + 1] + tarifaMegas * estimacion[i];
							tarifa[i]=0;
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
		sol=tarifa;
		return sol;
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