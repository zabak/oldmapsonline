package org.geotools.utils;

import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.geotools.geometry.DirectPosition2D;
import org.geotools.referencing.operation.builder.AdvancedAffineBuilder;
import org.geotools.referencing.operation.builder.AffineToGeometric;
import org.geotools.referencing.operation.builder.MappedPosition;
import org.geotools.referencing.operation.builder.MathTransformBuilder;
import org.geotools.referencing.operation.builder.SimilarTransformBuilder;
import org.geotools.referencing.operation.transform.AffineTransform2D;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.FactoryException;

/**
 * This takes care about the Math TransformBuidler configuration. And about calculated results
 * @author jezekjan
 *
 */
class Builder {

	private  MathTransformBuilder ab;
	private final List<MappedPosition> mps;
	//private final MathTransformBuilder atb;
	Builder(File gcps) throws FileNotFoundException, IOException, FactoryException{		
		mps = readFile(gcps);
		ab = new AdvancedAffineBuilder(mps);	
		Locale.setDefault(Locale.UK);	
	}
	
	public List<MappedPosition> getMappedPositions(){
		return mps;
	}
	public void setSkew(double skew){
		((AdvancedAffineBuilder)ab).setConstrain(AdvancedAffineBuilder.SXY, skew);
	}
	public void setPhi(double phi){
		((AdvancedAffineBuilder)ab).setConstrain(AdvancedAffineBuilder.PHI, phi);
	}
	
	public void setTx(double tx){
		((AdvancedAffineBuilder)ab).setConstrain(AdvancedAffineBuilder.TX, tx);
	}
	public void setTy(double ty){
		((AdvancedAffineBuilder)ab).setConstrain(AdvancedAffineBuilder.TY, ty);
	}
	
	public void setSx(double sx){
		((AdvancedAffineBuilder)ab).setConstrain(AdvancedAffineBuilder.SX, sx);
	}
	public void setSy(double sy){
		((AdvancedAffineBuilder)ab).setConstrain(AdvancedAffineBuilder.SY, sy);
	}
	public void printResut() throws FactoryException{
		
		
		NumberFormat formatter = new DecimalFormat("#0.00000000000");
		//System.out.println("== World file coeficients ==");
		System.out.println(   formatter.format(((AffineTransform)ab.getMathTransform()).getScaleX()));
		System.out.println(   formatter.format(((AffineTransform)ab.getMathTransform()).getShearY()));
		System.out.println(   formatter.format(((AffineTransform)ab.getMathTransform()).getShearX()));
	    System.out.println(   formatter.format(((AffineTransform)ab.getMathTransform()).getScaleY()));
		System.out.println(   formatter.format(((AffineTransform)ab.getMathTransform()).getTranslateX()));
		System.out.println(   formatter.format(((AffineTransform)ab.getMathTransform()).getTranslateY()));
		
		System.out.println("");

			
	}
	
	public void setSimilar(){
		
		ab = new SimilarTransformBuilder(mps);	
	}
	public void printGeomCoef() throws FactoryException{
		AffineToGeometric a2g = new AffineToGeometric((AffineTransform2D)ab.getMathTransform());
		NumberFormat formatter = new DecimalFormat("#0.000000000");
		
	/*	System.out.println("== builder geometric coeficients ==");
		System.out.println("sx   = " + formatter.format(((AdvancedAffineBuilder)ab).getXScale()));
		System.out.println("sy   = " + formatter.format(((AdvancedAffineBuilder)ab).getYScale()));
		System.out.println("skew = " + formatter.format(((AdvancedAffineBuilder)ab).getSkew()));
		System.out.println("tx   = " + formatter.format(((AdvancedAffineBuilder)ab).getXTranslate()));
		System.out.println("ty   = " + formatter.format(((AdvancedAffineBuilder)ab).getYTranslate()));
		System.out.println("phi  = " + formatter.format(((AdvancedAffineBuilder)ab).getRotation()));
		*/
		
		System.out.println("== geometric coeficients ==");
		System.out.println("sx   = " + formatter.format(a2g.getXScale()));
		System.out.println("sy   = " + formatter.format(a2g.getYScale()));
		System.out.println("skew = " + formatter.format(a2g.getSkew()));
		System.out.println("tx   = " + formatter.format(a2g.getXTranslate()));
		System.out.println("ty   = " + formatter.format(a2g.getYTranslate()));
		System.out.println("phi  = " + formatter.format(a2g.getRotation()));

	}
	public void printStatistics() throws FactoryException{
	    System.out.println("== Error stastics ==");
	    System.out.println( ab.getErrorStatistics());
	    System.out.println("== Error stastics ==");
	    System.out.println( ab.getMathTransform());
	}
	
	/**
	 * Reads the file with gcps
	 * @param file
	 * @return MappedPopsitions
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private List<MappedPosition> readFile(File file) throws FileNotFoundException, IOException{
		List<MappedPosition> mps = new ArrayList<MappedPosition>();
		FileInputStream stream = new FileInputStream(file);

		BufferedReader cti = new BufferedReader(new InputStreamReader(stream));
		String s;

		
		while ((s = cti.readLine()) != null) {
		
			String[] line = s.split(" ");			
			DirectPosition sp = new DirectPosition2D(null,
                                                       (new Float(line[2])).floatValue(), 
                                                       (new Float(line[3])).floatValue());
			DirectPosition tp = new DirectPosition2D(null,
					                                   (new Float(line[0])).floatValue(), 
					                                   (new Float(line[1])).floatValue());
			
			MappedPosition mp = new MappedPosition(sp, tp);
			mps.add(mp);		
			
		}				  
		   
		return mps;
		
	}
}
