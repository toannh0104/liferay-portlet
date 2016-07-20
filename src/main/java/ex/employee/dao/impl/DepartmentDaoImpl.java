package ex.employee.dao.impl;

import ex.employee.dao.DepartmentDao;
import ex.employee.model.Department;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 7/20/2016.
 */
@Repository
public class DepartmentDaoImpl extends GenericDaoImpl<Department, Integer> implements DepartmentDao {
}
