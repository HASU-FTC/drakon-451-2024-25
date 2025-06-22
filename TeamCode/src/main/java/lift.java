import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.hardware.motors.*;

@Config
public class lift extends SubsystemBase {

    PIDFController liftpid = new PIDFController(0.05,0,0,1);
    DcMotorEx LeftLift;
    DcMotorEx RightLift;

    private final int MIN_POSITION = 0;
    private final int MAX_POSITION = 2680;
    private int targetPosition = 0;

    public lift(final HardwareMap hardwareMap, final String leftname, final String rightname) {
        LeftLift = hardwareMap.get(DcMotorEx.class,leftname);
        RightLift = hardwareMap.get(DcMotorEx.class,rightname);

        LeftLift.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        RightLift.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        LeftLift.setTargetPosition(targetPosition);
        RightLift.setTargetPosition(targetPosition);

        LeftLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        RightLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        RightLift.setDirection(DcMotorEx.Direction.REVERSE);
    }

    public void high() {
        liftpid.setSetPoint(MAX_POSITION);

        while (!liftpid.atSetPoint()){
            double target = liftpid.calculate(
                    LeftLift.getCurrentPosition()
            );
            LeftLift.setVelocity(target);
            RightLift.setVelocity(target);
        }
        LeftLift.setPower(0);
        RightLift.setPower(0);
    }

    public void middle() {
        liftpid.setSetPoint(MAX_POSITION/2);

        while (!liftpid.atSetPoint()){
            double target = liftpid.calculate(
                    LeftLift.getCurrentPosition()
            );
            LeftLift.setVelocity(target);
            RightLift.setVelocity(target);
        }
        LeftLift.setPower(0);
        RightLift.setPower(0);
    }

    public void low() {
        liftpid.setSetPoint(MIN_POSITION);

        while (!liftpid.atSetPoint()){
            double target = liftpid.calculate(
                    LeftLift.getCurrentPosition()
            );
            LeftLift.setVelocity(target);
            RightLift.setVelocity(target);
        }
        LeftLift.setPower(0);
        RightLift.setPower(0);
    }

    public void IncrementPosition(int step) {
        targetPosition = Math.min(targetPosition + step, MAX_POSITION);
    }

    public void DecrementPosition(int step) {
        targetPosition = Math.max(targetPosition - step, MIN_POSITION);
    }

    @Override
    public void periodic() {
        int currentPosition = LeftLift.getCurrentPosition();
        double output = liftpid.calculate(currentPosition, targetPosition);
        LeftLift.setPower(output);
    }

    public int getTargetPosition() {
        return targetPosition;
    }
}
