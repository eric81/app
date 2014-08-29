package com.eudemon.taurus.app.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.crazycake.shiro.SerializeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eudemon.taurus.app.cache.redis.KryoUtil;
import com.eudemon.taurus.app.dao.ESessionDao;
import com.eudemon.taurus.app.entity.Esession;
import com.eudemon.taurus.app.json.JsonMapper;


@Component
public class DataBaseSessionDao extends AbstractSessionDAO {

	private static Logger logger = LoggerFactory.getLogger(DataBaseSessionDao.class);
			
	@Autowired
	private ESessionDao dao;

	@Override
	public Collection<Session> getActiveSessions() {
		Collection<Session> ct = new ArrayList<Session>();
		List<Esession> ls = dao.queryAll();
		for(Esession es : ls){
			Session ss = (SimpleSession)SerializeUtils.deserialize(es.getSession().getBytes());
			ct.add(ss);
		}
		return ct;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		Esession es = new Esession();
		es.setId((String) sessionId);
		es.setSession(new String(SerializeUtils.serialize(session)));
		dao.saveNoId(es);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		String id = (String) sessionId;
		Esession es = dao.query(id);
		if(null == es){
			return null;
		}
		Session se =  (SimpleSession)SerializeUtils.deserialize(es.getSession().getBytes());
		return se;
	}

	@Override
	public void update(Session session) {
		Esession es = new Esession();
		es.setId((String) session.getId());
		es.setSession(new String(SerializeUtils.serialize(session)));
		dao.update(es);
	}

	@Override
	public void delete(Session session) {
		String id = (String) session.getId();
		dao.delete(id);
	}

}
