package ex.employee.service;

import ex.employee.model.Department;

import java.util.List;


/**
 * Interface for service layer of department details
 * 
 * @author toannh
 * 
 */
public interface DepartmentService {

  /**
   * Retrieve list of department
   * 
   * @return
   * @throws Exception
   */
  public List<Department> getDepartmentList() throws Exception;
}
