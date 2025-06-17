import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

public class pivotbumpercommand extends CommandBase {
    private final pivot pivot_motors;
    private final GamepadEx gamepad2;
    private final ElapsedTime debounceTimer = new ElapsedTime();
    private final double debounceInterval = 0.2;
    private final int positionStep = 4;


    public pivotbumpercommand(pivot pivot_motors, GamepadEx gamepad2) {
        this.pivot_motors = pivot_motors;
        this.gamepad2 = gamepad2;
        addRequirements(pivot_motors);
    }
    @Override
    public void execute() {
       boolean rightBumper = gamepad2.getButton(GamepadKeys.Button.RIGHT_BUMPER);
       boolean leftBumper = gamepad2.getButton(GamepadKeys.Button.LEFT_BUMPER);

       if (debounceTimer.seconds() > debounceInterval) {
           if (rightBumper = true) {
               pivot_motors.IncrementPosition(positionStep);
               debounceTimer.reset();
           } else if (leftBumper = true) {
               pivot_motors.DecrementPosition(positionStep);
               debounceTimer.reset();
           }
       }
    }
}
