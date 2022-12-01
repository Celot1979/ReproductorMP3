package control;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import modelo.Modelo;
import modelo.Temporizador;


public class Control implements Runnable{
	public Control(File a) {
		try {
			/*
			* GENERAMOS UN OBJETO DE TIPO MEDIA CON EL ARCHIVO QUE PASA POR PARÁMETRO
			* CREAMOS EL OBJETO MEDIAPLAYER QUE NECESARIAMENTE TIENE QUE TENER POR PARÁMETRO EL OBJETO ANTERIOR
			*/
			//System.out.println(a.toString());
			this.a=a;
			miMedia = new Media(this.a.toURI().toString());
			miPlayer = new MediaPlayer(miMedia);
			
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println("Fallo al intentar reproduccir desde la clase Control la canción escogida");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Fallo al intentar reproduccir desde la clase Control la canción escogida" + "\n" + "El error es una excepción grave");
		}
	}
	
	
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
// MÉTODOS DE LA CLASE CONTROL
	
	public void play() {
		
		miPlayer.play();
		
	}
	public void play_auto(){
		miPlayer.setAutoPlay(true);
	}
	public void pause() {
		miPlayer.pause();
	}
	
	public void stop() {
		miPlayer.stop();
		
	}
	public void estado() {
		Double  prueba = miPlayer.getCurrentTime().toSeconds();
		System.out.println(prueba);
	}
	//CON ESTOS DOS MÉTODOS PODEMOS SABER LA DURACIÓN DE LA CANCIÓN, PERO EL PROBLEMA ES QUE QUEDA ENCAPSULADO EN UN THREAD Y NO SE PUEDE HACER NADA FUERA DE AHÍ.
//	public  void beginTimer() {
//		
//		Medir = new Temporizador(true);
//		tiempo = new Timer();
//		
//		
////		tarea = new TimerTask() {
////			
////		
//////			public synchronized void run() {
//////				andando = true;
//////				
//////				current = miPlayer.getCurrentTime().toSeconds();
//////				end = miMedia.getDuration().toSeconds();
//////				
//////				if (current / end == 1.0) {
//////					System.out.println(current/end);
//////					Medir.setTemp(false);
//////					Medir.isTemp();
//////					Medir.setId(1);
//////					frame = Medir.getId();
//////					System.out.println(Thread.currentThread().getName() + " Control");
//////					
//////					cancelTimer();
//////					
//////					
//////				}
//////			}
////		
////		};
//	
//		//tiempo.scheduleAtFixedRate(tarea, 1000, 1000);
//		
//	}
	public int resultado(int r) {
		
		return r;
		
	}
	public void cancelTimer() {
		andando = false;
		tiempo.cancel();
		
	}

	
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
// MÉTODO DE REPRODUCCIÓN DE LAS LISTAS
	
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
// GETTERS Y SETTERS CLASE CONTROL

	public Media getMiMedia() {
		return miMedia;
	}

	public void setMiMedia(Media miMedia) {
		this.miMedia = miMedia;
	}

	public MediaPlayer getMiPlayer() {
		return miPlayer;
	}

	public void setMiPlayer(MediaPlayer miPlayer) {
		this.miPlayer = miPlayer;
	}

	public MediaPlayer getMipista() {
		return mipista;
	}

	public void setMipista(MediaPlayer mipista) {
		this.mipista = mipista;
	}
	
	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}
	
	

	/**
	 * @return the res
	 */
	public int getRes() {
		return res;
	}


	/**
	 * @param res the res to set
	 */
	public void setRes(int res) {
		this.res = res;
	}



	private File a,Almaceno_cancion_escogida;
	private Media miMedia;
	private MediaPlayer miPlayer,mipista;
	private boolean control,andando,opcion;
	private double current,end;
	private File posicion;
	private Modelo canciones_escogidas;
	private Temporizador Medir;
	private int posicion_num, velocidad, frame,res;
	private TimerTask tarea;
	private Timer tiempo;
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		andando = true;

		current = miPlayer.getCurrentTime().toSeconds();
		end = miMedia.getDuration().toSeconds();
		if (current / end == 1.0) {
			System.out.println(current / end);
			Medir.setTemp(false);
			Medir.isTemp();
			Medir.setId(1);
			frame = Medir.getId();
			System.out.println(Thread.currentThread().getName() + " Control");

			cancelTimer();
			
		}
		//tiempo.scheduleAtFixedRate(tarea, 1000, 1000);
	}
	
	

}
