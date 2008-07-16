
package org.geotools.utils;

import java.io.File;
import java.util.List;

import org.geotools.referencing.operation.builder.AdvancedAffineBuilder;
import org.geotools.referencing.operation.builder.AffineToGeometric;
import org.geotools.referencing.operation.builder.AffineTransformBuilder;
import org.geotools.referencing.operation.builder.MappedPosition;
import org.geotools.referencing.operation.matrix.GeneralMatrix;
import org.geotools.referencing.operation.transform.AffineTransform2D;
import org.geotools.referencing.operation.transform.ProjectiveTransform;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;


public class Test {
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        AffineToGeometric a2g = new AffineToGeometric((AffineTransform2D) getMT());
        System.out.println(a2g.getSkew());
        System.out.println(a2g.getXTranslate());
        System.out.println(a2g.getYTranslate());

        Builder builder = new Builder(new File("./gcps.txt"));
        System.out.println("klokan------------------------------------------------------");
        transformTest(getMT(), builder.getMappedPositions());
        
        AdvancedAffineBuilder aab = new AdvancedAffineBuilder(builder.getMappedPositions());
        aab.setConstrain(aab.SXY, 0);
        System.out.println("Ja------------------------------------------------------");
        transformTest(aab.getMathTransform(), builder.getMappedPositions());
        
        System.out.println("Afinita------------------------------------------------------");
        AffineTransformBuilder atb = new  AffineTransformBuilder(builder.getMappedPositions());
        
        transformTest(atb.getMathTransform(), builder.getMappedPositions());
    }

    public static MathTransform getMT() throws FactoryException {
        GeneralMatrix M = new GeneralMatrix(3, 3);

        /**
         * Fill the metrix
         */
        double[] m0 = { 0.00217223465064,-0.000220864005539 , 14.9422196996 };
        double[] m1 = { -0.000315486212764 , -0.00152072713958, 51.1385723447 };
        double[] m2 = { 0, 0, 1 };
        M.setRow(0, m0);
        M.setRow(1, m1);
        M.setRow(2, m2);

        MathTransform mt = ProjectiveTransform.create(M);

        return mt;
    }

    public static void transformTest(MathTransform mt, List<MappedPosition> pts)
        throws FactoryException, TransformException {
        double[] points = new double[pts.size() * 2];
        double[] ptCalculated = new double[pts.size() * 2];

        for (int i = 0; i < pts.size(); i++) {
            points[(2 * i)] = pts.get(i).getSource().getOrdinate(0);
            points[(2 * i) + 1] = pts.get(i).getSource().getOrdinate(1);
        }

        mt.transform(points, 0, ptCalculated, 0, pts.size());

        double sumDx = 0;
        double sumDy = 0;
        double sum = 0;
        for (int i = 0; i < pts.size(); i++) {
        	double x2 =  Math.pow((pts.get(i).getTarget().getOrdinate(0) - ptCalculated[2 * i])     ,2);
        	double y2 =  Math.pow((pts.get(i).getTarget().getOrdinate(1) - ptCalculated[(2 * i) + 1]),2);
        	
        	sumDx = sumDx + x2;//sum + Math.pow(x2 + y2, 0.5);
        	sumDy = sumDx + y2;
          //   System.out.println(pts.get(i).getTarget().getCoordinates()[0] - ptCalculated[2 * i]);
          //  System.out.println(pts.get(i).getTarget().getCoordinates()[1] - ptCalculated[(2 * i) + 1]);
        }
        System.out.println(Math.pow( (sumDx+sumDy)/pts.size(),0.5));
    }
}
