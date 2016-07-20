package ex.employee.util;

import java.util.ArrayList;
import java.util.List;

import ex.employee.model.Employee;
import ex.employee.vo.EmployeeVO;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

/**
 * A utility class for Employee
 * @author toannh
 * 
 */
public class EmployeeUtil {

  public static Logger logger = Logger.getLogger(EmployeeUtil.class);

  /**
   * Load EmployeeVO list from the employee list
   *
   * @param employeeList
   * @return
   */
  public static List<EmployeeVO> getEmployeeVOList(List<Employee> employeeList) {
    List<EmployeeVO> employeeVOList = new ArrayList<EmployeeVO>();
    if (employeeList != null && !employeeList.isEmpty()) {
      logger.info("Employee Size is " + employeeList.size());
      // Copy list to all employee view
      EmployeeVO employeeVO = null;
      for (Employee employee : employeeList) {
        employeeVO = new EmployeeVO();
        BeanUtils.copyProperties(employee, employeeVO);
        employeeVOList.add(employeeVO);
      }
      logger.info("Employee View list size " + employeeVOList.size());
    }
    return employeeVOList;
  }
}
