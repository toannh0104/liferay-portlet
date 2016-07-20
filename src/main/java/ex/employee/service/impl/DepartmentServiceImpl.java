package ex.employee.service.impl;

import java.util.List;

import ex.employee.dao.GenericDao;
import ex.employee.model.Department;
import ex.employee.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of service layer for department
 * 
 * @author aspire
 * 
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

  private Logger logger = Logger.getLogger(DepartmentServiceImpl.class);

  /**
   * Reference to the data object
   */
  @Autowired
  private GenericDao<Department, Long> departmentDAO;

  /* (non-Javadoc)
   * @see ex.employee.service.EmployeeService#getEmployeeList()
   */
  @Override
  public List<Department> getDepartmentList() throws Exception {
    return departmentDAO.findAll();
  }
}
