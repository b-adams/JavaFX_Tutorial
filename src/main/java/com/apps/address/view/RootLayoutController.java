package com.apps.address.view;

/**
 * Created by badams on 11/2/14.
 */

import com.apps.address.MainApp;
import com.apps.address.util.PathChooser;
import com.sun.istack.internal.Nullable;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static com.apps.address.util.OptionalEx.ifPresent;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 *
 * @author Marco Jakob
 */
public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        PathChooser pathChooser = new PathChooser();

        // Set extension filter
        pathChooser.addExtensionFilter("XML files (*.xml)", "*.xml");

        // Show open file dialog
        pathChooser.showOpenDialog(mainApp.getPrimaryStage())
                .ifPresent(mainApp::loadPersonDataFromFile);

    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        ifPresent(mainApp.getPersonFilePath(),
                mainApp::savePersonDataToFile)
                .orElse(this::handleSaveAs);
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        PathChooser pathChooser = new PathChooser();

        // Set extension filter
        pathChooser.addExtensionFilter("XML files (*.xml)", "*.xml");

        // Show save file dialog
        pathChooser.showSaveDialog(mainApp.getPrimaryStage())
                .map(f -> {
                    //Add extensions if missing
                    String s = f.toString();
                    if(s.toLowerCase().endsWith(".xml")) return f;
                    else return Paths.get(s + ".xml");})
                .ifPresent(mainApp::savePersonDataToFile);
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Dialogs.create()
                .title("AddressApp")
                .masthead("About")
                .message("Author: Marco Jakob\nWebsite: http://code.makery.ch")
                .showInformation();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}