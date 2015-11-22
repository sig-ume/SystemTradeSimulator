/**
 *
 */
package jp.sigre.systemtradesimulator.matcher;

import jp.sigre.systemtradesimulator.varginhunt.KabuDataBean;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class KabuDataBeanMatcher extends TypeSafeMatcher<KabuDataBean> {

    public static Matcher<KabuDataBean> KabuDataBean(KabuDataBean KabuDataBean) {
        return new KabuDataBeanMatcher(KabuDataBean);
    }

    private KabuDataBean expected;
    private String fieldName;
    private Object expectedValue;
    private Object actualValue;

    public KabuDataBeanMatcher(KabuDataBean expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(KabuDataBean actual) {
        if (actual == null && expected == null) {
            return true;
        } else if (actual == null || expected == null) {
            return false;
        }
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
        return true;
    }

    private boolean notEquals(Object o1, Object o2) {
        if (o1 == null) {
            return o2 != null;
        } else {
            return !o1.equals(o2);
        }
    }

    @Override
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
