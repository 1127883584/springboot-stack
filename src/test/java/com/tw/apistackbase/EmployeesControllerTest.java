package com.tw.apistackbase;

import com.tw.apistackbase.controller.CompanyController;
import com.tw.apistackbase.controller.EmployeeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private EmployeeController employeeController;
    private EmployeeRepository mockEmployeeRepository;

    @Test
    public void should_return_employees_when_request_all_employee_api() throws Exception {
        mockEmployeeRepository = Mockito.mock(EmployeeRepository.class);
        List<Employee> mockEmployeeList = new ArrayList<>();
        mockEmployeeList.add(new Employee(1, "zhangsan", 18, "male", 5000));
        mockEmployeeList.add(new Employee(2, "lisi", 25, "female", 7000));
        Mockito.when(mockEmployeeRepository.getEmployees()).thenReturn(mockEmployeeList);

        mockMvc.perform(get("/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"zhangsan\",\n" +
                        "        \"age\": 18,\n" +
                        "        \"gender\": \"male\",\n" +
                        "        \"salary\": 5000\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 2,\n" +
                        "        \"name\": \"lisi\",\n" +
                        "        \"age\": 25,\n" +
                        "        \"gender\": \"female\",\n" +
                        "        \"salary\": 7000\n" +
                        "    }\n" +
                        "]"));
    }
}
