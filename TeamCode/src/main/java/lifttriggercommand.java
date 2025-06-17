import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

public class lifttriggercommand extends CommandBase {
    private final lift linear_slides;
    private final GamepadEx gamepad2;

    private final ElapsedTime debounceTimer = new ElapsedTime();
    private final double debounceInterval = 0.2;
    private final int positionStep = 20;

    public lifttriggercommand(lift linear_slides, GamepadEx gamepad) {
        this.linear_slides = linear_slides;
        this.gamepad2 = gamepad;
        addRequirements(linear_slides);
    }

    @Override
    public void execute() {
        double rightTrigger = gamepad2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
        double leftTrigger = gamepad2.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);

        if (debounceTimer.seconds() > debounceInterval) {
            if (rightTrigger > 0.5) {
                linear_slides.IncrementPosition(positionStep);
                debounceTimer.reset();
            } else if (leftTrigger > 0.5) {
                linear_slides.DecrementPosition(positionStep);
                debounceTimer.reset();
            }
        }
    }

}