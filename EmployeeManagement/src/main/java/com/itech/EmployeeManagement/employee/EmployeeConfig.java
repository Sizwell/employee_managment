package com.itech.EmployeeManagement.employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository){
        return args -> {
//            Employee sizwe = new Employee(
//                    "Sizwe",
//                    "Ncikana",
//                    "sizwe.ncikana@gmail.com",
//                    "0785100865",
//                    "9402125455088",
//                    LocalDate.of(1994, Month.FEBRUARY, 12),
//                    "male",
//                    "African",
//                    "Developer",
//                    2,
//                    "Cape Town",
//                    "Kensington",
//                    "Square on 10th",
//                    7785
//            );
//
//            Employee sibo = new Employee(
//                    "Sibo",
//                    "Gxhamza",
//                    "sibo.gxhamza@gmail.com",
//                    "0785100865",
//                    "9912315455088",
//                    LocalDate.of(1999, Month.DECEMBER, 31),
//                    "female",
//                    "African",
//                    "Developer",
//                    2,
//                    "Cape Town",
//                    "Kensington",
//                    "Square on 10th",
//                    7785
//            );
//
//            repository.saveAll(
//                    List.of(sizwe, sibo)
//            );
        };
    }
}
