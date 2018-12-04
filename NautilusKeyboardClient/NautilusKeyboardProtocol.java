import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NautilusKeyboardProtocol {

  public static String keyPressEventMessageToStringWithKeyEvent(KeyEvent keyEventInQuestion) {
      return String.format("%d~", keyEventInQuestion.getKeyCode());
  }

}
