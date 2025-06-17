import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

public class samplescorecommand extends SequentialCommandGroup {
    private final pivot pivot_motors;
    private final lift lift_motors;
    private final diffy diffy_servos;
    private final claw claw_servo;

    public samplescorecommand(pivot pivot_motors, lift lift_motors, diffy diffy_servos, claw claw_servo) {
        this.claw_servo = claw_servo;
        this.diffy_servos = diffy_servos;
        this.lift_motors = lift_motors;
        this.pivot_motors = pivot_motors;

        addCommands(
                new clawclosecommand(claw_servo),
                new ParallelCommandGroup(
                        new diffyupcommand(diffy_servos),
                        new pivotupcommand(pivot_motors),
                        new lifthighcommand(lift_motors)),
                new WaitCommand(150),
                new clawopencommand(claw_servo)
        );
        addRequirements(
                claw_servo,
                diffy_servos,
                lift_motors,
                pivot_motors
        );
    }
}
