Just a simple demo showing that fields similar to wicket PasswordTextField
nulling model on detach and nulling value on component tag won't get persisted to the disk when page is serialized.

## Requirements
* Maven 3
* JDK 14

## Build and run
    mvn clean spring-boot:run
    
## What to do
* Go to http://localhost:8080 (or use any other port the build is showing);
* Type some data to the form;
* Go to the http://localhost:8080/homepagestore and see that only usual TextField is saved to the PageStore
* Values for SecureTextField are not persisted to the PageStore.

## "Secure" implementation

```java
public class SecureTextField<T> extends TextField<T> {
    // ... constructors and other stuff

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
``` 