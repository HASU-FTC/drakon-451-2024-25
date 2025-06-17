import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

public class diffyjoystickcommand extends CommandBase {
    private final diffy diffywrist;
    private final GamepadEx gamepad2;

    public diffyjoystickcommand(diffy diffywrist, GamepadEx gamepad2) {
        this.diffywrist = diffywrist;
        this.gamepad2 = gamepad2;
        addRequirements(diffywrist);
    }
    @Override
    public void execute() {
        double pitch = -gamepad2.getLeftY();
        double roll = gamepad2.getLeftX();

        diffywrist.DiffyPitch(pitch);
        diffywrist.DiffyRoll(roll);
    }

    @Override
    public void end(boolean interrupted) {
        diffywrist.DiffyPitch(0);
        diffywrist.DiffyRoll(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
