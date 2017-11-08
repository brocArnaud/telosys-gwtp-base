package com.telosys.gwtp.base.shared.api;

public class Paths {

	private Paths() {
	}

	// Api general
	public static final String BASE_URL = "http://localhost:8080/bookstore-rest-api-0.1";
	public static final String API_ROOT = "/api/v1";

	public static final String ALLOWED_HEADERS = "Content-Type,Access-Control-Allow-Origin, Access-Control-Allow-Headers, Authorization, X-Requested-With,x-gwt-module-base";
	public static final String ALLOWED_METHOD = "GET,POST,PUT,DELETE,OPTIONS";
	public static final String ALLOWED_ORIGIN = "*";
	
	public static final String SIMPLE_DATE_PATTERN = "yyyy-MM-dd";

	// Commons path params
	public static final String ID = "/{id}";
	public static final String ID_PARAM = "id";
	public static final String CODE = "/{code}";
	public static final String CODE_PARAM = "code";
	public static final String BOOK_ID = "{bookId}";
	public static final String BOOK_ID_PARAM = "bookId";
	public static final String BADGE_NUMBER = "{badgeNumber}";
	public static final String BADGE_NUMBER_PARAM = "badgeNumber";

	// Author resource
	public static final String AUTHOR = "/author";
	public static final String AUTHOR_LIST_ITEM = "/authors-list-items";
	// Badge resource
	public static final String BADGE = "/badge";
	public static final String BADGE_LIST_ITEM = "/badges-list-items";
	// BookOrderItem resource
	public static final String BOOK_ORDER_ITEM = "/bookOrderItem";
	public static final String BOOK_ORDER_ITEM_LIST_ITEM = "/bookOrderItems-list-items";
	// BookOrder resource
	public static final String BOOK_ORDER = "/bookOrder";
	public static final String BOOK_ORDER_LIST_ITEM = "/bookOrders-list-items";
	// Book resource
	public static final String BOOK = "/book";
	public static final String BOOK_LIST_ITEM = "/books-list-items";
	// Country resource
	public static final String COUNTRY = "/country";
	public static final String COUNTRY_LIST_ITEM = "/countrys-list-items";
	// Customer resource
	public static final String CUSTOMER = "/customer";
	public static final String CUSTOMER_LIST_ITEM = "/customers-list-items";
	// EmployeeGroup resource
	public static final String EMPLOYEE_GROUP = "/employeeGroup";
	public static final String EMPLOYES_GROUP_LIST_ITEM = "/employeeGroups-list-items";
	// Employee resource
	public static final String EMPLOYEE = "/employee";
	public static final String EMPLOYES_LIST_ITEM = "/employees-list-items";
	// Publisher resource
	public static final String PUBLISHER = "/publisher";
	public static final String PUBLISHER_LIST_ITEM = "/publishers-list-items";
	// Review resource
	public static final String REVIEW = "/review";
	public static final String REVIEW_LIST_ITEM = "/reviews-list-items";
	// Shop resource
	public static final String SHOP = "/shop";
	public static final String SHOP_LIST_ITEM = "/shops-list-items";
	// Synopsis resource
	public static final String SYNOPSIS = "/synopsis";
	public static final String SYNOPSIS_LIST_ITEM = "/synopsiss-list-items";
	// Workgroup resource
	public static final String WORKGROUP = "/workgroup";
	public static final String WORKGROUP_LIST_ITEM = "/workgroups-list-items";

}