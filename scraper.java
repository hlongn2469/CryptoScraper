import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class scraper{
    public static void main(String[] args){
    	// arrayList data structure to contain each coin
    	ArrayList<token> list = new ArrayList<token>();
    	
        
        
        try {
        	String url = "https://www.coingecko.com/en?page=1";
        	Document doc = Jsoup.connect(url).get();
        	
        	for(Element row: doc.select("div.cmc-table__table-wrapper-outer tr.cmc-table-row")) {
        		String delims = "[ ]+";
        		if((row.select(".cmc-table__cell--sort-by__name.cmc-table__cell--left.cmc-table__cell--sortable.cmc-table__cell--sticky.cmc-table__cell").text().equals(""))){
        			continue;
        		} else {
        			// get name
        			String name = row.select(".cmc-table__cell--sort-by__name.cmc-table__cell--left.cmc-table__cell--sortable.cmc-table__cell--sticky.cmc-table__cell").text();
   
        			String[] token = name.split(delims);
    
        			System.out.println(name);
        	
        
        			// get symbol from url
        			String price = row.select(".cmc-table__cell--sort-by__symbol.cmc-table__cell--left.cmc-table__cell--sortable.cmc-table__cell").text();
        			System.out.println(price);
        		
        		
        		
        			// get market cap
        			String day_change = row.select(".cmc-table__cell--sort-by__market-cap.cmc-table__cell--right.cmc-table__cell--sortable.cmc-table__cell").text();
        			System.out.println(day_change);
        		
        			// get price
        			String week_change = row.select(".cmc-table__cell--sort-by__price.cmc-table__cell--right.cmc-table__cell--sortable.cmc-table__cell").text();
        			System.out.println(week_change);
        		
        		
        	
        			// get circulating supply from url
        			String mark_cap = row.select(".cmc-table__cell--sort-by__circulating-supply.cmc-table__cell--right.cmc-table__cell--sortable.cmc-table__cell").text();
        			System.out.println(mark_cap);
        		 
        		
        
        			// get volume 24h 
        			String volume_info = row.select(".cmc-table__cell--sort-by__volume-24-h.cmc-table__cell--right.cmc-table__cell--sortable.cmc-table__cell").text();
        			String[] volume_token = volume_info.split(delims);
        			String volume = volume_token[0];
        			System.out.println(volume);
        	
        			// get hour change
        			String supply_info = row.select(".cmc-table__cell--sort-by__percent-change-1-h.cmc-table__cell--right.cmc-table__cell--sortable.cmc-table__cell").text();
        			System.out.println(supply_info);
        			
        			// get day change
        			String a = row.select(".cmc-table__cell--sort-by__percent-change-24-h.cmc-table__cell--right.cmc-table__cell--sortable.cmc-table__cell").text();
        			System.out.println(a);
        			
        			// get week change
        			String b = row.select(".cmc-table__cell--sort-by__percent-change-7-d.cmc-table__cell--right.cmc-table__cell--sortable.cmc-table__cell").text();
        			System.out.println(b);
        			
        			System.out.println("--------");
        		}
        		
        	}
        } catch(Exception excep) {
        	
        	excep.printStackTrace();
        }
       
    }
}
