package com.telosys.gwtp.base.client.place;

public class NameTokens {

	private NameTokens() {
	}

	public static final String HOME = "/";
	public static final String ERROR = "/error";

	public static final String TEAM_LIST = "/team";
	public static final String TEAM_FORM = "/team/{id}";
	public static final String PLAYER_LIST = "/player";
	public static final String PLAYER_FORM = "/player/{id}";

	public static final String AUTHOR_LIST = "/author";
	public static final String AUTHOR_FORM = "/author/{id}";

	public static final String EMPLOYEE_GROUP_LIST = "/employeeGroup";
	public static final String EMPLOYEE_GROUP_FORM = "/employeeGroup/{employeeCode}/{groupId}";

	public static final String BADGE_LIST = "/badge";
	public static final String BADGE_FORM = "/badge/{badgeNumber}";

	public static final String BOOK_LIST = "/book";
	public static final String BOOK_FORM = "/book/{id}";

	public static final String BOOK_ORDER_LIST = "/bookOrder";
	public static final String BOOK_ORDER_FORM = "/bookOrder/{id}";
	public static final String BOOK_ORDER_ITEM_LIST = "/bookOrderItem";
	public static final String BOOK_ORDER_ITEM_FORM = "/bookOrderItem/{bookOrderId}/{bookId}";

	public static final String COUNTRY_LIST = "/country";
	public static final String COUNTRY_FORM = "/country/{code}";

	public static final String CUSTOMER_LIST = "/customer";
	public static final String CUSTOMER_FORM = "/customer/{code}";
	public static final String EMPLOYEE_LIST = "/employee";
	public static final String EMPLOYEE_FORM = "/employee/{code}";
	public static final String PUBLISHER_LIST = "/publisher";
	public static final String PUBLISHER_FORM = "/publisher/{code}";

	public static final String REVIEW_LIST = "/review";
	public static final String REVIEW_FORM = "/review/{customerCode}/{bookId}";
	public static final String SHOP_LIST = "/shop";
	public static final String SHOP_FORM = "/shop/{code}";

	public static final String SYNOPSIS_LIST = "/synopsis";
	public static final String SYNOPSIS_FORM = "/synopsis/{bookId}";

	public static final String WORKGROUP_LIST = "/workgroup";
	public static final String WORKGROUP_FORM = "/workgroup/{id}";

}
