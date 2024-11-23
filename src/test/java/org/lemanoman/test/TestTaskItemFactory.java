package org.lemanoman.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lemanoman.SqliteFactory;
import org.lemanoman.TaskItem;
import org.lemanoman.TaskItemFactory;

import java.io.File;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestTaskItemFactory {
    /**
     * Test the crud operations of TaskItemFactory
     */

    @BeforeEach
    public void setUp() {
        // Create a new database file for each test
        File file = new File("test.db");
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    public void tearDown() {
        // Delete the database file after each test
        File file = new File("test.db");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testCrud() {
        TaskItemFactory taskItemFactory = new SqliteFactory("test.db");
        TaskItem taskItem = new TaskItem(UUID.randomUUID().toString(), "Task 1", "Description 1");
        taskItemFactory.add(taskItem);

        TaskItem taskItem2 = taskItemFactory.get(taskItem.getId());
        assertEquals(taskItem.getName(), taskItem2.getName());
        taskItem2.setName("Task 2");
        taskItemFactory.update(taskItem2);
        TaskItem taskItem3 = taskItemFactory.get(taskItem2.getId());
        assertEquals("Task 2", taskItem3.getName());
        taskItemFactory.delete(taskItem3);
        TaskItem taskItem4 = taskItemFactory.get(taskItem3.getId());
        assertNull(taskItem4);
    }
}
