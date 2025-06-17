import com.seattlesolvers.solverslib.command.CommandBase;

public class diffyverticalupcommand extends CommandBase{
    private final diffy diffy_servos;

    public diffyverticalupcommand(diffy diffy_servos) {
        this.diffy_servos = diffy_servos;
        addRequirements(diffy_servos);
    }

    @Override
    public void initialize() {
        diffy_servos.DiffyVerticalUp();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
