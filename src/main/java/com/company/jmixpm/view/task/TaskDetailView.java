package com.company.jmixpm.view.task;

import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.Task;

import com.company.jmixpm.view.main.MainView;

import com.vaadin.flow.component.combobox.dataview.ComboBoxListDataView;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.view.*;

import java.util.ArrayList;

@Route(value = "tasks/:id", layout = MainView.class)
@ViewController("Task_.detail")
@ViewDescriptor("task-detail-view.xml")
@EditedEntityContainer("taskDc")
public class TaskDetailView extends StandardDetailView<Task> {

    @ViewComponent
    private JmixComboBox<String> labelField;

    // Объект для операций над элементами доступных опций (items).
    private ComboBoxListDataView<String> labelsDataView;


    @Subscribe
    public void onInit(final InitEvent event) {
        // Добавляем пустой лист, чтобы потом когда будут доступны опции,
        // добавить их в лист.
        labelsDataView = labelField.setItems(new ListDataProvider<>(new ArrayList<>()));
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        Project project = getEditedEntity().getProject();
        if (project != null && project.getProjectLabels() != null) {
            labelsDataView.addItems(project.getProjectLabels().getLabels());
        }
    }
}