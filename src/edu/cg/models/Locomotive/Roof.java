package edu.cg.models.Locomotive;

import edu.cg.models.IRenderable;
import edu.cg.util.glu.Cylinder;
import edu.cg.util.glu.Disk;

import static org.lwjgl.opengl.GL11.*;

/***
 * A 3D roof model renderer.
 * The roof is modeled using a cylinder bounded by disks that undergo a non-uniform scaling.
 */
public class Roof implements IRenderable {

    @Override
    public void render() {
        glPushMatrix();
        // TODO(7): Render the locomotive back body roof
        Materials.setMaterialRoof();

        glScaled(
                Specification.ROOF_WIDTH / 2,
                Specification.ROOF_HEIGHT / 2,
                1);

        (new Cylinder()).draw(1, 1, (float) Specification.ROOF_DEPTH, 20, 1);
        glPushMatrix();
        glTranslated(0, 0, Specification.ROOF_DEPTH - Specification.EPS);

        // roof disk
        (new Disk()).draw(0, 1, 20, 1);
        glPopMatrix();
        glRotated(180, 0, 1, 0);
        (new Disk()).draw(0, 1, 20, 1);

        glPopMatrix();
    }

    @Override
    public void init() {

    }
}
