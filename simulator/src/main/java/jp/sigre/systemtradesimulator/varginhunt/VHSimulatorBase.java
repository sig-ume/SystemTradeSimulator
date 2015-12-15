/**
 *
 */
package jp.sigre.systemtradesimulator.varginhunt;

import java.util.ArrayList;
import java.util.List;

import jp.sigre.systemtradesimulator.utils.CsvUtils;

/**
 * VarginHunt（ギャップトレード）のシミュレーション
 * @author sigre
 *
 */
public class VHSimulatorBase {
	String path = "";
	final int RSI_KIKAN = 2;
	List<AccesoriesBean> list = new ArrayList<>();
	List<TradeResultBean> tradeResults = new ArrayList<>();
	double gap = 0.99; //TODO;引数へ

	public VHSimulatorBase(String path) {
		this.path = path;
	}

	/**
	 * シュミレーション前に実行するメソッド
	 * CSVファイルの取り込みとAccessoriesBeanの作成
	 */
	public void before() {
		CsvUtils csv = new CsvUtils(path);
		addAll(csv.makeKabuDataList());
	}

	/**
	 * シュミレーションメソッド
	 * 買い：前日のRSIが5以下。始値の９９％で購入。
	 * 売り：3期間平均線を終値が超えたとき、終値で購入
	 */
	public void simulate() {

		//買いを探すときは0,売りを探すときは1
		int flag = 0;
		TradeResultBean tradeResult = new TradeResultBean();
		tradeResults = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			if (flag == 0 && isBuyableDay(i)) {
				tradeResult = new TradeResultBean();
				setBuyedDayData(tradeResult, list.get(i));
				flag = 1;
			} else if (flag == 1 && isSellableDay(i)) {
				setSelledDayData(tradeResult, list.get(i));
				tradeResults.add(tradeResult);
				flag = 0;
			}
		}

	}

	/**
	 * シミュレート結果の出力など、後処理。
	 */
	public void after() {

		//全体日数
		int dateCount = list.size();

		//取引数
		int tradeCount = tradeResults.size();

		//勝利トレード数
		double winTradeCount = 0;

		//利益
		double profit = 0;

		//購入総額
		double buySum = 0;

		//保有日数
		double  keepTerm = 0.0;

		long maxKeepTerm = -1;

		long minKeepTerm = 9999;

		for (TradeResultBean tradeResult : tradeResults) {

			System.out.println(tradeResult.toString());

			double tempProfit = tradeResult.getSellValue() - tradeResult.getBuyValue();

			buySum += tradeResult.getBuyValue();

			//損益を加算
			profit += tempProfit;

			//勝数を加算
			if (tempProfit > 0 ) {
				winTradeCount++;
			}

			long tempKeepTerm = (tradeResult.getSellDate().getTime() - tradeResult.getBuyDate().getTime()) / (1000 * 60 * 60 * 24);

			keepTerm += tempKeepTerm;

			maxKeepTerm = (tempKeepTerm > maxKeepTerm) ? tempKeepTerm : maxKeepTerm;
			minKeepTerm = (tempKeepTerm < minKeepTerm) ? tempKeepTerm : minKeepTerm;

			System.out.println(keepTerm);
		}

		//勝率
		double winRate = winTradeCount / tradeCount;

		keepTerm /= tradeCount;

		//利益率
		double profitRate = profit / buySum;

		System.out.println(	"全体日数："	+ dateCount + "\t" +
							"取引数："		+ tradeCount  + "\t" +
							"勝利数："		+ winTradeCount + "\t" +
							"勝率："		+ winRate + "\t" +
							"利益："		+ String.format("%05g", profit) + "\t" +
							"利益率："		+ String.format("%05g", profitRate) + "\t" +
							"保有平均期間："+ keepTerm + "\t" +
							"保有最大期間："+ maxKeepTerm + "\t" +
							"保有最小期間："+ minKeepTerm);
	}



	private void addAll(List<KabuDataBean> kabuList) {


		for (KabuDataBean kabu : kabuList) {

			AccesoriesBean accesory = new AccesoriesBean(kabu);
			//			accesory.setLastEndValue(list.get(list.size()-1).getEndValue());
			//			accesory.setRsi(calcRSI(list, 2));
			list.add(accesory);

		}

		setRSI(RSI_KIKAN);
	}


	/**
	 * gap値を取得
	 * @return
	 */
	public double getGap() {
		return gap;
	}

	/**
	 * gap値を設定
	 * @param gap
	 */
	public void setGap(double gap) {
		this.gap = gap;
	}


	private void setRSI(int kikan) {

		int count = list.size();
		double[] rsis = calcFirstParam(RSI_KIKAN);

		list.get(kikan).setAverageGain (rsis[0]);
		list.get(kikan).setAverageLoss (rsis[1]);
		list.get(kikan).setRsi		   (rsis[2]);
		list.get(kikan).setAverageLine3(rsis[3]);
		list.get(kikan).setLastEndValue(list.get(kikan-1).getEndValue());

		for (int i = RSI_KIKAN + 1; i < count; i++) {
			AccesoriesBean bean1 = list.get(i);
			AccesoriesBean bean2 = list.get(i-1);

			double temp = bean1.getEndValue() - bean2.getEndValue();
			double gain = temp > 0 ? temp			: 0;
			double loss = temp < 0 ? Math.abs(temp) : 0;

			double averageGain = (bean2.getAverageGain() * (RSI_KIKAN - 1) + gain) / RSI_KIKAN;
			double averageLoss = (bean2.getAverageLoss() * (RSI_KIKAN - 1) + loss) / RSI_KIKAN;


			double averageLine3 = (
					list.get(i  ).getEndValue() +
					list.get(i-1).getEndValue() +
					list.get(i-2).getEndValue()) / 3;

			bean1.setAverageGain(averageGain);
			bean1.setAverageLoss(averageLoss);
			bean1.setRsi(calcRSI(averageGain, averageLoss));

			bean1.setLastEndValue(bean2.getEndValue());

			bean1.setAverageLine3(averageLine3);

		}

		return;
	}

	private double[] calcFirstParam(int kikan) {

		double averageGain = 0;
		double averageLoss = 0;
		double totalEndValue = list.get(0).getEndValue();

		for (int i = 1; i <= kikan; i++) {
			double after  = list.get(i  ).getEndValue();
			double before = list.get(i-1).getEndValue();

			if (after >= before) {
				averageGain += after - before;
			} else {
				averageLoss += before - after;
			}

			totalEndValue += after;

		}

		averageGain /= RSI_KIKAN;
		averageLoss /= RSI_KIKAN;

		double rs = averageGain / averageLoss;
		double rsi = 100 - ( 100 / ( 1 + rs ));

		double averageLine3 = totalEndValue / RSI_KIKAN;


		double[] result = {averageGain, averageLoss, rsi, averageLine3};

		return result;
	}

	private double calcRSI(double averageGain, double averageLoss) {
		double rs = averageGain / averageLoss;
		double rsi = 100 - ( 100 / ( 1 + rs ));

		return rsi;
	}

	private boolean isBuyableDay(int i) {

		try {

			AccesoriesBean yesterday = list.get(i - 1);
			AccesoriesBean today = list.get(i);

			boolean conditionRSI = (yesterday.getRsi() < 5);

			boolean conditionValue = (yesterday.getLowValue() > today.getStartValue());

			return conditionRSI & conditionValue;

		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	private void setBuyedDayData(TradeResultBean tradeResult, AccesoriesBean accesory) {
		tradeResult.setBuyDate(accesory.getDate());
		tradeResult.setBuyValue(accesory.getStartValue() * gap);
	}

	private boolean isSellableDay(int i) {

		AccesoriesBean today = list.get(i);

		boolean conditionAveLine = (today.getEndValue() > today.getAverageLine3());

		return conditionAveLine;
	}

	private void setSelledDayData(TradeResultBean tradeResult, AccesoriesBean accesory) {
		tradeResult.setSellDate(accesory.getDate());
		tradeResult.setSellValue(accesory.getEndValue());
	}

}
