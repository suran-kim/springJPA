package com.example.springjpa;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;


@Slf4j
public class JDBCtest {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost/~/test"; // 기본적인 path
    static final String USER = "sa";
    static final String PASS = "";

    static final String DROP_TABLE_SQL = "DROP TABLE customers IF EXISTS";
    static final String CREATE_TABLE_SQL = "CREATE TABLE customers(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))";
    static final String INSERT_SQL = "INSERT INTO customers (id, first_name, last_name) VALUES(1, 'suran', 'kim')";


    @Test
    void jdbc_sample() {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);// 드라이버 매니저를 통해 connection획득
            log.info("GET CONNECTION");

            var statement = connection.createStatement();// state를 통해 쿼리를 날린다.
            statement.executeUpdate(DROP_TABLE_SQL); // 테이블이 존재한다면 삭제
            statement.executeUpdate(CREATE_TABLE_SQL);
            log.info("CREATE TABLE");
            connection.close(); // 커넥션 반납납
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

