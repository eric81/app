package com.eudemon.taurus.app.dao;

import java.util.List;

import com.eudemon.taurus.app.entity.Esession;

public interface ESessionDao extends BaseDao<Esession> {
	public boolean saveNoId(Esession t);
	Esession query(String id);
	List<Esession> queryAll();
	public boolean delete(String id);
}
