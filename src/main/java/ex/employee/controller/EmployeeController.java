package ex.employee.controller;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import java.util.Map;

/**
 * Controller for manages employee
 * 
 * @author toannh
 * 
 */
@RequestMapping("VIEW")
@Controller
public class EmployeeController {

  private Logger logger = Logger.getLogger(EmployeeController.class);
  // Constants
  private static final String EMPLOYEE_LIST_JSP = "employeeList";

  private MessageSource messageSource;

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
}
