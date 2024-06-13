package com.auction.model;

public class BidPojo {
int bidAmount;

public int getBidAmount() {
	return bidAmount;
}

public void setBidAmount(int bidAmount) {
	this.bidAmount = bidAmount;
}

public BidPojo(int bidAmount) {
	super();
	this.bidAmount = bidAmount;
}
public BidPojo()
{
	
}
@Override
public String toString() {
	return "BidPojo [bidAmount=" + bidAmount + "]";
}

}
