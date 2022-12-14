package Ventana_Informacion;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ventanaEmergenteAlerta {
	public static void alerta(String mensaje, String Titulo,int ancho,int altura) {
		Stage miStage = new Stage();
		miStage.setMinWidth(ancho);
		miStage.setMinHeight(altura);
		Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setHeaderText(null);
	    alert.setTitle(Titulo);
	    alert.setContentText(mensaje);
	    alert.showAndWait();
		
		miStage.showAndWait();
	}
	//Método sobre cargado para tener una ventana Standar,
	// Así no tenemos que pasar en el contructor una medida.
	public static void alerta(String mensaje, String Titulo) {
		Stage miStage = new Stage();
		miStage.initModality(Modality.APPLICATION_MODAL);
		
		miStage.setTitle(Titulo);
		miStage.setMinWidth(700);
		miStage.setMinHeight(300);
		Label miLabel = new Label();
		miLabel.setText(mensaje);
		
		Button miBoton = new Button();
		miBoton.setText("Cerrar");
		
		miBoton.setOnAction(e -> miStage.close());
		
		BorderPane miPane =  new BorderPane();
		Scene miScene = new Scene(miPane,800,600);
		
		miPane.setTop(miLabel);
		miPane.setCenter(miBoton);
		
		miStage.setScene(miScene);
		
		miStage.showAndWait();
	}
}
