import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;

import org.firstinspires.ftc.teamcode.R;

public class pivot extends SubsystemBase {
    PIDFController pivotpid = new PIDFController(0.05,0,0,1);

    DcMotorEx LeftPivot;
    DcMotorEx RightPivot;

    public pivot(final HardwareMap hardwareMap, final String leftname, final String rightname) {
        LeftPivot = hardwareMap.get(DcMotorEx.class,leftname);
        RightPivot = hardwareMap.get(DcMotorEx.class,rightname);

        LeftPivot.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        RightPivot.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        LeftPivot.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        RightPivot.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        RightPivot.setDirection(DcMotorEx.Direction.REVERSE);
    }

    public void up() {
        double lefttarget = pivotpid.calculate(697,LeftPivot.getCurrentPosition());
        double righttarget = pivotpid.calculate(697,RightPivot.getCurrentPosition());

        LeftPivot.setVelocity(lefttarget);
        RightPivot.setVelocity(righttarget);
    }

    public void middle() {
        double lefttarget = pivotpid.calculate(545,LeftPivot.getCurrentPosition());
        double righttarget = pivotpid.calculate(545,RightPivot.getCurrentPosition());

        LeftPivot.setVelocity(lefttarget);
        RightPivot.setVelocity(righttarget);
    }

    public void down() {
        double lefttarget = pivotpid.calculate(0,LeftPivot.getCurrentPosition());
        double righttarget = pivotpid.calculate(0,RightPivot.getCurrentPosition());

        LeftPivot.setVelocity(lefttarget);
        RightPivot.setVelocity(righttarget);


    }
}