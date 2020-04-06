package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

import java.io.Console;

public class UpdateKey implements DatabaseCommand {
    private String DatabaseName, TableName;
    private String Key, Value;
    private ExecutionEnvironment Environment;

    public UpdateKey(ExecutionEnvironment Environment, String DatabaseName, String TableName, String Key, String Value) {
        this.Environment = Environment;
        this.DatabaseName = DatabaseName;
        this.TableName = TableName;
        this.Key = Key;
        this.Value = Value;
    }
    public DatabaseCommandResult execute() throws DatabaseException {
        try {
            Environment.getDatabase(DatabaseName).get().write(TableName, Key, Value);
            return DatabaseCommandResult.DefaultDatabaseCommandResult.success("Data written");
        } catch (Exception e) {
            return DatabaseCommandResult.DefaultDatabaseCommandResult.error(e.getMessage());
        }
    }
}
