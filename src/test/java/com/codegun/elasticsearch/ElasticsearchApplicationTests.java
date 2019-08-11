package com.codegun.elasticsearch;

import com.codegun.elasticsearch.model.Department;
import com.codegun.elasticsearch.model.Employee;
import com.codegun.elasticsearch.model.Organization;
import com.codegun.elasticsearch.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class ElasticsearchApplicationTests {

    @ClassRule
    public static ElasticsearchContainer container = new ElasticsearchContainer();
    @Autowired
    EmployeeRepository repository;

    @BeforeClass
    public static void before() {
        System.setProperty("spring.data.elasticsearch.cluster-nodes", container.getContainerIpAddress() + ":" + container.getMappedPort(9300));
    }

    @Test
    public void testAdd() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Ahn junwoo");
        employee.setAge(33);
        employee.setPosition("Developer");
        employee.setDepartment(new Department(1L, "naverogq"));
        employee.setOrganization(new Organization(1L, "ogq", "seoul yangjea"));
        employee = repository.save(employee);
        Assert.assertNotNull(employee);
    }

    @Test
    public void testFindAll() {
        Iterable<Employee> employees = repository.findAll();
        Assert.assertTrue(employees.iterator().hasNext());
    }

    @Test
    public void testFindByOrganization() {
        List<Employee> employees = repository.findByOrganizationName("TestO");
        Assert.assertTrue(employees.size() > 0);
    }

    @Test
    public void testFindByName() {
        List<Employee> employees = repository.findByName("John Smith");
        Assert.assertTrue(employees.size() > 0);
    }

}
