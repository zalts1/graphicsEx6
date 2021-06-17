package edu.cg.models.Locomotive;

import edu.cg.models.Box;
import edu.cg.models.IRenderable;

import static org.lwjgl.opengl.GL21.*;

/***
 * A 3D locomotive front-body model renderer.
 */
public class FrontBody implements IRenderable {
    // The front body is composed of one box that represents the locomotive front body.
    private Box chassis = new Box(Specification.FRONT_BODY_WIDTH,
            Specification.FRONT_BODY_HEIGHT,
            Specification.FRONT_BODY_DEPTH);
    // The front body is composed of two front wheels.
    // Use a single wheel renderer along with affine transformations to render the two wheels.
    private Wheel wheel = new Wheel();
    // The front body is composed of a chimney model.
    private Chimney chimney = new Chimney();
    // The front body is composed of two front lights.
    // Use a single car light renderer along with affine transformations to render the two car lights.
    private CarLight carLight = new CarLight();

    @Override
    public void render() {
        glPushMatrix();
        Materials.setMaterialChassis();
        this.chassis.render();

        // render wheels
        glPushMatrix();
        glTranslated(
                0,
                -Specification.WHEEL_RADIUS,
                (Specification.WHEEL_RADIUS / 2));
        glPushMatrix();
        glTranslated(
                (Specification.FRONT_BODY_WIDTH / 2) - (Specification.WHEEL_WIDTH / 2),
                0,
                0);
        this.wheel.render();
        glPopMatrix();
        glTranslated(
                -(Specification.FRONT_BODY_WIDTH / 2) - (Specification.WHEEL_WIDTH / 2),
                0,
                0);
        this.wheel.render();
        glPopMatrix();

        // render chimney
        glPushMatrix();
        glTranslated(
                0,
                Specification.FRONT_BODY_HEIGHT + (Specification.WHEEL_RADIUS / 2),
                0);

        this.chimney.render();
        glPopMatrix();

        // render car lights
        glTranslated(
                0,
                0,
                Specification.FRONT_BODY_DEPTH / 2);

        glPushMatrix();
        glTranslated(
                Specification.BASE_UNIT,
                0,
                0);

        this.carLight.render();
        glPopMatrix();
        glTranslated(
                -Specification.BASE_UNIT,
                0,
                0);
        this.carLight.render();

        glPopMatrix();
    }

    @Override
    public void init() {

    }
}
