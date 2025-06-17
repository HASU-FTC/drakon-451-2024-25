import com.seattlesolvers.solverslib.command.CommandBase;

public class lifthighcommand extends CommandBase {
    private final lift linear_slides;

    public lifthighcommand(lift linear_slides) {
        this.linear_slides = linear_slides;
    }

    @Override
    public void execute() {
        linear_slides.high();
    }
}
