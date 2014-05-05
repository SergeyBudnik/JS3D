package js3d.objects.support;

@SuppressWarnings("unused")
public class Material {
    public static final Material BRASS = new Material(
            new Color(0.329412, 0.223529, 0.027451),
            new Color(0.780392, 0.568627, 0.113725),
            new Color(0.992157, 0.941176, 0.807843), 27.8974);

    public static final Material BRONZE = new Material(
            new Color(0.212500, 0.127500, 0.054000),
            new Color(0.714000, 0.428400, 0.181440),
            new Color(0.393548, 0.271906, 0.166721), 25.6000);

    public static final Material POLISHED_BRONZE = new Material(
            new Color(0.250000, 0.148000, 0.064750),
            new Color(0.400000, 0.236800, 0.103600),
            new Color(0.774597, 0.458561, 0.200621), 76.8000);

    public static final Material CHROME = new Material(
            new Color(0.250000, 0.250000, 0.250000),
            new Color(0.400000, 0.400000, 0.400000),
            new Color(0.774597, 0.774597, 0.774597), 76.8000);

    public static final Material COPPER = new Material(
            new Color(0.191250, 0.073500, 0.022500),
            new Color(0.703800, 0.270480, 0.082800),
            new Color(0.256777, 0.137622, 0.086014), 12.8000);

    public static final Material POLISHED_COPPER = new Material(
            new Color(0.229500, 0.088250, 0.027500),
            new Color(0.550800, 0.211800, 0.066000),
            new Color(0.580594, 0.223257, 0.0695701), 51.2000);

    public static final Material GOLD = new Material(
            new Color(0.247250, 0.199500, 0.074500),
            new Color(0.751640, 0.606480, 0.226480),
            new Color(0.628281, 0.555802, 0.366065), 51.2000);

    public static final Material POLISHED_GOLD = new Material(
            new Color(0.247250, 0.224500, 0.064500),
            new Color(0.346150, 0.314300, 0.090300),
            new Color(0.797357, 0.723991, 0.208006), 83.2000);

    public static final Material RUBY = new Material(
            new Color(0.174500, 0.011750, 0.011750),
            new Color(0.614240, 0.041360, 0.041360),
            new Color(0.727811, 0.626959, 0.626959), 76.8
    );

    public static final Material GRAY_MIRROR = new Material(
            new Color(0.0, 0.0, 0.0),
            new Color(0.2, 0.2, 0.2),
            new Color(1.0, 1.0, 1.0), 1, 1
    );

    public static final Material BLACK_MIRROR = new Material(
            new Color(0.03, 0.03, 0.03),
            new Color(0.04, 0.04, 0.04),
            new Color(0.4, 0.4, 0.4), 12, 1
    );

    public static final Material BLUE_MIRROR = new Material(
            new Color(0.0, 0.0, 0.3),
            new Color(0.2, 0.2, 0.6),
            new Color(1.0, 1.0, 1.0), 1, 1
    );

    public static final Material GREEN_MIRROR = new Material(
            new Color(0.0, 0.3, 0.0),
            new Color(0.2, 0.6, 0.2),
            new Color(0.1, 0.3, 0.1), 1, 1
    );

    public static final Material YELLOW_MIRROR = new Material(
            new Color(0.175, 0.175, 0.05),
            new Color(0.4, 0.4, 0.2),
            new Color(0.2, 0.2, 0.1), 1, 1
    );

    public static final Material RED_MIRROR = new Material(
            new Color(0.0, 0.0, 0.0),
            new Color(0.2, 0.2, 0.2),
            new Color(0.6, 0.0, 0.0), 1, 1
    );

    public static final Material RED = new Material(
            new Color(0.3, 0.0, 0.0),
            new Color(0.5, 0.1, 0.1),
            new Color(0.1, 0.1, 0.1), 100
    );

    public Color ambient, diffuse, specular;
    public double specularPower, reflectionRatio;

    public Material(Color ambient, Color diffuse, Color specular, double specularPower) {
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.specularPower = specularPower;
        this.reflectionRatio = 0;
    }

    public Material(Color ambient, Color diffuse, Color specular, double specularPower, double reflectionRatio) {
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.specularPower = specularPower;
        this.reflectionRatio = reflectionRatio;
    }
}
