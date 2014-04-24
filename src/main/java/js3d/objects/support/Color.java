package js3d.objects.support;

@SuppressWarnings("unused")
public class Color {
    public double r, g, b;

    public Color() {}

    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void add(Color color) {
        r += color.r;
        g += color.g;
        b += color.b;
    }

    public void mul(Color c) {
        r = r * c.r;
        g = g * c.g;
        b = b * c.b;
    }

    public Color clump() {
        Color c = new Color();

        if (r > 1) {
            c.r = 1;
        } else if (r < 0) {
            c.r = 0;
        } else {
            c.r = r;
        }

        if (g > 1) {
            c.g = 1;
        } else if (g < 0) {
            c.g = 0;
        } else {
            c.g = g;
        }

        if (b > 1) {
            c.b = 1;
        } else if (b < 0) {
            c.b = 0;
        } else {
            c.b = b;
        }

        return c;
    }

    public java.awt.Color awtColor() {
        return new java.awt.Color((int)(255 * r), (int)(255 * g), (int)(255 * b));
    }
}
