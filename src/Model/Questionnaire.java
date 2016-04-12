package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Jamie on 27/03/16.
 */
public class Questionnaire {

    private ArrayList<MultipleChoiceQuestion> questions;
    private boolean[] answers;

    public Questionnaire() {
        this.questions = new ArrayList<>();

//        // TEST CODE
//        MultipleChoiceQuestion q = new MultipleChoiceQuestion("What is Question 1?");
//        q.addChoice("True Answer 1", true);
//        q.addChoice("False Answer 2", false);
//        q.addChoice("False Answer 3", false);
//        q.addChoice("False Answer 4", false);
//        // Hardcode the index for now.
//        //TODO: create the index of the question when question is input first.
//        q.setIndex(0);
//        questions.add(q);
//
//        MultipleChoiceQuestion q1 = new MultipleChoiceQuestion("What is Question 2?");
//        q1.addChoice("True Answer 1", true);
//        q1.addChoice("False Answer 2", false);
//        q1.addChoice("False Answer 3", false);
//        q1.addChoice("False Answer 4", false);
//        q1.setIndex(1);
//        questions.add(q1);
//
//        ImageChoiceQuestion iq = new ImageChoiceQuestion("Who is the Site Manager?");
//
//
//        BufferedImage img = null;
//        try {
//            img = ImageIO.read(new File("C:\\images\\1.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        iq.addChoice(img, true);
//        try {
//            img = ImageIO.read(new File("C:\\images\\2.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        iq.addChoice(img, false);
//        try {
//            img = ImageIO.read(new File("C:\\images\\3.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        iq.addChoice(img, false);
//        try {
//            img = ImageIO.read(new File("C:\\images\\4.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        iq.addChoice(img, false);
//        iq.setIndex(2);
//        questions.add(iq);


//        try{
//            // Open the file
//            FileInputStream fstream = new FileInputStream("Questionnaire.txt");
//            // Get the object of DataInputStream
//            DataInputStream in = new DataInputStream(fstream);
//            BufferedReader br = new BufferedReader(new InputStreamReader(in));
//            String strLine;
//            //Read File Line By Line
//            int index = 0;
//            while ((strLine = br.readLine()) != null)   {
//                // split the line on your splitter(s)
//                String[] splitLine = strLine.split("|"); // here | is used as the delimiter
//                MultipleChoiceQuestion q = new MultipleChoiceQuestion(splitLine[0]);
//                q.addChoice(splitLine[1], true);
//                q.addChoice(splitLine[2], false);
//                q.addChoice(splitLine[3], false);
//                q.addChoice(splitLine[4], false);
//                // Hardcode the index for now.
//                //TODO: create the index of the question when question is input first.
//                q.setIndex(index);
//                questions.add(q);
//                index++;
//            }
//            //Close the input stream
//            in.close();
//        }catch (Exception e){//Catch exception if any
//            System.err.println("Error: " + e.getMessage());
//        }

        // initialise the answers array (to the size of the amount of questions)
        try{

            BufferedReader in = new BufferedReader(new FileReader("C:\\InductionApp\\Questionnaire.txt"));
            String s;
            int index = 0;
            while((s = in.readLine()) != null){

                String[] var = s.split("="); // use = as our delimiter
                MultipleChoiceQuestion question = new MultipleChoiceQuestion(var[0]);
                question.addChoice(var[1], true);
                question.addChoice(var[2], false);
                question.addChoice(var[3], false);
                question.addChoice(var[4], false);

                /* Shuffle the choices */
                ArrayList<QChoice> tempChoices = question.getChoices();
                Collections.shuffle(tempChoices);
                question.setChoices(tempChoices);

                // Set the index of the question.
                question.setIndex(index);
                questions.add(question);
                index++;

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        this.answers = new boolean[questions.size()];

//


    }

    public void addQuestion(MultipleChoiceQuestion q) {
        questions.add(q);
    }

    public void addAnswer(boolean ans, int index) {

        this.answers[index] = ans;


    }
    public ArrayList<MultipleChoiceQuestion> getQuestions() {
        return questions;
    }

    public boolean[] getAnswers() {
        return answers;
    }

    public String getCorrectAnswer(int index) {
        for (int i = 0 ; i < 4; i++) {
            if (questions.get(index).getChoices().get(i).isCorrect()) {
                return questions.get(index).getChoices().get(i).getText();
            }

        }
        return "The code should not execute this";
    }
}
