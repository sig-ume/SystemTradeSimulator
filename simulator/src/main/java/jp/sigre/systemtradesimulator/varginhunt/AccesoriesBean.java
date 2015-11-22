/**
 *
 */
package jp.sigre.systemtradesimulator.varginhunt;

/**
 * @author sigre
 *
 */
public class AccesoriesBean extends KabuDataBean {

	private double rsi = 50;
	private double averageGain = 0;
	private double averageLoss = 0;
	private double lastEndValue = 0;
	private double averageLine3 = 0;

	public double getAverageLine3() {
		return averageLine3;
	}

	public void setAverageLine3(double averageLine3) {
		this.averageLine3 = averageLine3;
	}

	public AccesoriesBean(KabuDataBean kabu) {
		setDate(kabu.getDate());
		setStartValue(kabu.getStartValue());
		setHighValue(kabu.getHighValue());
		setLowValue(kabu.getLowValue());
		setEndValue(kabu.getEndValue());
		setDekidaka(kabu.getDekidaka());
		setBaibai(kabu.getBaibai());
	}

	public AccesoriesBean() {
	}

	/**
	 * @return rsi
	 */
	public double getRsi() {
		return rsi;
	}
	/**
	 * @param rsi セットする rsi
	 */
	public void setRsi(double rsi) {
		this.rsi = rsi;
	}
	/**
	 * @return lastEndValue
	 */
	public double getLastEndValue() {
		return lastEndValue;
	}
	/**
	 * @param lastEndValue セットする lastEndValue
	 */
	public void setLastEndValue(double lastEndValue) {
		this.lastEndValue = lastEndValue;
	}
	/**
	 * @return averageGain
	 */
	public double getAverageGain() {
		return averageGain;
	}
	/**
	 * @param averageGain セットする averageGain
	 */
	public void setAverageGain(double averageGain) {
		this.averageGain = averageGain;
	}
	/**
	 * @return averageLoss
	 */
	public double getAverageLoss() {
		return averageLoss;
	}
	/**
	 * @param averageLoss セットする averageLoss
	 */
	public void setAverageLoss(double averageLoss) {
		this.averageLoss = averageLoss;
	}

	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "\n" +
				"AccesoriesBean [rsi=" + rsi + ", averageGain=" + averageGain
				+ ", averageLoss=" + averageLoss + ", lastEndValue="
				+ lastEndValue + ", averageLine3=" + averageLine3 + "]";
	}



}
