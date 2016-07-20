package ex.employee.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ex.employee.dao.GenericDao;
import ex.employee.model.Employee;
import ex.employee.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of service layer for employee
 * 
 * @author toannh
 * 
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

  private Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

  /**
   * Reference to the data object
   */
  @Autowired
  private GenericDao<Employee, Long> employeeDAO;
  
  /* (non-Javadoc)
   * @see EmployeeService#addEmployee(Employee)
   */
  @Transactional
  public Employee save(Employee employee) throws Exception {
    logger.info("Adding employee details for employee email - " + employee.getEmail());
    try {
      return employeeDAO.create(employee);
    } catch (Exception e) {
      logger.error("Error adding employee details", e);
    }
    return null;
  }

  /* (non-Javadoc)
   * @see EmployeeService#getEmployeeList()
   */
  @Transactional
  public List<Employee> getEmployeeList() throws Exception {
    logger.debug("In getEmployeeList()");
    try {
      return employeeDAO.findAll();
    } catch (Exception e) {
      logger.error("Error getting employee list.", e);
    }
    return null;
  }

  /* (non-Javadoc)
   * @see EmployeeService#removeEmployee(java.lang.Integer)
   */
  @Transactional
  public void removeEmployee(Integer id) throws Exception {
    logger.debug("In removeEmployee()");
    try {
      Employee employee = employeeDAO.findById(id);
      if(employee != null) {
        employeeDAO.delete(employee);
      }
    } catch (Exception e) {
      logger.error("Error removing employee by id - " + id, e);
    }
  }

  
  /* (non-Javadoc)
   * @see EmployeeService#getEmployee(java.lang.Integer)
   */
  @Transactional
  public Employee getEmployee(Integer id) {
    try {
      return employeeDAO.findById(id);
    } catch (Exception e) {
      logger.error("Error while fetching employee by id ",e);
    }
    return null;
  }

  /* (non-Javadoc)
   * @see EmployeeService#searchEmployees(java.lang.String)
   */
  @Transactional
  public List<Employee> searchEmployees(String string) {
    Map<String,Object> queryParameter = new HashMap<String, Object>();
    queryParameter.put("queryString", "%"+string+"%");
    try {
      return employeeDAO.findByQueryParams("fetchEmployeeByQueryString", queryParameter);
    } catch (Exception e) {
      logger.error("Error while fetching employee by id ",e);
    }
    return null;
  }  
}