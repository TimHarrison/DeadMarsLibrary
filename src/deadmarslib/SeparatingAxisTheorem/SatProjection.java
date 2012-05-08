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
        double minOverlap = this.max - this.min < proj.max - proj.min ? this.max - this.min : proj.max - proj.min;
        if(this.max - proj.min > 0 && this.max - proj.min <= minOverlap)
            return this.max - proj.min;
        if(proj.max - this.min > 0 && proj.max - this.min <= minOverlap)
            return proj.max - this.min;
        return 0;
    }
    
}
