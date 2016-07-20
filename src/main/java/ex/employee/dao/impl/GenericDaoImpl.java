package ex.employee.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ex.employee.dao.GenericDao;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Implementation of data access layer
 * 
 * @author toannh
 * 
 */
@Transactional
public class GenericDaoImpl<E, K extends Serializable> extends HibernateDaoSupport implements
        GenericDao<E, K> {

  private Logger log = Logger.getLogger(GenericDaoImpl.class);
  private Class<? extends E> clazz;

  @Resource(name = "sessionFactory")
  protected SessionFactory sessionFactory;

  protected Session getCurrentSession() {
    return getSessionFactory().getCurrentSession();
  }

  public GenericDaoImpl() {
    log.info("GenericDaoImpl Default Constructor");
  }

  public GenericDaoImpl(Class<? extends E> clazz) {
    this.clazz = clazz;
  }

  /*
   * (non-Javadoc)
   * 
   * @see GenericDao#create(java.lang.Object)
   */
  @Override
  public E create(E entity) throws Exception {
    log.debug("Executing method : create()");
   getCurrentSession().saveOrUpdate(entity);
    return entity;
  }

  /*
   * (non-Javadoc)
   * 
   * @see GenericDao#findById(java.lang.Long)
   */
  @SuppressWarnings("unchecked")
  public E findById(Integer id) throws Exception {
    log.debug("Executing method : findById() with id = " + id);
    log.debug("class is " + clazz);
    return (E) getCurrentSession().get(clazz, id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see GenericDao#merge(java.lang.Object)
   */
  @Override
  public E merge(E transientObject) throws Exception {
    log.debug("Executing method : merge()");
    getCurrentSession().merge(transientObject);
    return transientObject;

  }

  /*
   * (non-Javadoc)
   * 
   * @see GenericDao#delete(java.lang.Object)
   */
  @Override
  public void delete(E persistentObject) throws Exception {
    log.debug("Executing method : delete()");
    getCurrentSession().delete(persistentObject);
  }

  /*
   * (non-Javadoc)
   * 
   * @see GenericDao#findAll()
   */
  @SuppressWarnings("unchecked")
  public List<E> findAll() throws Exception {
    log.debug("Executing method : findAll()");
    return (List<E>) getHibernateTemplate().loadAll(this.clazz);
  }

  /*
   * (non-Javadoc)
   * 
   * @see GenericDao#findByQueryParams(java.lang.String, java.util.Map)
   */
  public List<E> findByQueryParams(String queryName, Map<String, Object> queryParameters)
      throws Exception {
    log.debug("Executing method : findByQueryParams()");
    Query query = createdNamedQuery(queryName);
    setParametersInQuery(query, queryParameters);
    return runQuery(query);
  }

  /**
   * Create name query with given queryname
   * 
   * @param queryName
   * @return
   * @throws Exception
   */
  private Query createdNamedQuery(String queryName) throws Exception {
    log.debug("Executing method : createdNamedQuery()");
    Query query = getCurrentSession().getNamedQuery(queryName);
    query.setCacheable(true);
    return query;
  }

  /**
   * Set the parameter into the query
   * 
   * @param query
   * @param queryParameters
   * @throws Exception
   */
  private static void setParametersInQuery(Query query, Map<String, Object> queryParameters)
      throws Exception {
    if (queryParameters != null) {
      Iterator<Map.Entry<String, Object>> queryParamIterator =
          queryParameters.entrySet().iterator();
      while (queryParamIterator.hasNext()) {
        Map.Entry<String, Object> entry = queryParamIterator.next();
        if (entry.getValue() instanceof Collection)
          query.setParameterList(entry.getKey(), (Collection) entry.getValue());
        else
          query.setParameter(entry.getKey(), entry.getValue());
      }
    }
  }

  /**
   * Runs the query and return result object list
   * 
   * @param query
   * @return
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  private List<E> runQuery(Query query) throws Exception {
    query.setCacheable(true);
    log.debug("Query :" + query.toString());
    return (List<E>) query.list();
  }
}
