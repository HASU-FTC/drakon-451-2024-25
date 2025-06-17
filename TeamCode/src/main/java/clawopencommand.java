import com.seattlesolvers.solverslib.command.CommandBase;

public class clawopencommand extends CommandBase {
    private final claw claw_servo;

    public clawopencommand(claw claw_servo) {
        this.claw_servo = claw_servo;
        addRequirements(claw_servo);
    }

    @Override
    public void initialize() {
        claw_servo.ClawOpen();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
