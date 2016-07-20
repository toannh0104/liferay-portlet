package ex.employee.dao.impl;

import ex.employee.dao.DepartmentCategoryDao;
import ex.employee.model.DepartmentCategory;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 7/20/2016.
 */
@Repository
public class DepartmentCategoryDaoImpl extends GenericDaoImpl<DepartmentCategory, Integer> implements DepartmentCategoryDao {
}
