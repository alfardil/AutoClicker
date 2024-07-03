import java.awt.*;
import java.awt.event.InputEvent;

public class AutoClicker {
    public static void main(String[] args) {
        try {
            while (true) {
                Robot r = new Robot();
                int button = InputEvent.BUTTON1_DOWN_MASK;
                System.out.println("Click");

                // presses down for some duration
                r.mousePress(button);
                Thread.sleep(100);
                // then lets go
                r.mouseRelease(button);
                // how long between each click. 1 second is 1000 milliseconds
                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}