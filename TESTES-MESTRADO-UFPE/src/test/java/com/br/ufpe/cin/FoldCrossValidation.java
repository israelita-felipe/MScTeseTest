package com.br.ufpe.cin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;

@Category(JUnitTest.class)
@RunWith(MockitoJUnitRunner.class)
public class FoldCrossValidation {

	private final String fileNGramPath = "filters/NGram";
	private final String fileCharNGramPath = "filters/CharNGram";
	private Instances dataset;
	private Instances datasetRiloff;

	private Classifier classifier;

	@Before
	public void init() throws Exception {
		ArffLoader arffLoader = new ArffLoader();
		arffLoader.setSource(new File("relations.triples.sentences.txt.arff"));
		dataset = arffLoader.getDataSet();
		dataset.setClassIndex(4);

		arffLoader = new ArffLoader();
		arffLoader.setSource(new File("rillof.triples.sentences.txt.arff"));
		datasetRiloff = arffLoader.getDataSet();
		datasetRiloff.setClassIndex(4);

		classifier = getClassifier(new FileInputStream(new File("classifier/classifier")));
	}

	private Filter getFilter(FileInputStream file) throws Exception {
		return (Filter) SerializationHelper.read(file);
	}

	private Classifier getClassifier(FileInputStream file) throws Exception {
		return (Classifier) SerializationHelper.read(file);
	}

	@Test
	public void NGramSintatic() throws FileNotFoundException, Exception {
		File path = new File(fileNGramPath);
		for (File f : path.listFiles()) {
			Filter filter = getFilter(new FileInputStream(f));
			filter.setInputFormat(dataset);
			Instances instances = Filter.useFilter(dataset, filter);
			Evaluation ev = new Evaluation(instances);
			ev.setMetricsToDisplay(Evaluation.getAllEvaluationMetricNames());
			ev.crossValidateModel(classifier, instances, 10, new Random(1));
			FileWriter fw = new FileWriter(new File("StringWordToVector - NGRAM/" + f.getName()));
			fw.write(classifier.toString());
			fw.write("\n");
			fw.write(ev.toSummaryString());
			fw.write("\n");
			fw.write(ev.toClassDetailsString());
			fw.write("\n");
			fw.write(ev.toMatrixString());
			fw.close();
		}
	}

	@Test
	public void CharNGramSintatic() throws FileNotFoundException, Exception {
		File path = new File(fileCharNGramPath);
		for (File f : path.listFiles()) {
			Filter filter = getFilter(new FileInputStream(f));
			filter.setInputFormat(dataset);
			Instances instances = Filter.useFilter(dataset, filter);
			Evaluation ev = new Evaluation(instances);
			ev.setMetricsToDisplay(Evaluation.getAllEvaluationMetricNames());
			ev.crossValidateModel(classifier, instances, 10, new Random(1));
			FileWriter fw = new FileWriter(new File("StringWordToVector - CNGRAM/" + f.getName()));
			fw.write(classifier.toString());
			fw.write("\n");
			fw.write(ev.toSummaryString());
			fw.write("\n");
			fw.write(ev.toClassDetailsString());
			fw.write("\n");
			fw.write(ev.toMatrixString());
			fw.close();
		}
	}

	@Test
	public void CharNGramNormalizedSintatic() throws FileNotFoundException, Exception {
		File path = new File(fileCharNGramPath);
		for (File f : path.listFiles()) {
			Filter filter = getFilter(new FileInputStream(f));
			filter.setInputFormat(dataset);
			Instances instances = Filter.useFilter(dataset, filter);

			filter = getFilter(new FileInputStream("filters/normalize"));
			filter.setInputFormat(instances);
			instances = Filter.useFilter(instances, filter);

			Evaluation ev = new Evaluation(instances);
			ev.setMetricsToDisplay(Evaluation.getAllEvaluationMetricNames());
			ev.crossValidateModel(classifier, instances, 10, new Random(1));
			FileWriter fw = new FileWriter(new File("StringWordToVector - CNGRAM/" + f.getName() + "+normalized"));
			fw.write(classifier.toString());
			fw.write("\n");
			fw.write(ev.toSummaryString());
			fw.write("\n");
			fw.write(ev.toClassDetailsString());
			fw.write("\n");
			fw.write(ev.toMatrixString());
			fw.close();
		}
	}

	@Test
	public void NGramNormalizedSintatic() throws FileNotFoundException, Exception {
		File path = new File(fileNGramPath);
		for (File f : path.listFiles()) {
			Filter filter = getFilter(new FileInputStream(f));
			filter.setInputFormat(dataset);
			Instances instances = Filter.useFilter(dataset, filter);

			filter = getFilter(new FileInputStream("filters/normalize"));
			filter.setInputFormat(instances);
			instances = Filter.useFilter(instances, filter);

			Evaluation ev = new Evaluation(instances);
			ev.setMetricsToDisplay(Evaluation.getAllEvaluationMetricNames());
			ev.crossValidateModel(classifier, instances, 10, new Random(1));
			FileWriter fw = new FileWriter(new File("StringWordToVector - NGRAM/" + f.getName() + "+normalized"));
			fw.write(classifier.toString());
			fw.write("\n");
			fw.write(ev.toSummaryString());
			fw.write("\n");
			fw.write(ev.toClassDetailsString());
			fw.write("\n");
			fw.write(ev.toMatrixString());
			fw.close();
		}
	}

	/////////////////////////////////////////////////////////
	//
	// RILOFF
	//
	/////////////////////////////////////////////////////////

	@Test
	public void NGramNormalizedRiloff() throws FileNotFoundException, Exception {
		File path = new File(fileNGramPath);
		for (File f : path.listFiles()) {
			Filter filter = getFilter(new FileInputStream(f));
			filter.setInputFormat(datasetRiloff);
			Instances instances = Filter.useFilter(datasetRiloff, filter);

			filter = getFilter(new FileInputStream("filters/normalize"));
			filter.setInputFormat(instances);
			instances = Filter.useFilter(instances, filter);

			Evaluation ev = new Evaluation(instances);
			ev.setMetricsToDisplay(Evaluation.getAllEvaluationMetricNames());
			ev.crossValidateModel(classifier, instances, 10, new Random(1));
			FileWriter fw = new FileWriter(
					new File("StringWordToVector -RILOFF- NGRAM/" + f.getName() + "+normalized"));
			fw.write(classifier.toString());
			fw.write("\n");
			fw.write(ev.toSummaryString());
			fw.write("\n");
			fw.write(ev.toClassDetailsString());
			fw.write("\n");
			fw.write(ev.toMatrixString());
			fw.close();
		}
	}

	@Test
	public void NGramRiloff() throws FileNotFoundException, Exception {
		File path = new File(fileNGramPath);
		for (File f : path.listFiles()) {
			Filter filter = getFilter(new FileInputStream(f));
			filter.setInputFormat(datasetRiloff);
			Instances instances = Filter.useFilter(datasetRiloff, filter);
			Evaluation ev = new Evaluation(instances);
			ev.setMetricsToDisplay(Evaluation.getAllEvaluationMetricNames());
			ev.crossValidateModel(classifier, instances, 10, new Random(1));
			FileWriter fw = new FileWriter(new File("StringWordToVector -RILOFF- NGRAM/" + f.getName()));
			fw.write(classifier.toString());
			fw.write("\n");
			fw.write(ev.toSummaryString());
			fw.write("\n");
			fw.write(ev.toClassDetailsString());
			fw.write("\n");
			fw.write(ev.toMatrixString());
			fw.close();
		}
	}

	@Test
	public void CharNGramNormalizedRiloff() throws FileNotFoundException, Exception {
		File path = new File(fileCharNGramPath);
		for (File f : path.listFiles()) {
			Filter filter = getFilter(new FileInputStream(f));
			filter.setInputFormat(datasetRiloff);
			Instances instances = Filter.useFilter(datasetRiloff, filter);

			filter = getFilter(new FileInputStream("filters/normalize"));
			filter.setInputFormat(instances);
			instances = Filter.useFilter(instances, filter);

			Evaluation ev = new Evaluation(instances);
			ev.setMetricsToDisplay(Evaluation.getAllEvaluationMetricNames());
			ev.crossValidateModel(classifier, instances, 10, new Random(1));
			FileWriter fw = new FileWriter(
					new File("StringWordToVector -RILOFF- CNGRAM/" + f.getName() + "+normalized"));
			fw.write(classifier.toString());
			fw.write("\n");
			fw.write(ev.toSummaryString());
			fw.write("\n");
			fw.write(ev.toClassDetailsString());
			fw.write("\n");
			fw.write(ev.toMatrixString());
			fw.close();
		}
	}

	@Test
	public void CharNGramRiloff() throws FileNotFoundException, Exception {
		File path = new File(fileCharNGramPath);
		for (File f : path.listFiles()) {
			Filter filter = getFilter(new FileInputStream(f));
			filter.setInputFormat(datasetRiloff);
			Instances instances = Filter.useFilter(datasetRiloff, filter);
			Evaluation ev = new Evaluation(instances);
			ev.setMetricsToDisplay(Evaluation.getAllEvaluationMetricNames());
			ev.crossValidateModel(classifier, instances, 10, new Random(1));
			FileWriter fw = new FileWriter(new File("StringWordToVector -RILOFF- CNGRAM/" + f.getName()));
			fw.write(classifier.toString());
			fw.write("\n");
			fw.write(ev.toSummaryString());
			fw.write("\n");
			fw.write(ev.toClassDetailsString());
			fw.write("\n");
			fw.write(ev.toMatrixString());
			fw.close();
		}
	}
}
