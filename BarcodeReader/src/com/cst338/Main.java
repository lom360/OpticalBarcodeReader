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
		
		int height = bottomRight[0] - topLeft[0] + 1;
		int width = bottomRight[1] - topLeft[1] + 1;
		
		
//		int topLeftCorner = startOfBarcode[0];
//		int leftEdge = startOfBarcode[1];
//		int rightEdge = endOfBarcode[1];
//		for(int i = topLeftCorner + 9; i >= topLeftCorner; i--) {
//			for(int j = leftEdge; j <= rightEdge; j++) {
//				if(strData[i].charAt(j) == '*')
//					
//			}
//		}
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
//		int i = indexes[0];
//		int j = indexes[1];
//		for(int k = strData[i].length()-1; k > j; k--) {
//			if(strData[i].charAt(k) == '*') {
//				intArray[0] = i + 10;
//				intArray[1] = k;
//			}
//		}
		
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
	
	public boolean[][] BarcodeImageHelper(String[] strData, int currIndex) {
		boolean[][] newList = new boolean[MAX_HEIGHT][MAX_WIDTH];
		int  botToTop = MAX_HEIGHT;
		
		while(strData[currIndex].charAt(0) != ' ') {
			String strTemp = strData[currIndex];
			for(int leftToRight = 0; leftToRight < strTemp.length(); leftToRight++) {
				if(strTemp.charAt(leftToRight) == '*') {
					newList[botToTop][leftToRight] = true;
					System.out.print('*');
				}
				else {
					newList[botToTop][leftToRight] = false;
					System.out.print(' ');
				}
				
			}
			botToTop--;
			currIndex--;
		}
		
		return newList;
	}
	
	public void TestPrint() {
		for(int i = 0; i < MAX_HEIGHT; i++) {
			for(int j = 0; i < MAX_WIDTH; j++) {
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
//		barcodeImage.TestPrint();
//		DataMatrix test = new DataMatrix();
//		System.out.println(test.scan(null));
	}
}
