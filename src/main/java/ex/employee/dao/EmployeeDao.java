package ex.employee.dao;

import ex.employee.model.Employee;
import org.springframework.stereotype.Repository;

/**
 * Created by toannh on 7/20/2016.
 */
public interface EmployeeDao extends GenericDao<Employee, Integer> {
}
