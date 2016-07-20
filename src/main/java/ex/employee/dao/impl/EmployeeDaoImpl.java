package ex.employee.dao.impl;

import ex.employee.dao.EmployeeDao;
import ex.employee.model.Employee;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 7/20/2016.
 */
@Repository
public class EmployeeDaoImpl extends GenericDaoImpl<Employee, Integer> implements EmployeeDao{

}
