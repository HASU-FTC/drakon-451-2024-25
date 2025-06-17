import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
public class samplepickcommand extends ParallelCommandGroup {
    private final pivot pivot_motors;
    private final lift lift_motors;
    private final diffy diffy_servos;
    private final claw claw_servo;
    public samplepickcommand(pivot pivot_motors, lift lift_motors, diffy diffy_servos, claw claw_servo) {
        this.claw_servo = claw_servo;
        this.diffy_servos = diffy_servos;
        this.lift_motors = lift_motors;
        this.pivot_motors = pivot_motors;

        addCommands(
                new liftmiddlecommand(lift_motors),
                new pivotdowncommand(pivot_motors),
                new diffydowncommand(diffy_servos),
                new clawopencommand(claw_servo)

        );
        addRequirements(claw_servo);
        addRequirements(diffy_servos);
        addRequirements(lift_motors);
        addRequirements(pivot_motors);
    }
}
