module hotelReservationSystem{
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	exports controllers to javafx.fxml;
	opens controllers to javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
//	opens models to javafx.base;
}
