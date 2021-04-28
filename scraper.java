// ============================================================================
// scraper.java
// Coinscraper project/ Cobalt Challenge
// Author: Kray Nguyen
// version 1
// date: 4/28/2021
// ============================================================================

// a lot of imports, but necessary
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class scraper{
	
	// ============================================================================
	// Request url access, get information of each coin, create new token object, 
	// then add that coin into an arrayList for storing purposes.
	// Finally, print to CSV file
	// ============================================================================
    public static void main(String[] args){
    	// arrayList data structure to contain each coin
    	ArrayList<token> list = new ArrayList<token>();
    	  
        try {
        	// url request
        	String url = "https://coinmarketcap.com/all/views/all/";
        	Document doc = Jsoup.connect(url).get();
        	
        	for(Element row: doc.select("div.cmc-table__table-wrapper-outer tr.cmc-table-row")) {
        		if((row.select(".cmc-table__cell--sort-by__name.cmc-table__cell--left.cmc-table__cell--sortable.cmc-table__cell--sticky.cmc-table__cell").text().equals(""))){
        			continue;
        		} else {
        			// get name
        			String name = row.select(".cmc-table__cell--sort-by__name.cmc-table__cell--left.cmc-table__cell--sortable.cmc-table__cell--sticky.cmc-table__cell").text();
        
        			// get symbol from url
        			String symbol = row.select(".cmc-table__cell--sort-by__symbol.cmc-table__cell--left.cmc-table__cell--sortable.cmc-table__cell").text();
        					
        			// get market cap
        			String market_cap = row.select(".cmc-table__cell--sort-by__market-cap.cmc-table__cell--right.cmc-table__cell--sortable.cmc-table__cell").text();
        			
        		
        			// get price
        			String price = row.select(".cmc-table__cell--sort-by__price.cmc-table__cell--right.cmc-table__cell--sortable.cmc-table__cell").text();
       	
        			// get circulating supply from url
        			String circ_supply = row.select(".cmc-table__cell--sort-by__circulating-supply.cmc-table__cell--right.cmc-table__cell--sortable.cmc-table__cell").text();
        			
        		 
        			// get volume 24h 
        			String volume_info = row.select(".cmc-table__cell--sort-by__volume-24-h.cmc-table__cell--right.cmc-table__cell--sortable.cmc-table__cell").text();
        			
        	
        			// get hour change
        			String hr_change = row.select(".cmc-table__cell--sort-by__percent-change-1-h.cmc-table__cell--right.cmc-table__cell--sortable.cmc-table__cell").text();
        		
        			
        			// get day change
        			String day_change = row.select(".cmc-table__cell--sort-by__percent-change-24-h.cmc-table__cell--right.cmc-table__cell--sortable.cmc-table__cell").text();
        			
        			
        			// get week change
        			String week_change = row.select(".cmc-table__cell--sort-by__percent-change-7-d.cmc-table__cell--right.cmc-table__cell--sortable.cmc-table__cell").text();
        			
        			
        			// create new token with above info and add to the list
        			token coin = new token(name, symbol, market_cap, price, circ_supply, volume_info, hr_change, day_change, week_change);
        			list.add(coin);
        		}
        		
        	}
      
        	printCSV(list);
        	
        	
        } catch(Exception excep) {
        	
        	excep.printStackTrace();
        }
       
    }
    
 // ============================================================================
 // 
 // ============================================================================
    
    public static void printCSV(ArrayList<token> list) throws IOException {
    	try {
    		// for excel headers
    		String[] columns = { "Name", "Symbol", "Market cap", "Price", "Circulating supply",
					"Volume(24h)", "%1h", "%24h", "%7d"};
    		
    		// create excel workbook and format it
    		Workbook excel = new XSSFWorkbook();
    		Sheet sheet = excel.createSheet("token");
    		Font header = excel.createFont();
    		header.setBold(true);
    		header.setFontHeightInPoints((short)17);
    		header.setColor(IndexedColors.RED.getIndex());
    		CellStyle headerCellStyle = excel.createCellStyle();
    		headerCellStyle.setFont(header);
    		
    		// set up header row in excel file
    		Row header_row = sheet.createRow(0);
    		for(int i = 0; i < columns.length;i++) {
    			Cell cell = header_row.createCell(i);
    			cell.setCellValue(columns[i]);
    			cell.setCellStyle(headerCellStyle);
    		}
    		
    		// fill in coin info in excel
    		int row_num = 1;
    		for(token coin: list) {
    			Row row = sheet.createRow(row_num++);
    			row.createCell(0).setCellValue(coin.getName());
    			row.createCell(1).setCellValue(coin.getSymbol());
    			row.createCell(2).setCellValue(coin.getMarketCap());
    			row.createCell(3).setCellValue(coin.getPrice());
    			row.createCell(4).setCellValue(coin.getCircSupply());
    			row.createCell(5).setCellValue(coin.getVol());
    			row.createCell(6).setCellValue(coin.getHourChange());
    			row.createCell(7).setCellValue(coin.getDayChange());
    			row.createCell(8).setCellValue(coin.getWeekChange());
    		}
    		
    		// resize cell if necessary
    		for(int i = 0; i < columns.length;i++) {
    			sheet.autoSizeColumn(i);
    		}
    		
    		// write to xlsx file
    		FileOutputStream fileOut = new FileOutputStream("coin.xlsx");
    		excel.write(fileOut);
    		
    		// close files
    		fileOut.close();
    		excel.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
