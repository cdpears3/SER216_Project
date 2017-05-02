package com;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.util.Date;
import java.util.Vector;


public class Polygon3D extends Polygon{

	public int[] 	zpoints=null;
	public Point3D[] normals=null;
	public String hexColor="FFFFFF";
	public int index=0;
	
	public double shadowCosin=1;

	/**
	 * Constructor for a Polygon3D object with given parameters.
	 * @param npoints	the number of points in the polygon
	 * @param xpoints	the x values of the polygon
	 * @param ypoints	the y values of the polygon
	 */
	public Polygon3D(int npoints, int[] xpoints, int[] ypoints) {
		super(xpoints,ypoints,npoints);
		
	}	

	/**
	 * Constructor for a Polygon3D object with given parameters.
	 * @param npoints	the number of points in the polygon
	 * @param xpoints	the x values of the polygon
	 * @param ypoints	the y values of the polygon
	 * @param zpoints	the z values of the polygon
	 * @param normals	the normals of points in polygon 
	 */
	public Polygon3D(int npoints, int[] xpoints, int[] ypoints, int[] zpoints,Point3D[] normals) {
		this(npoints,xpoints,ypoints,zpoints);
		this.normals = normals;
	}
	
	/**
	 * Constructor for a Polygon3D object with given parameters.
	 * @param npoints	the number of points in the polygon
	 * @param xpoints	the x values of the polygon
	 * @param ypoints	the y values of the polygon
	 * @param zpoints	the z values of the polygon
	 */
	public Polygon3D(int npoints, int[] xpoints, int[] ypoints, int[] zpoints) {
		this(npoints,xpoints,ypoints);
		this.zpoints = zpoints;
	}

	/**
	 * Constructor for a Polygon3D object with given parameters.
	 * @param npoints	the number of points in the polygon
	 */
	public Polygon3D(int npoints) {
		this.xpoints = new int[npoints];
		this.ypoints = new int[npoints];
		this.zpoints = new int[npoints];
		this.normals = new Point3D[npoints];
		this.npoints=npoints;
	}

	/**
	 * Constructor for a Polygon3D object with given parameters.
	 * @param points	the Vector containing the points of the polygon
	 */
	public Polygon3D(Vector points) {

		this.npoints=points.size();
		this.xpoints = new int[this.npoints];
		this.ypoints = new int[this.npoints];
		this.zpoints = new int[this.npoints];
		this.normals = new Point3D[npoints];


		for(int i=0;i<this.npoints;i++){

			Point3D p=(Point3D) points.elementAt(i);

			this.xpoints[i]=(int) p.x;
			this.ypoints[i]=(int) p.y;
			this.zpoints[i]=(int) p.z;
			this.normals[i]=p.getNormal();
		}


	}
	
	/**
	 * Adds the given point to this polygon.
	 * @param p	the point to be added
	 */
	public void addPoint(Point3D p) {
		
		addPoint((int)p.x,(int)p.y,(int)p.z);
	}
	
	/**
	 * Adds a point at with the given values to this polygon.
	 * @param x	the x-axis value
	 * @param y	the y-axis value
	 * @param z	the z-axis value
	 */
	public void addPoint(int x, int y,int z) {
		
		Polygon3D new_pol=new Polygon3D(this.npoints+1);

		for(int i=0;i<this.npoints;i++){
			new_pol.xpoints[i]=this.xpoints[i];
			new_pol.ypoints[i]=this.ypoints[i];
			new_pol.zpoints[i]=this.zpoints[i];
		}
		new_pol.xpoints[this.npoints]=x;
		new_pol.ypoints[this.npoints]=y;
		new_pol.zpoints[this.npoints]=z;
		
		this.setNpoints(new_pol.npoints);
		this.setXpoints(new_pol.xpoints);
		this.setYpoints(new_pol.ypoints);
		this.setZpoints(new_pol.zpoints);
		
	}
	
	/**
	 * Creates a clone of this polygon.
	 */
	public Polygon3D clone(){

		return buildTranslatedPolygon(0,0,0);

	}

	/**
	 * Constructs and empty polygon.
	 */
	public Polygon3D() {

	}
	
	/**
	 * Divides the given polygon into triangles.
	 * @param pol	the polygon to be divided
	 * @return		an array containing the triangles created from the polygon
	 */
	public static Polygon3D[] divideIntoTriangles(Polygon3D pol){
		
		if(pol.npoints<3)
			return new Polygon3D[0];
		
		Polygon3D[] triangles=new Polygon3D[pol.npoints-2];
		
		if(pol.npoints==3){
			triangles[0]=pol;
			return triangles;
			
		}
		
		for(int i=1;i<pol.npoints-1;i++){
			
			Polygon3D triangle=new Polygon3D(3);
			triangle.setShadowCosin(pol.getShadowCosin());
			
			triangle.xpoints[0]=pol.xpoints[0];
			triangle.ypoints[0]=pol.ypoints[0];
			triangle.zpoints[0]=pol.zpoints[0];
			triangle.normals[0]=pol.normals[0];
			
			triangle.xpoints[1]=pol.xpoints[i];
			triangle.ypoints[1]=pol.ypoints[i];
			triangle.zpoints[1]=pol.zpoints[i];
			triangle.normals[1]=pol.normals[i];
			
			triangle.xpoints[2]=pol.xpoints[i+1];
			triangle.ypoints[2]=pol.ypoints[i+1];
			triangle.zpoints[2]=pol.zpoints[i+1];
			triangle.normals[2]=pol.normals[i+1];

			
			triangles[i-1]=triangle;
			
		}
		
		return triangles;
		
	}
	
	/**
	 * Creates a sub polygon from this polygon.
	 * @param pol	the polygon to extract from
	 * @param numAngles	the number of angles in the new polygon
	 * @param startAngle	the starting angle for the new polygon
	 * @return	the sub polygon
	 */
	public static Polygon3D extractSubPolygon3D(Polygon3D pol,int numAngles,int startAngle){



		int[] xpoints = new int[numAngles];
		int[] ypoints = new int[numAngles];
		int[] zpoints = new int[numAngles];

		int counter=0;

		for(int i=startAngle;i<numAngles+startAngle;i++){

			xpoints[counter]=pol.xpoints[i%pol.npoints];
			ypoints[counter]=pol.ypoints[i%pol.npoints];
			zpoints[counter]=pol.zpoints[i%pol.npoints];

			counter++;	
		}

		Polygon3D new_pol = new Polygon3D(numAngles,xpoints,ypoints,zpoints);
		new_pol.setHexColor(pol.getHexColor());
		return new_pol;
	}

	/**
	 * Creates a polygon from a given area
	 * @param area	the area to create the polygon
	 * @return	the created polygon
	 */
	public static Polygon3D fromAreaToPolygon2D(Area area){

		Polygon3D pol=new Polygon3D();

		PathIterator pathIter = area.getPathIterator(null);
		//if(isDebug)System.out.println(p3d);


		while(!pathIter.isDone()){

			double[] coords = new double[6];

			int type=pathIter.currentSegment(coords);
			//System.out.println(type);		
			double px= coords[0];
			double py= coords[1];	


			if(type==PathIterator.SEG_MOVETO || type==PathIterator.SEG_LINETO)
			{		

				pol.addPoint((int)px,(int)py);
				//System.out.println(x+" "+y);
			}
			pathIter.next();
		}
		Polygon3D pol2=removeRedundant(pol);
		return pol2;
	}

	/**
	 * Remove redundant points within the given polygon.
	 * @param pol	the polygon under review
	 * @return	a polygon with unique points
	 */
	private static Polygon3D removeRedundant(Polygon3D pol) {

		boolean redundant=false;

		if(pol.xpoints[0]==pol.xpoints[pol.npoints-1]
		                               &&    pol.ypoints[0]==pol.ypoints[pol.npoints-1]                            
		)	
			redundant=true;

		if(!redundant)
			return pol;
		else{


			Polygon3D new_pol=new Polygon3D(pol.npoints-1);

			for(int i=0;i<pol.npoints-1;i++){

				new_pol.xpoints[i]=pol.xpoints[i];
				new_pol.ypoints[i]=pol.ypoints[i];

			}

			return new_pol;

		}

	}
	
	/**
	 * String representation of this polygon.
	 */
	public String toString() {
		StringBuffer sb=new StringBuffer();

		if(zpoints!=null)

			for(int i=0;i<npoints;i++){
				sb.append(xpoints[i]+","+ypoints[i]+","+zpoints[i]+"_");

			}

		else 

			for(int i=0;i<npoints;i++){
				sb.append(xpoints[i]+","+ypoints[i]+"_");

			}	

		return sb.toString();
	}
	
	
	public Area clipPolygonToArea2D(Area area_out){


		Area area_in = new Area(this);

		Area new_area_out = (Area) area_out.clone();
		new_area_out.intersect(area_in);

		return new_area_out;

	}

	/**
	 * 
	 * USING SUTHERLAND-HODGMAN ALGORITHM FOR CLIPPING
	 * 
	 * @param p_in
	 * @param p_out
	 * @return
	 */
	public static Polygon3D clipPolygon3D(Polygon3D p_in,Polygon3D  p_out){



		//build all vertices adding border points


		for(int i=0;i<p_out.npoints;i++){

			Polygon3D p_new=new Polygon3D();

			int x1=p_out.xpoints[i];
			int y1=p_out.ypoints[i];
			int z1=p_out.zpoints[i];



			int x2=0;
			int y2=0;
			int z2=0;

			if(i==p_out.npoints-1) {

				x2=p_out.xpoints[0];
				y2=p_out.ypoints[0];
				z2=p_out.zpoints[0];

			}
			else{

				x2=p_out.xpoints[i+1];
				y2=p_out.ypoints[i+1];
				z2=p_out.zpoints[i+1];

			}
			System.out.println("clipping side : "+i);


			Point ps=new Point(p_in.xpoints[0],p_in.ypoints[0]);

			for(int j=0;j<p_in.npoints;j++){

				//System.out.println("clipping vertex:"+j);

				Point po=new Point(p_in.xpoints[j],p_in.ypoints[j]);

				if(isInsideClipPlane(po.x-x1,po.y-y1,x2-x1,y2-y1)){
					if(!isInsideClipPlane(ps.x-x1,ps.y-y1,x2-x1,y2-y1)){

						Point pm=insersect(ps,po,x2,x1,y2,y1);
						if(pm!=null) p_new.addPoint(pm.x,pm.y);

					}

					p_new.addPoint(po.x,po.y);
				}
				else if(isInsideClipPlane(ps.x-x1,ps.y-y1,x2-x1,y2-y1)){

					Point pm=insersect(po,ps,x2,x1,y2,y1);
					if(pm!=null) p_new.addPoint(pm.x,pm.y);
				}

				ps.x=po.x;
				ps.y=po.y;
			}

			p_in=new Polygon3D();
			for(int j=0;j<p_new.npoints;j++){
				p_in.addPoint(p_new.xpoints[j],p_new.ypoints[j]);
				//System.out.println(p_new.xpoints[j]+" "+p_new.ypoints[j]);
			}

		}	

		return p_in;

	}

	public static Polygon3D clipPolygon3DInY(Polygon3D  p_old,int y){


		
        Vector newPoints=new Vector();


		for(int i=0;i<p_old.npoints;i++){



			int x1=p_old.xpoints[i];
			int y1=p_old.ypoints[i];
			int z1=p_old.zpoints[i];


			int x2=p_old.xpoints[(i+1)%p_old.npoints];
			int y2=p_old.ypoints[(i+1)%p_old.npoints];
			int z2=p_old.zpoints[(i+1)%p_old.npoints];
			
			
			if((y1-y)>=0 && (y2-y)>=0){
				
				newPoints.add(new Point3D(x1,y1,z1));
							
			}
			else if(((y1-y)<0 && (y2-y)>=0) || ((y1-y)>=0 && (y2-y)<0)){
				
				if((y1-y)>=0)
				   newPoints.add(new Point3D(x1,y1,z1));
				
				if(y1!=y2){
					
					double l=(y-y1)*1.0/(y2-y1);
					double yn=y;
					double zn=z1+l*(z2-z1);
					double xn=x1+l*(x2-x1);
					
					newPoints.add(new Point3D(xn,yn,zn));
					
				}
			}
			

		}
		
		Polygon3D p_new=new Polygon3D(newPoints.size());
		p_new.setShadowCosin(p_old.getShadowCosin());
		
		int new_size=newPoints.size();
		
		for(int j=0;j<new_size;j++){
			
			Point3D p=(Point3D) newPoints.elementAt(j);
			
			p_new.xpoints[j]=(int) p.x;
			p_new.ypoints[j]=(int) p.y;
			p_new.zpoints[j]=(int) p.z;
		}
		
		
		return p_new;
	}
	
	public static Polygon3D clipPolygon3DInX(Polygon3D  p_old,int x){


		
        Vector newPoints=new Vector();


		for(int i=0;i<p_old.npoints;i++){



			int x1=p_old.xpoints[i];
			int y1=p_old.ypoints[i];
			int z1=p_old.zpoints[i];


			int x2=p_old.xpoints[(i+1)%p_old.npoints];
			int y2=p_old.ypoints[(i+1)%p_old.npoints];
			int z2=p_old.zpoints[(i+1)%p_old.npoints];
			
			
			if((x1-x)>=0 && (x2-x)>=0){
				
				newPoints.add(new Point3D(x1,y1,z1));
							
			}
			else if(((x1-x)<0 && (x2-x)>=0) || ((x1-x)>=0 && (x2-x)<0)){
				
				if((x1-x)>=0)
				   newPoints.add(new Point3D(x1,y1,z1));
				
				if(x1!=x2){
					
					double l=(x-x1)*1.0/(x2-x1);
					double xn=x;
					double zn=z1+l*(z2-z1);
					double yn=y1+l*(y2-y1);
					
					newPoints.add(new Point3D(xn,yn,zn));
					
				}
			}
			

		}
		
		Polygon3D p_new=new Polygon3D(newPoints.size());
		
		int new_size=newPoints.size();
		
		for(int j=0;j<new_size;j++){
						
			Point3D p=(Point3D) newPoints.elementAt(j);
			
			p_new.xpoints[j]=(int) p.x;
			p_new.ypoints[j]=(int) p.y;
			p_new.zpoints[j]=(int) p.z;
		}
		
		
		return p_new;
	}

	/**
	 * Finds the intersection of two lines.
	 * @param p1	the ending point of the second line
	 * @param p2	the starting point of the second line
	 * @param x2	the starting x of the first line
	 * @param x1	the ending x of the first line
	 * @param y2	the starting y of the first line
	 * @param y1	the ending y of the first line
	 * @return		the point of intersection
	 */
	private static Point insersect(Point p1, Point p2, int x2, int x1, int y2, int y1) {

		Line2D.Double line1=new Line2D.Double(x2,y2,x1,y1);
		Line2D.Double line2=new Line2D.Double(p2.x,p2.y,p1.x,p1.y);

		//if(!line1.intersectsLine(line2))
		//	return null;

		Point insersection=new Point();

		if(x2!=x1 && p2.x!=p1.x){

			double a1=(y2-y1)/(x2-x1);
			double a2=(p2.y-p1.y)/(p2.x-p1.x);
			double b1=(-x1*y2+y1*x2)/(x2-x1);
			double b2=(-p2.y*p1.x+p1.y*p2.x)/(p2.x-p1.x);


			insersection.x=(int)((-b2+b1)/(a2-a1));
			insersection.y=(int)((a2*b1-b2*a1)/(a2-a1));

		}
		else if(x2==x1 && p2.x!=p1.x){

			double a2=(p2.y-p1.y)/(p2.x-p1.x);
			double b2=(-p2.y*p1.x+p1.y*p2.x)/(p2.x-p1.x);

			insersection.x=x2;
			insersection.y=(int) (a2*x2+b2);
		}
		else if(x2!=x1 && p2.x==p1.x){

			double a1=(y2-y1)/(x2-x1);
			double b1=(-x1*y2+y1*x2)/(x2-x1);

			insersection.x=p2.x;
			insersection.y=(int) (a1*p2.x+b1);
		}


		return insersection;
	}

	/**
	 * Test if a line fits within the plane.
	 * @param pox	the lines x
	 * @param poy	the lines y
	 * @param ax	the planes x
	 * @param ay	the planes y
	 * @return		true if it fits, false otherwise
	 */
	private static boolean isInsideClipPlane(int pox,int poy, int ax, int ay) {


		return (ax*poy-ay*pox)>=0;
	}

	/**
	 * Test if a polygons is faced towards the correct point.
	 * @param pol	the polygon being tested
	 * @param observer	the point the polygon should face
	 * @return	true if the polygon faces the point, false otherwise
	 */
	public static boolean isFacing(Polygon3D pol,Point3D observer){

		Point3D p0=new Point3D(pol.xpoints[0],pol.ypoints[0],pol.zpoints[0]);

	
		Point3D vectorObs=observer.substract(p0);

		Point3D normal=findNormal(pol);

		double cosin=Point3D.calculateCosin(normal,vectorObs);

		return cosin>=0;
	}
	
	/**
	 * Finds the normal point of the given polygon.
	 * @param pol	the polygon being tested
	 * @return		the normal point
	 */
	public static Point3D findNormal(Polygon3D pol){

		Point3D p0=new Point3D(pol.xpoints[0],pol.ypoints[0],pol.zpoints[0]);

		Point3D p1=new Point3D(pol.xpoints[1],pol.ypoints[1],pol.zpoints[1]);
		Point3D p2=new Point3D(pol.xpoints[2],pol.ypoints[2],pol.zpoints[2]);

		Point3D vector1=p1.substract(p0); 
		Point3D vector2=p2.substract(p0);

		

		Point3D normal=Point3D.calculateCrossProduct(vector1,vector2);

		

		return normal;
	}

	/**
	 * Tests if a point exists within a polygon.
	 * @param x	the x value of the point
	 * @param y	the y value of the point
	 * @return	true if the point exists, false otherwise
	 */
	public boolean hasInsidePoint(double x,double y){

		for(int i=0;i<npoints;i++){

			AnalyticLine line=new AnalyticLine(xpoints[i],ypoints[i],xpoints[(i+1)%npoints],ypoints[(i+1)%npoints]);


			double valPoint=line.signum(x,y);

			//near the border the precise calcutation is very difficult
			if(Math.abs(valPoint)<0.01) valPoint=0;

			for(int j=2;j<npoints;j++){

				double valVertex = line.signum(xpoints[(j+i)%npoints],ypoints[(j+i)%npoints]);


				if(valVertex*valPoint<0)
					return false;
			}

		}

		return true;
	}


	


	public static class AnalyticLine{


		double a;
		double b;
		double c;

		public AnalyticLine(double a, double b, double c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}

		public AnalyticLine(double x1, double y1, double x0,double y0) {
			super();
			this.a = (y0-y1);
			this.b = -(x0-x1);
			this.c = (x0*y1-y0*x1);
		}

		public double signum(double x,double y){

			return a*x+b*y+c;

		}


	}
	
	/**
	 * The main method of Polygon3D
	 */
	public static void main(String[] args) {

		int[] cx=new int[4];
		int[] cy=new int[4];
		int[] cz=new int[4];


		cx[0]=10;
		cy[0]=10;
		cx[1]=0;
		cy[1]=50;
		cx[2]=50;
		cy[2]=60;
		cx[3]=50;
		cy[3]=-10;



		int[] cx1=new int[3];
		int[] cy1=new int[3];
		int[] cz1=new int[3];

		Polygon3D p1=new Polygon3D(4,cx,cy,cz);

		cx1[0]=10;
		cy1[0]=-20;
		cx1[1]=50;
		cy1[1]=40;
		cx1[2]=30;
		cy1[2]=-40;

		Polygon3D p2=new Polygon3D(3,cx1,cy1,cz1);

		//System.out.println(p2.hasInsidePoint(20,0));
		//System.out.println(p2.hasInsidePoint(30,40));
		//System.out.println(p2.hasInsidePoint(40,10));

		/*Area out=new Area(p1); 
		Area in=new Area(p2);


		Polygon3D p3=fromAreaToPolygon2D(out);

		System.out.println(p3);
		out.intersect(in);
		Polygon3D p_res=clipPolygon3D(p1,p2);*/
		System.out.println(p2);
		Polygon3D p3=clipPolygon3DInY(p2,0);
		System.out.println(p3);
	}
	
	/**
	 * Builds a polygon starting at a new location.
	 * @param dx	the x value to be added to the current starting x
	 * @param dy	the y value to be added to the current starting y
	 * @param dz	the z value to be added to the current starting z
	 * @return		the newly positioned polygon
	 */
	public Polygon3D buildTranslatedPolygon(double dx,double dy,double dz){

		Polygon3D translatedPolygon=new Polygon3D(this.npoints);

		for(int i=0;i<this.npoints;i++){

			translatedPolygon.xpoints[i]=(int) (this.xpoints[i]+dx);
			translatedPolygon.ypoints[i]=(int) (this.ypoints[i]+dy);
			translatedPolygon.zpoints[i]=(int) (this.zpoints[i]+dz);

		}

		return translatedPolygon;

	}
	
	/**
	 * Moves this polygon to a new location.
	 * @param dx	the x value to add to this polygon's starting x
	 * @param dy	the y value to add to this polygon's starting y
	 * @param dz	the z value to add to this polygon's starting z
	 */
	public void translate(double dx,double dy,double dz){


		for(int i=0;i<this.npoints;i++){

			this.xpoints[i]=(int) (this.xpoints[i]+dx);
			this.ypoints[i]=(int) (this.ypoints[i]+dy);
			this.zpoints[i]=(int) (this.zpoints[i]+dz);

		}


	}
	
	/**
	 * Inverts this polygon.
	 * @param y0	the y value needed to invert the polygon
	 */
	public void invertY(int y0) {		
	

		for(int i=0;i<this.npoints;i++){

			this.ypoints[i]=y0-this.ypoints[i];
		
		}

		
	}
	
	/**
	 * Builds a polygon that represents a prism
	 * @param upperBase	the upper boundary of the prism
	 * @param lowerBase	the lower boundary of the prism
	 * @param i
	 * @return	the constructed prism
	 */
	public static Polygon3D buildPrismIFace(Polygon3D upperBase,Polygon3D lowerBase,int i){

		int n=upperBase.npoints;

		int[] cx=new int[4];
		int[] cy=new int[4];
		int[] cz=new int[4];
		
		
		cx[0]=upperBase.xpoints[i%n];
		cy[0]=upperBase.ypoints[i%n];
		cz[0]=upperBase.zpoints[i%n];

		cx[1]=upperBase.xpoints[(i+1)%n];
		cy[1]=upperBase.ypoints[(i+1)%n];
		cz[1]=upperBase.zpoints[(i+1)%n];

		cx[2]=lowerBase.xpoints[(i+1)%n];
		cy[2]=lowerBase.ypoints[(i+1)%n];
		cz[2]=lowerBase.zpoints[(i+1)%n];

		cx[3]=lowerBase.xpoints[i%n];
		cy[3]=lowerBase.ypoints[i%n];
		cz[3]=lowerBase.zpoints[i%n];
		

		
		

		Polygon3D base=new Polygon3D(4,cx,cy,cz);

		return base;

	}
	
	 
	/**
	 * Returns the hex value of the color as a string.
	 * @return the hex value of the color
	 */
	public String getHexColor() {
		return hexColor;
	}

	/**
	 * Sets the color to the given hex value.
	 * @param hexColor the new color
	 */
	public void setHexColor(String hexColor) {
		this.hexColor = hexColor;
	}
	
	/**
	 * Returns the index of this element.
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the index of this element.
	 * @param index the new index
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * Sets the number of points in the polygon.
	 * @param npoints the number of points
	 */
	public void setNpoints(int npoints) {
		this.npoints = npoints;
	}


	/**
	 * Get then x values of this polygon.
	 * @return the array of x values
	 */
	public int[] getXpoints() {
		return xpoints;
	}

	/**
	 * Set the x values of this polygon.
	 * @param xpoints	the array of x values to be in this polygon
	 */
	public void setXpoints(int[] xpoints) {
		this.xpoints = xpoints;
	}

	/**
	 * Get then y values of this polygon.
	 * @return the array of y values
	 */
	public int[] getYpoints() {
		return ypoints;
	}

	/**
	 * Set the y values of this polygon.
	 * @param ypoints	the array of y values to be in this polygon
	 */
	public void setYpoints(int[] ypoints) {
		this.ypoints = ypoints;
	}

	/**
	 * Get then z values of this polygon.
	 * @return the array of z values
	 */
	public int[] getZpoints() {
		return zpoints;
	}

	/**
	 * Set the z values of this polygon.
	 * @param zpoints	the array of z values to be in this polygon
	 */
	public void setZpoints(int[] zpoints) {
		this.zpoints = zpoints;
	}

	/**
	 * Finds the center of the given polygon.
	 * @param p3d the polygon being tested
	 * @return the point at the center of the polygon
	 */
	public static Point3D findCentroid(Polygon3D p3d) {
		
		
		double x=0;
		double y=0;
		double z=0;

		int n=p3d.npoints;
		for (int i = 0; i <n;  i++) {
           x+=p3d.xpoints[i];	
           y+=p3d.ypoints[i];	
           z+=p3d.zpoints[i];	
		}

        return new Point3D(x/n,y/n,z/n);

	}

	public double getShadowCosin() {
		return shadowCosin;
	}

	public void setShadowCosin(double shadowCosin) {
		this.shadowCosin = shadowCosin;
	}

}
