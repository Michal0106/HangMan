import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.util.ArrayList;

public class Password extends JPanel {
    String passwordCoded;
    String passwordUncoded;
    Frame frame;

    public Password(String passwordUncoded,String passwordCoded, Frame frame){
        this.passwordCoded = passwordCoded;
        this.passwordUncoded = passwordUncoded;
        this.frame = frame;
    }
    public String updatePassword(String passwordCoded, String passwordUncoded, String letter) {
        char[] letters = passwordCoded.toCharArray();
        for (int i = 0,counter=0; i < passwordUncoded.length(); i++,counter++) {
            if (String.valueOf(passwordUncoded.charAt(i)).equals(letter)) {
                letters[i] = letter.charAt(0);
            }
            counter++;
        }
        String newCodedPassword = String.valueOf(letters);
        this.passwordCoded = newCodedPassword;
        frame.passwordLabel.setText(newCodedPassword);
        if (newCodedPassword.equals(passwordUncoded)) {

            frame.buttonPom.setUI(new MetalButtonUI() {
                protected Color getDisabledTextColor() {
                    return Color.RED;
                }
            });
            frame.buttonPom.setEnabled(false);

            int x = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "YOU HAVE WON!!!", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                this.frame.dispose();
                new PasswordField();
            } else System.exit(0);
        }
        return newCodedPassword;
    }
    public String getPasswordCoded() {
        return passwordCoded;
    }
}
