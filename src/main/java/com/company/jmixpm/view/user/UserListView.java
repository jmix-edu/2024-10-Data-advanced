package com.company.jmixpm.view.user;

import com.company.jmixpm.app.UsersService;
import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.User;
import com.company.jmixpm.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.flowui.component.genericfilter.GenericFilter;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "users", layout = MainView.class)
@ViewController("User.list")
@ViewDescriptor("user-list-view.xml")
@LookupComponent("usersDataGrid")
@DialogMode(width = "64em")
public class UserListView extends StandardListView<User> {

    private Project filterProject;
    @ViewComponent
    private GenericFilter genericFilter;
    @Autowired
    private UsersService usersService;
    @Autowired
    private DataManager dataManager;

    @Install(to = "usersDl", target = Target.DATA_LOADER)
    private List<User> usersDlLoadDelegate(final LoadContext<User> loadContext) {
        LoadContext.Query query = loadContext.getQuery();
        if (filterProject != null && query != null) {
            return usersService.getUsersNotInProject(filterProject, query.getFirstResult(), query.getMaxResults());
        }

        return dataManager.loadList(loadContext);
    }

    public void setFilterProject(Project project) {
        this.filterProject = project;
        genericFilter.setVisible(false);
    }


}