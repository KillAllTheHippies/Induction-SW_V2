package view;

import controller.InductionSWController;
import model.MultipleChoiceQuestion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Jamie on 07/03/16.
 */
public class QuestionPanel extends JPanel {

//    private MultipleChoiceQuestion q;
//    private ImageChoiceQuestion iq;
    private MultipleChoiceQuestion q;
    private JRadioButton btn1,btn2,btn3,btn4;
    private ArrayList<JRadioButton> btnArray = new ArrayList<>();
    private BufferedImage im;
//    private DataModel dataModel;

    public QuestionPanel(MultipleChoiceQuestion q) {
        this.q = q;
//        this.dataModel = InductionSWController.getInstance().getDataModel();
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        btn1 = new JRadioButton();
        btn2 = new JRadioButton();
        btn3 = new JRadioButton();
        btn4 = new JRadioButton();

        RadioButtonsActionListener rdbListener = new RadioButtonsActionListener(this);
        btn1.addActionListener(rdbListener);
        btn2.addActionListener(rdbListener);
        btn3.addActionListener(rdbListener);
        btn4.addActionListener(rdbListener);

        btnArray.add(btn1);
        btnArray.add(btn2);
        btnArray.add(btn3);
        btnArray.add(btn4);

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(btn1);
        btnGroup.add(btn2);
        btnGroup.add(btn3);
        btnGroup.add(btn4);
        //JPanel qp = createQuestionPanel(q);

        // Create the label
        JLabel lblQuestion = new JLabel(q.getText());
        this.add(lblQuestion);


        // Create the radiobuttons
        for (int i =0; i < q.getChoices().size(); i++) {

            String s = q.getChoices().get(i).getText();
            JRadioButton btn = btnArray.get(i);
            btn.setText(s);
            btnGroup.add(btn);
            this.add(btn);
        }

    }

    /**
     * Constructor for Image choice question
     * To be implemented at a later stage
     *
     *
     */
//    public QuestionPanel(ImageChoiceQuestion iq) {
//        this.q = iq;
//
//        btn1 = new JRadioButton();
//        btn2 = new JRadioButton();
//        btn3 = new JRadioButton();
//        btn4 = new JRadioButton();
//
//        RadioButtonsActionListener rdbListener = new RadioButtonsActionListener(this);
//        btn1.addActionListener(rdbListener);
//        btn2.addActionListener(rdbListener);
//        btn3.addActionListener(rdbListener);
//        btn4.addActionListener(rdbListener);
//
//        btnArray.add(btn1);
//        btnArray.add(btn2);
//        btnArray.add(btn3);
//        btnArray.add(btn4);
//
//        ButtonGroup btnGroup = new ButtonGroup();
//        btnGroup.add(btn1);
//        btnGroup.add(btn2);
//        btnGroup.add(btn3);
//        btnGroup.add(btn4);
//        //JPanel qp = createQuestionPanel(q);
//
//        // Create the label
//        JLabel lblQuestion = new JLabel(iq.getText());
//        this.add(lblQuestion);
//
//        // Create the choices
//        for (int i =0; i < iq.getChoices().size(); i++) {
//            // get the image from the question
//            BufferedImage im = iq.getChoices().get(i);
//            // Get the radiobutton from the array
//            JRadioButton btn = btnArray.get(i);
//
//            //Display the image
//            ImagePanel imPanel = new ImagePanel(im);
//            imPanel.setPreferredSize(new Dimension(300,300));
//            this.add(imPanel);
//
//            //btn.setText(s);
//            btnGroup.add(btn);
//            this.add(btn);
//        }
//
//    }
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.drawImage(im, 0, 0, null); // see javadoc for more info on the parameters
//    }

    //Inner class implementation of ActionListener

    private class RadioButtonsActionListener implements ActionListener {
        //This is to allow this inner class to refer to its
        //containing class (i.e. UserInputFrame)
        private QuestionPanel outerClass;

        public RadioButtonsActionListener(QuestionPanel outerClass) {
            this.outerClass = outerClass;
        }

        public void actionPerformed(ActionEvent e) {
            //Listener for button clicks.
            JRadioButton sourceButton = (JRadioButton) e.getSource();

            // ------------------BTN1------------------
            if (sourceButton.equals(btn1)) {
                System.out.println(InductionSWController.getInstance().checkAnswer(q, 0));
                // Add the answer selected to the position in the array of answers
                InductionSWController.getInstance().getQuestionnaire().addAnswer
                        (InductionSWController.getInstance().checkAnswer(q, 0), q.getIndex());

                // ------------------BTN2------------------
            } else if (sourceButton.equals(btn2)) {
                System.out.println(InductionSWController.getInstance().checkAnswer(q, 1));
                // Add the answer selected to the position in the arraylist of answers
                InductionSWController.getInstance().getQuestionnaire().addAnswer
                        (InductionSWController.getInstance().checkAnswer(q, 1), q.getIndex() );

                // ------------------BTN3------------------
            } else if (sourceButton.equals(btn3)) {
                System.out.println(InductionSWController.getInstance().checkAnswer(q, 2));
                // Add the answer selected to the position in the arraylist of answers
                InductionSWController.getInstance().getQuestionnaire().addAnswer(InductionSWController.getInstance().checkAnswer(q, 2), q.getIndex() );
            }

            // ------------------BTN4------------------
            else {
                System.out.println(InductionSWController.getInstance().checkAnswer(q, 3));
                // Add the answer selected to the position in the arraylist of answers
                InductionSWController.getInstance().getQuestionnaire().addAnswer(InductionSWController.getInstance().checkAnswer(q, 3), q.getIndex() );
                }

            } // end else

        }// end actionperformed

    }

//    private JPanel createQuestionPanel(MultipleChoiceQuestion q) {
//
//        JPanel jp = new JPanel();
//
//
//        btn1 = new JRadioButton();
//        btn2 = new JRadioButton();
//        btn3 = new JRadioButton();
//        btn4 = new JRadioButton();
//        btnArray.add(btn1);
//        btnArray.add(btn2);
//        btnArray.add(btn3);
//        btnArray.add(btn4);
//
//
//        JLabel lblQuestion = new JLabel(q.getText());
//        jp.add(lblQuestion);
////        for (String s : q.getChoices()) {
////            for (JRadioButton btn : btnArray) {
////                btn = new JRadioButton(s);
////                btnGroup.add(btn);
////                jp.add(btn);
////            }
////        }
//
//
//        // return the fully constructed panel
//        //jp.setVisible(true);
//        return jp;
//
//    }

