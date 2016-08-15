package com.bcdbook.summer.test.demo.ienum;

import org.junit.Test;


//import com.bcdbook.demos.enumtest.Gender;


import com.bcdbook.summer.demo.enumtest.OrderState;
import com.bcdbook.summer.demo.enumtest.TestEnum.ColorEnum;
import com.bcdbook.summer.demo.enumtest.TestEnum.Gender;

public class TestEnum {
//	@Test
//	public void testEnum(){
//		System.out.println(Gender.MAN.getValue());
//	}
	
	@Test
	public void testEnumOrder(){
		System.out.println(OrderState.CANCEL.getName());
	}
	
	@Test
	public void coderEnum(){
		ColorEnum color = ColorEnum.yellow;
		System.out.println(color);
	}
	
	@Test
	public void genderEnum(){
		Gender gender = Gender.WOMEN;
		String genderValue = gender.getValue();
		int genderIndex = gender.getIndex();
		String genderName = gender.getName();
		
		System.out.println(gender);
		System.out.println(genderValue);
		System.out.println(genderIndex);
		System.out.println(genderName);
	}
}
