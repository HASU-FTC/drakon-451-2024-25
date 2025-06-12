import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.hardware.motors.*;
public class lift extends SubsystemBase {

    PIDFController liftpid = new PIDFController(0.05,0,0,1);
    DcMotorEx LeftLift;
    DcMotorEx RightLift;

    public lift(final HardwareMap hardwareMap, final String leftname, final String rightname) {
        LeftLift = hardwareMap.get(DcMotorEx.class,leftname);
        RightLift = hardwareMap.get(DcMotorEx.class,rightname);

        LeftLift.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        RightLift.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        LeftLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        RightLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        RightLift.setDirection(DcMotorEx.Direction.REVERSE);
    }

    public void high() {
        liftpid.setSetPoint(2688);

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
        liftpid.setSetPoint(1500);

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
        liftpid.setSetPoint(0);

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
}
