package jp.sigre.trademath;

import jp.sigre.systemtradesimulator.utils.CsvUtils;

import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;

/**
 * スピアマンの相関係数による株価推移の相関チェック
 * TODO：株主優待での値動きの仮定値と比較で相関がとれないか
 * TODO：スタバと吉野家の株価比較（スタバはまだ優待あるのか。）
 * TODO：優待が強い株を探す。
 * @author sigre
 *
 */
public class SpearmanCorrelationSimulator {

	static final String NTT_PATH = "target\\1417-T_NTT.csv";
	static final String DNA_PATH = "target\\2432-T_DeNA.csv";
	static int valueType = 4; //終値

	public SpearmanCorrelationSimulator() {
	}

	public static void main(String[] args) {

		CsvUtils csvNtt  = new CsvUtils(NTT_PATH);
		CsvUtils csvDeNA = new CsvUtils(DNA_PATH);


		double[] data_Ntt  = csvNtt .makeValuesArray(valueType);
		double[] data_DeNA = csvDeNA.makeValuesArray(valueType);

		SpearmansCorrelation cor=new SpearmansCorrelation();
		System.out.println( cor.correlation(data_Ntt, data_DeNA));

	}

}
