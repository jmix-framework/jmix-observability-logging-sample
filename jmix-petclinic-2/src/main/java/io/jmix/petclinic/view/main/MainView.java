package io.jmix.petclinic.view.main;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.component.main.JmixListMenu;
import io.jmix.flowui.facet.Timer;
import io.jmix.flowui.kit.component.main.ListMenu;
import io.jmix.flowui.view.*;
import io.jmix.petclinic.online.OnlineDemoDataCreator;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
@ViewController("MainView")
@ViewDescriptor("main-view.xml")
public class MainView extends StandardMainView {

    @Autowired
    private UiComponents uiComponents;

    @Autowired
    private MessageBundle messageBundle;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Autowired(required = false)
    private OnlineDemoDataCreator onlineDemoDataCreator;

    @ViewComponent
    private JmixListMenu menu;

    @Subscribe
    public void onInit(final InitEvent event) {
        // TODO - replace when https://github.com/jmix-framework/jmix/issues/2213 is implemented
        Image image = uiComponents.create(Image.class);
        image.setSrc("images/petclinic_logo_with_slogan.svg");
        image.setClassName("login-image");
        image.setMaxWidth("50%");
        VerticalLayout verticalLayout = uiComponents.create(VerticalLayout.class);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalLayout.add(image);
        verticalLayout.setHeightFull();
        verticalLayout.setWidthFull();
        getContent().showRouterLayoutContent(verticalLayout);

        initMyVisitBadge();
    }

    @Subscribe
    public void onReady(final ReadyEvent event) {
        if (onlineDemoDataCreator != null) {
            onlineDemoDataCreator.createDemoData();
        }
    }

    @Subscribe("refreshMyVisitsBadge")
    public void onRefreshMyVisitsBadgeTimerAction(final Timer.TimerActionEvent event) {
        ListMenu.MenuItem menuItem = menu.getMenuItem("petclinic_MyVisits");

        if (menuItem != null && menuItem.getSuffixComponent() instanceof Span badge) {
            badge.setText(messageBundle.formatMessage("myVisitMenuItemBadge.text", calculateAmountOfVisits()));
        }
    }

    private void initMyVisitBadge() {
        Span badge = uiComponents.create(Span.class);
        badge.setText(messageBundle.formatMessage("myVisitMenuItemBadge.text", calculateAmountOfVisits()));
        badge.getElement().getThemeList().add("badge warning");

        ListMenu.MenuItem menuItem = menu.getMenuItem("petclinic_MyVisits");
        if (menuItem != null) {
            menuItem.setSuffixComponent(badge);
        }
    }

    private long calculateAmountOfVisits() {
        return dataManager.loadValue("select count(e) from petclinic_Visit e " +
                                "where e.assignedNurse = :currentUser " +
                                "and e.treatmentStatus <> @enum(io.jmix.petclinic.entity.visit.VisitTreatmentStatus.DONE)",
                        Long.class)
                .parameter("currentUser", currentAuthentication.getUser())
                .one();
    }
}
