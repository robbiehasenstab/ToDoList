import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Arc;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.transform.Rotate;
import javafx.animation.RotateTransition;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
/**
 * @author rhasenstab3
 * @version 1
 * javafx class extending Application to create a ToDo List.
 */
public class ToDoList extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("To Do List");
        Text text = new Text(410, 75, "To-Do List");
        text.setFont(Font.font("Courier", FontWeight.BOLD, 35));
        int[] tasksRem = {0};
        int[] tasksCom = {0};
        Text com = new Text(200, 125, "Number of Tasks Completed: " + tasksCom[0]);
        Text rem = new Text(600, 125, "Number of Tasks Remaining: " + tasksRem[0]);
        com.setFont(Font.font(15));
        rem.setFont(Font.font(15));
        TextField txt = new TextField();
        txt.setPromptText("Task Name: ");
        txt.setFocusTraversable(false);
        txt.relocate(100, 600);
        txt.setPrefSize(200, 25);
        ChoiceBox<String> tasks = new ChoiceBox<String>();
        tasks.getItems().addAll("Study", "Shop", "Cook", "Sleep");
        tasks.relocate(350, 600);
        tasks.setPrefSize(100, 25);
        ChoiceBox<String> hours = new ChoiceBox<String>();
        hours.getItems().addAll("1", "2", "3", "4", "5");
        hours.relocate(500, 600);
        hours.setPrefSize(100, 25);
        Pane pane = new Pane();
        pane.getChildren().addAll(com, rem);
        ListView<String> listView = new ListView<String>();
        listView.setPrefWidth(500);
        Button btn = new Button("Enqueue");
        btn.setPrefSize(100, 25);
        Button btn2 = new Button("Dequeue");
        btn2.setPrefSize(100, 25);
        Circle lface = new Circle(125, 350, 50);
        Circle rface = new Circle(875, 350, 50);
        Arc rsmile = new Arc(875, 360, 20, 8, 190, 160);
        rsmile.setType(ArcType.OPEN);
        Line rleye = new Line(860, 325, 860, 340);
        Line rreye = new Line(890, 325, 890, 340);
        Text words1 = new Text(95, 350, "");
        //Text words2 = new Text(95, 350, "Good job!");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                if (txt.getText().isEmpty() && hours.getValue() == null && tasks.getValue() == null) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Please insert a task name,"
                        + "select a task type, and select the number of hours :)");
                    Media sound = new Media(new File("beep.mp3").toURI().toString());
                    MediaPlayer player = new MediaPlayer(sound);
                    player.play();
                    error.showAndWait();
                } else if (txt.getText().isEmpty() && tasks.getValue() == null) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Please insert a task name and select"
                        + "a task type :)");
                    Media sound = new Media(new File("beep.mp3").toURI().toString());
                    MediaPlayer player = new MediaPlayer(sound);
                    player.play();
                    error.showAndWait();
                } else if (hours.getValue() == null && tasks.getValue() == null) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Please select a task type and the number "
                        + "of hours :)");
                    Media sound = new Media(new File("beep.mp3").toURI().toString());
                    MediaPlayer player = new MediaPlayer(sound);
                    player.play();
                    error.showAndWait();
                } else if (txt.getText().isEmpty() && hours.getValue() == null) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Please insert a task name and select "
                        + "the number of hours :)");
                    Media sound = new Media(new File("beep.mp3").toURI().toString());
                    MediaPlayer player = new MediaPlayer(sound);
                    player.play();
                    error.showAndWait();
                } else if (hours.getValue() == null) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Please select the number of hours :)");
                    Media sound = new Media(new File("beep.mp3").toURI().toString());
                    MediaPlayer player = new MediaPlayer(sound);
                    player.play();
                    error.showAndWait();
                } else if (txt.getText().isEmpty()) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Please insert a task name :)");
                    Media sound = new Media(new File("beep.mp3").toURI().toString());
                    MediaPlayer player = new MediaPlayer(sound);
                    player.play();
                    error.showAndWait();
                } else if (tasks.getValue() == null) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Please select a task type :)");
                    Media sound = new Media(new File("beep.mp3").toURI().toString());
                    MediaPlayer player = new MediaPlayer(sound);
                    player.play();
                    error.showAndWait();
                } else {
                    int numero = 0;
                    int num = 0;
                    int index = 0;
                    String date = "";
                    for (String items: listView.getItems()) {
                        if (items.split("-")[0].trim().substring(6).equals(txt.getText())) {
                            num++;
                            numero += index;
                            if (items.split("-")[1].trim().substring(6).equals(tasks.getValue())) {
                                num++;
                                date = items.split("-")[3].trim();
                                date = date.substring(0, 23) + String.valueOf(Integer.valueOf(date.substring(23, 24)
                                     + hours.getValue()) + date.substring(25));
                                if (items.split("-")[2].trim().substring(7).equals(hours.getValue())) {
                                    num++;
                                    Alert alert = new Alert(Alert.AlertType.ERROR, "The task has already been added.");
                                    Media sound = new Media(new File("beep.mp3").toURI().toString());
                                    MediaPlayer player = new MediaPlayer(sound);
                                    player.play();
                                    alert.showAndWait();
                                }
                            }
                        }
                        index++;
                    }
                    if (num == 1) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "The task name already exists.");
                        Media sound = new Media(new File("beep.mp3").toURI().toString());
                        MediaPlayer player = new MediaPlayer(sound);
                        player.play();
                        alert.showAndWait();
                    } else if (num == 2) {
                        listView.getItems().remove(numero);
                        listView.getItems().add(numero, "Task: " + txt.getText() + " - Type: " + tasks.getValue()
                            + " - Hours: " + hours.getValue() + " - " + date);
                        txt.clear();
                        hours.getSelectionModel().clearSelection();
                        tasks.getSelectionModel().clearSelection();
                    }
                    if (num == 0) {
                        java.util.Date time = new java.util.Date();
                        listView.getItems().add("Task: " + txt.getText() + " - Type: " + tasks.getValue() + " - Hours: "
                            + hours.getValue() + " - Complete by " + time);
                        txt.clear();
                        hours.getSelectionModel().clearSelection();
                        tasks.getSelectionModel().clearSelection();
                        pane.getChildren().remove(rem);
                        tasksRem[0] += 1;
                        rem.setText("Number of Tasks Remaining: " + tasksRem[0]);
                        pane.getChildren().add(rem);
                        if (tasksRem[0] == 0 && tasksCom[0] == 0) {
                            lface.setFill(Color.RED);
                            rface.setFill(Color.RED);
                            words1.setText("Get to work!");
                            pane.getChildren().addAll(lface, rface, rsmile, words1, rleye, rreye);
                        } else {
                            pane.getChildren().removeAll(lface, rface, rsmile, words1, rleye, rreye);
                            words1.setText("Get to work!");
                            lface.setFill(Color.RED);
                            rface.setFill(Color.RED);
                            pane.getChildren().addAll(lface, rface, words1, rsmile, rleye, rreye);
                        }
                        RotateTransition rotate = new RotateTransition();
                        rotate.setAxis(Rotate.Z_AXIS);
                        rotate.setByAngle(360);
                        rotate.setCycleCount(10);
                        rotate.setDuration(Duration.millis(500));
                        rotate.setNode(words1);
                        rotate.play();
                    }
                }
            }
        });
        btn2.setOnAction(e -> {
            if (listView.getItems().isEmpty()) {
                Alert err = new Alert(Alert.AlertType.ERROR, "You have already completed all of your tasks.");
                Media sound = new Media(new File("beep.mp3").toURI().toString());
                MediaPlayer player = new MediaPlayer(sound);
                player.play();
                err.showAndWait();
            } else {
                listView.getItems().remove(0);
                pane.getChildren().remove(rem);
                tasksRem[0] -= 1;
                rem.setText("Number of Tasks Remaining: " + tasksRem[0]);
                pane.getChildren().add(rem);
                pane.getChildren().remove(com);
                tasksCom[0] += 1;
                com.setText("Number of Tasks Completed: " + tasksCom[0]);
                pane.getChildren().add(com);
                pane.getChildren().removeAll(lface, rface, words1, rsmile, rleye, rreye);
                words1.setText("Good job!");
                lface.setFill(Color.GREEN);
                rface.setFill(Color.GREEN);
                pane.getChildren().addAll(lface, rface, words1, rsmile, rleye, rreye);
                Media sound = new Media(new File("applause.mp3").toURI().toString());
                MediaPlayer player = new MediaPlayer(sound);
                player.play();
                RotateTransition rotate = new RotateTransition();
                rotate.setAxis(Rotate.Z_AXIS);
                rotate.setByAngle(360);
                rotate.setCycleCount(10);
                rotate.setDuration(Duration.millis(500));
                rotate.setNode(words1);
                rotate.play();
            }
        });
        btn.relocate(650, 600);
        btn2.relocate(800, 600);
        HBox hbox = new HBox(listView);
        hbox.relocate(250, 150);
        pane.getChildren().addAll(btn, btn2, tasks, hours, text, txt, hbox);
        Scene myScene = new Scene(pane, 1000, 700);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}