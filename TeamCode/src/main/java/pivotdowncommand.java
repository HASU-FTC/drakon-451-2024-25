import com.seattlesolvers.solverslib.command.CommandBase;

public class pivotdowncommand extends CommandBase{
    private final pivot pivot_motors;

    public pivotdowncommand(pivot pivot_motors) {
        this.pivot_motors = pivot_motors;
    }

    @Override
    public void execute() {
        pivot_motors.down();
    }
}
