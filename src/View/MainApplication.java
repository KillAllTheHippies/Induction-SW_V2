package view;

import controller.FilePersistor;
import controller.InductionSWController;
import controller.interfaces.IPersistor;
import model.DataModel;
import org.opencv.core.Core;

import javax.swing.*;

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
//        UserInputFrame uif = new UserInputFrame("Induction Application");
        MainDashBoardFrame dbf = new MainDashBoardFrame();
        dbf.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
//        dbf.setSize(700, 300);
        dbf.setVisible(true);

        // Connect the controller to the view
        InductionSWController.getInstance().setGuiReference(dbf);
    }
}
