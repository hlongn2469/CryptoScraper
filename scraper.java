import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class scraper{
	
    public static void main(String[] args){
    	// arrayList data structure to contain each coin
    	ArrayList<token> list = new ArrayList<token>();
    	
        
        
        try {
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
    
    public static void printCSV(ArrayList<token> list) {
    	File csvFile = new File("coin.csv");
    	try {
			PrintWriter out = new PrintWriter(csvFile);
			for(token coin: list) {
				out.printf("%s, %s, %s, %s, %s, %s, %s, %s, %s\n", coin.getName(), coin.getSymbol(), coin.getMarketCap(), coin.getPrice(),
						coin.getCircSupply(), coin.getVol(), coin.getHourChange(), coin.getDayChange(), coin.getWeekChange());
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
