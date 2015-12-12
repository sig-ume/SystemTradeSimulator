package jp.sigre.trademath;

import jp.sigre.systemtradesimulator.utils.CsvUtils;

import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;

/**
 * スピアマンの相関係数による株価推移の相関チェック
 * TODO：株主優待での値動きの仮定値と比較で相関がとれないか
 * TODO：吉野家の株価比較 http://diamond.jp/articles/-/61407 http://diamond.jp/category/z-kabunushiyutai
 * TODO：優待が強い株を探す。
 * @author sigre
 *
 */
public class SpearmanCorrelationSimulator {

	static final String NTT_PATH = "file\\1417-T\\1417-T_NTT.csv";
	static final String DNA_PATH = "file\\2432-T\\2432-T_DeNA.csv";

	static final String NTT_2013 = "file\\1417-T\\9432-T_2013.csv";
	static final String NTT_2014 = "file\\1417-T\\9432-T_2014.csv";

	static final String KIR_2013 = "file\\2503-T\\2503-T_2013.csv";
	static final String KIR_2014 = "file\\2503-T\\2503-T_2014.csv";

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

		CsvUtils ntt2013  = new CsvUtils(NTT_2013);
		CsvUtils ntt2014 = new CsvUtils(NTT_2014);


		double[] data_2013 = new double[245];
		double[] data_2014 = new double[245];

		System.arraycopy(ntt2013.makeValuesArray(valueType), 0,data_2013 , 0 ,ntt2013.makeValuesArray(valueType).length);
		System.arraycopy(ntt2014.makeValuesArray(valueType), 0,data_2014,  0 ,ntt2014.makeValuesArray(valueType).length);


		SpearmansCorrelation cor2=new SpearmansCorrelation();
		System.out.println( cor2.correlation(data_2013, data_2014));


		CsvUtils kir2013  = new CsvUtils(KIR_2013);
		CsvUtils kir2014 = new CsvUtils(KIR_2014);


		double[] data2_2013 = new double[245];
		double[] data2_2014 = new double[245];

		System.arraycopy(kir2013.makeValuesArray(valueType), 0,data2_2013 , 0 ,kir2013.makeValuesArray(valueType).length);
		System.arraycopy(kir2014.makeValuesArray(valueType), 0,data2_2014,  0 ,kir2014.makeValuesArray(valueType).length);


		SpearmansCorrelation cor3=new SpearmansCorrelation();
		System.out.println( cor3.correlation(data2_2013, data2_2014));
	}

}
