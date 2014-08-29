package com.eudemon.taurus.app.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.eudemon.taurus.app.dao.ESessionDao;
import com.eudemon.taurus.app.dao.common.ResultSetUtils;
import com.eudemon.taurus.app.dao.common.SpringJdbcBaseDao;
import com.eudemon.taurus.app.entity.Esession;

@Repository
public class ESessionDaoImpl extends SpringJdbcBaseDao<Esession> implements ESessionDao {

	@Override
	public Esession rsToObj(ResultSet rs) throws SQLException {
		Esession t = new Esession();
		t.setId(ResultSetUtils.getStringValue(rs, "id", ""));
		t.setSession(ResultSetUtils.getStringValue(rs, "session", ""));
		return t;
	}
	
	@Override
	public Esession query(long id) {
		String sql = "select * from esession where id=?";
		return queryForObject(sql, id);
	}
	
	@Override
	public Esession query(String id) {
		String sql = "select * from esession where id=?";
		return queryForObject(sql, id);
	}

	@Override
	public long save(Esession t) {
		String sql = new String("insert into esession(id,session) values(?,?)");
		return saveOnGeneratedKey(sql, t.getId(), t.getSession());
	}

	@Override
	public boolean update(Esession t) {
		String sql = "update esession set session=? where id=?";
		this.save(sql, t.getSession(), t.getId());
		return false;
	}

	@Override
	public boolean delete(long id) {
		String sql = "delete from esession where id=?";
		return save(sql, id);
	}

	@Override
	public boolean delete(String id) {
		String sql = "delete from esession where id=?";
		return save(sql, id);
	}

	@Override
	public List<Esession> queryAll() {
		String sql = "select * from esession";
		return queryForList(sql);
	}

	@Override
	public boolean saveNoId(Esession t) {
		String sql = new String("insert into esession(id,session) values(?,?)");
		return save(sql, t.getId(), t.getSession());
	}
}
