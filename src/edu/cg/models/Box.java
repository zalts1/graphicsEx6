package edu.cg.models;

import edu.cg.util.glu.Texture;

import static org.lwjgl.opengl.GL11.*;

/**
 * A simple 3D Box renderer. The box is centered at the origin in its local coordinate system.
 * The box can have different lengths along each of the main axes.
 */
public class Box implements IRenderable {
    private double rx, ry, rz;
    // TODO : If you wish to support textures this class must change.
    private Texture texBox = null;
    private boolean useTexture = false;
    // TODO: To support textures, you need to add a texture object.

    /**
     * Constructs an object that renders a 3D box centered at the origin, with lengths rx, ry and rz.
     * @param rx the length along the x-axis.
     * @param ry the length along the y-axis.
     * @param rz the length along the z-axis.
     */
    public Box(double rx, double ry, double rz) {
        this.rx = rx;
        this.ry = ry;
        this.rz = rz;
    }

    /**
     * Constructs an object that renders a 3D Square-box centered at the origin with length r.
     * @param r the edge length of the box.
     */
    public Box(double r) {
        this.rx = r;
        this.ry = r;
        this.rz = r;
    }

    /**
     * Constructs an object that renders a 3D Square-box centered at the origin with length r.
     * @param r the edge length of the box.
     * @param useTexture a boolean value indicating whether to render the box with textures.
     */
    public Box(double r, boolean useTexture) {
        this.rx = r;
        this.ry = r;
        this.rz = r;
        this.useTexture = useTexture;
    }

    @Override
    public void render() {
        // TODO : Copy your code from HW5.
        //  TODO : In order to support shading, you must add calls to glNormal() to your code.
        //  In addition, if you wish to support textures, you need to enable and bind the texture, and add calls to glTex().

        if(this.useTexture){
            texBox.bind();
        }

        // copy from hw5.
        glBegin(GL_QUADS);

        // X=-rx/2:
        glNormal3d(-1, 0, 0);
        glVertex3d(-rx / 2, -ry / 2, -rz / 2);
        glVertex3d(-rx / 2, -ry / 2, rz / 2);
        glVertex3d(-rx / 2, ry / 2, rz / 2);
        glVertex3d(-rx / 2, ry / 2, -rz / 2);

        // X=rx/2:
        glNormal3d(1, 0, 0);
        glVertex3d(rx / 2, -ry / 2, -rz / 2);
        glVertex3d(rx / 2, ry / 2, -rz / 2);
        glVertex3d(rx / 2, ry / 2, rz / 2);
        glVertex3d(rx / 2, -ry / 2, rz / 2);

        // Y=-ry/2
        glNormal3d(0, -1, 0);
        glVertex3d(rx / 2, -ry / 2, rz / 2);
        glVertex3d(-rx / 2, -ry / 2, rz / 2);
        glVertex3d(-rx / 2, -ry / 2, -rz / 2);
        glVertex3d(rx / 2, -ry / 2, -rz / 2);

        // Y=ry/2
        glNormal3d(0, 1, 0);
        glVertex3d(rx / 2, ry / 2, rz / 2);
        glVertex3d(rx / 2, ry / 2, -rz / 2);
        glVertex3d(-rx / 2, ry / 2, -rz / 2);
        glVertex3d(-rx / 2, ry / 2, rz / 2);

        // Z=-rz/2:
        glNormal3d(0, 0, -1);
        glVertex3d(-rx / 2, -ry / 2, -rz / 2);
        glVertex3d(-rx / 2, ry / 2, -rz / 2);
        glVertex3d(rx / 2, ry / 2, -rz / 2);
        glVertex3d(rx / 2, -ry / 2, -rz / 2);

        // Z=rz/2:
        glNormal3d(0, 0, 1);
        glVertex3d(-rx / 2, -ry / 2, rz / 2);
        glVertex3d(rx / 2, -ry / 2, rz / 2);
        glVertex3d(rx / 2, ry / 2, rz / 2);
        glVertex3d(-rx / 2, ry / 2, rz / 2);

        glEnd();
    }

    @Override
    public String toString() {
        return "Box";
    }

    @Override
    public void init() {
        // TODO: To support textures, include texture initialization here.

        if(useTexture){
            this.texBox = Texture.loadTexture("Textures/WoodBoxTexture.png");
        }
    }
}
