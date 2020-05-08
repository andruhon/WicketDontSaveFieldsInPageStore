package com.unimarket.quickstart.web;

import org.apache.wicket.devutils.pagestore.PageStorePage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

@org.springframework.stereotype.Component("wicketApplication")
public class QuickStartWicketApplication extends WebApplication {

    @Override
    public Class<? extends WebPage> getHomePage()
    {
        return HomePage.class;
    }

    @Override
    public void init()
    {
        super.init();
        // DebugDiskDataStore.register(this); // should be in def utils
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        mountPage("/", HomePage.class);
        mountPage("/homepagestore", ViewHomePageStorePage.class);
    }

}
