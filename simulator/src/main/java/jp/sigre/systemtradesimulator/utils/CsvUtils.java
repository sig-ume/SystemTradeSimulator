/**
 *
 */
package jp.sigre.systemtradesimulator.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import jp.sigre.systemtradesimulator.varginhunt.KabuDataBean;

/**
 * CSVファイルを操作するユーティリティ
 * @author sigre
 *
 */
public class CsvUtils {

	File target = null;

	public CsvUtils(String path) {
		target = new File(path);
	}

	public CsvUtils(File file) {
		target = file;
	}

	/**
	 * k-db.comの個別銘柄株価データのうち指定した種別の値を
	 * Double配列に格納する。
	 * @param valueType	CSV格納順 1:始値、2:高値、3:安値、4:終値、5:出来高、6:売買代金
	 * @return
	 */
	public double[] makeValuesArray(int valueType) {

		int count = 0;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		List<String[]> tokenList = splitCSV();

		double[] result = new double[tokenList.size()-2];

		for (String[] strList : tokenList) {
			try {
				dateFormat.parse(strList[0]);
			} catch (ParseException e) {
				continue;
			}

			result[count] = Double.parseDouble(strList[valueType]);
			count++;

		}

		return result;
	}

	/**
	 * k-db.comの個別銘柄株価データをKabuDataBeanクラスのリストに格納する。
	 * CSV格納順 0:日付、1:始値、2:高値、3:安値、4:終値、5:出来高、6:売買代金
	 * @return List<KabuDataBean>
	 */
	public List<KabuDataBean> makeKabuDataList() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;

		List<String[]> tokenList = splitCSV();
		List<KabuDataBean> result = new ArrayList<KabuDataBean>();

		for (String[] strList : tokenList) {
			KabuDataBean kabuData = new KabuDataBean();

			try {
				date = dateFormat.parse(strList[0]);
			} catch (ParseException e) {
				continue;
			}
			kabuData.setDate(date);
			kabuData.setStartValue(Double.parseDouble(strList[1]));
			kabuData.setHighValue(Double.parseDouble(strList[2]));
			kabuData.setLowValue(Double.parseDouble(strList[3]));
			kabuData.setEndValue(Double.parseDouble(strList[4]));
			kabuData.setDekidaka(Double.parseDouble(strList[5]));
			kabuData.setBaibai(Double.parseDouble(strList[6]));

			result.add(kabuData);

		}

		return result;
	}


	public List<String[]> splitCSV() {
		BufferedReader br = null;;
		List<String[]> result = new ArrayList<>();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(target), "Utf-8"));

			String line = "";

			while((line = br.readLine())!=null) {
				String[] strAry = line.split(",");
				result.add(strAry);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Collections.reverse(result);
		return result;

	}
}
