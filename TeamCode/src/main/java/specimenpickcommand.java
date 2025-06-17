import com.seattlesolvers.solverslib.command.ParallelCommandGroup;

public class specimenpickcommand extends ParallelCommandGroup {
    private final pivot pivot_motors;
    private final lift lift_motors;
    private final diffy diffy_servos;
    private final claw claw_servo;

    public specimenpickcommand (pivot pivot_motors, lift lift_motors, diffy diffy_servos, claw claw_servo) {
        this.claw_servo = claw_servo;
        this.diffy_servos = diffy_servos;
        this.lift_motors = lift_motors;
        this.pivot_motors = pivot_motors;

        addCommands(
                new pivotupcommand(pivot_motors),
                new liftmiddlecommand(lift_motors),
                new diffyupcommand(diffy_servos),
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
