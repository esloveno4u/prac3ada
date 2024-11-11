public class RecorridoTablero {
	private int[][] tablero; 
	private int[][] solucion;
	private int fila; //Fila de la casilla de inicio
	private int col;  //Columna de la casilla de inicio
	private int n;   
	private int m;

	public RecorridoTablero(int[][] t, int fila, int col) {
		tablero = t;
		n = tablero.length;
		m = tablero[0].length;
		this.fila = fila;
		this.col = col;
		solucion = null;
	}

	public int resolverMemo() {
		// Creamos la tabla auxiliar
		solucion = new int[n][m]; // -1 representará que la celda está vacía.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				solucion[i][j] = -1;
			}
		}
		// Rellenamos la tabla desde la casilla indicada
		return resuelve(fila, col);
	}

	private int resuelve(int i, int j) {
		
		//***Completar Implementación***
		if(solucion[i][j]==-1){
			if(i==0){
				solucion[i][j]=tablero[i][j];
			}else if(j==0){
				solucion[i][j]=tablero[i][j]+Math.max(resuelve(i-1,j),
						resuelve(i-1,j+1));
			}else if(j==m-1){
				solucion[i][j]=tablero[i][j]+Math.max(resuelve(i-1,j),
						resuelve(i-1,j-1));
			}else{
				solucion[i][j]=tablero[i][j]+maximo3(resuelve(i-1,j),
						resuelve(i-1,j-1),
						resuelve(i-1,j+1));
			}
		}
		return solucion[i][j];
	}

	private int maximo3(int a, int b, int c) {
		int res = a;
		if (b > res) {
			res = b;
		}
		if (c > res) {
			res = c;
		}
		return res;
	}

	public Recorrido reconstruirSol() {
		if (solucion == null) {
			throw new RuntimeException("Se debe resolver el problema primero");
		}
		
		//***Completar Implementación***
		Recorrido recorrido=new Recorrido();
		recorrido.add(fila, col);
		int valor=solucion[fila][col]-tablero[fila][col];
		int i=fila-1;
		int j=col;
		while( i>=0) {
			if(j>0 && solucion[i][j-1]==valor) {
				valor-=tablero[i][j-1];
				recorrido.add(i, j-1);
				j--;
			}else if(j<m-1 && solucion[i][j+1]==valor){
				valor-=tablero[i][j+1];
				recorrido.add(i, j+1);
				j++;
			}else{
				valor-=tablero[i][j];
				recorrido.add(i, j);
			}
			i--;
		}
		return recorrido;
	}

	public void imprimeMatrizSolucion() {
		if (solucion == null) {
			throw new RuntimeException("Se debe resolver el problema primero");
		}
		for (int i = 0; i < solucion.length; i++) {
			for (int j = 0; j < solucion[i].length; j++) {
				System.out.print(solucion[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
	
}
