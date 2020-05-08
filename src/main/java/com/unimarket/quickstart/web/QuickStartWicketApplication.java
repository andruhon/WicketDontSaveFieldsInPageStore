package com.unimarket.quickstart.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

@org.springframework.stereotype.Component("wicketApplication")
public class QuickStartWicketApplication extends WebApplication {

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();
        mountPage("/homepagestore", ViewHomePageStorePage.class);
    }

}
