package com.br.ufpe.cin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.Word;
import net.sf.extjwnl.dictionary.Dictionary;

public class SelectBestSentencesFromWordNet {

	private Map<Integer, Set<String>> map;
	private Dictionary dic;

	@Before
	public void init() throws FileNotFoundException, JWNLException {
		this.map = new HashMap<>();
		this.dic = Dictionary.getDefaultResourceInstance();

		Scanner axiomScan = new Scanner(new File("axiomas aprovados.txt"));

		while (axiomScan.hasNextLine()) {
			Set<String> words = new HashSet<>();
			words.addAll(Arrays.asList(axiomScan.nextLine().toLowerCase().split("(?<=.)(?=\\p{Lu})")));
			Set<String> newWords = new HashSet<>();
			for (String word : words) {
				newWords.addAll(Arrays.asList(word
						.replaceAll("subclassof|some|exactly|some|only|\\[|\\]|\\(|\\)|\\{|\\}| not | and | or ", "")
						.split("\\s+")));
			}
			newWords.remove("");
			newWords.remove(null);
			map.put(map.size(), newWords);
		}
		System.out.println(map.size());
		axiomScan.close();
	}

	@Test
	public void selectBestAxioms() throws IOException {
		Scanner scan = new Scanner(new File("WordNetDataset.txt"));
		FileWriter fw = new FileWriter("WordNetDatasetBest.txt");
		boolean contains = true;
		int i = 0;
		int total = 0;

		while (scan.hasNextLine()) {
			total++;
			String original = scan.nextLine();
			String sentence = original.toLowerCase()
					.replaceAll("subclassof|some|exactly|some|only|\\[|\\]|\\(|\\)|\\{|\\}| not | and | or ", "");
			for (Integer key : map.keySet()) {

				for (String expression : map.get(key)) {

					if (!Arrays.asList(sentence.split("\\s+")).contains(expression)) {
//						System.out.println(Arrays.asList(sentence.split("\\s+"))+" not contains "+expression);
						contains = false;
						for (String word : sentence.split("\\s+")) {
							if (synonyms(dic, word, new HashSet<>()).contains(expression)) {
//								System.out.println(synonyms(dic, word, new HashSet<>())+" contains "+word);
								contains = true;
								break;
							}
						}
					}
					if (!contains) {
						break;
					}
				}

				if (contains) {
					break;
				}
			}

			if (contains) {
				System.out.println((i++) + " de " + (total) + " -> " + original);
				fw.write(original);
				fw.write("\n");
			}
			contains = true;
		}
		fw.close();
		scan.close();
	}

	private static Set<String> synonyms(final Dictionary dic, final String inputWord, final Set<String> synonymSet) {
		IndexWord[] allWords;
		try {
			allWords = dic.lookupAllIndexWords(inputWord).getIndexWordArray();
			if (allWords.length == 1) {
				for (Synset synset : allWords[0].getSenses()) {
					for (Word wordObj : synset.getWords()) {
						synonymSet.add(wordObj.getLemma().toLowerCase());
					}
				}
			}
		} catch (JWNLException e) {
			e.printStackTrace();
		}
		return synonymSet;
	}

}
