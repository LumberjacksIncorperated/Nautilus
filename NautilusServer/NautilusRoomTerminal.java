public class NautilusRoomTerminal {
  private String currentTerminalString = "";

  private double leftTopX;
  private double leftTopY;
  private double leftTopZ;
  private double rightBottomX;
  private double rightBottomY;
  private double rightBottomZ;

  public static String getNautilusFormatDescriptionForTerminalConfigurationString() {
    return "terminal_initilisation_string~position-x~position-y~position-z~rotation-x~rotation-y~rotation-z";
  }

  private NautilusRoomTerminal(String terminalConfigurationString) {
    String[] terminalConfigurationStringComponents = terminalConfigurationString.split("~");

  }

  public NautilusRoomTerminal newNautilusTerminalFromConfigurationString(String terminalConfigurationString) {
    return new NautilusRoomTerminal(terminalConfigurationString);
  }

  public void pressKeyOnTerminal(NautilusKey keyThatWasPressed) {
    this.currentTerminalString = this.currentTerminalString + keyThatWasPressed.getStringRepresentationOfKey();
  }

}
