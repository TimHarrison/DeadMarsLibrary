package deadmarslib.SeparatingAxisTheorem;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

public class SatShape extends Polygon {
    
    int x, y;
    
    public SatShape() {
        super();
        x = 0;
        y = 0;
    }
    
    public SatShape(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);
        x = npoints >= 1 ? xpoints[0] : 0;
        y = npoints >= 1 ? ypoints[0] : 0;
    }
    
    public SatShape(Polygon p) {
        super(p.xpoints, p.ypoints, p.npoints);
        x = p.npoints >= 1 ? p.xpoints[0] : 0;
        y = p.npoints >= 1 ? p.ypoints[0] : 0;
    }
    
    public SatShape(Rectangle r) {
        super(new int[]{r.x, r.x+r.width,r.x+r.width, r.x}, new int[]{r.y, r.y, r.y+r.height, r.y+r.height}, 4);
        x = r.x;
        y = r.y;
    }
    
    public void setOriginX(int x) {
        this.x = x;
    }
    
    public void setOriginY(int y) {
        this.y = y;
    }
    
    public void setOrigin(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setOrigin(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
    
    public void moveOriginX(int dx) {
        this.x += dx;
    }
    
    public void moveOriginY(int dy) {
        this.y += dy;
    }
    
    public void moveOrigin(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    
    public void moveOrigin(Point dp) {
        this.x += dp.x;
        this.y += dp.y;
    }
    
    public void move(int dx, int dy) {
        int transX = dx - this.x;
        int transY = dy - this.y;
        this.translate(transX, transY);
    }
    
    public void move(Point dp) {
        int transX = dp.x - this.x;
        int transY = dp.y - this.y;
        this.translate(transX, transY);
    }
    
    @Override
    public void translate(int dx, int dy) {
        super.translate(dx, dy);
        this.x += dx;
        this.y += dy;
    }
    
    public SatAxis[] getAxes() {
        SatAxis[] axes = new SatAxis[npoints];
        for(int i = 0; i < npoints; i++) {
            SatAxis axis = getAxis(i);
            SatAxis normal = SatAxis.getNormal(axis);
            SatAxis normalized = SatAxis.normalize(normal);
            axes[i] = normalized;
        }
        return axes;
    }
    
    public SatAxis getAxis(int i) {
        i = i >= npoints || i < 0 ? 0 : i;
        Point p1 = new Point(xpoints[i], ypoints[i]);
        Point p2 = new Point(xpoints[i + 1 == npoints ? 0 : i + 1], ypoints[i + 1 == npoints ? 0 : i + 1]);
        SatAxis axis = new SatAxis(p1.x - p2.x, p1.y - p2.y);
        return axis;
    }

    public SatProjection project(SatAxis axis) {
        double min = axis.dot(new Point(this.xpoints[0], this.ypoints[0]));
        double max = min;
        for(int i = 1; i < npoints; i++) {
            double p = axis.dot(new Point(this.xpoints[i], this.ypoints[i]));
            if(p < min) {
                min = p;
            } else if (p > max) {
                max = p;
            }
        }
        return new SatProjection(min, max);
    }
    
    public SatMinimumTranslationVector collision(SatShape oShape) {
        ArrayList<Double> usedAxes = new ArrayList<>();
        double overlap = 100000000L;
        SatAxis smallest = null;
        double dir = 1.0f;
        SatAxis[] a1 = this.getAxes();
        SatAxis[] a2 = oShape.getAxes();
        for(int i = 0; i < this.npoints; i++) {
            if(usedAxes.contains(a1[i].y/a1[i].x))
                continue;
            usedAxes.add(a1[i].y/a1[i].x);
            SatAxis axis = a1[i];
            SatProjection p1 = this.project(axis);
            SatProjection p2 = oShape.project(axis);
            if(!p1.overlap(p2)) {
                return null;
            } else {
                double o = p1.getOverlap(p2);
                
                if(p1.contains(p2) || p2.contains(p1)) {
                    double mins = Math.abs(p1.getMin() - p2.getMin());
                    double maxs = Math.abs(p1.getMax() - p2.getMax());
                    
                    if(mins > maxs) {
                        o += mins;
                    } else {
                        o += maxs;
                    }
                }
                
                if(o < overlap) {
                    overlap = o;
                    smallest = axis;
                    dir = p1.getDirection(p2);
                }
            }
            
        }
        for(int i = 0; i < oShape.npoints; i++) {
            if(usedAxes.contains(a2[i].y/a2[i].x))
                continue;
            usedAxes.add(a2[i].y/a2[i].x);
            SatAxis axis = a2[i];
            SatProjection p1 = this.project(axis);
            SatProjection p2 = oShape.project(axis);
            if(!p1.overlap(p2)) {
                return null;
            } else {
                double o = p1.getOverlap(p2);
                
                if(p1.contains(p2) || p2.contains(p1)) {
                    double mins = Math.abs(p1.getMin() - p2.getMin());
                    double maxs = Math.abs(p1.getMax() - p2.getMax());
                    
                    if(mins > maxs) {
                        o += mins;
                    } else {
                        o += maxs;
                    }
                }
                
                if(o < overlap) {
                    overlap = o;
                    smallest = axis;
                    dir = p1.getDirection(p2);
                }
            }
        }
        SatMinimumTranslationVector mtv = new SatMinimumTranslationVector(smallest, overlap, dir);
        return mtv;
    }
}
