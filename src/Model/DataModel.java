package Model;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jamie on 07/03/16.
 * Collection of Inductees
 */
public class DataModel implements Serializable{
    private ArrayList<Inductee> inductees;


    public DataModel() {
        this.inductees = new ArrayList<Inductee>();
    }

    public void addInductee(Inductee i) {
        inductees.add(i);
    }


    public ArrayList<Inductee> getInductees() {
        return inductees;
    }




}
