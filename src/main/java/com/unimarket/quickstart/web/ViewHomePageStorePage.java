package com.unimarket.quickstart.web;

import org.apache.wicket.Page;
import org.apache.wicket.PageReference;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.pageStore.DelegatingPageStore;
import org.apache.wicket.pageStore.IPageStore;
import org.apache.wicket.pageStore.IPersistedPage;
import org.apache.wicket.pageStore.IPersistentPageStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ViewHomePageStorePage extends WebPage {

    public ViewHomePageStorePage() {
        String sessionId = Session.get().getId();
        List<Page> pages;
        if (sessionId != null) {
            pages = getPages(sessionId).stream()
                    .map(IPersistedPage::getPageId)
                    .map(PageReference::new)
                    .map(PageReference::getPage)
                    .filter(p -> p instanceof HomePage)
                    .collect(Collectors.toList());
        } else {
            pages = Collections.emptyList();
        }
        RepeatingView storeRecordsRepeater = new RepeatingView("storeRecords");
        for (Page page : pages) {
            storeRecordsRepeater.add(new FieldPanel(storeRecordsRepeater.newChildId(), "personalDetailsForm:nicknameField", Model.of(page)));
            storeRecordsRepeater.add(new FieldPanel(storeRecordsRepeater.newChildId(), "personalDetailsForm:nameField", Model.of(page)));
            storeRecordsRepeater.add(new FieldPanel(storeRecordsRepeater.newChildId(), "personalDetailsForm:surnameField", Model.of(page)));
            storeRecordsRepeater.add(new FieldPanel(storeRecordsRepeater.newChildId(), "personalDetailsForm:taxNumberField", Model.of(page)));
            storeRecordsRepeater.add(new FieldPanel(storeRecordsRepeater.newChildId(), "personalDetailsForm:password", Model.of(page)));
        }

        add(storeRecordsRepeater);
    }

    public static IPersistentPageStore getPersistentPageStore() {
        IPageStore store = Session.get().getPageManager().getPageStore();
        while (true) {
            if (store instanceof IPersistentPageStore) {
                return (IPersistentPageStore) store;
            }
            if (store instanceof DelegatingPageStore) {
                store = ((DelegatingPageStore) store).getDelegate();
            } else {
                break;
            }
        }

        return null;
    }

    private List<IPersistedPage> getPages(String sessId) {
        List<IPersistedPage> pages;
        pages = new ArrayList<>();

        IPersistentPageStore persistentPagesStore = getPersistentPageStore();

        if (persistentPagesStore != null) {
            pages.addAll(persistentPagesStore.getPersistedPages(sessId));
        }

        return pages;
    }

    static class FieldPanel extends Panel {

        public FieldPanel(String id, String path, IModel<Page> model) {
            super(id, model);
            add(new ExternalLink("pageId", model.map(p -> "/?"+p.getPageId()), model.map(Page::getPageId)));
            add(new Label("path", path));
            add(new Label("value", model.map(p -> p.get(path).getDefaultModel().getObject())));
        }

    }

}
