package com;

/**
 * This class represents a point on a 3-dimensional plane.
 * @author Group 12
 * @version 2.1
 */
public class Point3D {


	public double x;
	public double y;
	public double z;

	public double p_x;
	public double p_y;
	public double p_z;
	
	public double texture_x;
	public double texture_y;
	
	public Point3D normal=null;
	
	public boolean isSelected=false;
	
	/**
	 * Constructor for a Point3D object.
	 * @param x	
	 * @param y
	 * @param z	
	 * @param pX
	 * @param pY
	 * @param pZ
	 * @param textureX
	 * @param textureY
	 */
	public Point3D(double x, double y, double z, double pX, double pY,
			double pZ, double textureX, double textureY) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		p_x = pX;
		p_y = pY;
		p_z = pZ;
		texture_x = textureX;
		texture_y = textureY;
	}
	
	/**
	 * Constructor for a Point3D object.
	 * @param x
	 * @param y
	 * @param z
	 * @param normal
	 */
	public Point3D(double x, double y, double z, Point3D normal) {
		this(x,y,z);
		setNormal(normal);
	}

	/**
	 * Constructor for a Point3D object.
	 * @param x
	 * @param y
	 * @param z
	 * @param p_x
	 * @param p_y
	 * @param p_z
	 * @param normal
	 */
	public Point3D(double x, double y, double z, double p_x, double p_y,
			double p_z, Point3D normal) {
		
		this( x,  y,  z,  p_x,  p_y,p_z);
		setNormal(normal);
	}

	/**
	 * Constructor for a Point3D object.
	 * @param x
	 * @param y
	 * @param z
	 * @param p_x
	 * @param p_y
	 * @param p_z
	 */
	public Point3D(double x, double y, double z, double p_x, double p_y,
			double p_z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.p_x = p_x;
		this.p_y = p_y;
		this.p_z = p_z;
	}

	/**
	 * Constructor to create 3D point object at position (x, y, z) on a 3D plane.
	 * @param x	position on x-axis
	 * @param y	position on y-axis
	 * @param z	position on z-axis
	 */
	public Point3D(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Empty constructor.
	 */
	public Point3D() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Checks if two Point3D objects are equal.
	 * @param p	the Point3D being compared to this 3DPoint
	 * @return	true if points are equal, false otherwise
	 */
	public boolean equals(Point3D p){
		
		return this.x==p.x && this.y==p.y && this.z==p.z;
		
	}

	/**
	 * Creates a clone of this Point3D object.
	 */
	public Point3D clone()  {

		Point3D p=new Point3D(this.x,this.y,this.z,this.p_x,this.p_y,this.p_z,this.texture_x,this.texture_y);
		p.normal=this.normal;
		return p;

	}
	

	
	public boolean isSelected() {
		return isSelected;
	}


	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	/**
	 * Calculates the cosine of the line between 2 Point3D objects.
	 * @param a	the first point
	 * @param b	the second point
	 * @return	the cosine of the 2 points
	 */
	public static double calculateCosin(Point3D a, Point3D b) {

		double prod=-(calculateSquareNorm(b.substract(a))-calculateSquareNorm(a)-calculateSquareNorm(b))
		/(2*calculateNorm(a)*calculateNorm(b));
		
		
		//System.out.println(a.x+" "+a.y+" "+a.z);
		return prod;
	}

	/**
	 * Calculates the dot product between 2 Point3D objects.
	 * @param a	the first point
	 * @param b	the second point
	 * @return	the dot product of the 2 points
	 */
	public static double calculateDotProduct(Point3D a,
			Point3D b) {

		return a.x*b.x+a.y*b.y+a.z*b.z;
	}
	
	/**
	 * Calculates the dot product between 2 points.
	 * @param px	the x-axis position of the first point
	 * @param py	the y-axis position of the first point
	 * @param pz	the z-axis position of the first point
	 * @param b		the second point
	 * @return		the dot product of the 2 points
	 */
	public static double calculateDotProduct(double px,double py,double pz,
			Point3D b) {

		return px*b.x+py*b.y+pz*b.z;
	}

	/**
	 * Calculates the normal of a Point3D object.
	 * @param a	the point to be considered
	 * @return	the normal of the point
	 */
	public static double calculateNorm(Point3D a) {

		return Math.sqrt(calculateDotProduct(a,a));
		
	}

	/**
	 * Calculates the normal squared of a Point3D object.
	 * @param a	the point to be considered
	 * @return	the squared normal of the point
	 */
	private static double calculateSquareNorm(Point3D a) {

		return calculateDotProduct(a,a);
	}

	/**
	 * Calculates the distance between 2 Point3D objects.
	 * @param a	the first point
	 * @param b	the second point
	 * @return	the distance between the 2 points
	 */
	public static double distance(Point3D a,Point3D b){


		return calculateNorm(a.substract(b));
	}

	/**
	 * Calculates the distance between 2 points.
	 * @param x1	the x-axis position of the first point
	 * @param y1	the y-axis position of the first point
	 * @param z1	the z-axis position of the first point
	 * @param x2	the x-axis position of the second point
	 * @param y2	the y-axis position of the second point
	 * @param z2	the z-axis position of the second point
	 * @return		the distance beteween the two points
	 */
	public static double distance(double x1,double y1,double z1,double x2,double y2,double z2){


		return distance(new Point3D (x1,y1,z1),new Point3D (x2,y2,z2));
	}

	/**
	 * Calculates the orthogonal of a Point3D object.
	 * @param a	the point to be considered
	 * @return	the orthogonal of the point
	 */
	public static Point3D calculateOrthogonal(Point3D a){
		Point3D orth=new Point3D(-a.y,a.x,0);

		return orth;
	}

	/**
	 * Calculates the versor of this Point3D object.
	 * @return	the versor of this point
	 */
	public Point3D calculateVersor(){

		double norm=calculateNorm(this);
		if(norm==0)
			return new Point3D(0,0,0);
		double i_norm=1.0/norm;
		Point3D versor=new Point3D(this.x*i_norm,this.y*i_norm,this.z*i_norm);

		return versor;
	}

	/**
	 * Calculates the cross product of 2 Point3D objects.
	 * @param a	the first point
	 * @param b	the second point
	 * @return	the Point3D object created from the cross product of the 2 points
	 */
	public static Point3D calculateCrossProduct(Point3D a,
			Point3D b) {

		double x=a.y*b.z-b.y*a.z;
		double y=b.x*a.z-a.x*b.z;
		double z=a.x*b.y-b.x*a.y;

		Point3D pRes=new Point3D(x,y,z);

		return pRes;
	}

	/**
	 * Subtracts a Point3D object from this Point3D object.
	 * @param p0	the point to be subtracted from this point
	 * @return		the difference of the two points
	 */
	public Point3D substract(Point3D p0) {

		Point3D pRes=new Point3D(this.x-p0.x,this.y-p0.y,this.z-p0.z);

		return pRes;
	}
	
	/**
	 * Subtracts a Point3D object from this Point3D object.
	 * @param p0	the point to be subtracted
	 * @return		the difference of the two points
	 */
	public Point3D minus(Point3D p0) {

		this.x=this.x-p0.x;
		this.y=this.y-p0.y;
		this.z=this.z-p0.z;
		
		return this;
		
	}

	/**
	 * Adds a Point3D object to this Point3D object.
	 * @param p0	the point being added to this point
	 * @return		the sum of the two points
	 */
	public Point3D sum(Point3D p0) {

		Point3D pRes=new Point3D(this.x+p0.x,this.y+p0.y,this.z+p0.z);

		return pRes;
	}
	
	/**
	 * Multiplies a Point3D object by a given factor.
	 * @param factor	the factor this point is being multiplied
	 * @return			the product of the multiplication
	 */
	public Point3D multiply(Double factor) {

		
		Point3D pRes=new Point3D(this.x*factor,this.y*factor,this.z*factor);

		return pRes;
	}
	
	/**
	 * Returns the x-axis position of this Point3D object.
	 * @return this points value on the x-axis
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the position on the x-axis of this Point3D object.
	 * @param x	the new position
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Returns the y-axis position of this Point3D object.
	 * @return this points value on the y-axis
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the position on the y-axis of this Point3D object.
	 * @param y	the new position
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Returns the z-axis position of this Point3D object.
	 * @return this points value on the z-axis
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Sets the position on the z-axis of this Point3D object.
	 * @param z	the new position
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Returns the p_z value of this Point3D object.
	 * @return	the p_z value of this point
	 */
	public double getP_z() {
		return p_z;
	}

	/**
	 * Sets the p_z value of this Point3D object.
	 * @param p_z	the new value
	 */
	public void setP_z(double p_z) {
		this.p_z = p_z;
	}

	/**
	 * 
	 * @param p1	the first point
	 * @param p2	the second point
	 * @param y		
	 * @return		the point of intersection
	 */
	public static double foundXIntersection(Point3D p1, Point3D p2,
			double y) {

		if(p2.y-p1.y<1 && p2.y-p1.y>-1)
			return p1.x;

		return p1.x+((p2.x-p1.x)*(y-p1.y))/(p2.y-p1.y);
	

	}
	
	/**
	 * 
	 * @param p1x	the x-axis position of the first point
	 * @param p1y	the y-axis position of the first point 
	 * @param p2x	the x-axis position of the second point
	 * @param p2y	the y-axis position of the second point
	 * @param y		
	 * @return		the point of intersection
	 */
	public static double foundXIntersection(double p1x, double p1y,double p2x, double p2y,
			double y) {

		if(p2y-p1y<1 && p2y-p1y>-1)
			return p1x;

		return p1x+((p2x-p1x)*(y-p1y))/(p2y-p1y);
	

	}

	/**
	 * 
	 * @param p1	the first point
	 * @param p2	the second point
	 * @param y		
	 * @return		the position along the y-axis that these points intersect
	 */
	public static double foundZIntersection(Point3D p1, Point3D p2,
			double y) {

		if(p2.y-p1.y<1 && p2.y-p1.y>-1)
			return p1.z;

		return p1.z+((p2.z-p1.z)*(y-p1.y))/(p2.y-p1.y);


	}

	/**
	 *
	 * @param pstart	the starting point
	 * @param pend		the ending point
	 * @param y			
	 * @return			the intersection point
	 */
	public static Point3D foundPX_PY_PZ_Intersection(Point3D pstart, Point3D pend,
			double y) {
		
		Point3D intersect=new Point3D(); 

		double i_pstart_p_y=1.0/(pstart.p_y);
		double i_end_p_y=1.0/(pend.p_y);
		
		double l=(y-pstart.y)/(pend.y-pstart.y);
	
		double yi=1.0/((1-l)*i_pstart_p_y+l*i_end_p_y);
		
		intersect.p_x= ((1-l)*pstart.p_x*i_pstart_p_y+l*pend.p_x*i_end_p_y)*yi;
		intersect.p_y=  1.0/((1-l)*i_pstart_p_y+l*i_end_p_y);		
		intersect.p_z=  ((1-l)*pstart.p_z*i_pstart_p_y+l*pend.p_z*i_end_p_y)*yi;
		
		return intersect;

	}
	
	/**
	 * 
	 * @param pstart	the starting point
	 * @param pend		the ending point
	 * @param y			
	 * @return			the intersection point
	 */
	public static Point3D foundPX_PY_PZ_TEXTURE_Intersection(Point3D pstart, Point3D pend,
			double y) {
		
		Point3D intersect=new Point3D(); 

		double i_pstart_p_y=1.0/(pstart.p_y);
		double i_end_p_y=1.0/(pend.p_y);
		
		double l=(y-pstart.y)/(pend.y-pstart.y);
	
		double yi=1.0/((1-l)*i_pstart_p_y+l*i_end_p_y);
		
		intersect.p_x= ((1-l)*pstart.p_x*i_pstart_p_y+l*pend.p_x*i_end_p_y)*yi;
		intersect.p_y=  1.0/((1-l)*i_pstart_p_y+l*i_end_p_y);		
		intersect.p_z=  ((1-l)*pstart.p_z*i_pstart_p_y+l*pend.p_z*i_end_p_y)*yi;
		
		intersect.texture_x=  ((1-l)*pstart.texture_x*i_pstart_p_y+l*pend.texture_x*i_end_p_y)*yi;
		intersect.texture_y=  ((1-l)*pstart.texture_y*i_pstart_p_y+l*pend.texture_y*i_end_p_y)*yi;
		
		return intersect;

	}
	
	/**
	 * 
	 * @param pstart	the starting point
	 * @param pend		the ending point
	 * @param y			
	 * @return			the intersection point
	 */
	public static double foundPXIntersection(Point3D pstart, Point3D pend,
			double y) {

		double i_pstart_p_y=1.0/(pstart.p_y);
		double i_end_p_y=1.0/(pend.p_y);
		
		double l=(y-pstart.y)/(pend.y-pstart.y);
	
		double yi=1.0/((1-l)*i_pstart_p_y+l*i_end_p_y);
		
		return ((1-l)*pstart.p_x*i_pstart_p_y+l*pend.p_x*i_end_p_y)*yi;

	}
	
	/**
	 * 
	 * @param pstart	the starting point
	 * @param pend		the ending point
	 * @param y
	 * @return			the intersection point
	 */
	public static double foundPYIntersection(Point3D pstart, Point3D pend,
			double y) {

		double i_pstart_p_y=1.0/(pstart.p_y);
		double i_end_p_y=1.0/(pend.p_y);
		
		double l=(y-pstart.y)/(pend.y-pstart.y);
	
		return 1.0/((1-l)*i_pstart_p_y+l*i_end_p_y);
		
		

	}

	/**
	 * 
	 * @param pstart	the starting point
	 * @param pend		the ending point
	 * @param y
	 * @return			the intersection point
	 */
	public static double foundPZIntersection(Point3D pstart, Point3D pend,
			double y) {

		double i_pstart_p_y=1.0/(pstart.p_y);
		double i_end_p_y=1.0/(pend.p_y);
		
		double l=(y-pstart.y)/(pend.y-pstart.y);
	
		double yi=1.0/((1-l)*i_pstart_p_y+l*i_end_p_y);
		
		return ((1-l)*pstart.p_z*i_pstart_p_y+l*pend.p_z*i_end_p_y)*yi;


	}


	/**
	 * 
	 * @param x0
	 * @param y0
	 * @param cos
	 * @param sin
	 */
	public void rotate(double x0, double y0,double cos, double sin ) {
		
		double xx=this.x;
		double yy=this.y;
		double zz=this.z;
		
		this.x=x0+(xx-x0)*cos-(yy-y0)*sin;
		this.y=y0+(yy-y0)*cos+(xx-x0)*sin;
	}

	/**
	 * Builds and Point3D object at a new location.
	 * @param dx	the value to be added to the x-axis position
	 * @param dy	the value to be added to the y-axis position
	 * @param dz	the value to be added to the z-axis position
	 * @return		the new point
	 */
    public Point3D buildTranslatedPoint(double dx,double dy,double dz){
    	
    	Point3D p=new Point3D();
    	p.setX(this.getX()+dx);
    	p.setY(this.getY()+dy);
    	p.setZ(this.getZ()+dz);
    	
    	return p;
    }

    /**
     * Moves this Point3D object to a new location.
     * @param dx	the value to be added to the x-axis position
     * @param dy	the value to be added to the y-axis position
     * @param dz	the value to be added to the z-axis position
     */
	public void translate(double dx, double dy, double dz) {
		
		setX(this.getX()+dx);
    	setY(this.getY()+dy);
    	setZ(this.getZ()+dz);
		
	}
	
	/**
	 * Sets the position of the texture to a given x, y position.
	 * @param textureX	the x-axis position
	 * @param textureY	the y-axis position
	 */
	public void setTexurePositions(double textureX, double textureY){
		
		texture_x = textureX;
		texture_y = textureY;
	}

	/**
	 * Sets the position of the texture to a given point.
	 * @param p	the point to set the texture
	 */
	public void setTexurePositions(Point3D p){
		
		texture_x = p.x;
		texture_y = p.y;
	}
	
	/**
	 * Returns a string representation of this Point3D object.
	 */
	public String toString() {

		return x+" "+y+" "+z;
	}

	/**
	 * Gets the normal of this Point3D object.
	 * @return normal
	 */
	public Point3D getNormal() {
		return normal;
	}

	/**
	 * Sets the normal of this Point3D object.
	 * @param normal
	 */
	public void setNormal(Point3D normal) {
		this.normal = normal;
	}

}
