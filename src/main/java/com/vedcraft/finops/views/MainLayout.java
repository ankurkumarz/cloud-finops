package com.vedcraft.finops.views;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vedcraft.finops.components.appnav.AppNav;
import com.vedcraft.finops.components.appnav.AppNavItem;
import com.vedcraft.finops.views.about.AboutView;
import com.vedcraft.finops.views.addressform.AddressFormView;
import com.vedcraft.finops.views.cardlist.CardListView;
import com.vedcraft.finops.views.collaborativemasterdetail.CollaborativeMasterDetailView;
import com.vedcraft.finops.views.gridwithfilters.GridwithFiltersView;
import com.vedcraft.finops.views.helloworld.HelloWorldView;
import com.vedcraft.finops.views.imagelist.ImageListView;
import com.vedcraft.finops.views.masterdetail.MasterDetailView;
import com.vedcraft.finops.views.personform.PersonFormView;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Cloud FinOps");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        nav.addItem(new AppNavItem("Hello World", HelloWorldView.class, "la la-globe"));
        nav.addItem(new AppNavItem("About", AboutView.class, "la la-file"));
        nav.addItem(new AppNavItem("Card List", CardListView.class, "la la-list"));
        nav.addItem(new AppNavItem("Master-Detail", MasterDetailView.class, "la la-columns"));
        nav.addItem(
                new AppNavItem("Collaborative Master-Detail", CollaborativeMasterDetailView.class, "la la-columns"));
        nav.addItem(new AppNavItem("Person Form", PersonFormView.class, "la la-user"));
        nav.addItem(new AppNavItem("Address Form", AddressFormView.class, "la la-map-marker"));
        nav.addItem(new AppNavItem("Grid with Filters", GridwithFiltersView.class, "la la-filter"));
        nav.addItem(new AppNavItem("Image List", ImageListView.class, "la la-th-list"));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
