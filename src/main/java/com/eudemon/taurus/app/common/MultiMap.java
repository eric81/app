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
 * multi-multi relation
 * 
 * @author xiaoyang.zhang
 *
 * @param <Left>
 * @param <Right>
 */
public class MultiMap<Left, Right> implements Serializable{
	private static final long serialVersionUID = -8675204636275616378L;

	private Map<Left, Set<Right>> lmp = new HashMap<Left, Set<Right>>();

	private Map<Right, Set<Left>> rmp = new HashMap<Right, Set<Left>>();
	
	public synchronized Set<Left> getLeftKeys() {
		return lmp.keySet();
	}
	
	public synchronized Set<Right> getRightKeys() {
		return rmp.keySet();
	}

	public synchronized Set<Right> getRight(Left key) {
		return lmp.get(key);
	}

	public synchronized Set<Left> getLeft(Right key) {
		return rmp.get(key);
	}

	public synchronized void add(Left left, Right right) {
		Set<Right> rset = lmp.get(left);
		if (null == rset) {
			rset = new HashSet<Right>();
			lmp.put(left, rset);
		}
		rset.add(right);

		Set<Left> lset = rmp.get(right);
		if (null == lset) {
			lset = new HashSet<Left>();
			rmp.put(right, lset);
		}
		lset.add(left);
	}

	public synchronized void remove(Left left, Right right) {
		Set<Right> rset = lmp.get(left);
		Iterator<Right> rit = rset.iterator();
		Right rtmp = null;
		while (rit.hasNext()) {
			rtmp = rit.next();
			if (null != rtmp && rtmp.equals(right)) {
				rit.remove();
			}
		}
		
		Set<Left> lset = rmp.get(right);
		Iterator<Left> lit = lset.iterator();
		Left ltmp = null;
		while (lit.hasNext()) {
			ltmp = lit.next();
			if (null != ltmp && ltmp.equals(left)) {
				lit.remove();
			}
		}
	}
	
	public synchronized void removeByLeft(Left left) {
		lmp.remove(left);
		
		Iterator<Set<Left>> lsIt = rmp.values().iterator();
		while (lsIt.hasNext()) {
			Set<Left> st = lsIt.next();
			Iterator<Left> lit = st.iterator();
			Left ltmp = null;
			while (lit.hasNext()) {
				ltmp = lit.next();
				if (null != ltmp && ltmp.equals(left)) {
					lit.remove();
				}
			}
		}
	}
	
	public synchronized void removeByRight(Right right) {
		rmp.remove(right);
		
		Iterator<Set<Right>> rsIt = lmp.values().iterator();
		while (rsIt.hasNext()) {
			Set<Right> rst = rsIt.next();
			Iterator<Right> rIt = rst.iterator();
			Right rt = null;
			while (rIt.hasNext()) {
				rt = rIt.next();
				if (null != rt && rt.equals(right)) {
					rIt.remove();
				}
			}
		}
	}

	public synchronized String toString() {
		StringBuffer sb = new StringBuffer();
		Iterator<Left> lit = lmp.keySet().iterator();
		while (lit.hasNext()) {
			Left key = lit.next();
			Set<Right> set = lmp.get(key);
			sb.append(key.toString() + ":" + set.toString() + " ");
		}
		
		return sb.toString();
	}
	
	public synchronized List<String> status() {
		List<String> st = new ArrayList<String>();
		Iterator<Left> lit = lmp.keySet().iterator();
		while (lit.hasNext()) {
			Left key = lit.next();
			Set<Right> set = lmp.get(key);
			st.add(key.toString() + ":" + set.toString() + " ");
		}
		
		return st;
	}
	
	public static void main(String[] args){
		MultiMap<Long, String> smp = new MultiMap<Long, String>();
		smp.add(new Long(1), "a");
		smp.add(new Long(1), "b");
		smp.add(new Long(1), "c");
		smp.add(new Long(2), "a");
		smp.add(new Long(2), "b");
		smp.add(new Long(3), "c");
		System.out.println(smp);
		smp.remove(new Long(2), "b");
		smp.removeByRight("a");
		System.out.println(smp);
	}
}
