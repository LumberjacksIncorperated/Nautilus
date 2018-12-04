/*
    PROJECT
    -------
    Project Nautilus

    DESCRIPTION
    -----------
    Send keypresses to a server

    AUTHOR
    ------
    Lumberjacks Incorperated (2018)
*/
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;


class NautilusKeyboardClient extends JPanel implements KeyListener {
    private NautilusKeyListener nautilusKeyListener = null;

    public NautilusKeyboardClient() {
        this.setPreferredSize(new Dimension(500, 500));
        addKeyListener(this);
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public void setNautilusKeyListener(NautilusKeyListener keyListenerInQuestion) {
      this.nautilusKeyListener = keyListenerInQuestion;
    }

    public void keyPressed(KeyEvent keyEvent) {
      if (this.nautilusKeyListener != null) {
        this.nautilusKeyListener.listenToKeyPressEvent(keyEvent);
      }
    }
    public void keyReleased(KeyEvent keyEvent) {

    }
    public void keyTyped(KeyEvent keyEvent) { }

    public static void main(String[] commandLineArguments) {
      boolean hasParameterForURIConnectionToServer = commandLineArguments.length > 1;
      if (hasParameterForURIConnectionToServer) {
        NautilusKeyboardClient.setupAndRunNautilusKeyboardClientConnectingToNautilusServerWithURI(commandLineArguments[1]);
      } else {
        System.out.println("Usage: Nautilus <URI for Nautilus Server>");
      }
    }

    private static void showWindowAndAddPanelAsMainDisplay(JPanel panelInQuestion) {
      JFrame mainFrameContainingNautilusKeyboardClient = new JFrame();
      mainFrameContainingNautilusKeyboardClient.getContentPane().add(panelInQuestion);
      mainFrameContainingNautilusKeyboardClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrameContainingNautilusKeyboardClient.pack();
      mainFrameContainingNautilusKeyboardClient.setVisible(true);
    }

    private static void setupAndRunNautilusKeyboardClientConnectingToNautilusServerWithURI(URI nautilusServerURI) {
      NautilusServerConnection nautilusServerConnection = NautilusServerConnection.attemptToConnectToNautilusServerWithURI(nautilusServerURI);
      if (nautilusServerConnection.isConnectedToServer()) {
        NautilusKeyboardClient neutilusKeyboardClient = new NautilusKeyboardClient();
        neutilusKeyboardClient.setNautilusKeyListener(nautilusServerConnection.listenerToSendKeyPressesToNautilusServer());
        NautilusKeyboardClient.showWindowAndAddPanelAsMainDisplay(neutilusKeyboardClient);
      } else {
        System.out.printf("Failed to connect to Nautilus at: %s\n", nautilusServerURI.toString());
      }
    }
}
