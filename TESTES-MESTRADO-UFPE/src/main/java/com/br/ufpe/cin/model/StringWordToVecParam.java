package com.br.ufpe.cin.model;

public enum StringWordToVecParam {

	STEMMER("-stemmer weka.core.stemmers.LovinsStemmer "),
	STOPWORD("-stopwords-handler weka.core.stopwords.Rainbow "),
	NULL_STEMMER("-stemmer weka.core.stemmers.NullStemmer "),
	NULL_STOPWORD("-stopwords-handler weka.core.stopwords.Null "),
	NGRAM_1_3_TOKENIZER("-tokenizer \"weka.core.tokenizers.NGramTokenizer -max 3 -min 1 -delimiters \" \r\t\n\"\" "),
	NGRAM_1_2_TOKENIZER("-tokenizer \"weka.core.tokenizers.NGramTokenizer -max 2 -min 1 -delimiters \" \r\t\n\"\" "),
	NGRAM_1_1_TOKENIZER("-tokenizer \"weka.core.tokenizers.NGramTokenizer -max 1 -min 1 -delimiters \" \r\t\n\"\" "),
	CNGRAM_1_3_TOKENIZER("-tokenizer \"weka.core.tokenizers.CharacterNGramTokenizer -max 3 -min 1\" "),
	CNGRAM_1_2_TOKENIZER("-tokenizer \"weka.core.tokenizers.CharacterNGramTokenizer -max 3 -min 1\" "),
	CNGRAM_1_1_TOKENIZER("-tokenizer \"weka.core.tokenizers.CharacterNGramTokenizer -max 3 -min 1\" "),	
	IDF("-I "),
	TF("-T "),
	LOWERCASE("-L ");
	
	
	String value;
	
	StringWordToVecParam(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
		
}
