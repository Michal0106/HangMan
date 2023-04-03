import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PasswordField extends JFrame implements ActionListener{

    private JButton submitButton;
    private JPasswordField passwordField;
    public PasswordField(){
        submitButton = new JButton("Start Game!");
        submitButton.addActionListener(this);

        JPanel passwordPanel = new JPanel(new FlowLayout());


        JLabel labelPassword = new JLabel("Type in the password: ");
        passwordField = new JPasswordField(20);

        passwordPanel.add(labelPassword);passwordPanel.add(passwordField);

        this.setTitle("Password Field");
        this.add(passwordPanel);this.add(submitButton);
        this.setLayout(new GridLayout(3,1));
        this.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkValidPassword(passwordField.getText())) {
            if (!passwordField.getText().isEmpty()) {
                this.dispose();
                new Frame(passwordField.getText().toUpperCase());
            }
            else JOptionPane.showMessageDialog(this, "You did not type in any password", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Password is too long or you typed prohibited characters (1-10, !, *, space)etc.", "ERROR", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
    }
    public boolean checkValidPassword(String password){
        for(char letter : password.toCharArray()){
            if(letter != ' ' && (letter<'A' || letter > 'z' || password.length()>30)) return false;
        }
        return true;
    }
}
