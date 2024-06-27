import java.awt.*;
import java.awt.event.InputEvent;

public class AutoClicker {
    public static void main(String[] args) {
        try {
            while (true) {
                Robot r = new Robot();
                int button = InputEvent.BUTTON1_DOWN_MASK;
                System.out.print("Click");
                r.mousePress(button);
                Thread.sleep(400);
                r.mouseRelease(button);

                // each second is 1000 milliseconds
                Thread.sleep(2 * 1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}