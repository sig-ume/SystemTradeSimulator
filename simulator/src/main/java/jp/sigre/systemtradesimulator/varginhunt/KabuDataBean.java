/**
 *
 */
package jp.sigre.systemtradesimulator.varginhunt;

import java.util.Date;

/**
 * @author sigre
 *
 */
public class KabuDataBean {

	/**
	 * 日付（yyyy-MM-dd)
	 */
	private Date date;

	/**
	 * 始値
	 */
	private double startValue;

	/**
	 * 高値
	 */
	private double highValue;

	/**
	 * 安値
	 */
	private double lowValue;

	/**
	 * 終値
	 */
	private double endValue;


	/**
	 * 出来高
	 */
	double dekidaka;

	/**
	 * 売買代金
	 */
	private double baibai;

	/**
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date セットする date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return startValue
	 */
	public double getStartValue() {
		return startValue;
	}

	/**
	 * @param startValue セットする startValue
	 */
	public void setStartValue(double startValue) {
		this.startValue = startValue;
	}

	/**
	 * @return highValue
	 */
	public double getHighValue() {
		return highValue;
	}

	/**
	 * @param highValue セットする highValue
	 */
	public void setHighValue(double highValue) {
		this.highValue = highValue;
	}

	/**
	 * @return lowValue
	 */
	public double getLowValue() {
		return lowValue;
	}

	/**
	 * @param lowValue セットする lowValue
	 */
	public void setLowValue(double lowValue) {
		this.lowValue = lowValue;
	}

	/**
	 * @return endValue
	 */
	public double getEndValue() {
		return endValue;
	}

	/**
	 * @param endValue セットする endValue
	 */
	public void setEndValue(double endValue) {
		this.endValue = endValue;
	}

	/**
	 * @return dekidaka
	 */
	public double getDekidaka() {
		return dekidaka;
	}

	/**
	 * @param dekidaka セットする dekidaka
	 */
	public void setDekidaka(double dekidaka) {
		this.dekidaka = dekidaka;
	}

	/**
	 * @return baibai
	 */
	public double getBaibai() {
		return baibai;
	}

	/**
	 * @param baibai セットする baibai
	 */
	public void setBaibai(double baibai) {
		this.baibai = baibai;
	}

	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KabuDataBean [date=" + date + ", startValue=" + startValue
				+ ", highValue=" + highValue + ", lowValue=" + lowValue
				+ ", endValue=" + endValue + ", dekidaka=" + dekidaka
				+ ", baibai=" + baibai + "]";
	}



}
