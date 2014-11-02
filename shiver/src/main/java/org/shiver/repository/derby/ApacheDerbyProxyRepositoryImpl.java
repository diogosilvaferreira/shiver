package org.shiver.repository.derby;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.shiver.model.Proxy;
import org.shiver.repository.ProxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Tom
 *
 */
@Repository("ProxyService")
public class ApacheDerbyProxyRepositoryImpl implements ProxyRepository 
{
	private final String CREATE_SCHEMA_QUERY = 
			"CREATE TABLE proxies "
			+ "( "
				+ "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
				+ "host VARCHAR(250) NOT NULL, "
				+ "port INTEGER NOT NULL, "
				+ "user_name VARCHAR(250), "
				+ "password VARCHAR(250), "
				+ "secure BOOLEAN NOT NULL, "
				+ "default_proxy BOOLEAN NOT NULL, "
				+ "CONSTRAINT primary_key PRIMARY KEY (id) "
			+ ")";
	
	private final String INSERT_QUERY = "INSERT INTO proxies (host, port, user_name, password, secure, default_proxy) "
			+ "values (:host, :port, :user_name, :password, :secure, :default_proxy)";
	
	private final String UPDATE_QUERY = "UPDATE proxies SET host = :host, port = :port, user_name = :user_name, "
			+ "password = :password, secure = :secure, default_proxy = :default_proxy "
			+ "WHERE id = :id";
	
	private final String DELETE_QUERY = "DELETE FROM proxies WHERE id = :id";
	
	private final String FIND_BY_ID_QUERY = "SELECT id, host, port, user_name, password, secure, default_proxy "
			+ "FROM proxies "
			+ "WHERE id = :id";
	
	private final String FIND_ALL_QUERY = "SELECT id, host, port, user_name, password, secure, default_proxy "
			+ "FROM proxies";
	
	private final String FIND_DEFAULT_PROXY_QUERY = "SELECT id, host, port, user_name, password, secure, default_proxy "
			+ "FROM proxies "
			+ "WHERE default_proxy = true";
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public int insert(Proxy proxy) 
	{
		MapSqlParameterSource parameters = prepareUpdate(proxy);	
		return jdbc.update(INSERT_QUERY, parameters);
	}

	public int update(Proxy proxy) 
	{	
		MapSqlParameterSource parameters = prepareUpdate(proxy);	
		return jdbc.update(UPDATE_QUERY, parameters);
	}

	public int delete(Proxy proxy) 
	{
		SqlParameterSource parameters = new MapSqlParameterSource("id", proxy.getId());
		return jdbc.update(DELETE_QUERY, parameters);
	}

	public Proxy findById(int id) 
	{		
		SqlParameterSource parameters = new MapSqlParameterSource("id", id);
		return jdbc.queryForObject(FIND_BY_ID_QUERY, 
				parameters, getRowMapper());
	}
	
	public Proxy findDefaultProxy() 
	{
		Proxy proxy = null;
		try
		{
			proxy = jdbc.queryForObject(FIND_DEFAULT_PROXY_QUERY, 
						new MapSqlParameterSource(), getRowMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			return null;
		}
		
		return proxy; 
	}
	
	public List<Proxy> findAll() 
	{
		return jdbc.query(FIND_ALL_QUERY, 
				getRowMapper());
	}

	public MapSqlParameterSource prepareUpdate(Proxy proxy) 
	{
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		if (proxy.getId() != 0)
			parameters.addValue("id", proxy.getId());
		
		parameters.addValue("host", proxy.getHost());
		parameters.addValue("port", proxy.getPort());
		parameters.addValue("user_name", proxy.getUser());
		parameters.addValue("password", proxy.getPassword());
		parameters.addValue("secure", proxy.isSecure());
		parameters.addValue("default_proxy", proxy.isDefault());
		
		return parameters;
	}

	public RowMapper<Proxy> getRowMapper() {
		return new RowMapper<Proxy>() 
		{
			public Proxy mapRow(ResultSet rs, int row) throws SQLException 
			{
				return getResultSetExtractor().
						extractData(rs);
			}
		};
	}

	public ResultSetExtractor<Proxy> getResultSetExtractor() 
	{
		return new ResultSetExtractor<Proxy>() 
		{			
			public Proxy extractData(ResultSet rs) throws SQLException,
					DataAccessException 
			{				
				Proxy proxy = new Proxy();
				proxy.setId(rs.getInt("id"));
				proxy.setHost(rs.getString("host"));
				proxy.setPort(rs.getInt("port"));
				proxy.setUser(rs.getString("user_name"));
				proxy.setPassword(rs.getString("password"));
				proxy.setSecure(rs.getBoolean("secure"));
				proxy.setDefault(rs.getBoolean("default_proxy"));
				
				return proxy;
			}
		};
	}
	
	public void init() 
	{		
		boolean tableExists = false;
		
		try 
		{
			DatabaseMetaData metaData = dataSource.
					getConnection().
					getMetaData();
			
			ResultSet rs = metaData.getTables(
					null, 
					null, 
					null, 
					null);
			
			while(rs.next())
			{
				tableExists = true;
				break;
			}
			
			if (!tableExists)
				createSchema();
			
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	public void createSchema() 
	{		
		jdbc.update(CREATE_SCHEMA_QUERY, 
				new MapSqlParameterSource());		
	}
	
		
}
