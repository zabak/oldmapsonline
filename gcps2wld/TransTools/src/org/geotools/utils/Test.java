
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
    /*    AffineToGeometric a2g = new AffineToGeometric((AffineTransform2D) getMT());
        System.out.println(a2g.getSkew());
        System.out.println(a2g.getXTranslate());
        System.out.println(a2g.getYTranslate());*/

       Builder builder = new Builder(new File("./gcpswld.txt"));
            System.out.println("klokan------------------------------------------------------");
        transformTest(getMT(), builder.getMappedPositions());
        
        AdvancedAffineBuilder aab = new AdvancedAffineBuilder(builder.getMappedPositions());
        aab.setConstrain(aab.SXY, 0);
       // aab.setNumberOfIterationSteps(200);
      //  aab.setMaxIterationDifference(0.0000001);
        System.out.println("Ja------------------------------------------------------");
        transformTest(aab.getMathTransform(), builder.getMappedPositions());
        AffineToGeometric a2g2 = new AffineToGeometric((AffineTransform2D)aab.getMathTransform());
        System.out.println("Parametry------------------------------------------------------");
        System.out.println("xrot "+a2g2.getXRotation()*180/Math.PI);
        System.out.println("tX  "+a2g2.getXTranslate());
        System.out.println("tY  "+a2g2.getYTranslate());
        System.out.println("sx  "+a2g2.getXScale());
        System.out.println("sy  "+a2g2.getYScale());
        System.out.println("sxy "+a2g2.getSkew());
        System.out.println(aab.getMathTransform());
        
        System.out.println("Afinita------------------------------------------------------");
        AffineTransformBuilder atb = new  AffineTransformBuilder(builder.getMappedPositions());
       
       // transformTest(atb.getMathTransform(), builder.getMappedPositions());
   /*     AffineToGeometric a2g3 = new AffineToGeometric((AffineTransform2D)atb.getMathTransform());
        System.out.println("Parametry------------------------------------------------------");
        System.out.println(a2g3.getRotation()*180/Math.PI);
        System.out.println(a2g3.getXTranslate());
        System.out.println(a2g3.getYTranslate());
        System.out.println(a2g3.getXScale());
        System.out.println(a2g3.getYScale());
        System.out.println(a2g3.getSkew());
        System.out.println(atb.getMathTransform());*/
    }

    public static MathTransform getMT() throws FactoryException {
        GeneralMatrix M = new GeneralMatrix(3, 3);

        /**
         * Fill the metrix
         */
        double[] m0 = { 0.112804514325, 0.0307621213335, -106.676236219 };
        double[] m1 = { 0.0411346319449  , -0.084359723001, 7.8014489685 };
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
    	
    	sum =  sum + (x2 + y2);
    	
      //  System.out.println(pts.get(i).getTarget().getCoordinates()[0] +" "+ ptCalculated[2 * i]);
       // System.out.println(pts.get(i).getTarget().getCoordinates()[1] +" " + ptCalculated[(2 * i) + 1]);
        
        System.out.println(pts.get(i).getTarget().getCoordinates()[0] - ptCalculated[2 * i]);
        System.out.println(pts.get(i).getTarget().getCoordinates()[1] - ptCalculated[(2 * i) + 1]);
    }
    System.out.println("chyba  "+Math.pow(sum/pts.size(),0.5));
}
}
