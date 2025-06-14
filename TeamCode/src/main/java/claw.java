import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class claw extends SubsystemBase {
    private Servo clawservo;

    public claw(final HardwareMap hmap, final String clawservoname) {
        clawservo = hmap.get(Servo.class, clawservoname);
    }

    public void ClawOpen() {
        clawservo.setPosition(0);
    }

    public void ClawClose() {
        clawservo.setPosition(1);
    }
}
