package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LEDColour;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;

public class LEDSubsystem extends SubsystemBase {
    private AddressableLED ledStrip;
    private AddressableLEDBuffer ledBuffer;
    private int partyModeColours[] = { LEDColour.RED, LEDColour.RED, LEDColour.WHITE, 
                                       LEDColour.WHITE, LEDColour.BLUE, LEDColour.BLUE };
    private int tick = 0;

    public LEDSubsystem(int length, int channel) {
        ledStrip = new AddressableLED(channel);
        ledStrip.setLength(length);

        ledBuffer = new AddressableLEDBuffer(length);
        ledStrip.setData(ledBuffer);
        ledStrip.start();
    }

    public void setLEDColour(int index, int colour) {
        int red = (colour >> 16) & 0xFF;
        int green = (colour >> 8) & 0xFF;
        int blue = colour & 0xFF;
        ledBuffer.setRGB(index, red, green, blue);
    }

    public void setAllColour(int colour) {
        int red = (colour >> 16) & 0xFF;
        int green = (colour >> 8) & 0xFF;
        int blue = colour & 0xFF;
        int i;
        for(i=0;i<ledBuffer.getLength();i++) {
            ledBuffer.setRGB(i, red, green, blue);
        }
    }

    public void tickColours() {
        int i;
        for(i=0;i<ledBuffer.getLength();i++) {
            setLEDColour(i, partyModeColours[(i+tick) % (partyModeColours.length)]);
        }
        tick++;
    }

    public void partyMode() {
        double elapsedTime = 0.0; // Initialize elapsed time
        double delay = 0.015; 
        
        while (elapsedTime < 10) {
            tickColours();
            show();
            Timer.delay(delay); // Delay should only delay a single thread and not mess with the project
            elapsedTime += delay; // Increment elapsed time by the current delay
            delay += 0.001; // Increase delay by 10%
        }
        setAllColour(LEDColour.BLACK);
        show();
    }

    public void show() {
        // Update the LED strip with the latest data
        ledStrip.setData(ledBuffer);
    }
}
