package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class CreateTable implements DatabaseCommand {
    private String DatabaseName, TableName;
    private ExecutionEnvironment Environment;

    public CreateTable(ExecutionEnvironment environment, String databaseName, String tableName) {
        this.Environment = environment;
        this.TableName = tableName;
        this.DatabaseName = databaseName;
    }

    public DatabaseCommandResult execute() throws DatabaseException {
        try {
            Environment.getDatabase(DatabaseName).get().createTableIfNotExists(TableName);
            return DatabaseCommandResult.DefaultDatabaseCommandResult.success("Table created");
        } catch (Exception e) {
            return DatabaseCommandResult.DefaultDatabaseCommandResult.error(e.getMessage());
        }
    }
}
