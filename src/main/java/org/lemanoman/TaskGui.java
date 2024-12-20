package org.lemanoman;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.UUID;

public class TaskGui {
    private JTextField textField1;
    private JTextArea textArea1;
    private JPanel mainPanel;
    private JButton deleteButton;
    private JButton saveButton;
    private JPanel rightPanel;
    private JList list1;

    final private TaskItemFactory taskItemFactory;
    private JCheckBox doneCheckBox;
    private JButton novaTaskButton;
    private DefaultListModel<TaskItem> model = new DefaultListModel<>();
    private JScrollPane scrollPanel;

    private TaskItem selectedTaskItem = null;

    private void fillTaskSelected(TaskItem taskItem) {
        if (taskItem == null) {
            return;
        }
        selectedTaskItem = taskItem;
        textField1.setText(taskItem.getName());
        textArea1.setText(taskItem.getDescription());
        doneCheckBox.setSelected(taskItem.isDone());
    }

    public TaskGui(TaskItemFactory taskItemFactory) {
        this.taskItemFactory = taskItemFactory;
        list1.setModel(model);
        model.addAll(taskItemFactory.list());
        list1.addListSelectionListener(e -> {
            TaskItem taskItem = (TaskItem) list1.getSelectedValue();
            if (taskItem == null) {
                return;
            }
            fillTaskSelected(taskItem);

        });
        deleteButton.addActionListener(e -> {
            TaskItem taskItem = (TaskItem) list1.getSelectedValue();
            if (taskItem == null) {
                return;
            }
            model.removeElement(taskItem);
        });
        deleteButton.addActionListener(e -> deleteItem());
        saveButton.addActionListener(e -> saveChanges());
        novaTaskButton.addActionListener(e -> newTask());

    }

    public void deleteItem() {
        TaskItem taskItem = (TaskItem) list1.getSelectedValue();
        if (taskItem == null) {
            return;
        }
        taskItemFactory.delete(taskItem);
        model.removeElement(taskItem);
    }

    public void saveChanges() {
        TaskItem taskItem = this.selectedTaskItem;
        if (taskItem == null) {
            taskItem = new TaskItem();
            taskItem.setId(UUID.randomUUID().toString());
            taskItem.setName(textField1.getText());
            taskItem.setDescription(textArea1.getText());
            taskItem.setDone(doneCheckBox.isSelected());
            model.addElement(taskItem);
            taskItemFactory.add(taskItem);
            return;
        }
        taskItem.setName(textField1.getText());
        taskItem.setDescription(textArea1.getText());
        taskItem.setDone(doneCheckBox.isSelected());
        model.setElementAt(taskItem, list1.getSelectedIndex());
        taskItemFactory.update(taskItem);
    }

    public void newTask() {
        var task = new TaskItem(UUID.randomUUID().toString(), "New Task", "Description for new task");
        taskItemFactory.add(task);
        model.addElement(task);
    }


    public JPanel getMainPanel() {

        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(rightPanel, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        rightPanel.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list1 = new JList();
        scrollPane1.setViewportView(list1);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, 1, 1, new Dimension(400, -1), null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Label");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        panel1.add(textField1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textArea1 = new JTextArea();
        panel1.add(textArea1, new GridConstraints(2, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        panel1.add(deleteButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        doneCheckBox = new JCheckBox();
        doneCheckBox.setText("Done");
        panel1.add(doneCheckBox, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Save");
        panel1.add(saveButton, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        novaTaskButton = new JButton();
        novaTaskButton.setText("Nova Task");
        mainPanel.add(novaTaskButton, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
