package org.lemanoman;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SqliteFactory implements TaskItemFactory {
    public final String url;

    public SqliteFactory(String dbPath) {
        url = "jdbc:sqlite:" + dbPath;
        SqliteConnector connector = new SqliteConnector(url);
        try {
            connector.execute("CREATE TABLE IF NOT EXISTS tasks (id TEXT PRIMARY KEY, title TEXT, description TEXT, done BOOLEAN)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static TaskItem convert(ResultSet rs) throws SQLException {
        TaskItem task = new TaskItem();
        task.setId(rs.getString("id"));
        task.setName(rs.getString("title"));
        task.setDescription(rs.getString("description"));
        task.setDone(rs.getBoolean("done"));
        return task;
    }

    @Override
    public TaskItem add(TaskItem taskItem) {
        SqliteConnector connector = new SqliteConnector(url);
        try {
            connector.execute("INSERT INTO tasks (id,title,description,done) VALUES ('" + taskItem.getId() + "','" + taskItem.getName() + "','" + taskItem.getDescription() + "'," + (taskItem.isDone() ? 1 : 0) + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskItem;
    }

    @Override
    public TaskItem update(TaskItem taskItem) {
        SqliteConnector connector = new SqliteConnector(url);
        try {
            connector.execute("UPDATE tasks SET title=?,description=?,done=? WHERE id=?", true,
                    new StatementParam<>(1, taskItem.getName(), String.class),
                    new StatementParam<>(2, taskItem.getDescription(), String.class),
                    new StatementParam<>(3, taskItem.isDone(), Boolean.class),
                    new StatementParam<>(4, taskItem.getId(), String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return get(taskItem.getId());
    }

    @Override
    public boolean delete(TaskItem taskItem) {
        SqliteConnector connector = new SqliteConnector(url);
        try {
            connector.execute("DELETE FROM tasks WHERE id=?", true, new StatementParam<>(1, taskItem.getId(), String.class));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public TaskItem get(String id) {
        SqliteConnector connector = new SqliteConnector(url);
        try {
            return connector.executeQuery("SELECT * FROM tasks WHERE id=?", SqliteFactory::convert, new StatementParam<>(1, id, String.class)).getFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TaskItem> list() {
        SqliteConnector connector = new SqliteConnector(url);
        try {
            return connector.executeQuery("SELECT * FROM tasks", SqliteFactory::convert);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
