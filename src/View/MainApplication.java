package View;

import Controller.DatabasePersistor;
import Controller.FilePersistor;
import Controller.InductionSWController;
import Controller.Interfaces.IPersistor;
import Model.DataModel;
import Model.Inductee;
import org.opencv.core.Core;

import java.util.ArrayList;

/**
 * Created by Jamie on 01/03/16.
 */
public class MainApplication {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Create the persistor
        IPersistor persistor = new FilePersistor();
        InductionSWController.getInstance().setPersistor(persistor);

        // Create the model and instantiate the data in it from the persistor
        DataModel dataModel = persistor.read();

        // Connect the controller to the model
        InductionSWController.getInstance().setDataModel(dataModel);

        // Create an instance of our main application frame which builds the UI
        UserInputFrame uif = new UserInputFrame("Induction Application");
        uif.setSize(700, 300);
        uif.setVisible(true);

        // Connect the controller to the view
//        TwitterController.getInstance().setGuiReference(uif);
    }
}
