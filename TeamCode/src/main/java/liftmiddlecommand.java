import com.seattlesolvers.solverslib.command.CommandBase;

public class liftmiddlecommand extends CommandBase {
    private final lift linear_slides;

    public liftmiddlecommand(lift linear_slides) {
        this.linear_slides = linear_slides;
    }

    @Override
    public void execute() {
        linear_slides.middle();
    }
}
