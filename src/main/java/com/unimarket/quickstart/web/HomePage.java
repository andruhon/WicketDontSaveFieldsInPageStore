package com.unimarket.quickstart.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {

    private final Model<PersonBean> model;

    private final Model<String> passwordModel = Model.of();

    private final Model<String> outputModel = Model.of();

    public HomePage() {
        add(new Label("title", getString("title")));
        model = Model.of(new PersonBean());
        add(new Label("output", outputModel));
        Form<Object> form = new Form<>("personalDetailsForm") {
            @Override
            protected void onSubmit() {
                // Pretend it's doing some service method call
                PersonBean bean = model.getObject();
                String out = "Processing personal data \n";
                out += String.format("Nick: %s \n", bean.getNickname());
                out += String.format("Name: %s \n", bean.getName());
                out += String.format("Surname: %s \n", bean.getSurname());
                out += String.format("Tax number: %s \n", bean.getTaxNumber());
                out += String.format("Password: %s \n\n", passwordModel.getObject());
                outputModel.setObject(out + outputModel.getObject());
            }
        };
        form.add(new TextField<>("nicknameField", LambdaModel.of(model, PersonBean::getNickname, PersonBean::setNickname)));
        form.add(new SecureTextField<>("nameField", LambdaModel.of(model, PersonBean::getName, PersonBean::setName)));
        form.add(new SecureTextField<>("surnameField", LambdaModel.of(model, PersonBean::getSurname, PersonBean::setSurname)));
        form.add(new SecureTextField<>("taxNumberField", LambdaModel.of(model, PersonBean::getTaxNumber, PersonBean::setTaxNumber)));
        form.add(new PasswordTextField("password", passwordModel).setRequired(false));
        add(form);

        add(new BookmarkablePageLink<WebPage>("pageStoreLink", ViewHomePageStorePage.class));

    }

}
