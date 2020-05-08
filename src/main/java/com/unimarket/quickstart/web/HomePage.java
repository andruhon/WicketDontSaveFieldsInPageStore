package com.unimarket.quickstart.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {

    private final Model<PersonBean> model;

    private final Model<String> passwordModel = Model.of();

    public HomePage() {
        model = Model.of(new PersonBean());
        Form<Object> form = new Form<>("personalDetailsForm") {
            @Override
            protected void onSubmit() {
                PersonBean bean = model.getObject();
                System.out.println("Processing personal data");
                System.out.println(String.format("Nick: %s", bean.getNickname()));
                System.out.println(String.format("Name: %s", bean.getName()));
                System.out.println(String.format("Surname: %s", bean.getSurname()));
                System.out.println(String.format("Tax number: %s", bean.getTaxNumber()));
                System.out.println(String.format("Password: %s", passwordModel));
            }
        };
        form.add(new TextField<>("nicknameField", LambdaModel.of(model, PersonBean::getNickname, PersonBean::setNickname)));
        form.add(new SecureTextField<>("nameField", LambdaModel.of(model, PersonBean::getName, PersonBean::setName)));
        form.add(new SecureTextField<>("surnameField", LambdaModel.of(model, PersonBean::getSurname, PersonBean::setSurname)));
        form.add(new SecureTextField<>("taxNumberField", LambdaModel.of(model, PersonBean::getTaxNumber, PersonBean::setTaxNumber)));
        form.add(new PasswordTextField("password", passwordModel));
        add(form);

        add(new BookmarkablePageLink<WebPage>("pageStoreLink", ViewHomePageStorePage.class));

    }

}
