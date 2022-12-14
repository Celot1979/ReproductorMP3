package control;

import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import modelo.Temporizador;

public class Control implements Runnable {

	public Control(List<File> listSong, ListView<File> listView, Label listado_canciones) {
		thread = new Thread(this);
		this.i = 0;
		this.stop = false;
		this.listSong = listSong;
		this.listado_canciones = listado_canciones;
		this.listView = listView;
		
		thread.start();
		
	}

	public Control(File file) {
		iniciar(file);
	}

	public void iniciar(File a) {

		try {

			/*
			 * GENERAMOS UN OBJETO DE TIPO MEDIA CON EL ARCHIVO QUE PASA POR PARAMETRO
			 * CREAMOS EL OBJETO MEDIAPLAYER QUE NECESARIAMENTE TIENE QUE TENER POR
			 * PARAMETRO EL OBJETO ANTERIOR
			 */
			this.a = a;
			miMedia = new Media(this.a.toURI().toString());
			miPlayer = new MediaPlayer(miMedia);

		} catch (NullPointerException e) {
			System.out.println("Fallo al intentar reproduccir desde la clase Control la canción escogida");
		} catch (Exception e) {
			System.out.println("Fallo al intentar reproduccir desde la clase Control la canción escogida" + "\n"
					+ "El error es una excepción grave");
		}
	}

// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
// METODOS DE LA CLASE CONTROL

	public void play() {
		miPlayer.play();
	}

	public void play_auto() {
		miPlayer.setAutoPlay(true);
	}

	public void pause() {
		miPlayer.pause();
	}

	public void stop() {
		miPlayer.stop();
		stop = true;
	}

	@Override
	public void run() {
		
		
		while (i < listSong.size() && !stop) {
			File f = listSong.get(i);
			System.out.println("Reproducir canción " + f.getName());
			this.listView.selectionModelProperty().get().select(i);
		
			
			//listado_canciones.setText(f.getName());
			//listado_canciones.textProperty().bind(f.getName());
			this.iniciar(f);
			this.play();
			

			boolean endSong = false;
			while (!endSong && !stop) {
				current = miPlayer.getCurrentTime().toSeconds();
				end = miMedia.getDuration().toSeconds();
				System.out.println(current);
				System.out.println(end);

				if (current >= end) {
					System.out.print(current / end);

					System.out.println("FIN CANCION "+i);
					i++;
					//this.listView.selectionModelProperty().get().select(i);
					//listado_canciones.setText(f.getName());
					endSong = true;
				}
				try {
					Thread.sleep(1000 * 1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public int resultado(int r) {
		return r;
	}

	public void cancelTimer() {
		tiempo.cancel();
	}

// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
// METODO DE REPRODUCCION DE LAS LISTAS

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

	private Thread thread;
	private ListView<File> listView;
	private List<File> listSong;
	private int i;
	private File a;
	private Media miMedia;
	private MediaPlayer miPlayer, mipista;
	private double current, end;
	private Temporizador Medir;
	private int frame, res;
	private TimerTask tarea;
	private Timer tiempo;
	private boolean stop;
	private  Label listado_canciones;

}
