package adds;

/**
 * Created by Vladislav on 10-Sep-15.
 */
public class Vector2F {

    public float xPos;
    public float yPos;

    public static float worldXPos;
    public static float worldYPos;

    public Vector2F() {
        this.xPos = 0.0f;
        this.yPos = 0.0f;
    }

    public Vector2F(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public static Vector2F zero() {
        return new Vector2F(0, 0);
    }

    public void normalize() {
        double length = Math.sqrt(xPos * xPos + yPos * yPos);

        if (length != 0.0f) {
            float s = 1.0f / (float) length;
            xPos = xPos * s;
            yPos = yPos * s;
        }
    }

    public Vector2F getScreenLocation() {
        return new Vector2F(xPos, yPos);
    }

    public Vector2F getWorldLocation() {
        return new Vector2F(xPos - worldXPos, yPos - worldYPos);
    }

    public boolean equals(Vector2F vec) {
        return (xPos == vec.xPos && yPos == vec.yPos);
    }

    public Vector2F copy(Vector2F vec) {
        xPos = vec.xPos;
        yPos = vec.yPos;
        return new Vector2F(xPos, yPos);
    }

    public Vector2F add(Vector2F vec) {
        xPos += vec.xPos;
        yPos += vec.yPos;
        return new Vector2F(xPos, yPos);
    }

    public static void setWorldVariables(float wx, float wy) {
        worldXPos = wx;
        worldYPos = wy;
    }

    public static double getDistanceOnScreen(Vector2F vector1, Vector2F vector2) {
        float v1 = vector1.xPos - vector2.xPos;
        float v2 = vector1.xPos - vector2.yPos;
        return Math.sqrt(v1 * v1 + v2 * v2);
    }

    public double getDistanceBetweenWorldVectors(Vector2F vector) {
        float dx = Math.abs(getWorldLocation().xPos - vector.getWorldLocation().xPos);
        float dy = Math.abs(getWorldLocation().yPos - vector.getWorldLocation().yPos);
        return Math.abs(dx * dx - dy * dy);
    }

}
