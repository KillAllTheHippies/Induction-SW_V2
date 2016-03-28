package Controller.Interfaces;

import Model.ChoiceQuestion;
import Model.DataModel;
import Model.Inductee;

import java.util.ArrayList;

/**
 * Created by Jamie on 29/02/16.
 */
public interface IPersistor {
//    public void writeInductee(Inductee i, DataModel dm);
    //public void writeQuestion(ArrayList<ChoiceQuestion> dataModel);
    DataModel read();

    void write(DataModel dm);

}
