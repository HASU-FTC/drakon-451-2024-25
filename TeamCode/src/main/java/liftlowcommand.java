import com.seattlesolvers.solverslib.command.CommandBase;

public class liftlowcommand extends CommandBase {
    private final lift linear_slides;

    public liftlowcommand(lift linear_slides) {
        this.linear_slides = linear_slides;
    }

    @Override
    public void execute() {
        linear_slides.low();
    }
}
