// ============================================================================
// token.java
// Coinscraper project/ Cobalt Challenge
// Author: Kray Nguyen
// version 1
// date: 4/28/2021
// ============================================================================

// ============================================================================
// This class is mainly responsible for holding token information
// ============================================================================
public class token {
	// token atrributes
	private String token_name, symbol, market_cap, volume, circulating_supply, price, hour_change, day_change, week_change;
	
	// parameterized constructor
	public token(String name, String symbol, String market_cap, String price, String cir_supply, String vol, 
			String hour_change, String day_change, String week_change){
		this.token_name = name;
		this.symbol = symbol;
		this.market_cap = market_cap;
		this.volume = vol;
		this.circulating_supply = cir_supply;
		this.price = price;
		this.hour_change = hour_change;
		this.day_change = day_change;
		this.week_change = week_change;
	}
	
	// getters for the attributes
	public String getName() {
		return token_name;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public String getVol() {
		return volume;
	}
	
	public String getMarketCap() {
		return market_cap;
	}
	
	public String getCircSupply() {
		return circulating_supply;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getHourChange() {
		return hour_change;
	}
	
	public String getDayChange() {
		return day_change;
	}
	
	public String getWeekChange() {
		return week_change;
	}
	
}
