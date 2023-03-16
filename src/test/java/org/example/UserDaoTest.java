package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    //테스트 코드를 수행하기 전에 수행해야하는 작업
    @BeforeEach
    void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    void crateTest() throws SQLException {
        UserDao userDao = new UserDao();

        userDao.create(new User("kong", "password", "name", "email"));

        User user = userDao.findByUserId("kong");
        assertThat(user).isEqualTo(new User("kong", "password", "name", "email"));
    }
}
