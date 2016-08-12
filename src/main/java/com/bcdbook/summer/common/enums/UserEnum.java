package com.bcdbook.summer.common.enums;

/**
     * @Title: UserEnum.java    
     * @Description: 用户的枚举类,用来定义用户的常用标识字段的解释和对比
     * @author lason       
     * @created 2016年5月23日 下午5:30:43
 */
public class UserEnum {
	/**
	 * 
	     * @Title: UserEnum.java    
	     * @Description: 表示职位的枚举类
	     * @author lason       
	     * @created 2016年5月23日 下午5:32:05
	 */
	public enum Position{
		//通过括号赋值,而且必须带有一个参构造器和一个属性跟方法，否则编译出错
        //赋值必须都赋值或都不赋值，不能一部分赋值一部分不赋值；如果不赋值则不能写构造器，赋值编译也出错
        MAN(1,"男"),
        WOMEN(0,"女");
        
        private final int value;
		private final String name;

		//构造器默认也只能是private, 从而保证构造函数只能在内部使用
		private Position(int value,String name) {
            this.value = value;
            this.name = name;
        }
        
        public int getValue() {
            return value;
        }
        public String getName(){
        	return name;
        }
	}
}
