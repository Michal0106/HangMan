import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Frame extends JFrame implements ActionListener{
    DrawHangMan hangMan;
    Password passwordClass;
    JButton submitButton;
    String passwordCoded;
    JButton buttonPom;
    String passwordUncoded;
    JPanel passwordPanel;
    JLabel passwordLabel;
    JButton[][] letterButtons;
    public Frame(String passwordUncoded){

        hangMan = new DrawHangMan(this);

        passwordCoded = "";
        this.passwordUncoded = passwordUncoded;

        passwordPanel = new JPanel();

        passwordCoded = setPasswordCoded(passwordCoded,passwordUncoded);

        passwordClass = new Password(passwordUncoded,passwordCoded,this);

        passwordLabel = new JLabel(passwordCoded);
        passwordLabel.setSize(1000,100);
//        passwordLabel.setLayout(new FlowLayout());
        passwordLabel.setVerticalTextPosition(JLabel.CENTER);
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 40));

        passwordPanel.add(passwordLabel);

        submitButton = new JButton("submit");
        submitButton.setBackground(new Color(50,130,200));
        submitButton.addActionListener(this);

        String[][] letters = new String[][]{{"Q","W","E","R","T","Y","U","I","O","P"},{"A","S","D","F","G","H","J","K","L"},{"Z","X","C","V","B","N","M"}};

        JPanel[] panels = new JPanel[]{new JPanel(), new JPanel(), new JPanel()};
        JPanel finalPanel = new JPanel(new GridLayout(5,1));
        letterButtons = new JButton[letters.length][];

        for(int i = 0;i< letters.length;i++){
            letterButtons[i] = new JButton[letters[i].length];
            for(int j = 0;j<letters[i].length;j++){
                JButton button = new JButton(letters[i][j]);
                button.addActionListener(this);
                letterButtons[i][j] = button;
            }
        }
        for(int i = 0;i< letterButtons.length;i++){
            for(JButton button : letterButtons[i]) {
                button.setSize(40,20);
                panels[i].add(button);
            }
            finalPanel.add(panels[i]);
        }
        finalPanel.add(submitButton);
        this.add(passwordClass,BorderLayout.CENTER);
        this.add(passwordPanel,BorderLayout.NORTH);
        this.add(hangMan,BorderLayout.WEST);
        this.add(finalPanel,BorderLayout.SOUTH);
        this.setTitle("HANGMAN by Michal Mroz");
        this.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == submitButton) {
                if (!passwordUncoded.contains(buttonPom.getText())) {

                    hangMan.incrementGuesses();
                    if (hangMan.getGuesses() == 6) {
                        int x = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "YOU HAVE LOST", JOptionPane.YES_NO_OPTION);
                        if (x == JOptionPane.YES_OPTION) {
                            this.dispose();
                            new PasswordField();
                        } else System.exit(0);
                    }
                    buttonPom.setUI(new MetalButtonUI() {
                        protected Color getDisabledTextColor() {
                            return Color.RED;
                        }
                    });
                    buttonPom.setEnabled(false);
                    buttonPom = null;
                } else {
                    passwordCoded = passwordClass.updatePassword(passwordClass.getPasswordCoded(), passwordUncoded, buttonPom.getText());
                    buttonPom.setUI(new MetalButtonUI() {
                        protected Color getDisabledTextColor() {
                            return Color.RED;
                        }
                    });
                    buttonPom.setEnabled(false);
                }

            } else {
                for (JButton[] buttons : letterButtons) {
                    for (JButton button : buttons)
                        if (e.getSource() == button) buttonPom = button;
                }
            }
        } catch (NullPointerException nullPointerException){
            JOptionPane.showMessageDialog(this, "You need to pick the letter", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String setPasswordCoded(String passwordCoded, String passwordUncoded){
        for(char letter : passwordUncoded.toCharArray()){
            if(letter == ' ') passwordCoded += ' ';
            else passwordCoded += "-";
        }
        return passwordCoded;
    }

    public JButton getButtonPom() {
        return buttonPom;
    }
}
