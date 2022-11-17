package application;
	

import java.io.File;
import java.util.List;

import Ventana_Informacion.Informacion;
import control.Control;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.Modelo;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
			/*
			 * A) CREAMOS UN OBJETO DE ARCHIVO
			 * B) UNA LISTA DE OBJETOS DE ARCHIVOS
			 * C) INSTANCIAMOS EL OBEJETO QUE SERÁ EL ENCARGADO DE PODER DESARROLLAR TODO EL PROGRAMA.
			 * ESE OBJETO ES EL QUE CONTIENE TODO LA LOGICA DEL PROGRAMA.
			 */
			
			FileChooser fileChooser = new FileChooser();
			lista_Canciones_Directorio = fileChooser.showOpenMultipleDialog(primaryStage);
			canciones_escogidas= new Modelo(lista_Canciones_Directorio);
			dire = System.getProperty("user.dir") + "/";
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
			//IMAGEN DE LA APLICACIÓN		
			p = new Image("file:/Volumes/NO NAME/JSP/FX_AUDIO_EJERCICIO/src/img/Logo_limpio.png");
			pho = new ImageView(p);
			pho.setFitWidth(400);
			pho.setPreserveRatio(true);
			
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
			//ETIQUETA QUE MUESTRA EL NOMBRE DE CANCION
			nombre_cancion = new Label();
			nombre_cancion.getStyleClass().add("Label_Titulo");
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
			//LOS BOTONES DE LA APLICACION
			  //-- BOTÓN PARA COMENZAR LA CANCIÓN
			String mensaje = "file:/Volumes/NO NAME/JSP/FX_AUDIO_EJERCICIO/src/img/Play.png";
			Image img = new Image(mensaje);
			ImageView view = new ImageView(img);
			view.setFitHeight(25);
			view.setPreserveRatio(true);
			play = new Button();
			play.setPrefSize(70, 50);
			play.setGraphic(view);
			play.setOnAction(e -> {
				// uno.play();
				// uno.reproduccion_automatica(canciones_escogidas.getArray_Nombre_Musica());
				if (!reproduccion_auto.isSelected()) {
					uno.play();
				} else {

					// La implementacióm del objeto Control - uno - hace que funcionen los botones
					// con la / as canciones que no adjunten del método - automatico - del objeto
					// Modelo

					/*canciones_escogidas.automatico(reproduccion_auto, uno, Lista_Vi, listado_canciones,
							canciones_escogidas);
					uno.play();*/
					
					for(File v:canciones_escogidas.getArray_Nombre_Musica()) {
						
					}
					
				

				}

			});
			
			
			
			  //-- BOTÓN PARA PAUSAR LA CANCIÓN
			String mensaje2 = "file:/Volumes/NO NAME/JSP/FX_AUDIO_EJERCICIO/src/img/pausa.png";
			Image img2 = new Image(mensaje2);
			ImageView view2 = new ImageView(img2);
			view2.setFitHeight(25);
			view2.setPreserveRatio(true);
			pause= new Button();
			pause.setPrefSize(70, 50);
			pause.setGraphic(view2);
			pause.setOnAction(e -> {
				uno.pause();
				
			});
			
			  //-- BOTÓN PARA PARAR LA CANCIÓN
			String mensaje3 = "file:/Volumes/NO NAME/JSP/FX_AUDIO_EJERCICIO/src/img/stop.png";
			Image img3 = new Image(mensaje3);
			ImageView view3 = new ImageView(img3);
			view3.setFitHeight(25);
			view3.setPreserveRatio(true);
			stop= new Button();
			stop.setPrefSize(70, 50);
			stop.setGraphic(view3);
			stop.setOnAction(e ->{
				uno.stop();
				//uno.cancelTimer();
			});
			
			  //-- BOTÓN PARA AVANZAR LA CANCIÓN
			String mensaje4 = "file:/Volumes/NO NAME/JSP/Reproductor/src/img/adelante.png";
			Image img4 = new Image(mensaje4);
			ImageView view4 = new ImageView(img4);
			view4.setFitHeight(25);
			view4.setPreserveRatio(true);
			siguiente= new Button();
			siguiente.setPrefSize(70, 50);
			siguiente.setGraphic(view4);
			siguiente.setOnAction(e ->{
				metodo_adelantar();
				
				
			});
			
			  //-- BOTÓN PARA RETROCEDER LA CANCIÓN
			String mensaje5 = "file:/Volumes/NO NAME/JSP/Reproductor_etapas/src/img/atrasar.png";
			Image img5 = new Image(mensaje5);
			ImageView view5 = new ImageView(img5);
			view5.setFitHeight(25);
			view5.setPreserveRatio(true);
			atras= new Button();
			atras.setPrefSize(70, 50);
			atras.setGraphic(view5);
			atras.setOnAction(e ->{
				uno.stop();
				uno= new Control(canciones_escogidas.escoger_atras());
				Lista_Vi.selectionModelProperty().get().select(canciones_escogidas.getId());
				uno.play();
				listado_canciones.setText(canciones_escogidas.escoge_Nombre_atras().getName());
			
				
				
			});
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
// COMBOBOX PARA AUTOMATIZAR LA REPRODUCCIÓN DE LA PLAY LIST
			 reproduccion_auto= new CheckBox("PLAY THE PLAYLIST AUTOMATICALLY");
				

			
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
			// LIST_VIEW Y OBSERVABLELIST
			//LISTADO DE CANCIONES PARA ESCOGER
			listado_canciones = new Label("CANCIONES");
			listado_canciones.getStyleClass().add("Label");

			Lista_Vi = new ListView<File>();
			Lista_Vi.getStyleClass().add("Lista");
			/*
			 * En el siguiente bucle implementamos a la List View todas las canciones
			 * del ArrayList inicializado en la clase Modelo.
			 * 
			 * Nota: En la variables almacenamos los nombres de las canciones - sin la dirección absoluta -.
			 * Estás serán las que verán los usuarios en sus dispositivos. Posteriormente tendremos que concatenar la ruta completa.
			 */
			for (File t : canciones_escogidas.getArray_Nombre_Musica()) {
				String prueba = t.getName();
				prueba2 = new File(prueba);
				Lista_Vi.getItems().add(prueba2);
			}
			
			// Se permite seleccionar sola una canción a la vez con la siguiente instrucción
			Lista_Vi.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			
			// Tamaño de la lista en la app
			Lista_Vi.setPrefHeight(100);
			Lista_Vi.setPrefWidth(20);
			
			Lista_Vi.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
				@Override
				public void changed(ObservableValue observable, Object oldValue, Object newValue) {
					
					nombre_cancion_escogida = "" ;
					nombre_borrar_cancion ="";
					
					list_Obser = Lista_Vi.getSelectionModel().getSelectedItems();
					
					for (File i : list_Obser) {
						try {
							   // --- Se recorre la ObservableList donde está añadidas todas las canciones del ListView.
							   //     Al estar en el método de escucha almacena lo seleccionado por el usuario.
							nombre_cancion_escogida += i ;
							
							nombre_borrar_cancion += i;
							listado_canciones.setText(i.getName().toString());
							
							   // --- Concatenamos la ruta completa para inicializar el objeto Control que es donde está los métodos que hacen reproduccir la música.
							apunte = 	new File(dire + "/src/audio/" + nombre_cancion_escogida);
							 //System.out.print(i.getAbsoluteFile());
							uno = new Control(apunte);
							  // ---  Esta línea de código es fundamental para conocer la posición de la canción. Usada en los métodos de la clase Modelos - que es a la que per
							  //      tenece el objeto canciones_escogidas.
							
							canciones_escogidas.posicion(apunte);
							
						} catch (NullPointerException e) {
							// TODO Auto-generated catch block
							System.out.println("Error en la clase principal - main - en la interación del list_Obser");
						}
					}
					
					
					
					
				}
				
				
			});
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
			// SLIDER PARA CONTROLAR EL VOLUMEN
			//CONTROL PARA EL NIVEL DE VOLUMEN
			Label Volumen = new Label("VOLUMEN");
			Volumen.getStyleClass().add("Label");
			Slider vol = new Slider(0, 50, 150);
			
			vol.getStyleClass().add("Slider");
			vol.setValue(100);
			vol.valueProperty().addListener(new ChangeListener<>() {

				@Override
				public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
					// TODO Auto-generated method stub
					uno.getMiPlayer().setVolume(vol.getValue() * 0.01);
					
				}
			});
			
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
			
			
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
// SLIDER PARA PROGRESO DE LA CANCIÓN
			songpro = new ProgressBar();
			
			
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************			
			//CÓDIGO PARA EL MENÚ
			menuBar = new MenuBar();
			menuBar.getStyleClass().add("Menu");
			//- Base principal del menú
			Menu_Abrir_Archivo = new Menu("File");
			Menu_informacion= new Menu("Help");
			// .......................................................................................................................................................................
			// -- Submenú de Menu_Abrir_Archivo
			// --- Añadir canciones a la play list
			menuItem_añadir_cancion = new MenuItem("Add song to playlist");
			menuItem_añadir_cancion.setOnAction(e -> {
				/* -- ACUDO AL MÉTODO DE LA CLASE MODELO QUE REALIZA LAS SIGUIENTES ACCIONES:
				 *    A) ABRE EL DIRECTORIO LOCAL BUSCANDO NUEVAS CANCIONES.
				 *    B) MEDIANTE UN FOR EACH SE AÑADEN LAS CANCIONES CRONOLÓGICAMENTE.
				 *    C) SE ELIMINAN LAS DUPLICIDADES.
				 *    D) CON UN SEGUNDO BUCLE MOSTRAMOS LA LISTA FINAL DE LAS CANCIONES
				 */
				canciones_escogidas.añadir_cancion(primaryStage);
				Lista_Vi.getItems().clear();
			
			
				for (File t : canciones_escogidas.getArray_Nombre_Musica()) {
					String prueba = t.getName();
					prueba2 = new File(prueba);
					Lista_Vi.getItems().add(prueba2);
					Lista_Vi.getItems().indexOf(vol);
		
				}
			});
			
			// --- Quitar canciones de la play list
			menuItem_quitar_cancion = new MenuItem("Remove song");
			menuItem_quitar_cancion.setOnAction(e -> {
				//System.out.print(nombre_cancion_escogida);
				
				cancion_quitar = canciones_escogidas.getPosicion();
				File prueba2 = new File(nombre_borrar_cancion);
				Lista_Vi.getItems().remove(prueba2);
				canciones_escogidas.borrar_cancion(cancion_quitar);
				
				
			});
			// --- cerrar el programa
			menuItemNuevo = new MenuItem("Quit Player");
			menuItemNuevo.setOnAction(e -> {
				primaryStage.close();
			});
			
			
			// -- Submenú de Menu_informacion
			menuItemInfo = new MenuItem("Information");
			menuItemInfo.setOnAction(e -> {
				Informacion nueva = new Informacion();
				nueva.mostrar("    Author: Daniel GM" + "\n" +"    Version: Player 1.0"+ "\n"
						+  "    Company: WillsCompany", "Player Information", 550, 800);
			});
			// -- Se añade los submenús a sus raices
			Menu_Abrir_Archivo.getItems().addAll(menuItem_añadir_cancion,menuItem_quitar_cancion,menuItemNuevo);
			Menu_informacion.getItems().addAll(menuItemInfo);
			// -- Se implementa los submenús al menú
			menuBar.getMenus().addAll(Menu_Abrir_Archivo,Menu_informacion);
// ************************************************************************************************************************************************************************
// ************************************************************************************************************************************************************************
			//CONTENEDORES PARA COLOCAR LOS COMPONENTES
			HBox HORIZONTAL_MENU = new HBox(menuBar);
		    HORIZONTAL_MENU.setAlignment(Pos.TOP_LEFT);
		    //CONTENEDOR DEL LOGO DE LA APLICACIÓN
			HBox HORIZONTAL_LOGO = new HBox(20, pho);
			HORIZONTAL_LOGO.setAlignment(Pos.CENTER);
			//CONTENEDORES PARA COLOCAR CONTROL DE VOLUMEN
			HBox HORIZONTAL_VOLUMEN = new HBox(20,Volumen,vol);
			HORIZONTAL_VOLUMEN.setAlignment(Pos.CENTER);
			//CONTENEDORES PARA PROGRESSBAR
			/*HBox HORIZONTAL_PROGRESSBAR= new HBox(20,songpro);
			 HORIZONTAL_PROGRESSBAR.setAlignment(Pos.CENTER);*/
			
			//CONTENEDORES PARA COLOCAR LOS BOTONES
			HBox HORIZONTAL_BOTONES = new HBox(20,atras,play,pause,siguiente,stop);
			HORIZONTAL_BOTONES.setAlignment(Pos.CENTER);
			//CONTENEDORES ETIQUETA DE CANCIONES
			HBox HORIZONTAL_TITULO_CANCION = new HBox(20,listado_canciones);
			HORIZONTAL_TITULO_CANCION.setAlignment(Pos.CENTER);
			//CONTENEDORES LISTAR LAS CANCIONES QUE ESTÁN EN EL DIRECTORIO
			VBox VERTICAL_LISTADO_CANCIONES = new VBox(20,Lista_Vi);
			VERTICAL_LISTADO_CANCIONES.setAlignment(Pos.CENTER);
			//CONTENEDORES PARA COLOCAR EL CHECKBOX
			VBox REPRODUCCION_AUTOMATICA = new VBox(20,Lista_Vi);
			 REPRODUCCION_AUTOMATICA.setAlignment(Pos.CENTER);
			 REPRODUCCION_AUTOMATICA.getChildren().add(reproduccion_auto);  
			
			//CONTIENE TODOS LOS COMPONENTES HORIZONTALES
			VBox contenedor_vertical = new VBox(20,HORIZONTAL_MENU,HORIZONTAL_LOGO,HORIZONTAL_VOLUMEN,HORIZONTAL_BOTONES,HORIZONTAL_TITULO_CANCION, VERTICAL_LISTADO_CANCIONES, REPRODUCCION_AUTOMATICA);
			contenedor_vertical.setAlignment(Pos.CENTER);
			
			scene = new Scene(contenedor_vertical, 550, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("REPRODUCTOR AUDIO");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

	public void metodo_adelantar() {
		uno.stop();
		uno= new Control(canciones_escogidas.escoger());
		Lista_Vi.selectionModelProperty().get().select(canciones_escogidas.getId());
		uno.play();
		listado_canciones.setText(canciones_escogidas.escoge_Nombre().getName());
	}

	private Scene scene;
	private ImageView pho;
	private Image p;
	private Label nombre_cancion,volumen,listado_canciones;
	private ListView<File> Lista_Vi;
	private ListView<String> Lista_Ve;
	private ObservableList<File> list_Obser;
	private int list_situacion,contador;
	private String nombre_cancion_escogida,pasamos_nombre_cancion,nombre_borrar_cancion,nombre_cancion_escogida_quitar,Carpeta,test,dire;
	//private Obtener_canciones canciones;
	private Button play,pause,stop,siguiente,atras;
	private int posicion,posicion_array,cancion_quitar;
	//private final String Ruta = "/Volumes/NO NAME/JSP/FX_AUDIO_EJERCICIO/src/audio/";
	private File apunte,cancion_escogida_List_View,prueba2,prueba3,carperta;
	private Modelo canciones_escogidas;
	private Control uno;
	private boolean control = false;
	private MenuBar menuBar;
	private Menu Menu_Abrir_Archivo, Menu_Cerrar,Menu_informacion;
	private MenuItem menuItemNuevo,menuItemInfo,menuItem_añadir_cancion,menuItem_quitar_cancion;
	private List<File> lista_Canciones_Directorio;
	private CheckBox reproduccion_auto;  
	private ProgressBar songpro;
	
}
