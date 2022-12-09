package modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Ventana_Informacion.Informacion;
import control.Control;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Modelo {
	public Modelo(List<File> lista_Canciones_Directorio) {
		try {

			for (File a : lista_Canciones_Directorio) {
				// System.out.println(f);
				miAudio = new File(a.getAbsolutePath());
				// System.out.println(miAudio.getName());
				miMedia = new Media(miAudio.toURI().toString());
				// array_Ruta.add(miAudio);
				array_Nombre_Musica.add(miAudio);
			    System.out.println(array_Nombre_Musica.size());
			    

			}

		} catch (java.lang.NullPointerException  e) {
			//System.out.println("Fallo a la hora de descargar las canciones");
			 System.exit(0);
		}
	}

	

	//DEVUELVE LA POSICION DE LA CANCION ESCOGIDA POR EL USUARIO
	public int posicion(File a) {
		posicion = array_Nombre_Musica.indexOf(a);
		Almaceno_cancion_escogida = array_Nombre_Musica.get(posicion);
		// System.out.println(posicion);
		return posicion;
	}
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
	/*
	 * MÉTODOS PARA AVANZAR LA CANCIÓN. 1º MÉTODO--> ALMACENA LA POSICIÓN DE LA
	 * CANCIÓN.A CONTINUACIÓN LE SUMA UNA POSICIÓN. EL ARRAYLIST NOS PRESENTA LA
	 * POSICIÓN DE LA SEGUIENTE CANCIÓN.
	 * 
	 * 2º MÉTODO --> NOS MUESTRA EL NOMBRE DE LA CANCIÓN A SONAR.
	 */

	public File proxima_cancion() {
		try {
			if (posicion < 0) {
				posicion = posicion + 1;
			}
			Almaceno_cancion_escogida = array_Nombre_Musica.get(posicion + 1);
			// System.out.println(Almaceno_cancion_escogida);
			posicion = posicion + 1;
			// System.out.println(posicion);
			setId(posicion);
		} catch (java.lang.IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			/*
			 * Capturamos el error de no tener más canciones al final de la Playlist
			 */
			posicion= 0;
			Almaceno_cancion_escogida = array_Nombre_Musica.get(posicion);
			setId(posicion);
		}
		
		return Almaceno_cancion_escogida;
	}

	public File nom_prox_cancion() {
		posicion_nombre = posicion;
		Almaceno_nombre_cancion_escogida = array_Nombre_Musica.get(posicion_nombre);
		posicion_nombre = posicion_nombre + 1;
		return Almaceno_nombre_cancion_escogida;
	}

// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
	/*
	 * MÉTODOS PARA ATRASAR LA CANCIÓN.
	 */
	public File cancion_atras() {

		try {
			Almaceno_cancion_escogida = array_Nombre_Musica.get(posicion - 1);
			posicion = posicion - 1;
			setId(posicion);
		} catch (java.lang.IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			posicion= array_Nombre_Musica.size()-1;
			//System.out.println(ultimo_id);
			Almaceno_cancion_escogida = array_Nombre_Musica.get(posicion);
			setId(posicion);
		}
		return Almaceno_cancion_escogida;
	}

	public File nom_atras_cancion() {
		posicion_nombre = posicion;
		Almaceno_nombre_cancion_escogida = array_Nombre_Musica.get(posicion_nombre);
		posicion_nombre = posicion_nombre - 1;
		// System.out.println(posicion_nombre);
		return Almaceno_nombre_cancion_escogida;
	}

// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
// MÉTODO PARA AÑADIR UNA O VARIAS CANCIONES A LA PLAYLIST
	public void añadir_cancion(Stage primaryStage) {
		/*
		 * -- ESTE MÉTODO ES EL ENCARGADO DE IR AÑADIENDO CANCIONES DESDE EL MENÚ DE LA
		 * APLICACIÓN: A) Emerge una ventana donde localizaremos la canción / canciones
		 * que deseamos añadir a la PlayList. B) Posteriormente pasa a evaluar si ya
		 * había alguna canción escogida en el ArrayList - Array_Añadir_musica() -. En
		 * el supuesto que no exista ninguna,permite añadir la 1ª.
		 * 
		 * C) En el siguiente condicional vuelve a evaluar si hay o no canciones. Si las
		 * hay, añade la última canción / canciones al array - en orden crónologico-.
		 */
		FileChooser fileChooser = new FileChooser();
		lista_Canciones_Directorio = fileChooser.showOpenMultipleDialog(primaryStage);
		if (getArray_Añadir_musica().size() == 0) {
			for (File x : getArray_Nombre_Musica()) {
				array_Añadir_musica.add(x);
			}
		}

		if (getArray_Añadir_musica().size() != 0) {
			for (File t : lista_Canciones_Directorio) {
				array_Añadir_musica.add(t);
			}
		}
        
		setArray_Nombre_Musica(array_Añadir_musica);
	}

// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
// MÉTODO PARA BORRAR CANCIONES A LA PLAYLIST
	public void borrar_cancion(int x) {
		getArray_Nombre_Musica().remove(x);
	}
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
// MÉTODO PARA REPRODUCCIR AUTOMÁTICAMENTE LA PLAYLIST
	
	

	
	public void automatico(CheckBox reproduccion_auto,Control uno, ListView<File> Lista_Vi, Label Listado_canciones,
			Modelo canciones_escogidas) {
		int numero = array_Nombre_Musica.size();
		if (reproduccion_auto.isSelected()) {
			Almaceno_cancion_escogida = array_Nombre_Musica.get(id);
			Listado_canciones.setText(Almaceno_cancion_escogida.getName());
			Lista_Vi.selectionModelProperty().get().select(canciones_escogidas.getId());
			
		}
		
	}
	
	public void aut() {
		System.out.println(Medir.getId());
		System.out.println(Thread.currentThread().getName() + " modelo");
	}
	
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
// GETTERS Y SETTERS

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File getMiAudio() {
		return miAudio;
	}

	public void setMiAudio(File miAudio) {
		this.miAudio = miAudio;
	}

	public Media getMiMedia() {
		return miMedia;
	}

	public void setMiMedia(Media miMedia) {
		this.miMedia = miMedia;
	}

	public File[] getMis_archivos() {
		return mis_archivos;
	}

	public void setMis_archivos(File[] mis_archivos) {
		this.mis_archivos = mis_archivos;
	}

	public ArrayList<File> getArray_Nombre_Musica() {
		return array_Nombre_Musica;
	}

	public void setArray_Nombre_Musica(ArrayList<File> array_Nombre_Musica) {
		this.array_Nombre_Musica = array_Nombre_Musica;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public String getRuta_completa() {
		return ruta_completa;
	}

	public void setRuta_completa(String ruta_completa) {
		this.ruta_completa = ruta_completa;
	}

	public String getCadena_texto() {
		return cadena_texto;
	}

	public void setCadena_texto(String cadena_texto) {
		this.cadena_texto = cadena_texto;
	}

	

	public String getRuta() {
		return Ruta;
	}

	public File getAlmaceno_cancion_escogida() {
		return Almaceno_cancion_escogida;

	}

	public void setAlmaceno_cancion_escogida(File almaceno_cancion_escogida) {
		Almaceno_cancion_escogida = almaceno_cancion_escogida;
	}

	public ArrayList<File> getArray_Añadir_musica() {
		return array_Añadir_musica;
	}

	public void setArray_Añadir_musica(ArrayList<File> array_Añadir_musica) {
		this.array_Añadir_musica = array_Añadir_musica;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	private Media miMedia;
	private File[] mis_archivos;
	private ArrayList<File> array_Nombre_Musica = new ArrayList();
	private ArrayList<File> array_Añadir_musica = new ArrayList();
	private final String Ruta = "/Volumes/NO NAME/JSP/FX_AUDIO_EJERCICIO/src/audio/";
	private int posicion, pul, posicion_nombre,id,frame;
	private String ruta_completa, cadena_texto, r , hola;
	private Status status;
	private File file, miAudio, Almaceno_cancion_escogida, Almaceno_nombre_cancion_escogida, archivo_añadir_cancion,
			cancion_borrar;
	private List<File> lista_Canciones_Directorio;
	private Temporizador Medir;
	private TimerTask tarea;
	private Timer tiempo;
	private double current,end;
	private boolean andando;
	private Control uno;
	private Stage primaryStage;

}
