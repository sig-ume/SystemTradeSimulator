/**
 *
 */
package jp.sigre.systemtradesimulator.matcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;


/**
 * Date型と8桁の文字列をassertするクラス。
 * org.junit.internal.matchers.TypeSafeMatcherを継承して機能拡張する。
 */
public class DateMatcher extends TypeSafeMatcher<Date> {

    private String expected;
    private Date expectedDate;

    public DateMatcher(String expected) {
        this.expected = expected;
        if (expected == null) {
            return;
        }
        // 期待値が有効な日付かのチェックを事前に行う。
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        try {
            expectedDate = yyyyMMdd.parse(expected);
        } catch (ParseException e) {
            throw new IllegalArgumentException("8桁の有効な日付を指定してください。", e);
        }
    }

    /**
     * assertThatの期待値に指定するMatcherのファクトリメソッド。
     * @param date 期待値（8桁の有効な日付)
     * @return Matcher
     */
    public static DateMatcher isDate(String date) {
        return new DateMatcher(date);
    }

    /**
     * メッセージの構築。
     * 期待値をDateに変換してメッセージに追加する。(時間部分は差分として出ちゃうけど、めんどいのでこの辺は適当)
     * @param description
     */
    public void describeTo(Description description) {
        if (expected == null) {
            description.appendText(expected);
        } else {
            description.appendValue(expectedDate);
        }
    }

    /**
     * 実際の値との比較を行う。
     * @param item 実際の値
     * @return 一致している場合はtrue
     */
    @Override
    public boolean matchesSafely(Date item) {
        if (item == null && expected == null)  {
            return true;
        }
        // 実際の値を8桁の文字列日付に変換して比較を行う。
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        String actual = yyyyMMdd.format(item);
        return actual.equals(expected);
    }
}