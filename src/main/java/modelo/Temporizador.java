package modelo;

public class Temporizador {
	public Temporizador(boolean ini_temp) {
		this.temp= ini_temp;
		
	}
	
	
	public boolean isTemp() {
		//System.out.println(temp);
		if (temp == false) {
			//System.out.print("Hola desde Temporizador");
			id += 1;
			System.out.print(getId());
			System.out.println(Thread.currentThread().getName() + " Temporizador");
			
			
		}
	
		return temp;
	}


	public void setTemp(boolean temp) {
		this.temp = temp;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
	/**
	 * @return the fallo
	 */
	public int getFallo() {
		return fallo;
	}

	/**
	 * @param fallo the fallo to set
	 */
	public void setFallo(int fallo) {
		this.fallo = fallo;
	}



	private boolean temp;
	private int id,fallo;
}
