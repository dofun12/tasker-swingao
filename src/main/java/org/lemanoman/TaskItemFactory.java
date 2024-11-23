package org.lemanoman;

import java.util.List;

public interface TaskItemFactory {
    TaskItem add(TaskItem taskItem);
    TaskItem update(TaskItem taskItem);

    boolean delete(TaskItem taskItem);

    TaskItem get(String id);
    List<TaskItem> list();

}
