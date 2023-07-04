package Project;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TicTacToeButton extends Button {

 private boolean isX;

 public TicTacToeButton() {
     setFont(Font.font("Arial", FontWeight.BOLD, 32));
     setMinSize(100, 100);
     setMaxSize(100, 100);
 }

 public boolean isX() {
     return isX;
 }

 public void setX(boolean isX) {
     this.isX = isX;
     updateStyle();
 }

 private void updateStyle() {
     if (isX) {
         setStyle("-fx-text-fill: blue; -fx-background-color: lightpink;");
     } else {
         setStyle("-fx-text-fill: green; -fx-background-color: lightpink;");
     }
 }
}
