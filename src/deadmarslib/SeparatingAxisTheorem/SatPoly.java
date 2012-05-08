package deadmarslib.SeparatingAxisTheorem;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

public class SatPoly extends Polygon {
    
    public SatPoly(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);
    }
    
    public SatPoly(Polygon p) {
        super(p.xpoints, p.ypoints, p.npoints);
    }
    
    public SatAxis[] getAxes() {
        SatAxis[] axes = new SatAxis[npoints];
        for(int x = 0; x < npoints; x++) {
            SatAxis axis = getAxis(x);
            SatAxis normal = SatAxis.getNormal(axis);
            SatAxis normalized = SatAxis.normalize(normal);
            axes[x] = normalized;
        }
        return axes;
    }
    
    public SatAxis getAxis(int i) {
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
    
    public SatMinimumTranslationVector collision(SatPoly oPoly) {
        ArrayList<Double> usedAxes = new ArrayList<Double>();
        double overlap = 100000000L;
        SatAxis smallest = null;
        SatAxis[] a1 = this.getAxes();
        SatAxis[] a2 = oPoly.getAxes();
        for(int i = 0; i < this.npoints; i++) {
            if(usedAxes.contains(a1[i].y/a1[i].x))
                continue;
            usedAxes.add(a1[i].y/a1[i].x);
            SatAxis axis = a1[i];
            SatProjection p1 = this.project(axis);
            SatProjection p2 = oPoly.project(axis);
            if(!p1.overlap(p2)) {
                return null;
            } else {
                double o = p1.getOverlap(p2);
                if(o < overlap) {
                    overlap = o;
                    smallest = axis;
                }
            }
            
        }
        for(int i = 0; i < oPoly.npoints; i++) {
            if(usedAxes.contains(a2[i].y/a2[i].x))
                continue;
            usedAxes.add(a2[i].y/a2[i].x);
            SatAxis axis = a2[i];
            SatProjection p1 = this.project(axis);
            SatProjection p2 = oPoly.project(axis);
            if(!p1.overlap(p2)) {
                return null;
            } else {
                double o = p1.getOverlap(p2);
                if(o < overlap) {
                    overlap = o;
                    smallest = axis;
                }
            }
        }
        SatMinimumTranslationVector mtv = new SatMinimumTranslationVector(smallest, overlap);
        return mtv;
    }
}
