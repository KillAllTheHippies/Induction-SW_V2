package view;


import controller.InductionSWController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Jamie on 07/03/16.
 * Frame that holds the Questionnaire
 */
public class QuizFrame extends JFrame{

    private JButton checkAnswerButton;

//    private MultipleChoiceQuestion questionnaire;
    //private JRadioButton btn1,btn2,btn3,btn4;
    private ArrayList<JRadioButton> btnArray;
//    private DataModel dataModel;
    private Questionnaire questionnaire;
    private ArrayList<QuestionPanel> questionPanels;
//    private UserInputFrame parentFrame;

    public QuizFrame(String title) throws HeadlessException {
        super(title);
        // Reference to the datamodel
//        dataModel = InductionSWController.getInstance().getDataModel();
        btnArray = new ArrayList<>();
        questionPanels = new ArrayList<>();
        questionnaire = InductionSWController.getInstance().getQuestionnaire();
//        this.parentFrame = parentFrame;

        // Content of our JFrame
        JPanel mainPanel = new JPanel();

        // set border layout
        mainPanel.setLayout(new BorderLayout());

        JPanel centrePanel = createCenterPanel();
        JScrollPane sp = new JScrollPane(centrePanel);
        mainPanel.add(sp, BorderLayout.CENTER);

        JPanel bottomPanel = createBottomButtonPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);


        // add to the layout
        this.add(mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

//    private JPanel createQuestionPanel(MultipleChoiceQuestion questionnaire) {
//        JRadioButton btn1,btn2,btn3,btn4;
//        JPanel mainPanel = new JPanel();
//        ButtonGroup btnGroup = new ButtonGroup();
////        btnGroup.add(btn1);
////        btnGroup.add(btn2);
////        btnGroup.add(btn3);
////        btnGroup.add(btn4);
//
//        // Create the buttons and set the actionCommand
//        btn1 = new JRadioButton();
//        btn1.setActionCommand("1");
//        btn2 = new JRadioButton();
//        btn2.setActionCommand("2");
//        btn3 = new JRadioButton();
//        btn3.setActionCommand("3");
//        btn4 = new JRadioButton();
//        btn4.setActionCommand("4");
//
//        // Add the buttons to an arraylist
//
//        btnArray.add(btn1);
//        btnArray.add(btn2);
//        btnArray.add(btn3);
//        btnArray.add(btn4);
//
//
//        //TODO: Get this working
//        JLabel lblQuestion = new JLabel(questionnaire.getText());
//        mainPanel.add(lblQuestion);
//        for (int i =0; i < questionnaire.getChoices().size(); i++) {
//            String s = questionnaire.getChoices().get(i);
//            JRadioButton btn = new JRadioButton(s);
//            btnGroup.add(btn);
//            mainPanel.add(btn);
//        }
//
//            // return the fully constructed panel
//            //mainPanel.setVisible(true);
//        return mainPanel;
//
//    }

    private JPanel createCenterPanel() {

        JPanel mainPanel = new JPanel(new GridLayout(0,1));

        for (MultipleChoiceQuestion quest : questionnaire.getQuestions()) {
//            if (quest instanceof MultipleChoiceQuestion) {
            questionPanels.add(new QuestionPanel(quest));
//                QuestionPanel qp = new QuestionPanel((MultipleChoiceQuestion) quest);
                //sp.add(qp);
//                mainPanel.add(qp);
//                System.out.println(quest.getText());
//            }
//            else // else it is an ImageChoiceQuestion
//            {
//                QuestionPanel qp = new QuestionPanel((ImageChoiceQuestion) quest);
//                //sp.add(qp);
//                mainPanel.add(qp);
//                System.out.println(quest.getText());
//            }

        } // end for
        for (QuestionPanel p : questionPanels) {
            mainPanel.add(p);
        }


        return mainPanel;
    }

    private JPanel createBottomButtonPanel() {
        // Instantiate panel, button & listener, add listener to button,
        // add button to panel, return panel.
        JPanel buttonPanel = new JPanel();
        checkAnswerButton = new JButton("Check Answer");
        ButtonsActionListener buttonListener =
                new ButtonsActionListener(this);

        checkAnswerButton.addActionListener(buttonListener);

        buttonPanel.add(checkAnswerButton);

        return buttonPanel;
    }

    private class ButtonsActionListener implements ActionListener {
        //This is to allow this inner class to refer to its
        //containing class (i.e. UserInputFrame)
        private QuizFrame outerClass;

        public ButtonsActionListener(QuizFrame outerClass) {
            this.outerClass = outerClass;
        }

        public boolean validateQuestionnaire() {
            for (QuestionPanel qPanel : questionPanels) {
                if (qPanel.getButtonGroup().getSelection() == null) {
                    return false;
                }
            }
            return true; // true if no nulls
        }

        public void actionPerformed(ActionEvent e) {
            //Listener for button clicks.
            JButton sourceButton = (JButton) e.getSource();

            // ------------------CHECK ANSWER BUTTON------------------
            // TODO: Validation: Ensure all questions are answered.
            if (sourceButton.equals(checkAnswerButton)) {
//                for (int i = 0; i< InductionSWController.getInstance().getQuestionnaire().getQuestions().size(); i++) {
////                    MultipleChoiceQuestion quest = InductionSWController.getInstance().getQuestionnaire().getQuestions().get(i);
//
//                    //output to the console
//
////                    System.out.println( "+++++++++++" + InductionSWController.getInstance().getQuestionnaire().getAnswers()[i] + "+++++++++++");
//
//
//                    // Collect the answer
//
//                }
//                for (int i = 0 ; i < InductionSWController.getInstance().getQuestionnaire().getQuestions().size(); i++) {
//                    System.out.println(InductionSWController.getInstance().getCurrentInductee().getQuizAnswers()[i]);
//                }
                if (validateQuestionnaire()) {


//                System.out.println("All questions answered: " + validateQuestionnaire());
                    int quizScore = InductionSWController.getInstance().calculateQuizScore
                            (InductionSWController.getInstance().getCurrentInductee());
                    int quizSize = InductionSWController.getInstance().getQuestionnaire().getQuestions().size();

                    final JDialog frame = new JDialog(outerClass, "Score", true);
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridLayout(0, 1));

                    JLabel scoreLabel = new JLabel("Your Score was: " + quizScore + " out of " + quizSize +
                            "(" + (quizScore * 100) / quizSize + "%)");

                    JLabel wrongLabel = new JLabel("Your wrong answers. ");
                    panel.add(scoreLabel);
                    panel.add(wrongLabel);
                    panel.add(new JSeparator(JSeparator.HORIZONTAL));

                    for (String wrongAnswer : InductionSWController.getInstance().getCurrentInductee().getWrongAnswers()) {
                    /* create a label with the wrong answer and add it to the panel
                     * wrongAnswer format: Wrong_answer|Correct_answer|Question_index
                     * Wrong answer = 0, correct answer = 1 question index = 2*/
                        String[] data = wrongAnswer.split("\\|");

                        panel.add(new JLabel("Question " + (Integer.parseInt(data[2]) + 1) + ":"));
                        panel.add(new JLabel("*************"));
                        panel.add(new JLabel("Your answer: " + data[0]));
                        panel.add(new JLabel("The Correct Answer was: " + data[1]));
                        panel.add(new JLabel("*************"));

                    }


                    frame.getContentPane().add(panel);
                    frame.pack();
                    frame.setVisible(true);

                    // Enable the userinput frame and hide this one
//                outerClass.setVisible(false);
//                parentFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog (null, "You need to answer all questions", "Error", JOptionPane.ERROR_MESSAGE);
                    
                } // else radiobutton group not selected
                }

            } // end else

        }// end actionperformed
//
    }

