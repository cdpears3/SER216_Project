package com;


import java.util.StringTokenizer;
import java.util.Vector;

/**
 * This class is responsible for maintaining data relevant to the lines on the graph, such as points.
 * 
 * @author Group 12
 * @version 2.1
 */
public class LineData implements Cloneable{

		public Vector lineDatas=new Vector();
		public boolean isSelected=false;

		/**
		 * Returns the size of the Vector lineDatas.
		 * @return	the size of the lineDatas Vector
		 */
		public int size(){

			return lineDatas.size();
		}
		
		/**
		 * Adds the given Integer into this lineDatas Vector.
		 * @param n	the Integer to be added
		 */
		public void addIndex(int n){
			lineDatas.add(new Integer(n));
		}

		/**
		 * Returns the Integer held at the given index in the lineDatas Vector.
		 * @param i	the index we wish to return
		 * @return	the value at given index
		 */
		public int getIndex(int i){
			return ((Integer)lineDatas.elementAt(i)).intValue();
		}
		
		/**
		 * Returns a String representation of the lineDatas Vector
		 */
		public String toString() {
			
			return decomposeLineData(this);
		}

		/**
		 * Empty constructor that creates an empty LineData object.
		 */
		public LineData(){}
		
		/**
		 * Constructor that creates a LineData object with a lineDatas vector containing the
		 * four given Integers.
		 * @param i	first Integer to be stored in lineDatas
		 * @param j	second Integer to be stored in lineDatas
		 * @param k	third Integer to be stored in lineDatas
		 * @param l fourth Integer to be stored in lineDatas
		 */
		public LineData (int i, int j, int k, int l) {
			
			this.addIndex(i);
			this.addIndex(j);
			this.addIndex(k);
			this.addIndex(l);

			
		}

		public boolean isSelected() {
			return isSelected;
		}

		public void setSelected(boolean isSelected) {
			this.isSelected = isSelected;
		} 
		
		/**
		 * Returns a clone of this LineData object.
		 * @return	a clone of this LineData object
		 */
		public LineData clone()  {
		
			LineData ldnew=new LineData();
			
			for(int i=0;i<size();i++){
				
				ldnew.addIndex(getIndex(i));
			}
			
			return ldnew;
		}
		
		/**
		 * Helper method that breaks down the Vector into a String representation.
		 * @param ld	the lineData object being represented
		 * @return		the String representation of the LineData object
		 */
		private String decomposeLineData(LineData ld) {

			String str="";

			for(int j=0;j<ld.size();j++){

				if(j>0)
					str+=",";
				str+=ld.getIndex(j);

			}

			return str;
		}

		/**
		 * Returns the index of the given Integer in the lineDatas Vector
		 * @param i	the Integer being searched for
		 * @return	the index of the found Integer or -1 if not found
		 */
		public int positionOf(int i) {
			
			for(int j=0;j<size();j++){
				
				if(i==getIndex(j))
					return j;
			}
			
			return -1;
		}
		
		/**
		 * Constructs a Polygon3D object using the LineData object and Vector
		 * @param ld		the LineData object
		 * @param points	the Vector object
		 * @return			a Polygon3D object
		 */
		public static Polygon3D buildPolygon(LineData ld,Vector points) {



			int size=ld.size();

			int[] cxr=new int[size];
			int[] cyr=new int[size];
			int[] czr=new int[size];
			Point3D[] normals = new Point3D[size];


			for(int i=0;i<size;i++){


				int num=ld.getIndex(i);

				Point3D p=(Point3D) points.elementAt(num);

				//real coordinates
				cxr[i]=(int)(p.x);
				cyr[i]=(int)(p.y);
				czr[i]=(int)(p.z);

				normals[i]=p.getNormal();

			}


			Polygon3D p3dr=new Polygon3D(size,cxr,cyr,czr,normals);

	        return p3dr;

		}
		
		
	}
