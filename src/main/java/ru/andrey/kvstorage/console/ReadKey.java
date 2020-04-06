package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKey implements DatabaseCommand {
    private String DatabaseName, TableName;
    private String Key;
    private ExecutionEnvironment Environment;

    public ReadKey(ExecutionEnvironment Environment, String DatabaseName, String TableName, String Key) {
        this.Environment = Environment;
        this.DatabaseName = DatabaseName;
        this.TableName = TableName;
        this.Key = Key;
    }
    public DatabaseCommandResult execute() throws DatabaseException {
        try {
            return DatabaseCommandResult.DefaultDatabaseCommandResult.
                    success(Environment.getDatabase(DatabaseName).get().read(TableName, Key));
        } catch (Exception e) {
            return DatabaseCommandResult.DefaultDatabaseCommandResult.error(e.getMessage());
        }
    }
}
