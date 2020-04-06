package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    class DefaultDatabaseCommandResult implements DatabaseCommandResult {
        private String Result;
        private boolean Success;

        private DefaultDatabaseCommandResult(String result, boolean success) {
            this.Result = result;
            this.Success = success;
        }

        public static DatabaseCommandResult success(String result) {
            return new DefaultDatabaseCommandResult(result, true);
        }

        public static DatabaseCommandResult error(String error) {
            return new DefaultDatabaseCommandResult(error, false);
        }

        public Optional<String> getResult() {
            return this.Success ? Optional.of(this.Result) : Optional.empty();
        }

        public DatabaseCommandStatus getStatus() {
            return this.Success ? DatabaseCommandStatus.SUCCESS : DatabaseCommandStatus.FAILED;
        }

        public boolean isSuccess() {
            return this.Success;
        }

        public String getErrorMessage() {
            return !Success ? this.Result : null;
        }
    }
}