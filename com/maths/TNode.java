package com.maths;
import java.util.Vector;

/**
 * TNode class.
 * 
 * @author Group 12
 * @version 2.1 5/01/2017
 */
public class TNode {

	String label=null;
	TNode parent=null;
	Vector children=null;
	
	boolean marked=false;
	Object value=null;
	

	/**
	 * The getter for marked boolean.
	 * 
	 * @return marked The boolean for marked. 
	 */
	public boolean isMarked() {
		return marked;
	}

	
	/**
	 * The setter for marked boolean.
	 * 
	 * @param marked Set marked boolean to this.
	 */
	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	
	/**
	 * Getter for label String.
	 * 
	 * @return label The label String.
	 */
	public String getLabel() {
		return label;
	}

	
	/**
	 * Setter for label String.
	 * 
	 * @param label The string to set label to.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	
	/**
	 * Getter for the TNode parent.
	 * 
	 * @return parent The parent TNode.
	 */
	public TNode getParent() {
		return parent;
	}

	
	/**
	 * The Setter for the TNode parent.
	 * 
	 * @param parent Sets the TNode parent.
	 */
	public void setParent(TNode parent) {
		this.parent = parent;
	}

	
	/**
	 * Getter for the value Object.
	 * 
	 * @return value The value object.
	 */
	public Object getValue() {
		return value;
	}

	
	/**
	 * Setter for the value object.
	 * 
	 * @param value The value object to set.
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	
	/**
	 * Constructor for the TNode object, default with no parameters.
	 */
	public TNode(){
		
		children=new Vector();
	}
	
	
	/**
	 * Constructor for the TNode object. 
	 * 
	 * @param value The Object value.
	 */
	public TNode(Object value){
		
		children=new Vector();
		this.value=value;
	}
	
	
	/**
	 * Constructor for the TNode object.
	 * 
	 * @param parent The parent for building TNode with.
	 */
	public TNode(TNode parent){
		
		this.parent=parent;
		children=new Vector();
	}
	
	
	/**
	 * Constructor for the TNode object.
	 * 
	 * @param parent The parent TNode value.
	 * @param value The Object value.
	 */
	public TNode(TNode parent,Object value){
		
		this.parent=parent;
		children=new Vector();
		this.value=value;
	}
	
	
	/**
	 * The appendChild method, adds a child TNode.
	 * 
	 * @param child The child to add.
	 */
	public void appendChild(TNode child){
		
		child.setParent(this);
		children.add(child);
	}

	
	/**
	 * Gets the number of children
	 * 
	 * @return The amount of children
	 */
	public int getChildCount() {
		
		return children.size();
	}

	
	/**
	 * Gets a child at an element.
	 * 
	 * @param i The element of where to get the child at.
	 * @return The TNode child at the specified index.
	 */
	public TNode getChildAt(int i) {
		
		return (TNode) children.elementAt(i);
	}
	
	
	/**
	 * Removes a child at an element.
	 * 
	 * @param i Remove a child at this element.
	 */
	public void removeChildAt(int i) {
		
		children.removeElementAt(i);
	}
	
	
	/**
	 * Removes the TNode child.
	 * 
	 * @param tNode Removes this child TNode.
	 */
	public void removeChild(TNode tNode) {
		
		children.remove(tNode);
	}
	
	
	/**
	 * Clears the TNode object by creating a new vector object.
	 */
	public void clearTnode(){
		
		children=new Vector();
		
	}
	
	
	/**
	 * Converts the value object to a string
	 * 
	 * @return value The value as a string.
	 */
	public String toString(){
		
		if(value==null)
			return null;
		
		return value.toString();
	}

	
	/**
	 * Prints the value object.
	 */
	public void print() {

		System.out.println(toString());
		
		for (int i = 0; i <   getChildCount(); i++) {

			TNode child = getChildAt(i);
			child.print();

		}

	}

	
	/**
	 * Prints the TNode branches.
	 */
	public void printBranches() {
		
		if(getChildCount()==0)
		{
			Vector nodes=new Vector();
			
			nodes.add(this);
			TNode currentNode=this;
			TNode parent=null;
			
			while((parent=currentNode.getParent())!=null){
				
				nodes.add(parent);
				currentNode=parent;
			}
			System.out.println();
			for (int i = nodes.size()-1; i >=0; i--) {

				TNode member=(TNode) nodes.elementAt(i);
				System.out.print(member.toString());
				if(i>0)
					System.out.print("->");
			}
			System.out.println();
		}
			
			
			
		for (int i = 0; i <   getChildCount(); i++) {

			TNode child = getChildAt(i);
			child.printBranches();
			

		}
		
	}
	
	
	/**
	 * Prints the entire TNode tree.
	 */
	public void printTree() {
		
		System.out.print(toString());
		
		if(getChildCount()==0)
			return;
		
		System.out.print("(");
		for (int i = 0; i <   getChildCount(); i++) {

			if(i>0)
				System.out.print(",");
			
			TNode child = getChildAt(i);
			child.printTree();
			

		}
		System.out.print(")");
	}

	
	/**
	 * Sets the label String
	 * 
	 * @param ch Character to append to the label string.
	 */
	public void setLabel(char ch) {
		setLabel(""+ch);
		
	}
	
}
