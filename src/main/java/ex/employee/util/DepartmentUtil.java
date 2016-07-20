package ex.employee.util;

import java.util.List;
import java.util.Map;

import ex.employee.model.Department;
import ex.employee.service.DepartmentService;
import org.apache.log4j.Logger;

/**
 * A Utility class for department
 * @author toannh
 * 
 */
public class DepartmentUtil {

  public static Logger logger = Logger.getLogger(DepartmentUtil.class);

  /**
   * Use to load departments and set in map
   * @param departmentService
   * @param map
   */
  public static void loadDepartmentList(DepartmentService departmentService, Map<String, Object> map) {
    List<Department> departmentList = null;
    try {
      departmentList = departmentService.getDepartmentList();
    } catch (Exception e) {
      logger.error("Error getting department category list.", e);
    }
    if (departmentList != null)
      map.put("departments", departmentList);
  }
}
