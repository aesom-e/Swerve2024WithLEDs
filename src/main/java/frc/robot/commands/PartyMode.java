package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class PartyMode extends Command{

    public PartyMode() {
        addRequirements(RobotContainer.ledSubsystem);
    }

    @Override
    public void initialize() {
        RobotContainer.ledSubsystem.partyMode();
    }
}
