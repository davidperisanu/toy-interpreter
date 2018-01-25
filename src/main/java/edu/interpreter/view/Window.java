package edu.interpreter.view;

import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class Window {
    private Stage window;

    /**
     * Initializes a new instance of the <code>Window</code> class with the defualt values.
     */
    public Window() {
        window = new Stage();
    }

    /**
     * Initializes a new instance of the <code>Window</code> class with the specified values.
     * @param parent The parent of the current <code>Window</code>.
     */
    public Window(Stage parent) {
        window = new Stage();

        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(parent);
    }

    /**
     * Gets the current stage.
     */
    public Stage stage() {
        return window;
    }

    /**
     * Shows the current <code>Window</code>.
     */
    public final void show() {
        window.show();
    }

    /**
     * Shows the current <code>Window</code> and waits for it to be closed.
     */
    public final void showAndWait() {
        window.showAndWait();
    }
}
