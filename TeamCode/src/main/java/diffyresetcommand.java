import com.seattlesolvers.solverslib.command.CommandBase;

public class diffyresetcommand extends CommandBase{
    private final diffy diffy_servos;

    public diffyresetcommand(diffy diffy_servos) {
        this.diffy_servos = diffy_servos;
        addRequirements(diffy_servos);
    }

    @Override
    public void initialize() {
        diffy_servos.DiffyReset();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

