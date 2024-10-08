package com.company.jmixpm.view.workload;


import com.company.jmixpm.view.main.MainView;
import com.company.jmixpm.view.workloadinfo.WorkLoadInfoView;
import com.vaadin.flow.router.Route;
import io.jmix.core.entity.KeyValueEntity;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "work-load-view", layout = MainView.class)
@ViewController("WorkLoadView")
@ViewDescriptor("work-load-view.xml")
public class WorkLoadView extends StandardView {

    @ViewComponent
    private DataGrid<KeyValueEntity> workloadDataGrid;
    @Autowired
    private DialogWindows dialogWindows;

    @Subscribe("workloadDataGrid.workLoadInfo")
    public void onWorkloadDataGridWorkLoadInfo(final ActionPerformedEvent event) {
        KeyValueEntity workloadItem = workloadDataGrid.getSingleSelectedItem();
        if (workloadItem == null) {
            return;
        }
        dialogWindows.view(this, WorkLoadInfoView.class)
                .withViewConfigurer(workloadInfoView -> workloadInfoView.withItem(workloadItem))
                .open();
    }
}