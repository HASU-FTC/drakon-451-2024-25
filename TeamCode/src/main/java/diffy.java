import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class diffy extends SubsystemBase {
    private Servo leftdiffy;
    private Servo rightdiffy;

    public diffy(final HardwareMap hMap, final String leftname, final String rightname) {
        leftdiffy = hMap.get(Servo.class, leftname);
        rightdiffy = hMap.get(Servo.class, rightname);
    }

    public void diffypitch(double inputY) {
        double leftpos = (inputY + 1.0) / 2.0;
        double rightpos = 1 - ((inputY +1.0) / 2.0);

        leftpos = Math.max(0.0, Math.min(1.0, leftpos));
        rightpos = Math.max(0.0, Math.min(1.0, rightpos));

        leftdiffy.setPosition(leftpos);
        rightdiffy.setPosition(rightpos);
    }

    public void diffyroll(double inputx) {
        double leftpos = (inputx + 1.0) / 2.0;
        double rightpos = 1 - ((inputx +1.0) / 2.0);

        leftpos = Math.max(0.0, Math.min(1.0, leftpos));
        rightpos = Math.max(0.0, Math.min(1.0, rightpos));

        leftdiffy.setPosition(leftpos);
        rightdiffy.setPosition(rightpos);
    }

    public void diffyup(){
        leftdiffy.setPosition(1);
        rightdiffy.setPosition(0);
    }

    public void diffydown() {
        leftdiffy.setPosition(0);
        rightdiffy.setPosition(1);
    }

    public void diffyreset() {
        leftdiffy.setPosition(0.5);
        rightdiffy.setPosition(0.5);
    }

}
