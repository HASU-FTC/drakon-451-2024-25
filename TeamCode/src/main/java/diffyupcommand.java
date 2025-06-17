import com.seattlesolvers.solverslib.command.CommandBase;

public class diffyupcommand extends CommandBase {
    private final diffy diffy_servos;

    public diffyupcommand(diffy diffy_servos) {
        this.diffy_servos = diffy_servos;
        addRequirements(diffy_servos);
    }

    @Override
    public void initialize() {
        diffy_servos.DiffyUp();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
