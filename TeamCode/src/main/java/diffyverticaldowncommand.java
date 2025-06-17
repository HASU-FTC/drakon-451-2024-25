import com.seattlesolvers.solverslib.command.CommandBase;

public class diffyverticaldowncommand extends CommandBase{
    private final diffy diffy_servos;

    public diffyverticaldowncommand(diffy diffy_servos) {
        this.diffy_servos = diffy_servos;
        addRequirements(diffy_servos);
    }

    @Override
    public void initialize() {
        diffy_servos.DiffyVerticalDown();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
