package control;

import java.io.File;
import javax.swing.JOptionPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import modelo.Modelo;

public class Control {
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
	
	public void pause() {
		miPlayer.pause();
	}
	
	public void stop() {
		miPlayer.stop();
	}
	
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
	
	private File a,Almaceno_cancion_escogida;
	private Media miMedia;
	private MediaPlayer miPlayer,mipista;
	private boolean control;
	private File posicion;
	private Modelo canciones_escogidas;
	private int posicion_num;
	
	

}
