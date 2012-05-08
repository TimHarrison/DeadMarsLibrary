package deadmarslib.SeparatingAxisTheorem;

import java.awt.Point;

public class SatAxis {
    public double x;
    public double y;
    
    public SatAxis(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public SatAxis(Point axis) {
        this.x = axis.x;
        this.y = axis.y;
    }
    
    public static SatAxis getNormal(SatAxis p) {
        SatAxis normal = new SatAxis(-p.y, p.x);
        return normal;
    }
    
    public static SatAxis normalize(SatAxis p) {
        double pLen = Math.abs(Math.sqrt(p.x*p.x + p.y*p.y));
        SatAxis normalized = new SatAxis((p.x / pLen), (p.y / pLen));
        return normalized;
    }
    
    public double dot(Point vert) {
        return (this.x * vert.x) + (this.y * vert.y);
    }
}
