package org.lemanoman;

public class TaskItem {
    private String id;
    private String name;
    private boolean done;

    private String description;

    public TaskItem(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.done = false;
    }

    public TaskItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.name+" "+(this.done?": OK":"");
    }
}
