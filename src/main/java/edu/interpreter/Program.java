package edu.interpreter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

import javax.naming.directory.InvalidAttributesException;

import edu.interpreter.controller.Controller;
import edu.interpreter.model.utilities.exceptions.InvalidOperationException;
import edu.interpreter.examples.Exam;
import edu.interpreter.examples.Laboratory8;
import edu.interpreter.view.ConsoleMenu;
import edu.interpreter.view.ControllerWindow;
import edu.interpreter.view.commands.ExitCommand;
import edu.interpreter.view.commands.RunExampleCommand;

/**
 * The main entry point for the application.
 * @author David Perisanu
 */
public class Program extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception
    {
        StackPane layout;
        Scene scene;
        ListView<Controller> listView;

        layout = new StackPane();
        scene = new Scene(layout, 1200, 550);
        listView = new ListView<>();

        // Window
        mainStage.setTitle("ToyInterpreter Language");
        mainStage.setMinWidth(1200);
        mainStage.setMinHeight(550);
        mainStage.centerOnScreen();
        mainStage.setScene(scene);

        // Content
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @SuppressWarnings("unchecked")
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    MultipleSelectionModel<Controller> selectionModel;
                    ControllerWindow window;

                    selectionModel = ((ListView<Controller>)mouseEvent.getSource()).getSelectionModel();
                    window = new ControllerWindow(mainStage, "Example " + (selectionModel.getSelectedIndex() + 1), selectionModel.getSelectedItem());

                    window.showAndWait();
                }
            }
        });
        listView.getItems().add(Laboratory8.example1());
        listView.getItems().add(Exam.example1());
        listView.getItems().add(Exam.example2());
        layout.getChildren().add(listView);

        mainStage.show();
    }
}
