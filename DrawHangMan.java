import javax.swing.*;
import java.awt.*;

public class DrawHangMan extends JPanel {
    private int guesses;
    Frame frame;

    public DrawHangMan(Frame frame) {
        this.frame = frame;
        guesses = 0;
        setPreferredSize(new Dimension(400, 400));
    }

    public void incrementGuesses() {
        guesses++;
        repaint();
    }

    public int getGuesses() {
        return guesses;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(50, 350, 300, 30);
        g.fillRect(100, 100, 10, 250);
        g.fillRect(100, 100, 100, 10);

        if (guesses >= 1) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(4));
            g2.fillOval(188,120,8,8);
            g2.fillOval(207,120,8,8);
            g2.drawArc(180,140,30,8,10,80);
            g2.drawOval(175, 110, 50, 50);
        }

        if (guesses >= 2) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(4));
            g2.drawLine(200, 160, 200, 250);
        }

        if (guesses >= 3) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(4));
            g2.drawLine(200, 175, 175, 200);
        }

        if (guesses >= 4) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(4));
            g2.drawLine(200, 175, 225, 200);
        }

        if (guesses >= 5) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(4));
            g2.drawLine(200, 250, 175, 300);
        }

        if (guesses >= 6) {
            g.drawLine(200, 250, 225, 300);
        }
    }
}
