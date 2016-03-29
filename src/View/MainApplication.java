package view;

import controller.FilePersistor;
import controller.InductionSWController;
import controller.interfaces.IPersistor;
import model.DataModel;
import org.opencv.core.Core;

/**
 * Created by Jamie on 01/03/16.
 * TODO: Connect the inductee to the quiz and collect the answers
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
