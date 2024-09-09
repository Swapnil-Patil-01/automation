package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders{

	//DataProvider1
	@DataProvider(name="LoginData")
	public String [][]getdata() throws IOException {
		String path=".\\testData\\OpenCart_LoginData.xlsx";   //taking xl file from testdata
		
		ExcelUtility xlutil =new ExcelUtility(path);    //creating an object for XLUtility
		
		int totalRows= xlutil.getRowCount("Sheet1");
		int totalCols= xlutil.getCellCount("Sheet1", 1);
		
		String loginData[][] = new String[totalRows][totalCols]; //created for two dimenion array which can stores
		
		for(int i=1; i<=totalRows;i++) {  //read data from xl storing in two dimensional array
			for(int j=0; j<totalCols; j++) {   //i is for rows and j is for columns
				loginData[i-1][j]= xlutil.getCellData("Sheet1", i, j);
			}
		}
		return loginData;
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
}
