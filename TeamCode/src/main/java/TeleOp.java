import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Teleop")
public class TeleOp extends CommandOpMode {
    private lift lift_motors;
    private pivot pivot_motors;
    private diffy diffyservos;
    private claw clawservo;


    @Override
    public void initialize() {

        GamepadEx gamepad1Ex = new GamepadEx(gamepad1);
        GamepadEx gamepad2Ex = new GamepadEx(gamepad2);

        lift_motors = new lift(hardwareMap, "leftlift", "rightlift");
        pivot_motors = new pivot(hardwareMap, "leftpivot", "rightpivot");
        diffyservos = new diffy(hardwareMap, "leftdiffyservo", "rightdiffyservo");
        clawservo = new claw(hardwareMap, "clawservo");

        register(lift_motors);
        register(pivot_motors);
        register(diffyservos);
        register(clawservo);


        MecanumDrive drive = new MecanumDrive(
                new Motor(hardwareMap, "leftFront", Motor.GoBILDA.RPM_312),
                new Motor(hardwareMap, "rightFront", Motor.GoBILDA.RPM_312),
                new Motor(hardwareMap, "leftRear", Motor.GoBILDA.RPM_312),
                new Motor(hardwareMap, "rightRear", Motor.GoBILDA.RPM_312)
        );

        schedule(
                new RunCommand(() -> drive.driveRobotCentric(
                        gamepad1Ex.getLeftX(),
                        gamepad1Ex.getLeftY(),
                        gamepad1Ex.getRightX(),
                        false
                ))
        );

        schedule(new lifttriggercommand(lift_motors, gamepad2Ex));
        schedule(new pivotbumpercommand(pivot_motors, gamepad2Ex));
        schedule(new diffyjoystickcommand(diffyservos, gamepad2Ex));

        gamepad2Ex.getGamepadButton(GamepadKeys.Button.TOUCHPAD)
                .whenPressed(new clawopencommand(clawservo));

        gamepad2Ex.getGamepadButton(GamepadKeys.Button.CROSS)
                .whenPressed(new specimenpickcommand(pivot_motors, lift_motors, diffyservos, clawservo));

        gamepad2Ex.getGamepadButton(GamepadKeys.Button.SQUARE)
                .whenPressed(new specimenscorecommand(pivot_motors, lift_motors, diffyservos, clawservo));


        gamepad2Ex.getGamepadButton(GamepadKeys.Button.CIRCLE)
                .whenPressed(new samplepickcommand(pivot_motors, lift_motors, diffyservos, clawservo));

        gamepad2Ex.getGamepadButton(GamepadKeys.Button.TRIANGLE)
                .whenPressed(new samplescorecommand(pivot_motors, lift_motors, diffyservos, clawservo));
    }
}

