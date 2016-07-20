package ex.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import ex.employee.constant.Constants;
import ex.employee.model.Employee;
import ex.employee.service.DepartmentCategoryService;
import ex.employee.service.DepartmentService;
import ex.employee.service.EmployeeService;
import ex.employee.util.DepartmentCategoryUtil;
import ex.employee.util.DepartmentUtil;
import ex.employee.util.EmployeeUtil;
import ex.employee.util.JsonServiceUtil;
import ex.employee.vo.EmployeeVO;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

/**
 * Controller for manages employee
 * 
 * @author toannh
 * 
 */
@RequestMapping("VIEW")
@Controller
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private DepartmentService departmentService;
  @Autowired
  private DepartmentCategoryService departmentCategoryService;
  @Autowired
  private MessageSource messageSource;
  private Logger logger = Logger.getLogger(EmployeeController.class);
  
  // Constants
  private static final String EMPLOYEE_LIST_JSP = "employeeList";
  private static final String EMPLOYEE_FORM_JSP = "employeeForm";
  private static final String EMPLOYEE_VIEW_JSP = "employeeView";

  /**
   * Default render method when page is load
   * 
   * @param map
   * @return
   */
  @RenderMapping
  public String defaultRender(Map<String, Object> map) {
    logger.debug("in defaultRender()");
    return EMPLOYEE_LIST_JSP;
  }

  /**
   * Render and set required objects for employee form
   * 
   * @param map
   * @return
   */
  @RenderMapping(params = "render=employeeForm")
  public String employeeForm(@RequestParam(value = "employeeId", required = false) Integer employeeId,
      Map<String, Object> map) {
    Employee employee = null;
    if (employeeId != null) {
      logger.info("employee id is " + employeeId);
      employee = employeeService.getEmployee(employeeId);
    }
    if (employee == null) {
      employee = new Employee();
    }
    map.put("employee", employee);
    DepartmentCategoryUtil.loadDepartmentCategoryList(departmentCategoryService, map);
    DepartmentUtil.loadDepartmentList(departmentService, map);
    return EMPLOYEE_FORM_JSP;
  }

  /**
   * View employee data
   * 
   * @param employeeId
   * @param map
   * @return
   */
  @RenderMapping(params = "render=employeeView")
  public String employeeView(@RequestParam(value = "employeetId", required = false) Integer employeeId,
      Map<String, Object> map) {
    EmployeeVO employeeVO = null;
    if (employeeId != null) {
      logger.info("employee id is " + employeeId);
      Employee employee = employeeService.getEmployee(employeeId);
      employeeVO = new EmployeeVO();
      BeanUtils.copyProperties(employee, employeeVO);
    }
    if (employeeVO != null) {
      map.put("employee", employeeVO);
    }
    return EMPLOYEE_VIEW_JSP;
  }

  /**
   * Create a new employee
   * 
   * @param actionRequest
   * @param actionResponse
   * @param model
   * @param employee
   * @param result
   * @throws IOException
   * @throws PortletException
   */
  @ActionMapping(params = "action=save")
  public void saveEmployee(ActionRequest actionRequest, ActionResponse actionResponse, Model model,
                          @ModelAttribute("employee") Employee employee, BindingResult result) throws IOException,
      PortletException {
    if (employee.getDepartment().getId() == null) {
      employee.setDepartment(null);
    }

    try {
      employee = employeeService.save(employee);
    } catch (Exception e) {
      logger.error("Error while saving employee", e);
    }

    if (employee != null) {
      actionRequest.setAttribute(Constants.SUCCESS,
          messageSource.getMessage("employee.save.success", null, null));
    } else {
      actionRequest.setAttribute(Constants.ERROR,
          messageSource.getMessage("employee.error.success", null, null));
    }
  }

  /**
   * Delete Employee
   * 
   * @param employeeId
   * @param request
   * @param response
   * @param model
   * @throws IOException
   * @throws PortletException
   */
  @ResourceMapping("deleteEmployee")
  public void deleteEmployee(@RequestParam("employeeId") Integer employeeId, ResourceRequest request,
      ResourceResponse response, Model model) throws IOException, PortletException {
    logger.info("Employee Id=" + employeeId);
    PrintWriter writer = null;
    Map<String, Object> map = new HashMap<String, Object>();
    String actionMessage = null;
    try {
      writer = response.getWriter();
      employeeService.removeEmployee(employeeId);
      actionMessage = messageSource.getMessage("employee.delete.success", null, null);
      map.put("status", Constants.SUCCESS);
    } catch (Exception e) {
      actionMessage = messageSource.getMessage("employee.delete.error", null, null);
      map.put("status", Constants.ERROR);
    }
    map.put("actionMessage", actionMessage);
    JsonServiceUtil.writeJson(writer, map);
  }

  /**
   * Retrieve all employee and set in view object
   * 
   * @param request
   * @param response
   */
  @ResourceMapping("getAllEmployee")
  public void getAllEmployee(ResourceRequest request, ResourceResponse response) {
    PrintWriter writer = null;
    List<Employee> employeeList = null;
    try {
      writer = response.getWriter();
      employeeList = employeeService.getEmployeeList();
    } catch (Exception e) {
      logger.error("Error while getting all employee", e);
    }
    Map<String, Object> map = new HashMap<String, Object>();
    List<EmployeeVO> employeeVOList = EmployeeUtil.getEmployeeVOList(employeeList);
    map.put("aaData", employeeVOList);
    JsonServiceUtil.writeJson(writer, map);
  }

  /**
   * Search employee by query string
   * 
   * @param request
   * @param response
   */
  @ResourceMapping("searchEmployees")
  public void searchEmployees(@RequestParam("quertString") String queryString,
      ResourceRequest request, ResourceResponse response) {
    logger.info("searchEmployees()");
    PrintWriter writer = null;
    List<Employee> employeeList = null;
    try {
      writer = response.getWriter();
      employeeList = employeeService.searchEmployees(queryString);
    } catch (Exception e) {
      logger.error("Error while getting all employee", e);
    }
    Map<String, Object> map = new HashMap<String, Object>();
    List<EmployeeVO> listEmployeeVO = EmployeeUtil.getEmployeeVOList(employeeList);
    map.put("employeeList", listEmployeeVO);
    JsonServiceUtil.writeJson(writer, map);
  }
}
