package controller;



        import controller.interfaces.IGui;
        import controller.interfaces.IPersistor;
        import model.*;
        import view.JPanelOpenCV;
        import videoPlayer.VideoPlayer;

        import java.awt.image.BufferedImage;
        import java.util.ArrayList;

public class InductionSWController
{
    //THIS IS THE STATIC PART
    //This is the static variable which will point at the
    //instance of WorldCupController once created.
    private static InductionSWController instance = null;

    //This is the static method which "manages" the static
    //instance. A static method is required to access a static
    //variable.
    //If the instance is not created it will be created. If it is
    //already created then we don't need to create another instance.
    //Either way the one and only instance gets returned.
    public static InductionSWController getInstance()
    {
        if(instance == null)
        {
            instance = new InductionSWController();
        }
        return instance;
    }

    /////EVERYTHING BELOW THIS IS THE "INSTANCE PART"

    private ArrayList<Inductee> newlyAddedInductees;

    //Reference to the data model
    private DataModel dataModel;
    private Questionnaire questionnaire;
    //private ArrayList<Inductee> dataModel;

    //Reference to the GUI
    //Any GUI which implements this interface can be
    //communicated with by this controller.
    //If we had just put private UserInputFrame gui then
    //we would be restricting this controller to only being
    //capable of connecting to a Swing GUI.
    private IGui gui;

    //Add a reference to the persistor.
    private IPersistor persistor;

    //Default constructor
    //Making this private means that it can only be called
    //from inside this class (i.e. Only our getInstance()
    //method can call this now. Nobody outside this class
    //can create an instance of it.
    private InductionSWController() {
        this.newlyAddedInductees = new ArrayList<Inductee>();
        this.questionnaire = new Questionnaire();
    }

    public void setDataModel(DataModel dataModel)
    {
        this.dataModel = dataModel;
    }

    public DataModel getDataModel()
    {
        return this.dataModel;
    }

    public Questionnaire getQuestionnaire(){ return this.questionnaire;}

//    public void setQuestionnaire(Questionnaire questionnaire) {
//        this.questionnaire = questionnaire;
//    }


    public void setGuiReference(IGui gui)
    {
        this.gui = gui;
    }

    public IGui getGuiReference()
    {
        return this.gui;
    }

    public void setPersistor(IPersistor persistor)
    {
        this.persistor = persistor;
    }

    public IPersistor getPersistor()
    {
        return this.persistor;
    }

    public Inductee createInductee( String name, String company, String jobTitle, String supervisor, String carReg, String competencies, long dateOfInduction) {
        Inductee i = new Inductee( name, company, jobTitle, supervisor, carReg, competencies, dateOfInduction);

        // Capture the picture and add it to the inductee
        try {
            i.setPhoto(takePicture());
            System.out.println("picture taken, createInductee");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Add the inductee to the datamodel
        this.dataModel.addInductee(i);


        this.newlyAddedInductees.add(i);
        return i;
    }
    public void takeQuiz(int inducteeIndex) {

    }

    public BufferedImage takePicture() throws InterruptedException {

        JPanelOpenCV jp = new JPanelOpenCV();
        return jp.capturePhoto();
    }


    // Write the newly added inductees and clear the arraylist
    public void save()
    {
        this.persistor.write(this.dataModel);
//        this.persistor.writeInductee(this.newlyAddedInductees);
//        this.newlyAddedInductees.clear();
    }


    public boolean checkAnswer(MultipleChoiceQuestion q, int ans) {
        // Check the answer given the question and the answer

//        return this.getQuestionnaire().getAnswers()[ans - 1];
        return q.checkAnswer(ans);
    }



    public void launchVideo() {
       VideoPlayer.main(null);
    }

//    public void saveDataModel()
//    {
//        FilePersistor fps = new FilePersistor();
//        fps.write(this.dataModel);
//    }
}