/**
 *
 */
package jp.sigre.systemtradesimulator.utils;

import static jp.sigre.systemtradesimulator.matcher.KabuDataBeanMatcher.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import jp.sigre.systemtradesimulator.varginhunt.KabuDataBean;

import org.junit.Test;

/**
 * @author sigre
 *
 */
public class CsvUtlisTest {

	String path = "file\\1417-T\\1417-T_NTT.csv";
	CsvUtils target = new CsvUtils(path);
	/**
	 * {@link jp.sigre.systemtradesimulator.utils.CsvUtils#makeKabuDataList()} のためのテスト・メソッド。
	 */
	@Test
	public void testMakeKabuDataList() {
		//準備
		List<KabuDataBean> result = target.makeKabuDataList();
		int expectedSize = 250;
		KabuDataBean expected = new KabuDataBean();
		expected.setDate(new Date(115, 10, 6));
		expected.setStartValue(1017);
		expected.setHighValue(1025);
		expected.setLowValue(1009);
		expected.setEndValue(1021);
		expected.setDekidaka(343100);
		expected.setBaibai(349447100);

		//検証
		assertThat(result.size(), is(expectedSize));
		assertThat(result.get(result.size()-1), is(KabuDataBean(expected)));
	}

	/**
	 * {@link jp.sigre.systemtradesimulator.utils.CsvUtils#makeKabuDataList()} のためのテスト・メソッド。
	 */
	@Test
	public void testMakeValuesArray_始値_引数1() {
		//準備
		double[] result = target.makeValuesArray(1);
		int expectedSize = 250;

		double expected0 = 1225;
		double expected2 = 1230;
		double expectedEnd = 1017;

		//検証
		assertThat(result.length, is(expectedSize));
		assertThat(result[0], is(expected0));
		assertThat(result[2], is(expected2));
		assertThat(result[result.length-1], is(expectedEnd));
	}

	/**
	 * {@link jp.sigre.systemtradesimulator.utils.CsvUtils#makeKabuDataList()} のためのテスト・メソッド。
	 */
	@Test
	public void testMakeValuesArray_終値_引数4() {
		//準備
		double[] result = target.makeValuesArray(4);
		int expectedSize = 250;

		double expected0 = 1231;
		double expected2 = 1237;
		double expectedEnd = 1021;

		//検証
		assertThat(result.length, is(expectedSize));
		assertThat(result[0], is(expected0));
		assertThat(result[2], is(expected2));
		assertThat(result[result.length-1], is(expectedEnd));
	}

}

