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

import java.util.ArrayList;



@Autonomous(name = "Specimen Auto")
public class specimenauto extends CommandOpMode {
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;

    private final pivot pivot_motors = new pivot(hardwareMap, "leftpivot", "rightpivot");
    private final lift lift_motors = new lift(hardwareMap, "leftlift", "rightlift");
    private final diffy diffy_servos = new diffy(hardwareMap, "leftdiffyservo", "rightdiffyservo");
    private final claw claw_servo = new claw(hardwareMap, "clawservo");

    private int pathState;

    private final Pose startpose = new Pose(9.26896551724138,63.55862068965517, Math.toRadians(0));
    private final Pose preloadscorepose = new Pose(37.90344827586207,67.36551724137931, Math.toRadians(0));

    //specimen push ready
    private final Pose specimenpushreadypose = new Pose(69.51724137931035,29.462068965517247, Math.toRadians(0));
    private final Pose speccontrol1 = new Pose(0,0);
    private final Pose speccontrol2 = new Pose(0.993103448275862,44.358620689655176);

    //specimen push poses
    private final Pose push1pose = new Pose(11.255172413793103,19.696551724137926,Math.toRadians(0));
    private final Pose return1pose = new Pose(64.71724137931034,14.896551724137929,Math.toRadians(0));
    private final Pose returncontrol1pose = new Pose(76.13793103448276,30.620689655172416);

    private final Pose push2pose = new Pose(10.593103448275862,15.062068965517234,Math.toRadians(0));
    private final Pose return2pose = new Pose(63.724137931034484,8.93793103448276,Math.toRadians(0));
    private final Pose returncontrol2pose = new Pose(76.63448275862069,26.97931034482759);

    private final Pose push3pose = new Pose(9.931034482758621,9.103448275862066,Math.toRadians(0));
    private final Pose return3pose = new Pose(10.096551724137932,35.089655172413785,Math.toRadians(0));
    private final Pose returncontrol3pose = new Pose(20.02758620689655,21.51724137931035);

    //spec scoring positions
    private final Pose specscore1pose = new Pose(37.73793103448276,80.44137931034483,Math.toRadians(0));
    private final Pose specreturn1pose = new Pose(9.931034482758621,35.25517241379311,Math.toRadians(0));

    private final Pose specscore2pose = new Pose(37.73793103448276,77.62758620689654,Math.toRadians(0));
    private final Pose specreturn2pose = new Pose(9.931034482758621,35.25517241379311,Math.toRadians(0));

    private final Pose specscore3pose = new Pose(37.57241379310345,74.64827586206897,Math.toRadians(0));
    private final Pose specreturn3pose = new Pose(9.931034482758621,35.25517241379311,Math.toRadians(0));

    private final Pose specscore4pose = new Pose(37.73793103448276,71.50344827586207,Math.toRadians(0));
    private final Pose specreturn4pose = new Pose(9.6,35.25517241379311,Math.toRadians(0));


    private Path preloadscore, pushreadypath;
    private PathChain push1, return1, push2, return2, push3, return3;
    private PathChain score1, specreturn1;
    private PathChain score2, specreturn2;
    private PathChain score3, specreturn3;
    private PathChain score4, specreturn4;

    public void buildPaths() {
        //preload score path
        preloadscore = new Path(new BezierLine(new Point(startpose), new Point(preloadscorepose)));
        preloadscore.setConstantHeadingInterpolation(preloadscorepose.getHeading());

        //pre-specimen push path (pushes samples into human player zone)
        pushreadypath = new Path(new BezierCurve(new Point(preloadscorepose), new Point(speccontrol1), new Point(speccontrol2), new Point(specimenpushreadypose)));
        pushreadypath.setConstantHeadingInterpolation(specimenpushreadypose.getHeading());

        //sample pushing pathchain

        push1 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specimenpushreadypose), new Point(push1pose)))
                .setConstantHeadingInterpolation(push1pose.getHeading())
                .build();

        return1 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(push1pose), new Point(returncontrol1pose), new Point(return1pose)))
                .setConstantHeadingInterpolation(return1pose.getHeading())
                .build();

        push2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(return1pose), new Point(push2pose)))
                .setConstantHeadingInterpolation(push2pose.getHeading())
                .build();

        return2 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(push2pose), new Point(returncontrol2pose), new Point(return2pose)))
                .setConstantHeadingInterpolation(return2pose.getHeading())
                .build();

        push3 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(return2pose), new Point(push3pose)))
                .setConstantHeadingInterpolation(push3pose.getHeading())
                .build();


        return3 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(push3pose), new Point(returncontrol3pose), new Point(return3pose)))
                .setConstantHeadingInterpolation(return3pose.getHeading())
                .build();

        //specimen scoring path chain
        //scores 4 specimens (5 specimens in total)

        score1 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(return3pose), new Point(specscore1pose)))
                .setConstantHeadingInterpolation(specscore1pose.getHeading())
                .build();

        specreturn1 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specscore1pose), new Point(specreturn1pose)))
                .setConstantHeadingInterpolation(specreturn1pose.getHeading())
                .build();

        score2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specreturn1pose), new Point(specscore2pose)))
                .setConstantHeadingInterpolation(specscore2pose.getHeading())
                .build();

        specreturn2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specscore2pose), new Point(specreturn2pose)))
                .setConstantHeadingInterpolation(specreturn2pose.getHeading())
                .build();

        score3 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specreturn2pose), new Point(specscore3pose)))
                .setConstantHeadingInterpolation(specscore3pose.getHeading())
                .build();

        specreturn3 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specscore3pose), new Point(specreturn3pose)))
                .setConstantHeadingInterpolation(specreturn3pose.getHeading())
                .build();

        score4 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specreturn3pose), new Point(specscore4pose)))
                .setConstantHeadingInterpolation(specscore4pose.getHeading())
                .build();

        specreturn4 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specscore4pose), new Point(specreturn4pose)))
                .setConstantHeadingInterpolation(specreturn4pose.getHeading())
                .build();
    }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(preloadscore);
                setPathState(1);
                break;
            case 1:
                if (!follower.isBusy()) {
                    new specimenscorecommand(pivot_motors, lift_motors, diffy_servos, claw_servo);
                    follower.followPath(pushreadypath, true);
                    setPathState(2);
                }
                break;
            case 2:
                if (!follower.isBusy()) {
                    follower.followPath(push1, true);
                    setPathState(3);
                }
                break;
            case 3:
                if (!follower.isBusy()) {
                    follower.followPath(return1, true);
                    setPathState(4);
                }
                break;
            case 4:
                if (!follower.isBusy()) {
                    follower.followPath(push2, true);
                    setPathState(5);
                }
                break;
            case 5:
                if (!follower.isBusy()) {
                    follower.followPath(return2, true);
                    setPathState(6);
                }
                break;
            case 6:
                if (!follower.isBusy()) {
                    follower.followPath(push3, true);
                    setPathState(7);
                }
                break;
            case 7:
                if (!follower.isBusy()) {
                    new samplepickcommand(pivot_motors,lift_motors,diffy_servos,claw_servo);
                    follower.followPath(return3, true);
                    setPathState(8);
                }
                break;

            case 8:
                if (!follower.isBusy()) {
                    new specimenscorecommand(pivot_motors,lift_motors,diffy_servos,claw_servo);
                    follower.followPath(score1, true);
                    setPathState(9);
                }
                break;
            case 9:
                if (!follower.isBusy()) {
                    new samplepickcommand(pivot_motors,lift_motors,diffy_servos,claw_servo);
                    follower.followPath(specreturn1, true);
                    setPathState(10);
                }
                break;
            case 10:
                if (!follower.isBusy()) {
                    new samplescorecommand(pivot_motors,lift_motors,diffy_servos,claw_servo);
                    follower.followPath(score2, true);
                    setPathState(11);
                }
                break;
            case 11:
                if (!follower.isBusy()) {
                    new samplepickcommand(pivot_motors,lift_motors,diffy_servos,claw_servo);
                    follower.followPath(specreturn2, true);
                    setPathState(12);
                }
                break;
            case 12:
                if (!follower.isBusy()) {
                    new samplescorecommand(pivot_motors,lift_motors,diffy_servos,claw_servo);
                    follower.followPath(score3, true);
                    setPathState(13);
                }
                break;
            case 13:
                if (!follower.isBusy()) {
                    new samplepickcommand(pivot_motors,lift_motors,diffy_servos,claw_servo);
                    follower.followPath(specreturn3, true);
                    setPathState(14);
                }
                break;
            case 14:
                if (!follower.isBusy()) {
                    new samplescorecommand(pivot_motors,lift_motors,diffy_servos,claw_servo);
                    follower.followPath(score4, true);
                    setPathState(15);
                }
                break;
            case 15:
                if (!follower.isBusy()) {
                    new samplepickcommand(pivot_motors,lift_motors,diffy_servos,claw_servo);
                    follower.followPath(specreturn4, true);
                    setPathState(16);
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
