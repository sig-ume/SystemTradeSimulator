/**
 *
 */
package jp.sigre.systemtradesimulator.matcher;

import jp.sigre.systemtradesimulator.varginhunt.AccesoriesBean;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;


/**
 * @author sigre
 *
 */
public class AccesoriesBeanMatcher extends TypeSafeMatcher<AccesoriesBean> {

    public static AccesoriesBeanMatcher AccesoriesBean(AccesoriesBean AccesoriesBean) {
        return new AccesoriesBeanMatcher(AccesoriesBean);
    }

    private AccesoriesBean expected;
    private String fieldName;
    private Object expectedValue;
    private Object actualValue;
    private KabuDataBeanMatcher kabuMatcher;

	/**
	 * @param expected
	 */
	public AccesoriesBeanMatcher(AccesoriesBean expected) {
		kabuMatcher = new KabuDataBeanMatcher(expected);
		this.expected = expected;
	}

	protected boolean matchesSafely(AccesoriesBean actual) {
        if (actual == null && expected == null) {
            return true;
        } else if (actual == null || expected == null) {
            return false;
        }
        //TODO:KabuDataBeanMatcherと共通化
//        if (!kabuMatcher.matchesSafely(actual)) {
//            return false;
//        }
        if (notEquals(actual.getDate(), expected.getDate())) {
            fieldName = "Date";
            actualValue = actual.getDate();
            expectedValue = expected.getDate();
            return false;
        }
        if (notEquals(actual.getStartValue(), expected.getStartValue())) {
            fieldName = "StartValue";
            actualValue = actual.getStartValue();
            expectedValue = expected.getStartValue();
            return false;
        }
        if (notEquals(actual.getHighValue(), expected.getHighValue())) {
            fieldName = "HighValue";
            actualValue = actual.getHighValue();
            expectedValue = expected.getHighValue();
            return false;
        }
        if (notEquals(actual.getLowValue(), expected.getLowValue())) {
            fieldName = "getLowValue()";
            actualValue = actual.getLowValue();
            expectedValue = expected.getLowValue();
            return false;
        }
        if (notEquals(actual.getEndValue(), expected.getEndValue())) {
            fieldName = "getEndValue()";
            actualValue = actual.getEndValue();
            expectedValue = expected.getEndValue();
            return false;
        }
        if (notEquals(actual.getDekidaka(), expected.getDekidaka())) {
            fieldName = "getDekidaka()";
            actualValue = actual.getDekidaka();
            expectedValue = expected.getDekidaka();
            return false;
        }
        if (notEquals(actual.getBaibai(), expected.getBaibai())) {
            fieldName = "getBaibai()";
            actualValue = actual.getBaibai();
            expectedValue = expected.getBaibai();
            return false;
        }
        if (notEquals(actual.getAverageGain(), expected.getAverageGain())) {
            fieldName = "AverageGain";
            actualValue = actual.getAverageGain();
            expectedValue = expected.getAverageGain();
            return false;
        }
        if (notEquals(actual.getAverageLoss(), expected.getAverageLoss())) {
            fieldName = "getAverageLoss()";
            actualValue = actual.getAverageLoss();
            expectedValue = expected.getAverageLoss();
            return false;
        }
        if (notEquals(actual.getLastEndValue(), expected.getLastEndValue())) {
            fieldName = "getLastEndValue()";
            actualValue = actual.getLastEndValue();
            expectedValue = expected.getLastEndValue();
            return false;
        }
        if (notEquals(actual.getRsi(), expected.getRsi())) {
            fieldName = "RSI";
            actualValue = actual.getRsi();
            expectedValue = expected.getRsi();
            return false;
        }
        return true;
    }

    private boolean notEquals(Object o1, Object o2) {
        if (o1 == null) {
            return o2 != null;
        } else {
            return !o1.equals(o2);
        }
    }

    public void describeTo(Description desc) {
        if (fieldName == null || expected == null) {
            desc.appendValue(expected);
        } else {
            desc.appendValue(expected);
            desc.appendText(String.format("%n - %s is ", fieldName)).appendValue(expectedValue).appendText(", but ")
                    .appendValue(actualValue);
        }
    }

}
