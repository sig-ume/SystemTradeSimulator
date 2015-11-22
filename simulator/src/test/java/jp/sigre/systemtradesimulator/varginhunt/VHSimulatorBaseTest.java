/**
 *
 */
package jp.sigre.systemtradesimulator.varginhunt;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import jp.sigre.systemtradesimulator.matcher.AccesoriesBeanMatcher;

import org.junit.Test;

/**
 * @author sigre
 *
 */
public class VHSimulatorBaseTest {

	final String PATH = "target\\\\1417-T\\\\1417-T_NTT.csv";
	VHSimulatorBase target = new VHSimulatorBase(PATH);

	/**
	 * {@link jp.sigre.systemtradesimulator.varginhunt.VHSimulatorBase#before()} のためのテスト・メソッド。
	 */
	@Test
	public void beforeTest() {
		int expectedSize = 250;
		AccesoriesBean expected = new AccesoriesBean();
		expected.setDate(new Date(114, 9, 29));
		expected.setStartValue(1225);
		expected.setHighValue(1245);
		expected.setLowValue(1211);
		expected.setEndValue(1231);
		expected.setDekidaka(310400);
		expected.setBaibai(381717000);
		expected.setAverageGain(0);
		expected.setAverageLoss(0);
		expected.setRsi(50);
		expected.setLastEndValue(0);
		expected.setAverageLine3(0);

		AccesoriesBean expected2 = new AccesoriesBean();
		expected2.setDate(new Date(114, 9, 31));
		expected2.setStartValue(1230);
		expected2.setHighValue(1240);
		expected2.setLowValue(1200);
		expected2.setEndValue(1237);
		expected2.setDekidaka(437300);
		expected2.setBaibai(535242500);
		expected2.setAverageGain(10.5);
		expected2.setAverageLoss(7.5);
		expected2.setRsi(100 - ( 100 / ( 1 + 10.5 / 7.5 )));
		expected2.setLastEndValue(1216);
		expected.setAverageLine3(1228);


		target.before();

		List<AccesoriesBean> actualList = target.list;

		assertThat(actualList.size(), is(expectedSize));
		assertThat(actualList.get(0), is(new AccesoriesBeanMatcher(expected)));
		assertThat(actualList.get(2), is(new AccesoriesBeanMatcher(expected2)));
	}

	@Test
	public void afterTest() {
		target.before();
		target.simulate();

		for (AccesoriesBean acc : target.list) {
			System.out.println(acc.toString());
		}

		target.after();


	}

}
