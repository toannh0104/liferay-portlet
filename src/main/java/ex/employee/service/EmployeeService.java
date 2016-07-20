package ex.employee.service;

import java.util.List;
import ex.employee.model.Employee;

/**
 * Interface for service layer of employee
 * 
 * @author toannh
 * 
 */
public interface EmployeeService {

  /**
   * Add new employee
   * 
   * @param employee
   * @throws Exception
   */
  public Employee save(Employee employee) throws Exception;

  /**
   * Retrieve list of employee
   * 
   * @return
   * @throws Exception
   */
  public List<Employee> getEmployeeList() throws Exception;

  /**
   * Remove employee by id
   * 
   * @param id
   * @throws Exception
   */
  public void removeEmployee(Integer id) throws Exception;
  
  /**
   * Retrieve Employee by id
   * @param id
   * @return
   * @throws Exception
   */
  public Employee getEmployee(Integer id);
  
  /**
   * Search employee by string
   * @return
   */
  public List<Employee> searchEmployees(String string);
}
