module Reproductor_etapas {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.media;
	requires java.desktop;
	requires java.xml;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
}
