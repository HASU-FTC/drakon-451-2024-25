import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
@Config
public class diffy extends SubsystemBase {
    private Servo leftdiffy;
    private Servo rightdiffy;

    public diffy(final HardwareMap hMap, final String leftname, final String rightname) {
        leftdiffy = hMap.get(Servo.class, leftname);
        rightdiffy = hMap.get(Servo.class, rightname);
    }

    public void DiffyPitch(double inputY) {
        double leftpos = (inputY + 1.0) / 2.0;
        double rightpos = 1 - ((inputY +1.0) / 2.0);

        leftpos = Math.max(0.0, Math.min(1.0, leftpos));
        rightpos = Math.max(0.0, Math.min(1.0, rightpos));

        leftdiffy.setPosition(leftpos);
        rightdiffy.setPosition(rightpos);
    }

    public void DiffyRoll(double inputx) {
        double leftpos = (inputx + 1.0) / 2.0;
        double rightpos = 1 - ((inputx +1.0) / 2.0);

        leftpos = Math.max(0.0, Math.min(1.0, leftpos));
        rightpos = Math.max(0.0, Math.min(1.0, rightpos));

        leftdiffy.setPosition(leftpos);
        rightdiffy.setPosition(rightpos);
    }

    public void DiffyUp(){
        leftdiffy.setPosition(1);
        rightdiffy.setPosition(0);
    }

    public void DiffyDown() {
        leftdiffy.setPosition(0);
        rightdiffy.setPosition(1);
    }

    public void DiffyReset() {
        leftdiffy.setPosition(0.5);
        rightdiffy.setPosition(0.5);
    }

    public void DiffyVerticalDown() {
        leftdiffy.setPosition(0.5);
        rightdiffy.setPosition(0);
    }

    public void DiffyVerticalUp() {
        leftdiffy.setPosition(0);
        rightdiffy.setPosition(0.5);
    }

}
