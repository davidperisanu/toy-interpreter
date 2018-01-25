package edu.interpreter.view;

import java.io.BufferedReader;
import java.util.concurrent.Executors;

import edu.interpreter.controller.Controller;
import edu.interpreter.model.ProgramState;
import edu.interpreter.model.utilities.Pair;
import edu.interpreter.model.utilities.exceptions.IndexOutOfRangeException;
import edu.interpreter.model.utilities.interfaces.IDeque;
import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.model.utilities.interfaces.IFileTable;
import edu.interpreter.model.utilities.interfaces.IHeap;
import edu.interpreter.model.utilities.interfaces.ILatchTable;
import edu.interpreter.model.statements.Statement;
import edu.interpreter.model.utilities.interfaces.IList;
import edu.interpreter.repository.IRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ControllerWindow extends Window {
    private Controller controller;

    private Label programStatesNo;
    private TableView<Pair<Integer, Integer>> heapTable, latchTable;
    private TableView<Pair<Integer, String>> fileTable;
    private TableView<Pair<String, Integer>> symbolTable;
    private ListView<String> outputMessages, executionStack;
    private ListView<Integer> programStates;

    @SuppressWarnings("unchecked")
    public ControllerWindow(Stage parent, String title, Controller controller) {
        super(parent);

        Stage window;
        GridPane layout;
        Button runButton;

        controller.executor(Executors.newFixedThreadPool(2));
        this.controller = controller;
        window = stage();
        layout = new GridPane();
        programStatesNo = new Label("Number of program states: " + controller.repository().programStates().size());
        heapTable = new TableView<>();
        latchTable = new TableView<>();
        outputMessages = new ListView<>();
        fileTable = new TableView<>();
        programStates = new ListView<>();
        symbolTable = new TableView<>();
        executionStack = new ListView<>();
        runButton = new Button("One step for all");
        
        // Window
        window.setTitle(title);
        window.setMinWidth(800);
        window.setMinHeight(600);
        window.setOnCloseRequest(event -> {
            controller.closeExecutor();
        });

        // Layout
        layout.setVgap(1);
        layout.setHgap(3);
        window.setScene(new Scene(layout, 800, 650));

        // Content
        //  Top Label
        programStatesNo.setPadding(new Insets(5, 0, 0, 5));
        programStatesNo.setFont(Font.font("Calibri", 15));
        layout.add(programStatesNo, 0, 0);
        //  Heap Table
        TableColumn<Pair<Integer, Integer>, Integer> addressColumn, valueColumn;

        addressColumn = new TableColumn<>("Address");
        valueColumn = new TableColumn<>("Value");

        addressColumn.setCellValueFactory(new PropertyValueFactory<>("left"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("right"));
        heapTable.getColumns().addAll(addressColumn, valueColumn);
        layout.add(heapTable, 0, 1);
        //  Output Messages
        outputMessages.setEditable(false);
        layout.add(outputMessages, 1, 1);
        //  File Table
        TableColumn<Pair<Integer, String>, String> identifierColumn, fileNameColumn;

        identifierColumn = new TableColumn<>("Identifier");
        fileNameColumn = new TableColumn<>("File name");
        
        identifierColumn.setCellValueFactory(new PropertyValueFactory<>("left"));
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("right"));
        fileTable.getColumns().addAll(identifierColumn, fileNameColumn);
        layout.add(fileTable, 2, 1);
        //  Program states
        programStates.setEditable(false);
        layout.add(programStates, 0, 2);
        programStates.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 1) {
                    MultipleSelectionModel<Integer> selectionModel;

                    selectionModel = ((ListView<Integer>)mouseEvent.getSource()).getSelectionModel();

                    if (selectionModel.getSelectedItem() != null && selectionModel.getSelectedItem() >= 0) {
                        symbolTable.setItems(symbolTable(programState(controller.repository().programStates(), selectionModel.getSelectedItem()).symbolTable()));
                    }

                    if (selectionModel.getSelectedItem() != null && selectionModel.getSelectedItem() >= 0) {
                        executionStack.setItems(executionStack(programState(controller.repository().programStates(), selectionModel.getSelectedItem()).executionStack()));
                    }
                }
            }
        });
        //  Symbol table
        TableColumn<Pair<String, Integer>, String> variableNameColumn, variableValueColumn;
        
        variableNameColumn = new TableColumn<>("Variable name");
        variableValueColumn = new TableColumn<>("Value");
        
        variableNameColumn.setCellValueFactory(new PropertyValueFactory<>("left"));
        variableValueColumn.setCellValueFactory(new PropertyValueFactory<>("right"));
        symbolTable.getColumns().addAll(variableNameColumn, variableValueColumn);
        layout.add(symbolTable, 1, 2);
        //  Execution stack
        executionStack.setEditable(false);
        layout.add(executionStack, 2, 2);
        // Latch table
        TableColumn<Pair<Integer, Integer>, Integer> locationColumn, latchValueColumn;

        locationColumn = new TableColumn<>("Location");
        latchValueColumn = new TableColumn<>("Value");

        locationColumn.setCellValueFactory(new PropertyValueFactory<>("left"));
        latchValueColumn.setCellValueFactory(new PropertyValueFactory<>("right"));
        latchTable.getColumns().addAll(locationColumn, latchValueColumn);
        layout.add(latchTable, 0, 3);
        //  Run button
        layout.add(runButton, 2, 0);
        runButton.setOnAction(new EventHandler<ActionEvent>(){
        
            @Override
            public void handle(ActionEvent e) {
                IList<ProgramState> programStates;
                
                programStates = controller.removeCompletedPrograms(controller.repository().programStates());
                try {
                    controller.executeOneStepGlobal(programStates);
                    programStates = controller.removeCompletedPrograms(controller.repository().programStates());
                    updateUI();
                }
                catch (IndexOutOfRangeException ex) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    
                    alert.setTitle("Attention");
                    alert.setHeaderText("Execution done.");
                    alert.showAndWait();
                }
                catch (Exception ex) {
                    // Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    
                    // alert.setTitle("Attention");
                    // alert.setHeaderText("Execution done.");
                    // alert.showAndWait();

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    
                    alert.setTitle("Error");
                    alert.setHeaderText("Message: " + ex.getMessage());
                    alert.showAndWait();
                }
            }
        });

        updateUI();
    }

    private void updateUI() {
        IRepository repository = controller.repository();

        programStatesNo.setText("Number of program states: " + repository.programStates().size());

        heapTable.setItems(heap(repository.programStates().get(0).heap()));

        outputMessages.setItems(outputMessages(repository.programStates().get(0).outputMessages()));

        fileTable.setItems(fileTable(repository.programStates().get(0).fileTable()));

        programStates.setItems(programStatesIdentifiers(repository.programStates()));

        if (programStates.getSelectionModel().getSelectedItem() != null && programStates.getSelectionModel().getSelectedItem() >= 0) {
            symbolTable.setItems(symbolTable(programState(repository.programStates(), programStates.getSelectionModel().getSelectedItem()).symbolTable()));
        }

        if (programStates.getSelectionModel().getSelectedItem() != null && programStates.getSelectionModel().getSelectedItem() >= 0) {
            executionStack.setItems(executionStack(programState(repository.programStates(), programStates.getSelectionModel().getSelectedItem()).executionStack()));
        }

        if (repository.programStates().get(0).latchTable() != null)
            latchTable.setItems(latchTable(repository.programStates().get(0).latchTable()));
    }

    private ObservableList<Pair<Integer, Integer>> heap(IHeap<Integer, Integer> heap) {
        ObservableList<Pair<Integer, Integer>> pairs = FXCollections.observableArrayList();
        
        for (Integer address : heap.allKeys()) {
            pairs.add(new Pair<>(address, heap.get(address)));
        }

        return pairs;
    }

    private ObservableList<String> outputMessages(IList<String> outputMessages) {
        ObservableList<String> messages = FXCollections.observableArrayList();
        
        for (String str : outputMessages.all()) {
            messages.add(str);
        }

        return messages;
    }

    private ObservableList<Pair<Integer, String>> fileTable(IFileTable<Integer, Pair<String, BufferedReader>> fileTable) {
        ObservableList<Pair<Integer, String>> pairs = FXCollections.observableArrayList();
        
        for (Integer identifier : fileTable.allKeys()) {
            pairs.add(new Pair<>(identifier, fileTable.get(identifier).left()));
        }

        return pairs;
    }

    private ObservableList<Integer> programStatesIdentifiers(IList<ProgramState> programStates) {
        ObservableList<Integer> identifiers = FXCollections.observableArrayList();
        
        for (ProgramState s : programStates.all()) {
            identifiers.add(s.id());
        }

        return identifiers;
    }

    private ProgramState programState(IList<ProgramState> programStates, int id) {
        for (ProgramState s : programStates.all())
            if (s.id() == id)
                return s;

        return null;
    }

    private ObservableList<Pair<String, Integer>> symbolTable(IDictionary<String, Integer> symbolTable) {
        ObservableList<Pair<String, Integer>> pairs = FXCollections.observableArrayList();
        
        for (String var : symbolTable.allKeys()) {
            pairs.add(new Pair<>(var, symbolTable.get(var)));
        }

        return pairs;
    }

    private ObservableList<String> executionStack(IDeque<Statement> executionStack) {
        ObservableList<String> statements = FXCollections.observableArrayList();
        
        for (Statement s : executionStack.all()) {
            statements.add(s.toString());
        }

        return statements;
    }

    private ObservableList<Pair<Integer, Integer>> latchTable(ILatchTable<Integer, Integer> latchTable) {
        ObservableList<Pair<Integer, Integer>> pairs = FXCollections.observableArrayList();
        
        for (Integer address : latchTable.allKeys()) {
            pairs.add(new Pair<>(address, latchTable.get(address)));
        }

        return pairs;
    }
}
