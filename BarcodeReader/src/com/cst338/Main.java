package com.cst338;

interface BarcodeIO {
	public boolean scan(BarcodeImage bc);
	public boolean readText(String text);
	public boolean generateImageFromText();
	public boolean translateImageToText();
	public boolean displayTextToConsole();
	public void displayImageToConsole();
}

class BarcodeImage implements Cloneable {
	public static final int MAX_HEIGHT = 30;
	public static final int MAX_WIDTH = 65;
	private boolean[][] imageData;
	
	public BarcodeImage() {
		this.imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
		for(int i = 0; i < MAX_HEIGHT; i++) {
			for(int j = 0; j < MAX_WIDTH; j++) {
				this.imageData[i][j] = false;
			}
		}
	}
	
	public BarcodeImage(String[] strData) {
		int[] topLeft = FindTopLeftBarcode(strData);
		int[] bottomRight = FindBottomRightBarcode(strData);
		
//		int height = bottomRight[0] - topLeft[0] + 1;
//		int width = bottomRight[1] - topLeft[1] + 1;
		this.imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
		
		int topEdge = topLeft[0];
		int leftEdge = topLeft[1];
		int bottomEdge = bottomRight[0];
		int rightEdge = bottomRight[1];
		int goUp = MAX_HEIGHT - 1;
		int goRight = 0;
		for(int i = bottomEdge; i >= topEdge; i--) {
			for(int j = leftEdge; j <= rightEdge; j++) {
				if(strData[i].charAt(j) == '*') 
					imageData[goUp][goRight++] = true;
				else
					imageData[goUp][goRight++] = false;
			}
			goUp--; // Move up the matrix
			goRight = 0; // reset to left side of matrix
		}
	}
	
	public int[] FindTopLeftBarcode(String[] strData) {
		int[] intArray = new int[2];
		for(int i = 0; i < strData.length - 1; i++) {
			for(int j = 0; j < strData[i].length(); j++) {
				if(strData[i].charAt(j) == '*') {
					intArray[0] = i;
					intArray[1] = j;
					return intArray;
				}
			}
		}
		return intArray;
	}
	
	public int[] FindBottomRightBarcode(String[] strData) {
		int[] intArray = new int[2];
		for(int i = strData.length - 1; i >= 0; i--) {
			for(int j = strData[i].length() - 1; j >= 0; j--) {
				if(strData[i].charAt(j) == '*') {
					intArray[0] = i;
					intArray[1] = j;
					return intArray;
				}
			}
		}
		return intArray;
	}
	
	public void testChange() {
		this.imageData[0][0] = !this.imageData[0][0];
	}
	
	public BarcodeImage clone() {
		try {
			// Shallow copy
			BarcodeImage cloned = (BarcodeImage) super.clone();
			
			// Deep copy any mutable fields
			cloned.imageData = imageData.clone();
			for (int i = 0; i < imageData.length; i++) {
				cloned.imageData[i] = imageData[i].clone();
			}
			
			return cloned;
		}
		catch (CloneNotSupportedException e) {
			// Should never happen since BarcodeImage implements Cloneable
			throw new AssertionError();
		}
	}
	
	public void TestPrint() {
		for(int i = 0; i < MAX_HEIGHT; i++) {
			for(int j = 0; j < MAX_WIDTH; j++) {
				if(this.imageData[i][j] == true)
					System.out.print('*');
				else
					System.out.print(' ');
			}
			System.out.println("");
		}
	}
}

class DataMatrix implements BarcodeIO {
	public static final char BLACK_CHAR = '*';
	public static final char WHITH_CHAR = ' ';
	private BarcodeImage image;
	private String text;
	private int actualWidth, actualHeight;
	
	public DataMatrix() {
		this.actualHeight = 0;
		this.actualWidth = 0;
		this.text = " ";
		this.image = new BarcodeImage();
	}
	public boolean scan(BarcodeImage bc) {
		return true;
	}
	public boolean readText(String text) {
		return true;
	}
	public boolean generateImageFromText() {
		return true;
	}
	public boolean translateImageToText() {
		return true;
	}
	public boolean displayTextToConsole() {
		return true;
	}
	public void displayImageToConsole() {
		
	}
}


public class Main {
	public static void main(String[] args) {
		 String[] sImageIn =  
			 {  
			 "                                               ",  
			 "                                               ",  
			 "                                               ",  
			 "     * * * * * * * * * * * * * * * * * * * * * ",  
			 "     *                                       * ",  
			 "     ****** **** ****** ******* ** *** *****   ",  
			 "     *     *    ****************************** ",  
			 "     * **    * *        **  *    * * *   *     ",  
			 "     *   *    *  *****    *   * *   *  **  *** ",  
			 "     *  **     * *** **   **  *    **  ***  *  ",  
			 "     ***  * **   **  *   ****    *  *  ** * ** ",  
			 "     *****  ***  *  * *   ** ** **  *   * *    ",  
			 "     ***************************************** ",   
			 "                                               ",  
			 "                                               ",  
			 "                                               "  
			  
			 };
		BarcodeImage barcodeImage = new BarcodeImage(sImageIn);
		BarcodeImage clone = barcodeImage.clone();
		clone.testChange();
		clone.TestPrint();
		barcodeImage.TestPrint();
//		DataMatrix test = new DataMatrix();
//		System.out.println(test.scan(null));
	}
}
