package ex.employee.service.impl;

import java.util.List;

import ex.employee.dao.GenericDao;
import ex.employee.model.DepartmentCategory;
import ex.employee.service.DepartmentCategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of service layer for employee categories
 * 
 * @author toannh
 * 
 */
@Service("departmentCategoryService")
public class DepartmentCategoryServiceImpl implements DepartmentCategoryService {

  private Logger logger = Logger.getLogger(DepartmentCategoryServiceImpl.class);

  /**
   * Reference to the data object
   */
  @Autowired
  private GenericDao<DepartmentCategory, Long> departmentCategoryDAO;

  /* (non-Javadoc)
   * @see DepartmentCategoryService#getDepartmentCategories()
   */
  @Override
  public List<DepartmentCategory> getDepartmentCategories() throws Exception {
    logger.debug("getDepartmentCategories()");
    return departmentCategoryDAO.findAll();
  }
}
