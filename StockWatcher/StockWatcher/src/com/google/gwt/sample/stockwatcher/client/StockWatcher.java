package com.google.gwt.sample.stockwatcher.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StockWatcher implements EntryPoint {
	private VerticalPanel mainPanel = new VerticalPanel();
	private FlexTable stockFlexTable = new FlexTable();
	private HorizontalPanel addPanel = new HorizontalPanel();
	private TextBox newSymbolTextBox = new TextBox();
	private Button newSymbolAddButton = new Button();
	private Label lastUpdatedLabel = new Label();
	private List<String> stocks = new ArrayList<>();
	private int REFRESH_INTERVAL = 5000;
	private static final String JSON_URL = GWT.getModuleBaseURL() + "stockPrices?q=";
	private Label errorMsgLabel = new Label();
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Please sign in to your Google Account to "
			+ "access the StockWatcher application.");
	private Anchor signInLink = new Anchor("Sign In");
    private final StockServiceAsync stockService = GWT.create(StockService.class);

	private Anchor signOutLink = new Anchor("Sign Out");

	@Override
	public void onModuleLoad() {
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL()+ "StockWatcher.html", new AsyncCallback<LoginInfo>() {
			public void onFailure(Throwable error) {
				//System.out.println("Login Failed");
				handleError(error);
			}
	
			public void onSuccess(LoginInfo result) {
				System.out.println("loggedIn: " + result.getEmailAddress());
				loginInfo = result;
			    if(loginInfo.isLoggedIn()) {
			    	//System.out.println("I am logged in");
			    	loadStockWatcher();
			    } else {
			    	loadLogin();
			    }
		     }
	    });	
	}
	
	private void loadLogin() {
	    // Assemble login panel.
	    signInLink.setHref(loginInfo.getLoginUrl());
	    loginPanel.add(loginLabel);
	    loginPanel.add(signInLink);
	    RootPanel.get("stockList").add(loginPanel);
	}
	
	
	public void loadStockWatcher() {
		// TODO Create table for stock data.
		stockFlexTable.setText(0, 0, "Symbol");
		stockFlexTable.setText(0, 1, "Price");
		stockFlexTable.setText(0, 2, "Change");
		stockFlexTable.setText(0, 3, "Remove");
	    stockFlexTable.setCellPadding(6);

	    // Add styles to elements in the stock list table.
	    stockFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
	    stockFlexTable.addStyleName("watchList");
	    stockFlexTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
	    stockFlexTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
	    stockFlexTable.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");
	    
	    loadStocks();
	    
	    // TODO Assemble Add Stock panel.
		addPanel.add(newSymbolTextBox);
		addPanel.add(newSymbolAddButton);
		addPanel.addStyleName("addPanel");
		
		// TODO Assemble Main panel.
		mainPanel.add(signOutLink);
	    mainPanel.add(stockFlexTable);
	    mainPanel.add(addPanel);
	    mainPanel.add(lastUpdatedLabel);
	    mainPanel.add(errorMsgLabel);
	    
		// TODO Associate the Main panel with the HTML host page.
	    RootPanel.get("stockList").add(mainPanel);
	    
	    newSymbolAddButton.setText("Add Stock");
	    
	    // TODO Move cursor focus to the input box.
	    newSymbolTextBox.setFocus(true);
	    
	    //TODO Setup timer to refresh list automatically
	    Timer refreshTimer = new Timer(){
	    	@Override
	    	public void run() {
	    		refreshWatchList();
	    	}
	    };
	    refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
	    
	    //TODO Add event handler for button to listen and act on click event
	    newSymbolAddButton.	addClickHandler(new ClickHandler() {
	    	public void onClick(ClickEvent event) {
	    		addStock();
	    	}
	    });
	    
	    //TODO Add event handler for text box to listen and respond to keyboard "enter" event
	    newSymbolTextBox.addKeyDownHandler(new KeyDownHandler(){
	    	
	    	@Override
			public void onKeyDown(KeyDownEvent event) {
				if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					addStock();
				}
				
			}
		});
	}
	
	private void loadStocks() {
	    stockService.getStocks(new AsyncCallback<String[]>() {
	      public void onFailure(Throwable error) {
	    	  handleError(error);
	      }
	      public void onSuccess(String[] symbols) {
	        displayStocks(symbols);
	      }
	    });
	  }

	  private void displayStocks(String[] symbols) {
	    for (String symbol : symbols) {
	      displayStock(symbol);
	    }
	  }
	  
	private void addStock(){
		final String symbol = newSymbolTextBox.getText().trim().toUpperCase();
		
		if(!symbol.matches("^[0-9A-Z&#92;&#92;.]{1,10}$")) {
			Window.alert("'" + symbol + "' is not a valid symbol.");
			newSymbolTextBox.selectAll();
			return;
		}
		newSymbolTextBox.setText("");

	    // TODO Don't add the stock if it's already in the table.
		if(stocks.contains(symbol))
			return;
		
	    // TODO Add the stock to the table
		//displayStock(symbol);
		addStock(symbol);
		
	}
	private void addStock(final String symbol) {
	    stockService.addStock(symbol, new AsyncCallback<Void>() {
	      public void onFailure(Throwable error) {
	    	  handleError(error);
	      }
	      public void onSuccess(Void ignore) {
	        displayStock(symbol);
	      }
	    });
	  }
	private void displayStock(final String symbol) {
		int row = stockFlexTable.getRowCount();
		stocks.add(symbol);
		stockFlexTable.setText(row, 0, symbol);
	    
		// TODO Add a button to remove this stock from the table.
		Button remButton = new Button();
		remButton.setText("X");
		remButton.addStyleDependentName("remove");
		remButton.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		          removeStock(symbol);
		        }
		      });
		stockFlexTable.setWidget(row, 3, remButton);
	    
		
		stockFlexTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
	    stockFlexTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");
	    stockFlexTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");
	    refreshWatchList();
	}
	private void removeStock(final String symbol) {
	    stockService.removeStock(symbol, new AsyncCallback<Void>() {
	      public void onFailure(Throwable error) {
	    	  handleError(error);
	      }
	      public void onSuccess(Void ignore) {
	        undisplayStock(symbol);
	      }
	    });
	  }

	  private void undisplayStock(String symbol) {
	    int removedIndex = stocks.indexOf(symbol);
	    stocks.remove(removedIndex);
	    stockFlexTable.removeRow(removedIndex+1);
	  }
	  private void handleError(Throwable error) {
		    Window.alert(error.getMessage());
		    if (error instanceof NotLoggedInException) {
		      Window.Location.replace(loginInfo.getLogoutUrl());
		    }
		  }
	private void refreshWatchList() {
		//Below is the client side implementation of generating stock prices 
		/*final double MAX_PRICE = 100.0; // $100.00
		final double MAX_PRICE_CHANGE = 0.02; // +/- 2%
		
		StockPrice[] prices = new StockPrice[stocks.size()];
		for (int i = 0; i < stocks.size(); i++) {
			double price = Random.nextDouble() * MAX_PRICE;
			double change = price * MAX_PRICE_CHANGE
					* (Random.nextDouble() * 2.0 - 1.0);
			
			prices[i] = new StockPrice(stocks.get(i), price, change);
		}
		
		updateTable(prices);
		*/
		
		//Query the server for the stock prices
		StringBuffer url = new StringBuffer(JSON_URL);
		for(String symbol: stocks) {
			url.append(symbol).append("+");
		}
		url.setLength(url.length()-1);
	    String finalURL = URL.encode(url.toString());
	    //System.out.println("URL: " + finalURL);
	    //TODO Send to server
	    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, finalURL);
	    try {
	    	  Request request = builder.sendRequest(null, new RequestCallback() {
	    	    public void onError(Request request, Throwable exception) {
	    	      displayError("Couldn't retrieve JSON");
	    	    }

	    	        public void onResponseReceived(Request request, Response response) {
	    	      if (200 == response.getStatusCode()) {
	    	        updateTable(JsonUtils.<JsArray<StockData>>safeEval(response.getText()));
	    	      } else {
	    	        displayError("Couldn't retrieve JSON (" + response.getStatusText()
	    	            + ")");
	    	      }
	    	    }
	    	  });
    	} catch (RequestException e) {
	    	  displayError("Couldn't retrieve JSON");
    	}
	}

	private void updateTable(JsArray<StockData> prices) {
		for (int i = 0; i < prices.length(); i++) {
			updateTable(prices.get(i));
		}
	      // Display time stamp showing last refresh.
	      DateTimeFormat dateFormat = DateTimeFormat.getFormat(
	        DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM);
	      lastUpdatedLabel.setText("Last update : " 
	        + dateFormat.format(new Date()));
	      
	      errorMsgLabel.setVisible(false);
    }

	private void updateTable(StockData stockPrice) {
		if(!stocks.contains(stockPrice.getSymbol()))
			return;
		
		int row = stocks.indexOf(stockPrice.getSymbol()) + 1;
		String priceText = NumberFormat.getFormat("#,##0.00").format(stockPrice.getPrice());
		
		NumberFormat changeFormat = NumberFormat.getFormat("+#,##0.00;-#,##0.00");
		String changeText = changeFormat.format(stockPrice.getChange());
	    String changePercentText = changeFormat.format(stockPrice.getChangePercent());
	 // Populate the Price and Change fields with new data.
		stockFlexTable.setText(row, 1, priceText);
		Label changeWidget = new Label();
		stockFlexTable.setWidget(row, 2, changeWidget );
		
		//changeWidget = (Label)stockFlexTable.getWidget(row, 2);
	    changeWidget.setText(changeText + " (" + changePercentText + "%)");
	     // Change the color of text in the Change field based on its value.
	    String changeStyleName = "noChange";
	    if (stockPrice.getChangePercent() < -0.1f) {
	      changeStyleName = "negativeChange";
	    }
	    else if (stockPrice.getChangePercent() > 0.1f) {
	      changeStyleName = "positiveChange";
	    }

	    changeWidget.setStyleName(changeStyleName);
		
	}
	/**
     * If can't get JSON, display error message.
     * @param error
     */
	private void displayError(String error) {
	System.out.println("I am in error");
	  errorMsgLabel.setText("Error: " + error);
	  errorMsgLabel.setVisible(true);
	}

	
}