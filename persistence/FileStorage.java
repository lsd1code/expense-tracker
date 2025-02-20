package org.lesedibale.projects.expense_tracker.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lesedibale.projects.expense_tracker.expense_tracker.Expense;
import org.lesedibale.projects.task_manager.task.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private final ObjectMapper mapper;
    private final File filepath;

    public FileStorage() {
        mapper = new ObjectMapper();
        filepath = new File("src/main/java/org/lesedibale/projects/expense_tracker/expenses.json");
    }

    public List<Expense> load(){
        try {
            return mapper.readValue(filepath, new TypeReference<List<Expense>>() {});
        }
        catch (IOException ignored) {
            return new ArrayList<>();
        }
    }
    public boolean save(List<Expense> expenses){
        try {
            mapper.writeValue(filepath, expenses);
            return true;
        } catch (IOException e) {
            System.out.println("Enable to save tasks. Please try again later");
            return false;
        }
    }
}
