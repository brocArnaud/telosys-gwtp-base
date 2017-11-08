package com.telosys.gwtp.base.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.telosys.gwtp.base.client.application.content.author.form.AuthorFormPresenter;
import com.telosys.gwtp.base.client.application.content.author.form.AuthorFormPresenter.AuthorFormProxy;
import com.telosys.gwtp.base.client.application.content.author.form.AuthorFormPresenter.AuthorFormView;
import com.telosys.gwtp.base.client.application.content.author.form.AuthorFormViewImpl;
import com.telosys.gwtp.base.client.application.content.author.list.AuthorListPresenter;
import com.telosys.gwtp.base.client.application.content.author.list.AuthorListPresenter.AuthorListProxy;
import com.telosys.gwtp.base.client.application.content.author.list.AuthorListPresenter.AuthorListView;
import com.telosys.gwtp.base.client.application.content.author.list.AuthorListViewImpl;
import com.telosys.gwtp.base.client.application.content.badge.form.BadgeFormPresenter;
import com.telosys.gwtp.base.client.application.content.badge.form.BadgeFormPresenter.BadgeFormProxy;
import com.telosys.gwtp.base.client.application.content.badge.form.BadgeFormPresenter.BadgeFormView;
import com.telosys.gwtp.base.client.application.content.badge.form.BadgeFormViewImpl;
import com.telosys.gwtp.base.client.application.content.badge.list.BadgeListPresenter;
import com.telosys.gwtp.base.client.application.content.badge.list.BadgeListPresenter.BadgeListProxy;
import com.telosys.gwtp.base.client.application.content.badge.list.BadgeListPresenter.BadgeListView;
import com.telosys.gwtp.base.client.application.content.badge.list.BadgeListViewImpl;
import com.telosys.gwtp.base.client.application.content.book.form.BookFormPresenter;
import com.telosys.gwtp.base.client.application.content.book.form.BookFormPresenter.BookFormProxy;
import com.telosys.gwtp.base.client.application.content.book.form.BookFormPresenter.BookFormView;
import com.telosys.gwtp.base.client.application.content.book.form.BookFormViewImpl;
import com.telosys.gwtp.base.client.application.content.book.list.BookListPresenter;
import com.telosys.gwtp.base.client.application.content.book.list.BookListPresenter.BookListProxy;
import com.telosys.gwtp.base.client.application.content.book.list.BookListPresenter.BookListView;
import com.telosys.gwtp.base.client.application.content.book.list.BookListViewImpl;
import com.telosys.gwtp.base.client.application.content.book.order.form.BookOrderFormPresenter;
import com.telosys.gwtp.base.client.application.content.book.order.form.BookOrderFormPresenter.BookOrderFormProxy;
import com.telosys.gwtp.base.client.application.content.book.order.form.BookOrderFormPresenter.BookOrderFormView;
import com.telosys.gwtp.base.client.application.content.book.order.form.BookOrderFormViewImpl;
import com.telosys.gwtp.base.client.application.content.book.order.item.form.BookOrderItemFormPresenter;
import com.telosys.gwtp.base.client.application.content.book.order.item.form.BookOrderItemFormPresenter.BookOrderItemFormProxy;
import com.telosys.gwtp.base.client.application.content.book.order.item.form.BookOrderItemFormPresenter.BookOrderItemFormView;
import com.telosys.gwtp.base.client.application.content.book.order.item.form.BookOrderItemFormViewImpl;
import com.telosys.gwtp.base.client.application.content.book.order.item.list.BookOrderItemListPresenter;
import com.telosys.gwtp.base.client.application.content.book.order.item.list.BookOrderItemListPresenter.BookOrderItemListProxy;
import com.telosys.gwtp.base.client.application.content.book.order.item.list.BookOrderItemListPresenter.BookOrderItemListView;
import com.telosys.gwtp.base.client.application.content.book.order.item.list.BookOrderItemListViewImpl;
import com.telosys.gwtp.base.client.application.content.book.order.list.BookOrderListPresenter;
import com.telosys.gwtp.base.client.application.content.book.order.list.BookOrderListPresenter.BookOrderListProxy;
import com.telosys.gwtp.base.client.application.content.book.order.list.BookOrderListPresenter.BookOrderListView;
import com.telosys.gwtp.base.client.application.content.book.order.list.BookOrderListViewImpl;
import com.telosys.gwtp.base.client.application.content.country.form.CountryFormPresenter;
import com.telosys.gwtp.base.client.application.content.country.form.CountryFormPresenter.CountryFormProxy;
import com.telosys.gwtp.base.client.application.content.country.form.CountryFormPresenter.CountryFormView;
import com.telosys.gwtp.base.client.application.content.country.form.CountryFormViewImpl;
import com.telosys.gwtp.base.client.application.content.country.list.CountryListPresenter;
import com.telosys.gwtp.base.client.application.content.country.list.CountryListPresenter.CountryListProxy;
import com.telosys.gwtp.base.client.application.content.country.list.CountryListPresenter.CountryListView;
import com.telosys.gwtp.base.client.application.content.country.list.CountryListViewImpl;
import com.telosys.gwtp.base.client.application.content.customer.form.CustomerFormPresenter;
import com.telosys.gwtp.base.client.application.content.customer.form.CustomerFormPresenter.CustomerFormProxy;
import com.telosys.gwtp.base.client.application.content.customer.form.CustomerFormPresenter.CustomerFormView;
import com.telosys.gwtp.base.client.application.content.customer.form.CustomerFormViewImpl;
import com.telosys.gwtp.base.client.application.content.customer.list.CustomerListPresenter;
import com.telosys.gwtp.base.client.application.content.customer.list.CustomerListPresenter.CustomerListProxy;
import com.telosys.gwtp.base.client.application.content.customer.list.CustomerListPresenter.CustomerListView;
import com.telosys.gwtp.base.client.application.content.customer.list.CustomerListViewImpl;
import com.telosys.gwtp.base.client.application.content.employee.form.EmployeeFormPresenter;
import com.telosys.gwtp.base.client.application.content.employee.form.EmployeeFormPresenter.EmployeeFormProxy;
import com.telosys.gwtp.base.client.application.content.employee.form.EmployeeFormPresenter.EmployeeFormView;
import com.telosys.gwtp.base.client.application.content.employee.form.EmployeeFormViewImpl;
import com.telosys.gwtp.base.client.application.content.employee.group.form.EmployeeGroupFormPresenter;
import com.telosys.gwtp.base.client.application.content.employee.group.form.EmployeeGroupFormPresenter.EmployeeGroupFormProxy;
import com.telosys.gwtp.base.client.application.content.employee.group.form.EmployeeGroupFormPresenter.EmployeeGroupFormView;
import com.telosys.gwtp.base.client.application.content.employee.group.form.EmployeeGroupFormViewImpl;
import com.telosys.gwtp.base.client.application.content.employee.group.list.EmployeeGroupListPresenter;
import com.telosys.gwtp.base.client.application.content.employee.group.list.EmployeeGroupListPresenter.EmployeeGroupListProxy;
import com.telosys.gwtp.base.client.application.content.employee.group.list.EmployeeGroupListPresenter.EmployeeGroupListView;
import com.telosys.gwtp.base.client.application.content.employee.group.list.EmployeeGroupListViewImpl;
import com.telosys.gwtp.base.client.application.content.employee.list.EmployeeListPresenter;
import com.telosys.gwtp.base.client.application.content.employee.list.EmployeeListPresenter.EmployeeListProxy;
import com.telosys.gwtp.base.client.application.content.employee.list.EmployeeListPresenter.EmployeeListView;
import com.telosys.gwtp.base.client.application.content.employee.list.EmployeeListViewImpl;
import com.telosys.gwtp.base.client.application.content.error.ErrorPresenter;
import com.telosys.gwtp.base.client.application.content.error.ErrorPresenter.ErrorProxy;
import com.telosys.gwtp.base.client.application.content.error.ErrorPresenter.ErrorView;
import com.telosys.gwtp.base.client.application.content.error.ErrorViewImpl;
import com.telosys.gwtp.base.client.application.content.home.HomePresenter;
import com.telosys.gwtp.base.client.application.content.home.HomePresenter.HomeProxy;
import com.telosys.gwtp.base.client.application.content.home.HomePresenter.HomeView;
import com.telosys.gwtp.base.client.application.content.home.HomeViewImpl;
import com.telosys.gwtp.base.client.application.content.publisher.form.PublisherFormPresenter;
import com.telosys.gwtp.base.client.application.content.publisher.form.PublisherFormPresenter.PublisherFormProxy;
import com.telosys.gwtp.base.client.application.content.publisher.form.PublisherFormPresenter.PublisherFormView;
import com.telosys.gwtp.base.client.application.content.publisher.form.PublisherFormViewImpl;
import com.telosys.gwtp.base.client.application.content.publisher.list.PublisherListPresenter;
import com.telosys.gwtp.base.client.application.content.publisher.list.PublisherListPresenter.PublisherListProxy;
import com.telosys.gwtp.base.client.application.content.publisher.list.PublisherListPresenter.PublisherListView;
import com.telosys.gwtp.base.client.application.content.publisher.list.PublisherListViewImpl;
import com.telosys.gwtp.base.client.application.content.review.form.ReviewFormPresenter;
import com.telosys.gwtp.base.client.application.content.review.form.ReviewFormPresenter.ReviewFormProxy;
import com.telosys.gwtp.base.client.application.content.review.form.ReviewFormPresenter.ReviewFormView;
import com.telosys.gwtp.base.client.application.content.review.form.ReviewFormViewImpl;
import com.telosys.gwtp.base.client.application.content.review.list.ReviewListPresenter;
import com.telosys.gwtp.base.client.application.content.review.list.ReviewListPresenter.ReviewListProxy;
import com.telosys.gwtp.base.client.application.content.review.list.ReviewListPresenter.ReviewListView;
import com.telosys.gwtp.base.client.application.content.review.list.ReviewListViewImpl;
import com.telosys.gwtp.base.client.application.content.shop.form.ShopFormPresenter;
import com.telosys.gwtp.base.client.application.content.shop.form.ShopFormPresenter.ShopFormProxy;
import com.telosys.gwtp.base.client.application.content.shop.form.ShopFormPresenter.ShopFormView;
import com.telosys.gwtp.base.client.application.content.shop.form.ShopFormViewImpl;
import com.telosys.gwtp.base.client.application.content.shop.list.ShopListPresenter;
import com.telosys.gwtp.base.client.application.content.shop.list.ShopListPresenter.ShopListProxy;
import com.telosys.gwtp.base.client.application.content.shop.list.ShopListPresenter.ShopListView;
import com.telosys.gwtp.base.client.application.content.shop.list.ShopListViewImpl;
import com.telosys.gwtp.base.client.application.content.synopsis.form.SynopsisFormPresenter;
import com.telosys.gwtp.base.client.application.content.synopsis.form.SynopsisFormPresenter.SynopsisFormProxy;
import com.telosys.gwtp.base.client.application.content.synopsis.form.SynopsisFormPresenter.SynopsisFormView;
import com.telosys.gwtp.base.client.application.content.synopsis.form.SynopsisFormViewImpl;
import com.telosys.gwtp.base.client.application.content.synopsis.list.SynopsisListPresenter;
import com.telosys.gwtp.base.client.application.content.synopsis.list.SynopsisListPresenter.SynopsisListProxy;
import com.telosys.gwtp.base.client.application.content.synopsis.list.SynopsisListPresenter.SynopsisListView;
import com.telosys.gwtp.base.client.application.content.synopsis.list.SynopsisListViewImpl;
import com.telosys.gwtp.base.client.application.content.workgroup.form.WorkgroupFormPresenter;
import com.telosys.gwtp.base.client.application.content.workgroup.form.WorkgroupFormPresenter.WorkgroupFormProxy;
import com.telosys.gwtp.base.client.application.content.workgroup.form.WorkgroupFormPresenter.WorkgroupFormView;
import com.telosys.gwtp.base.client.application.content.workgroup.form.WorkgroupFormViewImpl;
import com.telosys.gwtp.base.client.application.content.workgroup.list.WorkgroupListPresenter;
import com.telosys.gwtp.base.client.application.content.workgroup.list.WorkgroupListPresenter.WorkgroupListProxy;
import com.telosys.gwtp.base.client.application.content.workgroup.list.WorkgroupListPresenter.WorkgroupListView;
import com.telosys.gwtp.base.client.application.content.workgroup.list.WorkgroupListViewImpl;
import com.telosys.gwtp.base.client.application.layout.footer.FooterPresenter;
import com.telosys.gwtp.base.client.application.layout.footer.FooterPresenter.FooterProxy;
import com.telosys.gwtp.base.client.application.layout.footer.FooterPresenter.FooterView;
import com.telosys.gwtp.base.client.application.layout.footer.FooterViewImpl;
import com.telosys.gwtp.base.client.application.layout.header.HeaderPresenter;
import com.telosys.gwtp.base.client.application.layout.header.HeaderPresenter.HeaderProxy;
import com.telosys.gwtp.base.client.application.layout.header.HeaderPresenter.HeaderView;
import com.telosys.gwtp.base.client.application.layout.header.HeaderViewImpl;

public class ApplicationModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		/** Header part */
		bindPresenter(HeaderPresenter.class, HeaderView.class, HeaderViewImpl.class, HeaderProxy.class);
		/** Footer part */
		bindPresenter(FooterPresenter.class, FooterView.class, FooterViewImpl.class, FooterProxy.class);
		/** Home Page */
		bindPresenter(HomePresenter.class, HomeView.class, HomeViewImpl.class, HomeProxy.class);
		/** Error page */
		bindPresenter(ErrorPresenter.class, ErrorView.class, ErrorViewImpl.class, ErrorProxy.class);

		bindPresenter(AuthorListPresenter.class, AuthorListView.class, AuthorListViewImpl.class, AuthorListProxy.class);
		bindPresenter(AuthorFormPresenter.class, AuthorFormView.class, AuthorFormViewImpl.class, AuthorFormProxy.class);

		bindPresenter(BadgeListPresenter.class, BadgeListView.class, BadgeListViewImpl.class, BadgeListProxy.class);
		bindPresenter(BadgeFormPresenter.class, BadgeFormView.class, BadgeFormViewImpl.class, BadgeFormProxy.class);

		bindPresenter(BookListPresenter.class, BookListView.class, BookListViewImpl.class, BookListProxy.class);
		bindPresenter(BookFormPresenter.class, BookFormView.class, BookFormViewImpl.class, BookFormProxy.class);

		bindPresenter(BookOrderListPresenter.class, BookOrderListView.class, BookOrderListViewImpl.class, BookOrderListProxy.class);
		bindPresenter(BookOrderFormPresenter.class, BookOrderFormView.class, BookOrderFormViewImpl.class, BookOrderFormProxy.class);

		bindPresenter(BookOrderItemListPresenter.class, BookOrderItemListView.class, BookOrderItemListViewImpl.class, BookOrderItemListProxy.class);
		bindPresenter(BookOrderItemFormPresenter.class, BookOrderItemFormView.class, BookOrderItemFormViewImpl.class, BookOrderItemFormProxy.class);

		bindPresenter(CountryListPresenter.class, CountryListView.class, CountryListViewImpl.class, CountryListProxy.class);
		bindPresenter(CountryFormPresenter.class, CountryFormView.class, CountryFormViewImpl.class, CountryFormProxy.class);

		bindPresenter(CustomerListPresenter.class, CustomerListView.class, CustomerListViewImpl.class, CustomerListProxy.class);
		bindPresenter(CustomerFormPresenter.class, CustomerFormView.class, CustomerFormViewImpl.class, CustomerFormProxy.class);

		bindPresenter(EmployeeListPresenter.class, EmployeeListView.class, EmployeeListViewImpl.class, EmployeeListProxy.class);
		bindPresenter(EmployeeFormPresenter.class, EmployeeFormView.class, EmployeeFormViewImpl.class, EmployeeFormProxy.class);

		bindPresenter(EmployeeGroupListPresenter.class, EmployeeGroupListView.class, EmployeeGroupListViewImpl.class, EmployeeGroupListProxy.class);
		bindPresenter(EmployeeGroupFormPresenter.class, EmployeeGroupFormView.class, EmployeeGroupFormViewImpl.class, EmployeeGroupFormProxy.class);

		bindPresenter(PublisherListPresenter.class, PublisherListView.class, PublisherListViewImpl.class, PublisherListProxy.class);
		bindPresenter(PublisherFormPresenter.class, PublisherFormView.class, PublisherFormViewImpl.class, PublisherFormProxy.class);

		bindPresenter(ReviewListPresenter.class, ReviewListView.class, ReviewListViewImpl.class, ReviewListProxy.class);
		bindPresenter(ReviewFormPresenter.class, ReviewFormView.class, ReviewFormViewImpl.class, ReviewFormProxy.class);

		bindPresenter(ShopListPresenter.class, ShopListView.class, ShopListViewImpl.class, ShopListProxy.class);
		bindPresenter(ShopFormPresenter.class, ShopFormView.class, ShopFormViewImpl.class, ShopFormProxy.class);

		bindPresenter(SynopsisListPresenter.class, SynopsisListView.class, SynopsisListViewImpl.class, SynopsisListProxy.class);
		bindPresenter(SynopsisFormPresenter.class, SynopsisFormView.class, SynopsisFormViewImpl.class, SynopsisFormProxy.class);

		bindPresenter(WorkgroupListPresenter.class, WorkgroupListView.class, WorkgroupListViewImpl.class, WorkgroupListProxy.class);
		bindPresenter(WorkgroupFormPresenter.class, WorkgroupFormView.class, WorkgroupFormViewImpl.class, WorkgroupFormProxy.class);

		bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class, ApplicationPresenter.MyProxy.class);
	}
}