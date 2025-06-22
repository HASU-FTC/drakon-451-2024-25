import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;

import org.firstinspires.ftc.teamcode.R;
@Config
public class pivot extends SubsystemBase {
    PIDFController pivotpid = new PIDFController(0.05,0,0,1);

    DcMotorEx LeftPivot;
    DcMotorEx RightPivot;

    private final int MIN_POSITION = 0;
    private final int MAX_POSITION = 697;
    private int targetPosition = 0;


    public pivot(final HardwareMap hardwareMap, final String leftname, final String rightname) {
        LeftPivot = hardwareMap.get(DcMotorEx.class,leftname);
        RightPivot = hardwareMap.get(DcMotorEx.class,rightname);

        LeftPivot.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        RightPivot.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        LeftPivot.setTargetPosition(targetPosition);
        RightPivot.setTargetPosition(targetPosition);

        LeftPivot.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        RightPivot.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        RightPivot.setDirection(DcMotorEx.Direction.REVERSE);
    }

    public void up() {
        pivotpid.setSetPoint(MAX_POSITION);

        while (!pivotpid.atSetPoint()){
            double target = pivotpid.calculate(
                    LeftPivot.getCurrentPosition()
            );
            LeftPivot.setVelocity(target);
            RightPivot.setVelocity(target);
        }
        LeftPivot.setPower(0);
        RightPivot.setPower(0);
    }

    public void middle() {
        pivotpid.setSetPoint(MAX_POSITION/2);

        while (!pivotpid.atSetPoint()){
            double target = pivotpid.calculate(
                    LeftPivot.getCurrentPosition()
            );
            LeftPivot.setVelocity(target);
            RightPivot.setVelocity(target);
        }
        LeftPivot.setPower(0);
        RightPivot.setPower(0);
    }

    public void down() {
        pivotpid.setSetPoint(MIN_POSITION);

        while (!pivotpid.atSetPoint()){
            double target = pivotpid.calculate(
                    LeftPivot.getCurrentPosition()
            );
            LeftPivot.setVelocity(target);
            RightPivot.setVelocity(target);
        }
        LeftPivot.setPower(0);
        RightPivot.setPower(0);
    }

    public void IncrementPosition(int step) {
        targetPosition = Math.min(targetPosition + step, MAX_POSITION);
    }

    public void DecrementPosition(int step) {
        targetPosition = Math.max(targetPosition - step, MIN_POSITION);
    }

    @Override
    public void periodic() {
        int currentPosition = LeftPivot.getCurrentPosition();
        double output = pivotpid.calculate(currentPosition, targetPosition);
        LeftPivot.setPower(output);
    }

    public int getTargetPosition() {
        return targetPosition;
    }
}