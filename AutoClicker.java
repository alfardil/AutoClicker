import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

public class AutoClicker {

    private static JFrame frame;
    private static JButton startButton;
    private static JButton stopButton;
    private static JTextField clickDelayField;
    private static JTextField holdDurationField;
    private static volatile boolean running = false;
    private static Timer clickTimer;
    private static Robot robot;

    public static void main(String[] args) {
        frame = new JFrame("Auto Clicker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLayout(new GridLayout(3, 2));

        frame.add(new JLabel("Click Delay (ms):"));
        clickDelayField = new JTextField("1500");
        frame.add(clickDelayField);

        frame.add(new JLabel("Hold Duration (ms):"));
        holdDurationField = new JTextField("100");
        frame.add(holdDurationField);

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        frame.add(startButton);
        frame.add(stopButton);

        startButton.addActionListener(new StartButtonListener());
        stopButton.addActionListener(new StopButtonListener());

        frame.setVisible(true);

        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class StartButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!running) {
                running = true;
                int delay = Integer.parseInt(clickDelayField.getText());
                clickTimer = new Timer(delay, new ClickerAction());
                clickTimer.start();
            }
        }
    }

    private static class StopButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            running = false;
            if (clickTimer != null) {
                clickTimer.stop();
            }
        }
    }

    private static class ClickerAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (running) {
                try {
                    // Get the current mouse location
                    PointerInfo pointerInfo = MouseInfo.getPointerInfo();
                    Point mouseLocation = pointerInfo.getLocation();

                    int holdDuration = Integer.parseInt(holdDurationField.getText());
                    int button = InputEvent.BUTTON1_DOWN_MASK;

                    // Move the mouse to the current location
                    robot.mouseMove(mouseLocation.x, mouseLocation.y);
                    // Press mouse button
                    robot.mousePress(button);
                    // Hold for the specified duration
                    Thread.sleep(holdDuration);
                    // Release mouse button
                    robot.mouseRelease(button);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
