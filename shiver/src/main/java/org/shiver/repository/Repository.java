package org.shiver.repository;

import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 * 
 * @author Tom
 *
 * @param <T>
 */
public interface Repository<T> {
	
	/**
	 * 
	 * @param source
	 * @return
	 */
	public int insert( T source );
	
	/**
	 * 
	 * @param source
	 * @return
	 */
	public int update( T source );
	
	/**
	 * 
	 * @param source
	 * @return
	 */
	public int delete(T source);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public T findById(int id);
	
	/**
	 * 
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 
	 * @param source
	 * @return
	 */
	public MapSqlParameterSource prepareUpdate(T source);
	
	/**
	 * 
	 * @return
	 */
	public RowMapper<T> getRowMapper();
	
	/**
	 * 
	 * @return
	 */
	public ResultSetExtractor<T> getResultSetExtractor();
}
