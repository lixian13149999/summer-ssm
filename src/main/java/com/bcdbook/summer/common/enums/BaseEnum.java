package com.bcdbook.summer.common.enums;

public class BaseEnum {
	public enum BackMsg {
		// text,image,voice,video,shortvideo,location,link,news;
		DATA_NOT_EXIST("{\"coder\":4,\"msg\":\"data not exist\"}", "想要查询的数据为空"),
		DATA_IS_NULL("{\"coder\":3,\"msg\":\"data is null\"}", "存在关键数据为空的现象"),
		DATA_EXIST("{\"coder\":2,\"msg\":\"data already exist\"}", "词条数据已存在"),
		ERROR("{\"coder\":1,\"msg\":\"error\"}", "处理出错"),
		SUCCESS("{\"coder\":0,\"msg\":\"success\"}", "成功"); 

		private final String value;
		private final String name;

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		private BackMsg(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}
}
