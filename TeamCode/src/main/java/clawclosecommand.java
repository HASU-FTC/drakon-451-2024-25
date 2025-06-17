import com.seattlesolvers.solverslib.command.CommandBase;

public class clawclosecommand extends CommandBase{
    private final claw claw_servo;

    public clawclosecommand(claw claw_servo) {
        this.claw_servo = claw_servo;
        addRequirements(claw_servo);
    }

    @Override
    public void initialize() {
        claw_servo.ClawClose();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
