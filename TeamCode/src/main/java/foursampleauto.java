import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;
@Autonomous(name = "4 Sample Auto")
public class foursampleauto extends CommandOpMode{
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;

    private final pivot pivot_motors = new pivot(hardwareMap, "leftpivot", "rightpivot");
    private final lift lift_motors = new lift(hardwareMap, "leftlift", "rightlift");
    private final diffy diffy_servos = new diffy(hardwareMap, "leftdiffyservo", "rightdiffyservo");
    private final claw claw_servo = new claw(hardwareMap, "clawservo");

    private int pathState;

    private final Pose startpose = new Pose(9.6,84.24827586206897,Math.toRadians(0));
    private final Pose preloadscorepose = new Pose(17.213793103448275,126.28965517241379,Math.toRadians(-45));


    //Sample 2
    private final Pose samplepick2pose = new Pose(24.49655172413793,120.99310344827586,Math.toRadians(0));
    private final Pose samplescore2pose = new Pose(17.71034482758621,126.28965517241379,Math.toRadians(-45));

    //Sample 3
    private final Pose samplepick3pose = new Pose(24.66206896551724,131.0896551724138,Math.toRadians(0));
    private final Pose samplescore3pose = new Pose(17.544827586206896,126.28965517241379,Math.toRadians(-45));

    //Sample 4
    private final Pose samplepick4pose = new Pose(45.186206896551724,123.14482758620689,Math.toRadians(90));
    private final Pose samplescore4pose = new Pose(17.379310344827587,126.62068965517241,Math.toRadians(-45));

    //Park
    private final Pose parkpose = new Pose(59.08965517241379, 96.16551724137932, Math.toRadians(-90));


    private Path preload, park;
    private PathChain samplepick2, samplescore2;
    private PathChain samplepick3, samplescore3;
    private PathChain samplepick4, samplescore4;

    public void buildPaths(){

        //scoring preload (sample 1)
        preload = new Path(new BezierLine(new Point(startpose), new Point(preloadscorepose)));
        preload.setLinearHeadingInterpolation(startpose.getHeading(), preloadscorepose.getHeading());


        //scoring sample 2
        samplepick2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(preloadscorepose), new Point(samplepick2pose)))
                .setLinearHeadingInterpolation(preloadscorepose.getHeading(), samplepick2pose.getHeading())
                .build();
        samplescore2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(samplepick2pose), new Point(samplescore2pose)))
                .setLinearHeadingInterpolation(samplepick2pose.getHeading(), samplescore2pose.getHeading())
                .build();

        //scoring sample 3
        samplepick3 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(samplescore2pose), new Point(samplepick3pose)))
                .setLinearHeadingInterpolation(samplescore2pose.getHeading(), samplepick3pose.getHeading())
                .build();
        samplescore2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(samplepick3pose), new Point(samplescore3pose)))
                .setLinearHeadingInterpolation(samplepick3pose.getHeading(), samplescore3pose.getHeading())
                .build();

        //scoring sample 4
        samplepick4 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(samplescore3pose), new Point(samplepick4pose)))
                .setLinearHeadingInterpolation(samplescore3pose.getHeading(), samplepick4pose.getHeading())
                .build();
        samplescore4 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(samplepick4pose), new Point(samplescore4pose)))
                .setLinearHeadingInterpolation(samplepick4pose.getHeading(), samplescore4pose.getHeading())
                .build();

        park = new Path(new BezierLine(new Point(samplescore4pose), new Point(parkpose)));
        park.setLinearHeadingInterpolation(samplescore4pose.getHeading(), parkpose.getHeading());
    }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(preload);
                setPathState(1);
                break;
            case 1:
                if (!follower.isBusy()) {
                    new samplescorecommand(pivot_motors, lift_motors, diffy_servos, claw_servo);
                    follower.followPath(samplepick2, true);
                    setPathState(2);
                }
                break;
            case 2:
                if (!follower.isBusy()) {
                    new samplepickcommand(pivot_motors, lift_motors, diffy_servos, claw_servo);
                    follower.followPath(samplescore2, true);
                    setPathState(3);
                }
                break;
            case 3:
                if (!follower.isBusy()) {
                    new samplescorecommand(pivot_motors, lift_motors, diffy_servos, claw_servo);
                    follower.followPath(samplepick3, true);
                    setPathState(4);
                }
                break;
            case 4:
                if (!follower.isBusy()) {
                    new samplepickcommand(pivot_motors, lift_motors, diffy_servos, claw_servo);
                    follower.followPath(samplescore3, true);
                    setPathState(5);
                }
                break;
            case 5:
                if (!follower.isBusy()) {
                    new samplescorecommand(pivot_motors, lift_motors, diffy_servos, claw_servo);
                    follower.followPath(samplepick4, true);
                    setPathState(6);
                }
                break;
            case 6:
                if (!follower.isBusy()) {
                    new samplepickcommand(pivot_motors, lift_motors, diffy_servos, claw_servo);
                    follower.followPath(samplescore4, true);
                    setPathState(7);
                }
                break;
            case 7:
                if (!follower.isBusy()) {
                    new samplescorecommand(pivot_motors, lift_motors, diffy_servos, claw_servo);
                    follower.followPath(park,true);
                    setPathState(8);
                }
                break;
            case 8:
                if (!follower.isBusy()) {
                    setPathState(-1);
                }
                break;
        }
    }
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    @Override
    public void initialize() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();

        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(startpose);
        buildPaths();
    }

    @Override
    public void initialize_loop() {}

    @Override
    public void run() {
        opmodeTimer.resetTimer();
        setPathState(0);
    }

    @Override
    public void end() {
    }
}
