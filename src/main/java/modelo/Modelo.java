package modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import control.Control;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
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

			}

		} catch (java.lang.NullPointerException  e) {
			//System.out.println("Fallo a la hora de descargar las canciones");
			 System.exit(0);
		}
	}

	// DEVUELVE LA POSICION DE LA CANCION ESCOGIDA POR EL USUARIO
	public int posicion(File a) {
		posicion = array_Nombre_Musica.indexOf(a);
		almaceno_cancion_escogida = array_Nombre_Musica.get(posicion);
		// System.out.println(posicion);
		return posicion;
	}
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
	/*
	 * METODOS PARA AVANZAR LA CANCION. 1º METODO--> ALMACENA LA POSICION DE LA
	 * CANCION.A CONTINUACION LE SUMA UNA POSICION. EL ARRAYLIST NOS PRESENTA LA
	 * POSICION DE LA SEGUIENTE CANCION.
	 * 
	 * 2Âº METODO --> NOS MUESTRA EL NOMBRE DE LA CANCION A SONAR.
	 */

	public File nextSong() {

		if(posicion < array_Nombre_Musica.size() - 1) {
			posicion++;
		}else {
			posicion = 0;
		}
		almaceno_cancion_escogida = array_Nombre_Musica.get(posicion);
		
		setId(posicion);
		return almaceno_cancion_escogida;
	}

	public File escoge_Nombre() {
		posicion_nombre = posicion;
		almaceno_nombre_cancion_escogida = array_Nombre_Musica.get(posicion_nombre);
		posicion_nombre = posicion_nombre + 1;
		return almaceno_nombre_cancion_escogida;
	}

// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
	/*
	 * METODOS PARA ATRASAR LA CANCION.
	 */
	public File previusSong() {
		if (posicion > 0) {
			posicion--;
		}else {
			posicion = array_Nombre_Musica.size() - 1;
		}
		almaceno_cancion_escogida = array_Nombre_Musica.get(posicion);
		setId(posicion);
		return almaceno_cancion_escogida;
	}

	public File escoge_Nombre_atras() {
		posicion_nombre = posicion;
		almaceno_nombre_cancion_escogida = array_Nombre_Musica.get(posicion_nombre);
		posicion_nombre = posicion_nombre - 1;
		// System.out.println(posicion_nombre);
		return almaceno_nombre_cancion_escogida;
	}

// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
// METODO PARA AÑADIR UNA O VARIAS CANCIONES A LA PLAYLIST
	public void anadir_cancion(Stage primaryStage) {
		/*
		 * -- ESTE METODO ES EL ENCARGADO DE IR AÑADIENDO CANCIONES DESDE EL MENÃš DE
		 * LA APLICACION: A) Emerge una ventana donde localizaremos la canción /
		 * canciones que deseamos anadir a la PlayList. B) Posteriormente pasa a evaluar
		 * si ya habÃ­a alguna canción escogida en el ArrayList - Array_anadir_musica()
		 * -. En el supuesto que no exista ninguna, permite anadir la 1ª.
		 * 
		 * C) En el siguiente condicional vuelve a evaluar si hay o no canciones. Si las
		 * hay, ande la última canción / canciones al array - en orden crónologico-.
		 */
		FileChooser fileChooser = new FileChooser();
		lista_Canciones_Directorio = fileChooser.showOpenMultipleDialog(primaryStage);
		if (getArray_anadir_musica().size() == 0) {
			for (File x : getArray_Nombre_Musica()) {
				array_anadir_musica.add(x);
			}
		}

		if (getArray_anadir_musica().size() != 0) {
			for (File t : lista_Canciones_Directorio) {
				array_anadir_musica.add(t);
			}
		}

		setArray_Nombre_Musica(array_anadir_musica);

	}

// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
// METODO PARA BORRAR CANCIONES A LA PLAYLIST
	public void borrar_cancion(int x) {

		getArray_Nombre_Musica().remove(x);

	}
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
// METODO PARA REPRODUCCIR AUTOMÁTICAMENTE LA PLAYLIST

	public void automatico(CheckBox reproduccion_auto, Control uno, ListView<File> Lista_Vi, Label Listado_canciones,
			Modelo canciones_escogidas) {
		int numero = array_Nombre_Musica.size();
		if (reproduccion_auto.isSelected()) {
			almaceno_cancion_escogida = array_Nombre_Musica.get(id);
			Listado_canciones.setText(almaceno_cancion_escogida.getName());
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

	
	public File getAlmaceno_cancion_escogida() {
		return almaceno_cancion_escogida;

	}

	public void setAlmaceno_cancion_escogida(File almaceno_cancion_escogida) {
		this.almaceno_cancion_escogida = almaceno_cancion_escogida;
	}

	public ArrayList<File> getArray_anadir_musica() {
		return array_anadir_musica;
	}

	public void setArray_anadir_musica(ArrayList<File> array_anadir_musica) {
		this.array_anadir_musica = array_anadir_musica;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private Media miMedia;
	private File[] mis_archivos;
	private ArrayList<File> array_Nombre_Musica = new ArrayList<>();
	private ArrayList<File> array_anadir_musica = new ArrayList<>();
	private int posicion = -1, posicion_nombre, id, frame;
	private String ruta_completa, cadena_texto;
	private File file, miAudio, almaceno_cancion_escogida, almaceno_nombre_cancion_escogida;
	private List<File> lista_Canciones_Directorio;
	private Temporizador Medir;
	private TimerTask tarea;
	private Timer tiempo;
	

}
