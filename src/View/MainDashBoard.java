package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jamie on 07/04/16.
 */
public class MainDashBoard extends JFrame{

    public MainDashBoard() throws HeadlessException {
        /* DEFAULT CONSTRUCTOR */
        JPanel mainPanel = new JPanel();
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0,1));
        JButton beginInductionbtn = new JButton("Begin Induction");

        return centerPanel;
    }
}
