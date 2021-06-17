package edu.cg.models.Locomotive;

import edu.cg.models.Box;
import edu.cg.models.IRenderable;

import static org.lwjgl.opengl.GL21.*;


/***
 * A 3D locomotive back body renderer. The back-body of the locomotive model is composed of a chassis, two back wheels,
 * , a roof, windows and a door.
 */
public class BackBody implements IRenderable {
    // The back body is composed of one box that represents the locomotive front body.
    private Box chassis = new Box(Specification.BACK_BODY_WIDTH, Specification.BACK_BODY_HEIGHT, Specification.BACK_BODY_DEPTH);
    // The back body is composed of two back wheels.
    private Wheel wheel = new Wheel();
    // The back body is composed of a roof that lies on-top of the locomotive chassis.
    private Roof roof = new Roof();
    // TODO (9): Define your window/door objects here. You are free to implement these models as you wish as long as you
    //           stick to the locomotive sketch.
    private Box door = new Box(Specification.DOOR_WIDTH, Specification.DOOR_HEIGHT, Specification.DOOR_DEPTH);
    private Box window = new Box(Specification.WINDOW_WIDTH, Specification.WINDOW_HEIGHT, Specification.WINDOW_DEPTH);
    private Box windshield = new Box(
            Specification.WINDSHIELD_WIDTH,
            Specification.WINDSHIELD_HEIGHT,
            Specification.WINDSHIELD_DEPTH);
    @Override
    public void render() {
        glPushMatrix();
        // TODO(8): render the back-body of the locomotive model. You need to combine the chassis, wheels and roof using
        //          affine transformations. In addition, you need to render the back-body windows and door. You can do
        //          that using simple QUADRATIC polygons (use GL_QUADS).
        Materials.setMaterialChassis();

        this.chassis.render();
        glPushMatrix();

        // render roof
        renderRoof();

        this.roof.render();
        glPopMatrix();

        renderWheels();

        renderWindshield();

        renderLeftSideWindows();

        renderRightSideWindows();

        renderDoor();
    }

    private void renderRoof() {
        glTranslated(
                0,
                Specification.BACK_BODY_HEIGHT / 2,
                -Specification.ROOF_DEPTH / 2);
    }

    private void renderWheels() {
        glTranslated(
                0,
                -Specification.BACK_BODY_HEIGHT / 2,
                -Specification.BACK_BODY_DEPTH / 4);

        glPushMatrix();
        glTranslated(
                (Specification.BACK_BODY_WIDTH / 2) - (Specification.WHEEL_WIDTH / 2),
                0,
                0);

        this.wheel.render();
        glPopMatrix();

        glTranslated(
                (-Specification.BACK_BODY_WIDTH / 2) - (Specification.WHEEL_WIDTH / 2),
                0,
                0);

        this.wheel.render();
        glPopMatrix();
    }

    private void renderLeftSideWindows() {
        Materials.setMaterialWindow();
        glPushMatrix();
        glRotated(90, 0, 1, 0);
        glTranslated(
                0,
                Specification.BASE_UNIT * 0.5,
                Specification.BACK_BODY_WIDTH / 2
        );

        glPushMatrix();
        glTranslated(
                Specification.BASE_UNIT * 2.75,
                0,
                0
        );
        this.window.render();
        glPopMatrix();

        glPushMatrix();
        glTranslated(
                Specification.BASE_UNIT * 0.25,
                0,
                0
        );
        this.window.render();
        glPopMatrix();

        glPushMatrix();
        glTranslated(
                -Specification.BASE_UNIT * 2.25,
                0,
                0
        );
        this.window.render();
        glPopMatrix();

        glPopMatrix();
    }

    private void renderWindshield() {
        // front
        Materials.setMaterialWindow();
        glPushMatrix();

        glTranslated(
                0,
                Specification.BASE_UNIT * 0.5,
                Specification.BACK_BODY_DEPTH / 2
        );
        this.windshield.render();
        glPopMatrix();

        // back
        glPushMatrix();
        glTranslated(
                0,
                Specification.BACK_BODY_HEIGHT / 8,
                -(Specification.BACK_BODY_DEPTH / 2) - Specification.EPS
        );

        this.windshield.render();
        glPopMatrix();
    }

    private void renderRightSideWindows() {
        glPushMatrix();
        glRotated(-90, 0, 1, 0);
        glTranslated(
                0,
                Specification.BASE_UNIT * 0.5,
                Specification.BACK_BODY_WIDTH / 2
        );

        glPushMatrix();
        glTranslated(
                -Specification.BASE_UNIT * 0.25,
                0,
                0
        );
        this.window.render();
        glPopMatrix();

        glPushMatrix();
        glTranslated(
                -Specification.BASE_UNIT * 2.75,
                0,
                0
        );
        this.window.render();
        glPopMatrix();

        glPopMatrix();
    }

    private void renderDoor() {
        Materials.setMaterialDoor();

        glPushMatrix();
        glRotated(-90, 0, 1, 0);
        glTranslated(
                0,
                Specification.BASE_UNIT * 0.5,
                Specification.BACK_BODY_WIDTH / 2
        );

        glPushMatrix();
        glTranslated(
                (Specification.BACK_BODY_WIDTH / 2) - Specification.EPS,
                -Specification.BASE_UNIT * 0.75,
                0
        );
        this.door.render();
        glPopMatrix();

        glPopMatrix();
    }


    @Override
    public void init() {

    }
}
