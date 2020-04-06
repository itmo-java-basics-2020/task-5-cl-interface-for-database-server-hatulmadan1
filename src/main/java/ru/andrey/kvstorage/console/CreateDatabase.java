package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CreateDatabase implements DatabaseCommand{
    private String DatabaseName;
    private ExecutionEnvironment Environment;

    public CreateDatabase(ExecutionEnvironment environment, String databaseName) {
        this.Environment = environment;
        this.DatabaseName = databaseName;
    }

    public DatabaseCommandResult execute() throws DatabaseException {
        try {
            Environment.addDatabase(Environment.getDatabase(DatabaseName).get());
            return DatabaseCommandResult.DefaultDatabaseCommandResult.success("DataBase created");
        } catch (Exception e) {
            return DatabaseCommandResult.DefaultDatabaseCommandResult.error(e.getMessage());
        }
    }
}
