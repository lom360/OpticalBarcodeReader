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
		int i = strData.length - 1;
		while (i >= 0) {
			if(strData[i--].charAt(0) == ' ') {
				System.out.print('*');
				continue;
			}
			else  {
				System.out.print('*');
				this.imageData = BarcodeImageHelper(strData, i);
				break;
			}
		}
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
