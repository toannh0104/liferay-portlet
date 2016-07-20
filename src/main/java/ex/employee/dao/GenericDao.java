package ex.employee.dao;

import java.util.List;
import java.util.Map;

/**
 * GenericDao interface define all basic methods of DAO class related operation.
 * 
 * @author toannh
 * 
 */
public interface GenericDao<E, K> {

  /**
   * Persist the newInstance object into database
   * 
   * @throws Exception
   */
  public E create(E entity) throws Exception;

  /**
   * Retrieve an object that was previously persisted to the database using the indicated id as
   * primary key
   */
  public E findById(Integer id) throws Exception;

  /** Save changes made to a persistent object. */
  public E merge(E transientObject) throws Exception;

  /** Remove an object from persistent storage in the database */
  public void delete(E persistentObject) throws Exception;

  /** Retrieve a list of objects */
  public List<E> findAll() throws Exception;
  
  /**
   * Find list by namedQuery
   * @param queryName
   * @param queryParameters
   * @return
   */
  public List<E> findByQueryParams(String queryName, Map<String, Object> queryParameters) throws Exception;
}
