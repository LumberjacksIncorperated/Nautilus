import java.net.URI;
import java.net.URISyntaxException;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;



public class NautilusServerConnection implements NautilusKeyListener {
   private boolean isConnected = false;
   private WebSocketClient webSocketToServerConnection = null;

   private NautilusServerConnection(URI nautilusServerURI) {
     try {
       // cc = new ChatClient(new URI(uriField.getText()), area, ( Draft ) draft.getSelectedItem() );
       this.webSocketToServerConnection = new WebSocketClient( nautilusServerURI ) {

         @Override
         public void onMessage( String message ) {
           System.out.printf("Message from server: %s\n", message);
         }

         @Override
         public void onOpen( ServerHandshake handshake ) {
           System.out.printf("Handshake with server successful\n");
         }

         @Override
         public void onClose( int code, String reason, boolean remote ) {
           System.out.printf("Connection closed with server because: %s\n", reason);
         }

         @Override
         public void onError( Exception exception ) {
           exception.printStackTrace();
         }
       };
       this.webSocketToServerConnection.connect();
       this.isConnected = true;
     } catch ( URISyntaxException exception ) {
       exception.printStackTrace();
     }
   }

   public void listenToKeyPressEvent(KeyEvent keyEvent) {
     this.webSocketToServerConnection.send(NautilusKeyboardProtocol.keyPressEventMessageToStringWithKeyEvent(keyEvent));
   }

   public static NautilusServerConnection attemptToConnectToNautilusServerWithURI(URI nautilusServerURI) {
     return new NautilusServerConnection(nautilusServerURI);
   }

   public boolean isConnectedToServer() {
     return this.isConnected;
   }

   public NautilusKeyListener listenerToSendKeyPressesToNautilusServer() {
     return this;
   }
}
