package ex.employee.service;

import java.util.List;

import ex.employee.model.DepartmentCategory;

/**
 * Interface for service layer of department category
 * 
 * @author aspire
 * 
 */
public interface DepartmentCategoryService {

  /**
   * Retrieve list of category
   * 
   * @return
   * @throws Exception
   */
  public List<DepartmentCategory> getDepartmentCategories() throws Exception;
}
