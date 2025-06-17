import com.seattlesolvers.solverslib.command.CommandBase;

public class pivotmiddlecommand extends CommandBase{
    private final pivot pivot_motors;

    public pivotmiddlecommand(pivot pivot_motors) {
        this.pivot_motors = pivot_motors;
    }

    @Override
    public void execute() {
        pivot_motors.middle();
    }
}
