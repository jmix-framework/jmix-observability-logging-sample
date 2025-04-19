package io.jmix.petclinic.portal.view.main;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import io.jmix.flowui.view.*;



@Route("")
@ViewController(id = "MainView")
@ViewDescriptor(path = "main-view.xml")
public class MainView extends StandardView implements RouterLayout {

    @ViewComponent
    private Div contentWrapper;

    @Override
    public void showRouterLayoutContent(HasElement content) {
        Component target = null;
        if (content != null) {
            target = content.getElement().getComponent().orElseThrow(() ->
                    new IllegalArgumentException("Content must be a Component"));
        }

        if (target == null) {
            return;
        }

        target.addClassName("jmix-main-view-content");
        this.contentWrapper.add(target);
    }
}
