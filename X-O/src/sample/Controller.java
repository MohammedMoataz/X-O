package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private AnchorPane pane;
    private String text;

    private int[][] x_o;
    private boolean change;
    private int counter;

    @FXML
    private void initialize() {
        this.text = null;
        this.x_o = new int[3][3];
        this.change = false;
        this.counter = 0;

        this.pane.setOnMouseClicked(event -> {
            int x = setCoordinate((int) event.getX());
            int y = setCoordinate((int) event.getY());

            if (x_o[x / 200][y / 200] == 0) {
                this.counter++;
                this.mkLabel(x, y);
                this.winner(x, y);
            }
        });
    }

    private int setCoordinate(int co) {
        if (co < 200)
            co = 50;
        else if (co < 400 && co > 200)
            co = 225;
        else
            co = 400;

        return co;
    }

    private void mkLabel(int x, int y) {
        Label label = new Label();
        label.setPrefWidth(149.0);
        label.setPrefHeight(149.0);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(new Font("Arial", 100));

        if (this.change) {
            label.setText("O");
            label.setTextFill(Paint.valueOf("#df0d13"));
        } else {
            label.setText("X");
            label.setTextFill(Paint.valueOf("#130ddf"));
        }

        change = !change;
        this.pane.getChildren().add(label);
        this.text = label.getText();
    }

    private void winner(int x, int y) {
        x_o[x / 200][y / 200] = (change) ? 8 : 9;
        int winner = -1;
        int[] start = new int[2],
                end = new int[2];

        if (this.counter > 4) {
            if (x_o[0][0] == (x_o[1][1]) && x_o[0][0] == (x_o[2][2])) {
                winner = x_o[1][1];
                start = new int[]{0, 0};
                end = new int[]{2, 2};

            } else if (x_o[0][2] == (x_o[1][1]) && x_o[0][2] == (x_o[2][0])) {
                winner = x_o[1][1];
                start = new int[]{0, 2};
                end = new int[]{2, 0};

            } else {
                for (int i = 0; i < 3; i++) {
                    if (x_o[i][0] == (x_o[i][1]) && x_o[i][0] == (x_o[i][2])) {
                        winner = x_o[i][0];
                        start = new int[]{i, 0};
                        end = new int[]{i, 2};
                        break;

                    } else if (x_o[0][i] == (x_o[1][i]) && x_o[0][i] == (x_o[2][i])) {
                        winner = x_o[0][i];
                        start = new int[]{0, i};
                        end = new int[]{2, i};
                        break;
                    }
                }
            }
        }

        if (this.counter == 9 || winner > 0)
            this.showWinner(winner, start, end);
    }

    private void showWinner(int winner, int[] start, int[] end) {
        Line line = new Line();
        line.setStartX(start[0] * 200 + 100);
        line.setStartY(start[1] * 200 + 100);
        line.setEndX(end[0] * 200 + 100);
        line.setEndY(end[1] * 200 + 100);
        line.setStrokeWidth(7);
        line.setStroke(Color.valueOf("06df13"));
        this.pane.getChildren().add(line);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game over");
        alert.setHeaderText((winner != -1) ? this.text + ": is the winner." : "No winner!");
        alert.setContentText("DO you would to play again?");

        this.newGame(alert);
    }

    private void newGame(Alert alert) {
        Stage stage = (Stage) this.pane.getScene().getWindow();
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == ButtonType.CANCEL)
                stage.close();

            else {
                try {
                    this.pane.getScene().setRoot(
                            new FXMLLoader(getClass().getResource("sample.fxml")).load()
                    );

                    this.x_o = new int[3][3];
                    this.counter = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
