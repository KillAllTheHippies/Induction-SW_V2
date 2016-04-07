package view;

import controller.InductionSWController;
import model.Inductee;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Jamie on 29/02/16.
 * <p>
 * Initial frame to be created when application is first started
 */
public class UserInputFrame extends JFrame {

    private JButton closeButton;
    private JButton startButton;
    private JButton addUserButton;
    private JButton takeQuizButton;
    private JButton displayUsersButton;
    private JButton BackEndButton;
    private JButton displayVideoButton;

    private JTextField tfName;
    private JTextField tfCompany;
    private JTextField tfJobTitle;
    private JTextField tfCarReg;
    private JTextField tfSupervisor;
    private JTextField tfCompetencies;


    public UserInputFrame(String title) throws HeadlessException {

        super(title);

        // Content of our JFrame
        JPanel mainPanel = new JPanel();

        // set border layout
        mainPanel.setLayout(new BorderLayout());

        // create the side and bottom panels and add them to the layout
        JPanel sidePanel = createSideButtonPanel();
        mainPanel.add(sidePanel, BorderLayout.EAST);
        JPanel bottomPanel = createBottomButtonPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // create the Inductee info input panel

        JPanel centerPanel = createCenterPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // add to the layout
        this.getContentPane().add(mainPanel);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    //Show image on frame
    public void showImage(BufferedImage img, String text, int x, int y) {
        JFrame frame0 = new JFrame();
        frame0.getContentPane().add(new JPanelOpenCV(img));
        // frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setTitle(text);
        frame0.setSize(img.getWidth(), img.getHeight() + 30);
        frame0.setLocation(x, y);
        frame0.setVisible(true);
    }

    private JPanel createCenterPanel() {

        JLabel lblName = new JLabel("Name:");
        JLabel lblSupervisor = new JLabel("Supervisor:");
        JLabel lblCompany = new JLabel("Company:");
        JLabel lblJobTitle = new JLabel("Job Title:");
        JLabel lblCompetencies = new JLabel("Competencies:");
        JLabel lblCarReg = new JLabel("Vehicle Registration:");


        tfName = new JTextField("", 20);
        tfSupervisor = new JTextField("", 20);
        tfCompany = new JTextField("", 20);
        tfJobTitle = new JTextField("", 20);
        tfCompetencies = new JTextField("", 20);
        tfCarReg = new JTextField("", 20);


        JPanel panel = new JPanel(new MigLayout("wrap 3"));
        panel.add(lblName, "cell 0 0");
        panel.add(tfName, "span 2");
        panel.add(lblCompany, "cell 0 1");
        panel.add(tfCompany, "span 2");
        panel.add(lblJobTitle, "cell 0 2");
        panel.add(tfJobTitle, "span 2");
        panel.add(lblSupervisor, "cell 0 3");
        panel.add(tfSupervisor, "span 2");
        panel.add(lblCarReg, "cell 0 4");
        panel.add(tfCarReg, "span 2");
        panel.add(lblCompetencies, "cell 0 5");
        panel.add(tfCompetencies, "span 2");

        return panel;
    }

    /**
     * This method creates the bottom JPanel, puts buttons on it and returns the fully constructed JPanel
     */
    private JPanel createBottomButtonPanel() {
        // Instantiate panel, button & listener, add listener to button,
        // add button to panel, return panel.
        JPanel buttonPanel = new JPanel();
        closeButton = new JButton("Close");
        startButton = new JButton("START");
        ButtonsActionListener buttonListener =
                new ButtonsActionListener(this);

        closeButton.addActionListener(buttonListener);
        startButton.addActionListener(buttonListener);

        buttonPanel.add(closeButton);
        buttonPanel.add(Box.createHorizontalStrut(5));
        buttonPanel.add(startButton);


        return buttonPanel;
    }

    private JPanel createSideButtonPanel() {
        // instantiate the buttons
        this.addUserButton = new JButton("Add User");
        this.displayUsersButton = new JButton("Display Users");
        this.BackEndButton = new JButton("Launch Backend");
        this.takeQuizButton = new JButton("Take Quiz");
        this.displayVideoButton = new JButton("Display Video");


        // Instantiate the listener for the buttons,
        // passing it in a reference to this class (TwitterFrame)
        // and assign it to the buttons
        ButtonsActionListener buttonListener = new ButtonsActionListener(this);
        addUserButton.addActionListener(buttonListener);
        displayUsersButton.addActionListener(buttonListener);
        takeQuizButton.addActionListener(buttonListener);
        BackEndButton.addActionListener(buttonListener);
        displayVideoButton.addActionListener(buttonListener);

        // Create panel, assign layout, add components.
        JPanel sideButtonPanel = new JPanel();
        sideButtonPanel.setLayout(new BoxLayout(sideButtonPanel, BoxLayout.Y_AXIS));
        sideButtonPanel.add(addUserButton);
        sideButtonPanel.add(Box.createVerticalStrut(5));
        sideButtonPanel.add(displayUsersButton);
        sideButtonPanel.add(Box.createVerticalStrut(5));
        sideButtonPanel.add(BackEndButton);
        sideButtonPanel.add(Box.createVerticalStrut(20));
        sideButtonPanel.add(displayVideoButton);
        sideButtonPanel.add(Box.createVerticalStrut(5));
        sideButtonPanel.add(takeQuizButton);


        return sideButtonPanel;
    }


    //Inner class implementation of ActionListener
    private class ButtonsActionListener implements ActionListener {
        //This is to allow this inner class to refer to its
        //containing class (i.e. UserInputFrame)
        private UserInputFrame outerClass;

        public ButtonsActionListener(UserInputFrame outerClass) {
            this.outerClass = outerClass;
        }

        public void actionPerformed(ActionEvent e) {
            //Listener for button clicks.
            JButton sourceButton = (JButton) e.getSource();

            // ------------------ADD USER BUTTON------------------
            if (sourceButton.equals(addUserButton)) {
                InductionSWController.getInstance().createInductee(tfName.getText(), tfCompany.getText(),
                        tfJobTitle.getText(), tfSupervisor.getText(), tfCarReg.getText(),
                        tfCompetencies.getText(), System.currentTimeMillis());
                System.out.println("user added");

                // ------------------CLOSE BUTTON------------------
            } else if (sourceButton.equals(closeButton)) {

                InductionSWController.getInstance().getPersistor().write
                        (InductionSWController.getInstance().getDataModel());
                dispose();

            }
            // ------------------START BUTTON------------------
            else if (sourceButton.equals(startButton)) {


                Inductee i = InductionSWController.getInstance().
                        createInductee(tfName.getText(), tfCompany.getText(), tfJobTitle.getText(),
                                tfSupervisor.getText(), tfCarReg.getText(), tfCompetencies.getText(),
                                System.currentTimeMillis());
                InductionSWController.getInstance().setCurrentInductee(i);

                InductionSWController.getInstance().launchVideo();
//                QuizFrame qf = new QuizFrame("Quiz", outerClass);
//                qf.setSize(400,300);
                // hide the frame
//                outerClass.setVisible(false);
//                i.getIndex()
            }
            // ------------------TAKE QUIZ BUTTON------------------
            else if (sourceButton.equals(takeQuizButton)) {
                QuizFrame qf = new QuizFrame("Quiz");

                qf.setSize(400, 300);
            }
            // ------------------Launch Backend BUTTON------------------
            else if (sourceButton.equals(BackEndButton)) {
                BackEndFrame bef = new BackEndFrame("Back End");
                bef.setSize(400, 300);
                bef.setVisible(true);
            }
            // ------------------DISPLAY VIDEO BUTTON------------------
            else if (sourceButton.equals(displayVideoButton)) {
                InductionSWController.getInstance().launchVideo();
            }
            // ------------------DISPLAY USERS BUTTON------------------
            else {
//                int count = 0;
//                for (Inductee i : InductionSWController.getInstance().getDataModel().getInductees()) {
//                    System.out.println(i.toString());
//
//                    count++;
//                    showImage(i.getPhoto(), i.getName(), count * 25, count * 10);
//                }

                DisplayInducteesFrame dif = new DisplayInducteesFrame("View Inductees");
                dif.setSize(400, 300);
                dif.setVisible(true);

            } // end else

        }// end actionperformed

    }
}
