package test01;

import java.util.Scanner;

/**
 * 点游戏(24点) 【解法一】穷举法
 * 
 * @author DELL
 *
 */
public class Demo1 {
	public static final double Precision = 1E-6; // 精度
	public static final int CardsNumber = 4; // 牌的数量
	public static final int ResultValue = 24; // 要求的运算结果值
	private double[] number; // 所有可能的四则运算所得到的值，初始为牌的值
	private String[] result; // 存放要输出的四则运算结果字符串
	// 构造函数对数组进行初始化

	public Demo1() {
		number = new double[CardsNumber];
		result = new String[CardsNumber];
		Scanner input = new Scanner(System.in);
		System.out.print("请输入" + CardsNumber + "张牌的值(以空格分隔):");
		for (int i = 0; i < CardsNumber; i++) {
			number[i] = input.nextInt();
			result[i] = Double.toString(number[i]);
		}

	}

	/**
	 * 穷举计算的函数
	 * 
	 * @param n
	 *            四则运算结果的数量，初始为牌的数量
	 * @return 是否可以找到对应的运算表达式
	 */
	public boolean pointsGame(int n) {
		if (n == 1) {
			if (Math.abs(number[0] - ResultValue) <= Precision) {
				System.out.println("满足条件的运算表达式为：" + result[0]);
				return true;
			} else {
				return false;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				double a, b; // 当前取出的两个数
				String expa, expb; // 当前存储的四则运算字符串
				a = number[i];
				b = number[j];
				number[j] = number[n - 1];

				expa = result[i];
				expb = result[j];
				result[j] = result[n - 1];
				// 加法运算
				number[i] = a + b;
				result[i] = "(" + expa + "+" + expb + ")";
				if (pointsGame(n - 1))
					return true;
				// 减法运算
				number[i] = a - b;
				result[i] = "(" + expa + "-" + expb + ")";
				if (pointsGame(n - 1))
					return true;

				number[i] = b - a;
				result[i] = "(" + expb + "-" + expa + ")";
				if (pointsGame(n - 1))
					return true;
				// 乘法运算
				number[i] = a * b;
				result[i] = "(" + expa + "*" + expb + ")";
				if (pointsGame(n - 1))
					return true;
				// 除法运算
				if (b != 0) {
					number[i] = a / b;
					result[i] = "(" + expa + "/" + expb + ")";
					if (pointsGame(n - 1))
						return true;
				}

				if (a != 0) {
					number[i] = b / a;
					result[i] = "(" + expb + "/" + expa + ")";
					if (pointsGame(n - 1))
						return true;
				}
				// 如果还没找到，就恢复原来的状态继续下一轮搜索
				number[i] = a;
				number[j] = b;
				result[i] = expa;
				result[j] = expb;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Demo1 pg = new Demo1();
		if (pg.pointsGame(CardsNumber)) {
			System.out.println("构造成功！");
		} else
			System.out.println("构造失败！");
	}

}
