package com.br.ufpe.cin.model;

public class _1GRAM extends StringWordToVecConfig{

	@Override
	public void init() {
		getParams().add(StringWordToVecParam.NGRAM_1_1_TOKENIZER);
	}

}
