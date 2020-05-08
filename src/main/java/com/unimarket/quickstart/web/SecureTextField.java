package com.unimarket.quickstart.web;


import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

/**
 * A field value of which is nuked on detach
 */
public class SecureTextField<T> extends TextField<T> {

    public SecureTextField(String id) {
        super(id);
    }

    public SecureTextField(String id, Class<T> type) {
        super(id, type);
    }

    public SecureTextField(String id, IModel<T> model) {
        super(id, model);
    }

    public SecureTextField(String id, IModel<T> model, Class<T> type) {
        super(id, model, type);
    }

    @Override
    protected void onComponentTag(final ComponentTag tag)
    {
        super.onComponentTag(tag);
        tag.put("value", "");
    }

    @Override
    protected void onDetach() {
        clearInput();

        if (getModel() != null) {
            getModel().setObject(null);
        }

        super.onDetach();
    }

}
