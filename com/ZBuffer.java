package com;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class ZBuffer{

		int rgbColor;
		double z=0;
		
		public double p_x=0;
		public double p_y=0;
		public double p_z=0;

		/**
		 * Gets the integer value of this color.
		 * @return the integer value of this color
		 */
		public int getRgbColor() {
			return rgbColor;
		}
		
		/**
		 * Sets this color to the given integer value
		 * @param rgbColor the new color
		 */
		public void setRgbColor(int rgbColor) {
			this.rgbColor = rgbColor;
		}
		
		/**
		 * Return the z value of this ZBuffer.
		 * @return this z
		 */
		public double getZ() {
			return z;
		}
		
		/**
		 * Sets the z value of this ZBuffer.
		 * @param z the new value
		 */
		public void setZ(double z) {
			this.z = z;
		}
		
		/**
		 * Constructs a ZBuffer with given parameters.
		 * @param rgbColor the color of this buffer
		 * @param z	the z value of this buffer
		 */
		public ZBuffer(int rgbColor, double z) {
			super();
			this.rgbColor = rgbColor;
			this.z = z;
		}
		
		/**
		 * Default ZBuffer constructor.
		 */
		public ZBuffer() {
			super();
		}
		
		/**
		 * Returns the color of the given hex string.
		 * @param col	the hex value of the color
		 * @return	the color of the hex value
		 */
		public static Color  fromHexToColor(String col){



			int r=Integer.parseInt(col.substring(0,2),16);
			int g=Integer.parseInt(col.substring(2,4),16);
			int b=Integer.parseInt(col.substring(4,6),16);

			Color color=new Color(r,g,b);

			return color;
		}

		/**
		 * Returns the hex value of a color as a string.
		 * @param col	the color being converting to hex
		 * @return	the hex value of the color
		 */
		public static String fromColorToHex(Color col){

			String exe="";

			exe+=addZeros(Integer.toHexString(col.getRed()))
					+addZeros(Integer.toHexString(col.getGreen()))
					+addZeros(Integer.toHexString(col.getBlue()));

			return exe;

		}

		/**
		 * Prepends zero's to the hex value to fill it out.
		 * @param hexString	the hex value being filled
		 * @return	the filled hex value
		 */
		public static String addZeros(String hexString) {
			
			if(hexString.length()==1)
				return "0"+hexString;
			else 
				return hexString;
		}
		
		/**
		 * Update the color.
		 * @param xs the x position for the new color
		 * @param ys the y position for the new color
		 * @param zs the z position for the new color
		 * @param rgbColor	the new color
		 */
		public void update(double xs,double ys,double zs, int rgbColor) {
			
			
			if(getZ()==0 ||  getZ()>ys ){

				setZ(ys);
				setRgbColor(rgbColor);
				
				p_x=xs;
				p_y=ys;
				p_z=zs;

			}

		}

		/**
		 * Checks if the color has been changed.
		 * @param ys	
		 * @return	true if the color hasn't been changed, false otherwise
		 */
		public boolean isToUpdate(double ys){


			return getZ()==0 ||  getZ()>ys;
		}	
		
		/**
		 * Sets the color of the given position.
		 * @param xs	the x position
		 * @param ys	the y position
		 * @param zs	the z position
		 * @param rgbColor	the new color
		 */
		public void set(double xs,double ys,double zs, int rgbColor) {

			setZ(ys);
			setRgbColor(rgbColor);

			p_x=xs;
			p_y=ys;
			p_z=zs;
		}

}
