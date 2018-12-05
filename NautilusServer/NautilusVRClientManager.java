
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;



public class NautilusVRClientManager extends WebSocketServer {
  private NautilusRoomObserver nautilusRoomObserver;
  private NautilusWebSocketClientPool<NautilusVRClient> poolOfConnectedVRClients = new NautilusWebSocketClientPool<NautilusVRClient>();

  private class NautilusVRClient {
    public NautilusVRClient() {  }
  }

  public static NautilusKeyboardClientManager createNautilusVRClientManagerAndStartListeningForClientsOnPortWithNautilusRoomObserver(int vrPortNumber, NautilusRoomObserver nautilusRoomObserver) {
    NautilusVRClientManager newNautilusVRClientManager = new NautilusVRClientManager(vrPortNumber, nautilusRoomObserver);
    newNautilusVRClientManager.start();
  }

  private NautilusKeyboardClientManager(int vrPortNumber, NautilusRoomObserver nautilusRoomObserver) {
    super(new InetSocketAddress(vrPortNumber));
    this.nautilusRoomObserver = nautilusRoomObserver;
  }

  @Override
  public void onOpen(WebSocket vrClientWebSocketConnection, ClientHandshake handshake) {
    System.out.println(vrClientWebSocketConnection.getRemoteSocketAddress().getAddress().getHostAddress() + " connected as a VR client");
  }

  @Override
  public void onClose(WebSocket vrClientWebSocketConnection, int code, String reason, boolean remote) {
    System.out.println(vrClientWebSocketConnection.getRemoteSocketAddress().getAddress().getHostAddress() + " disconnected from being a VR client");
  }

  @Override
  public void onMessage(WebSocket vrClientWebSocketConnection, String message) {
    System.out.println("Got a trashy string message from VR client should not see this");
  }

  @Override
  public void onMessage(WebSocket vrClientWebSocketConnection, ByteBuffer message) {
    System.out.println("Got a trashy byte message from VR client should not see this");
  }

  @Override
  public void onError(WebSocket vrClientWebSocketConnection, Exception exception) {
    exception.printStackTrace();
  }

  @Override
  public void onStart() {
    System.out.println("Started listening for VR client connections");
    //setConnectionLostTimeout(0);
    //setConnectionLostTimeout(100);
  }

}
