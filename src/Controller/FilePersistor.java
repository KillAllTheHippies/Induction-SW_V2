package Controller;

/**
 * Created by Jamie on 18/03/16.
 */

import Controller.Interfaces.IPersistor;
import Model.DataModel;
import Model.Inductee;

import java.io.*;
import java.util.ArrayList;


public class FilePersistor implements IPersistor{

    private static final String FILE_LOCATION = "DataModel.dat";

    public void write(DataModel dm)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("DataModel.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dm);
            oos.close();
            fos.close();
        }
        catch(IOException ioex)
        {
            System.out.println(ioex.getMessage());
        }

    }


//    public void writeInductee(Inductee i, DataModel dm) {
//        dm.addInductee(i);
//        write(dm);
//    }

    public DataModel read() {
        try
        {
            FileInputStream fis = new FileInputStream(FILE_LOCATION);
            ObjectInputStream ois = new ObjectInputStream(fis);
            //We know that a DataModel object was serialized INTO
            //the file, therefore a DataModel object MUST be coming
            //out of the file => We can cast it to a Country object.
            DataModel serializedObject = (DataModel) ois.readObject();
            ois.close();
            fis.close();
            return serializedObject;

        }
        catch(IOException ioex)
        {
            System.out.println(ioex.getMessage());
            // initialise the datamodel.
            DataModel dm = new DataModel();
            return dm;
        }
        catch(ClassNotFoundException cnfe)
        {

            System.out.println("Class not found exception");
            System.out.println(cnfe.getMessage());
        }
        //We will only come to this piece of code if something has
        //failed in relation to reading the serialized file above.
        //If we arrive here we still must return something.
        return null;
    }
    }






