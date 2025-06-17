import com.seattlesolvers.solverslib.command.CommandBase;

public class diffydowncommand extends CommandBase{
    private final diffy diffy_servos;

    public diffydowncommand(diffy diffy_servos) {
        this.diffy_servos = diffy_servos;
        addRequirements(diffy_servos);
    }

    @Override
    public void initialize() {
        diffy_servos.DiffyDown();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
