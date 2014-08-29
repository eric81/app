package com.eudemon.taurus.app.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Object Type
 * 
 * single-multi relation
 * 
 * @author xiaoyang.zhang
 *
 * @param <Key>
 * @param <ObjInSet>
 */
public class SetMap<Key, ObjInSet> implements Serializable{
	private static final long serialVersionUID = -489452408591644715L;
	private Map<Key, Set<ObjInSet>>  mp = new HashMap<Key, Set<ObjInSet>>();
	
	public Set<ObjInSet> get(Key key){
		return mp.get(key);
	}
	
	public void add(Key key, ObjInSet obj){
		Set<ObjInSet> set = mp.get(key);
		if(null == set){
			set = new HashSet<ObjInSet>();
			mp.put(key, set);
		}
		
		set.add(obj);
	}
	
	public void remove(Key key,  ObjInSet obj){
		if(null == obj){
			return;
		}
		Set<ObjInSet> set = mp.get(key);
		Iterator<ObjInSet> it = set.iterator();
		ObjInSet tmp = null;
		while(it.hasNext()){
			tmp = it.next();
			if(null != tmp && tmp.equals(obj)){
				it.remove();
			}
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		Iterator<Key> it = mp.keySet().iterator();
		while(it.hasNext()){
			Key key = it.next();
			Set<ObjInSet> set = mp.get(key);
			sb.append(key.toString() + ":" + set.toString() + "\n");
		}
		
		return sb.toString();
	}
	
	public List<String> status(){
		List<String> ls = new ArrayList<String>();
		
		Iterator<Key> it = mp.keySet().iterator();
		while(it.hasNext()){
			Key key = it.next();
			Set<ObjInSet> set = mp.get(key);
			ls.add(key.toString() + ":" + set);
		}
		
		return ls;
	}
	
	public static void main(String[] args){
		SetMap<Long, String> smp = new SetMap<Long, String>();
		smp.add(new Long(1), "eric");
		smp.add(new Long(1), "allen");
		smp.add(new Long(2), "a");
		smp.add(new Long(2), "b");
		System.out.println(smp.get(new Long(1)));
		System.out.println(smp);
		smp.remove(new Long(2), "b");
		System.out.println(smp);
	}
}
