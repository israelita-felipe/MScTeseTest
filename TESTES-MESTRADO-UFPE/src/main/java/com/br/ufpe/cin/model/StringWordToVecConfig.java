package com.br.ufpe.cin.model;

import java.util.HashSet;
import java.util.Set;

public abstract class StringWordToVecConfig {

	private Set<StringWordToVecParam> params;
	
	public StringWordToVecConfig() {
		this.params = new HashSet<>();		
	}

	public Set<StringWordToVecParam> getParams() {
		return params;
	}
	
	/**
	 * This method is used to add params
	 */
	public abstract void init();
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("weka.filters.unsupervised.attribute.StringToWordVector -R 1-4 -W 100000 -prune-rate -1.0 -C -N 0 -O ");
		for(StringWordToVecParam param:params) {
			sb.append(param.toString());
		}
		return sb.toString();
	}
}
