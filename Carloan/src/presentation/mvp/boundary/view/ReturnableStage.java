
package presentation.mvp.boundary.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Properties;

public class ReturnableStage extends Stage {
    protected Scene scene;

    public ReturnableStage(Object parameter, String schemeResource) {
        this(parameter, schemeResource, false);
    }

    public ReturnableStage(Object parameter, String schemeResource, boolean fullscreen) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(schemeResource));
        
        try {
            root = (Region) fxmlLoader.load();
            Schermata schermata = fxmlLoader.getController();

            schermata.initData(parameter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleLabelTranslator.translateAll(root, properties);

        width = root.getPrefWidth();
        height = root.getPrefHeight();

        if (fullscreen) {
            width = Screen.getPrimary().getBounds().getWidth();
            height = Screen.getPrimary().getBounds().getHeight();
        }

        scene = new Scene(root, width, height);

        this.setMinWidth(width);
        this.setMinHeight(height);

        setScene(scene);
    }

    /*
    public ReturnableStage() {
    }
    */

    public void setResult(Object object) {
        value = object;
    }

    @Override
    public Object showWindow(Parameter parameter) {
        showAndWait();
        return value;
    }
}