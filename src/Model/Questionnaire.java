package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jamie on 27/03/16.
 */
public class Questionnaire {

    private ArrayList<Question> questions;
    private String[] answers;

    public Questionnaire() {
        this.questions = new ArrayList<>();

        // TEST CODE
        ChoiceQuestion q = new ChoiceQuestion("What is Question 1?");
        q.addChoice("True Answer 1", true);
        q.addChoice("False Answer 2", false);
        q.addChoice("False Answer 3", false);
        q.addChoice("False Answer 4", false);
        // Hardcode the index for now.
        //TODO: create the index of the question when question is input first.
        q.setIndex(0);
        questions.add(q);

        ChoiceQuestion q1 = new ChoiceQuestion("What is Question 2?");
        q1.addChoice("True Answer 1", true);
        q1.addChoice("False Answer 2", false);
        q1.addChoice("False Answer 3", false);
        q1.addChoice("False Answer 4", false);
        q1.setIndex(1);
        questions.add(q1);

        ImageChoiceQuestion iq = new ImageChoiceQuestion("Who is the Site Manager?");


        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("C:\\images\\1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        iq.addChoice(img, true);
        try {
            img = ImageIO.read(new File("C:\\images\\2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        iq.addChoice(img, false);
        try {
            img = ImageIO.read(new File("C:\\images\\3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        iq.addChoice(img, false);
        try {
            img = ImageIO.read(new File("C:\\images\\4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        iq.addChoice(img, false);
        iq.setIndex(2);
        questions.add(iq);

        // initialise the answers array (to the size of the amount of questions)

        this.answers = new String[questions.size()];

    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public void addAnswer(String ans, int index) {

        this.answers[index] = ans;


    }
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public String[] getAnswers() {
        return answers;
    }
}
