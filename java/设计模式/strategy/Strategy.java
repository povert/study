package strategy;
/*
 * 策略模式定义一系列算法，并且使得他们可以相互替换，
 * 此模式使得算法可以独立于使用他们的客户端而变化
 */
// UML图
/*
 *  CashContext{ <>--strategy------------> CashSuper{acceptCash()}
 *  GetResult()o--->strategy.acceptCash}           |
 *   }          ---------------------------------<实现>------------
 *              |                    |                            |
 *        CashDiscount           CashNormal                  CashReturn
 *        {acceptCash()}         {acceptCash()}             {acceptCash()}                         
 */ 
 
public class Strategy {
	public static void main(String[] args) {
		double money = 976.0;
		CashContext shop = new CashContext(TYPE.NORMAL);
		System.out.println("正常收费:"+shop.GetResult(money));
		shop = new CashContext(TYPE.CASH_DISCOUNT);
		System.out.println("折扣收费:"+shop.GetResult(money));
		shop = new CashContext(TYPE.CASH_RETURN);
		System.out.println("满减收费:"+shop.GetResult(money));
		
	}
}
/* 这里用以超市收银为例，超市收银分为3种，满减，打折，不优惠*/
interface CashSuper{
	double acceptCash(double money);
}
enum TYPE{NORMAL,CASH_DISCOUNT,CASH_RETURN}

//正常收费
class CashNormal implements CashSuper{
	@Override
	public double acceptCash(double money) {
		// TODO 自动生成的方法存根
		return money;
	} 
}
//折扣
class CashDiscount implements CashSuper{
	private double moneyDiscount;
	CashDiscount(double moneyDiscount){this.moneyDiscount=moneyDiscount;}
	@Override
	public double acceptCash(double money) {
		// TODO 自动生成的方法存根
		return money*moneyDiscount;
	} 
}
// 满减
class CashReturn implements CashSuper{
	private double moneyCondition;
	private double moneyReturn;
	
	CashReturn(double moneyCondition,double moneyReturn){
		this.moneyCondition = moneyCondition;
		this.moneyReturn = moneyReturn;
		}
	@Override
	public double acceptCash(double money) {
		// TODO 自动生成的方法存根
		double result = money;
		if(money>=moneyCondition)
			result = money - Math.floor(money/moneyCondition)*moneyReturn;
		return result;
	} 
}

//收银
class CashContext{
	private CashSuper cs;
	private TYPE t;
	CashContext(TYPE t){
		switch (t) {
		case NORMAL: cs = new CashNormal();
			break;
		case CASH_DISCOUNT: cs = new CashDiscount(0.8);//打8折
			break;
		case CASH_RETURN: cs = new CashReturn(300, 100);//满300，减100
			break;
		}
	}
	public double GetResult(double money) {
		return cs.acceptCash(money);
	}
}
