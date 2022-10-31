package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLDataHelper {


    @SneakyThrows
    private static Connection getConnection() {
        var connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass"
        );
        return connection;
    }


    @SneakyThrows
    public static DataHelper.VerificationCode getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        var runner = new QueryRunner();

        try (
                var connection = getConnection()) {
            var code = runner.query(connection, codeSQL, new ScalarHandler<String>());
            return new DataHelper.VerificationCode(code);
        }

    }

    @SneakyThrows
    public static void deleteDatabase() {
        var deleteTransactions = "DELETE FROM card_transactions";
        var deleteCodes = "DELETE FROM auth_codes";
        var deleteCards = "DELETE FROM cards";
        var deleteUsers = "DELETE FROM users";
        var runner = new QueryRunner();

        try (
                var connection = getConnection()) {
            runner.execute(connection, deleteTransactions);
            runner.execute(connection, deleteCodes);
            runner.execute(connection, deleteCards);
            runner.execute(connection, deleteUsers);

        }

    }


}
