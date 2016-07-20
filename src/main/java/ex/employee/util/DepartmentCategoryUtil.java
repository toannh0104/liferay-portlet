package ex.employee.util;

import java.util.List;
import java.util.Map;

import ex.employee.model.DepartmentCategory;
import ex.employee.service.DepartmentCategoryService;
import org.apache.log4j.Logger;

/**
 * A Utility class for department category
 * @author aspire20
 * 
 */
public class DepartmentCategoryUtil {

  public static Logger logger = Logger.getLogger(DepartmentCategoryUtil.class);

  /**
   * Use to load department category and set in map
   * @param departmentCategoryService
   * @param map
   */
  public static void loadDepartmentCategoryList(DepartmentCategoryService departmentCategoryService,
                                                Map<String, Object> map) {
    List<DepartmentCategory> departmentCategoryList = null;
    try {
      departmentCategoryList = departmentCategoryService.getDepartmentCategories();
    } catch (Exception e) {
      logger.error("Error getting department category list.", e);
    }
    if (departmentCategoryList != null)
      map.put("departmentCategory", departmentCategoryList);
  }
}
