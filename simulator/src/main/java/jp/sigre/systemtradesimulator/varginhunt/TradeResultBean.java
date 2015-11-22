/**
 *
 */
package jp.sigre.systemtradesimulator.varginhunt;

import java.util.Date;

/**
 * @author sigre
 *
 */
public class TradeResultBean {

	private Date buyDate;
	private double buyValue;
	private Date sellDate;
	private double sellValue;

	/**
	 * @return buyDate
	 */
	public Date getBuyDate() {
		return buyDate;
	}
	/**
	 * @param buyDate セットする buyDate
	 */
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	/**
	 * @return buyValue
	 */
	public double getBuyValue() {
		return buyValue;
	}
	/**
	 * @param buyValue セットする buyValue
	 */
	public void setBuyValue(double buyValue) {
		this.buyValue = buyValue;
	}
	/**
	 * @return sellDate
	 */
	public Date getSellDate() {
		return sellDate;
	}
	/**
	 * @param sellDate セットする sellDate
	 */
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	/**
	 * @return sellValue
	 */
	public double getSellValue() {
		return sellValue;
	}
	/**
	 * @param sellValue セットする sellValue
	 */
	public void setSellValue(double sellValue) {
		this.sellValue = sellValue;
	}
	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TradeResultBean [buyDate=" + buyDate + ", buyValue=" + buyValue
				+ ", sellDate=" + sellDate + ", sellValue=" + sellValue + "]";
	}



}
