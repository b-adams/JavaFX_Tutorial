package com.apps.address.util;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Created by badams on 11/2/14.
 */
public class PathChooser {
    private FileChooser fc;
    public PathChooser(){
        fc = new FileChooser();
    }

    public void addExtensionFilter(String description, String filter)
    {
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(description, filter);
        fc.getExtensionFilters().add(extFilter);
    }

    public Optional<Path> showSaveDialog(Stage stage)
    {
        return Optional.ofNullable(fc.showSaveDialog(stage)).map(File::toPath);
    }

    public Optional<Path> showOpenDialog(Stage stage) {
        return Optional.ofNullable(fc.showOpenDialog(stage)).map(File::toPath);
    }
}
