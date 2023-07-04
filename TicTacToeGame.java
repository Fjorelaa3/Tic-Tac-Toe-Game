package Project;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TicTacToeGame extends Application {

    private char currentPlayer = 'X';
    private TicTacToeButton[][] buttons = new TicTacToeButton[3][3];

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create buttons and add them to the grid pane
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                TicTacToeButton button = createButton();
                buttons[row][col] = button;
                gridPane.add(button, col, row);
            }
        }

        Scene scene = new Scene(gridPane, 320, 320);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TicTacToeButton createButton() {
        TicTacToeButton button = new TicTacToeButton();
        button.setPrefSize(100, 100);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        button.setOnAction(e -> {
            handleButtonClick(button);
        });
        return button;
    }

    private void handleButtonClick(TicTacToeButton button) {
        if (button.getText().isEmpty()) {
            button.setText(Character.toString(currentPlayer));
            button.setX(currentPlayer == 'X');
            if (checkForWin()) {
                showAlert("Player " + currentPlayer + " wins!");
                resetGame();
            } else if (isBoardFull()) {
                showAlert("It's a draw!");
                resetGame();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkForWin() {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (checkRowCol(buttons[row][0], buttons[row][1], buttons[row][2])) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (checkRowCol(buttons[0][col], buttons[1][col], buttons[2][col])) {
                return true;
            }
        }

        // Check diagonals
        if (checkRowCol(buttons[0][0], buttons[1][1], buttons[2][2])
                || checkRowCol(buttons[0][2], buttons[1][1], buttons[2][0])) {
            return true;
        }

        return false;
    }

    private boolean checkRowCol(TicTacToeButton b1, TicTacToeButton b2, TicTacToeButton b3) {
        return (!b1.getText().isEmpty() && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText()));
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        currentPlayer = 'X';
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setX(false);
            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
