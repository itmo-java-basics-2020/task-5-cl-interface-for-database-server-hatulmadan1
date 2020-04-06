package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseCommands;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;
import java.util.Scanner;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        DatabaseServer DBServer = new DatabaseServer(new ExecutionEnvironment() {
            @Override
            public Optional<Database> getDatabase(String name) {
                return Optional.empty();
            }

            @Override
            public void addDatabase(Database db) {

            }
        });
        Scanner in  = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your command");
            String Command = in.nextLine();
            System.out.println(Command);

            if (Command.equals("QUIT")) {
                break;
            }

            DBServer.executeNextCommand(Command);
        }
        in.close();
    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        try {
            String[] CommandAndArguments = commandText.split(" ");
            return DatabaseCommands.valueOf(CommandAndArguments[0]).getCommand(this.env, CommandAndArguments).execute();
        } catch (Exception e) {
            System.out.println("Sorry, wrong command. Please, try again: " + e.getMessage());
            return DatabaseCommandResult.DefaultDatabaseCommandResult.error(e.getMessage());
        }
    }
}
