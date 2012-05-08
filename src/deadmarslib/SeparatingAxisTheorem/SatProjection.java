package deadmarslib.SeparatingAxisTheorem;

public class SatProjection {
    private double min, max;
    
    public SatProjection(double min, double max) {
        this.min = min;
        this.max = max;
    }
    
    public boolean overlap(SatProjection proj) {
        return (!(proj.max < this.min || this.max < proj.min));
    }
    
    public double getOverlap(SatProjection proj) {
        double x = Math.max(this.min, proj.min);
        double y = Math.min(this.max, proj.max);
        return Math.max(x,y) - Math.min(x,y);
    }
    
    public double getDirection(SatProjection proj) {
        return Math.abs(this.max) > Math.abs(proj.max) ? 1.0f : -1.0f;
    }
}
