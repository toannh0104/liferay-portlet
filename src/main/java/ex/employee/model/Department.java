package ex.employee.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * It is master table of department category and related department name
 * 
 * @author toannh
 * 
 */
@Entity
@Table(name = "DEPARTMENT")
public class Department implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -1872011675486750426L;

  @Id
  @Column(name = "ID")
  @GeneratedValue
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "CATEGORY_ID")
  private DepartmentCategory departmentCategory;

  @Column(name = "DEPARTMENT_NAME")
  private String departmentName;

  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the departmentName
   */
  public String getDepartmentName() {
    return departmentName;
  }

  /**
   * @param departmentName the departmentName to set
   */
  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  /**
   * @return the departmentCategory
   */
  public DepartmentCategory getDepartmentCategory() {
    return departmentCategory;
  }

  /**
   * @param departmentCategory the departmentCategory to set
   */
  public void setDepartmentCategory(DepartmentCategory departmentCategory) {
    this.departmentCategory = departmentCategory;
  }
}
