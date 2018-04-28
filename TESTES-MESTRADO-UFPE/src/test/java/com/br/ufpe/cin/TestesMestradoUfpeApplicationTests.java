package com.br.ufpe.cin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestesMestradoUfpeApplicationTests {

	private static final String measures = "Weighted Avg";
	private static final String rmsePattern = "Root mean squared error";

	@Test
	public void _3BestNgramTest() throws IOException {
		System.out.println("_NGRAM_");

		File cngram = new File("StringWordToVector - NGRAM");

		Detail _1gram = null;
		Detail _1_2gram = null;
		Detail _1_3gram = null;

		for (File f : cngram.listFiles()) {
			if (f.isDirectory()) {
				continue;
			}
			String name = f.getName().toUpperCase();

			BufferedReader s = new BufferedReader(new FileReader(f));
			double fmeasure = 0, precision = 0, recall = 0, rmse = 0;

			String line = "";
			while (true) {
				if (line.startsWith(rmsePattern)) {
					rmse = Double.parseDouble(line.split("(\\s\\s|\\s\\s\\s)+")[1].trim());
					line = s.readLine();
				} else if (line.startsWith(measures)) {
					String[] values = line.split("(\\s\\s|\\s\\s\\s)+");
					if (!line.contains("?")) {
						precision = Double.parseDouble(values[3].trim().replace(",", "."));
						recall = Double.parseDouble(values[4].trim().replace(",", "."));
						fmeasure = Double.parseDouble(values[5].trim().replace(",", "."));
					}
					break;
				} else {
					line = s.readLine();
				}
			}
			s.close();

			if (name.startsWith("1GRAM")) {
				if (_1gram == null) {
					_1gram = new Detail(fmeasure, recall, precision, rmse, name);
				} else {
					Detail _tmp1gram = new Detail(fmeasure, recall, precision, rmse, name);
					if (_tmp1gram.getMean() > _1gram.getMean()) {
						_1gram = _tmp1gram;
					} else if (_tmp1gram.getMean() == _1gram.getMean()) {
						if (_tmp1gram.getTitle().length() < _1gram.getTitle().length()) {
							_1gram = _tmp1gram;
						}
					}
				}
			} else if (name.startsWith("1-2GRAM")) {
				if (_1_2gram == null) {
					_1_2gram = new Detail(fmeasure, recall, precision, rmse, name);
				} else {
					Detail _tmp1_2gram = new Detail(fmeasure, recall, precision, rmse, name);
					if (_tmp1_2gram.getMean() > _1_2gram.getMean()) {
						_1_2gram = _tmp1_2gram;
					} else if (_tmp1_2gram.getMean() == _1_2gram.getMean()) {
						if (_tmp1_2gram.getTitle().length() < _1_2gram.getTitle().length()) {
							_1_2gram = _tmp1_2gram;
						}
					}
				}
			} else if (name.startsWith("1-3GRAM")) {
				if (_1_3gram == null) {
					_1_3gram = new Detail(fmeasure, recall, precision, rmse, name);
				} else {
					Detail _tmp1_3gram = new Detail(fmeasure, recall, precision, rmse, name);
					if (_tmp1_3gram.getMean() > _1_3gram.getMean()) {
						_1_3gram = _tmp1_3gram;
					} else if (_tmp1_3gram.getMean() == _1_3gram.getMean()) {
						if (_tmp1_3gram.getTitle().length() < _1_3gram.getTitle().length()) {
							_1_3gram = _tmp1_3gram;
						}
					}
				}
			}
		}
		System.out.println(_1gram);
		System.out.println(_1_2gram);
		System.out.println(_1_3gram);
	}

	@Test
	public void _3BestCNgramTest() throws IOException {
		System.out.println("_CNGRAM_");

		File cngram = new File("StringWordToVector - CNGRAM");

		Detail _1gram = null;
		Detail _1_2gram = null;
		Detail _1_3gram = null;

		for (File f : cngram.listFiles()) {
			if (f.isDirectory()) {
				continue;
			}
			String name = f.getName().toUpperCase();

			BufferedReader s = new BufferedReader(new FileReader(f));

			double fmeasure = 0, precision = 0, recall = 0, rmse = 0;
			String line = "";
			while (true) {
				if (line.startsWith(rmsePattern)) {
					rmse = Double.parseDouble(line.split("(\\s\\s|\\s\\s\\s)+")[1].trim());
					line = s.readLine();
				} else if (line.startsWith(measures)) {
					String[] values = line.split("(\\s\\s|\\s\\s\\s)+");
					if (!line.contains("?")) {
						precision = Double.parseDouble(values[3].trim().replace(",", "."));
						recall = Double.parseDouble(values[4].trim().replace(",", "."));
						fmeasure = Double.parseDouble(values[5].trim().replace(",", "."));
					}
					break;
				} else {
					line = s.readLine();
				}
			}
			s.close();

			if (name.startsWith("1GRAM")) {
				if (_1gram == null) {
					_1gram = new Detail(fmeasure, recall, precision, rmse, name);
				} else {
					Detail _tmp1gram = new Detail(fmeasure, recall, precision, rmse, name);
					if (_tmp1gram.getMean() > _1gram.getMean()) {
						_1gram = _tmp1gram;
					} else if (_tmp1gram.getMean() == _1gram.getMean()) {
						if (_tmp1gram.getTitle().length() < _1gram.getTitle().length()) {
							_1gram = _tmp1gram;
						}
					}
				}
			} else if (name.startsWith("1-2GRAM")) {
				if (_1_2gram == null) {
					_1_2gram = new Detail(fmeasure, recall, precision, rmse, name);
				} else {
					Detail _tmp1_2gram = new Detail(fmeasure, recall, precision, rmse, name);
					if (_tmp1_2gram.getMean() > _1_2gram.getMean()) {
						_1_2gram = _tmp1_2gram;
					} else if (_tmp1_2gram.getMean() == _1_2gram.getMean()) {
						if (_tmp1_2gram.getTitle().length() < _1_2gram.getTitle().length()) {
							_1_2gram = _tmp1_2gram;
						}
					}
				}
			} else if (name.startsWith("1-3GRAM")) {
				if (_1_3gram == null) {
					_1_3gram = new Detail(fmeasure, recall, precision, rmse, name);
				} else {
					Detail _tmp1_3gram = new Detail(fmeasure, recall, precision, rmse, name);
					if (_tmp1_3gram.getMean() > _1_3gram.getMean()) {
						_1_3gram = _tmp1_3gram;
					} else if (_tmp1_3gram.getMean() == _1_3gram.getMean()) {
						if (_tmp1_3gram.getTitle().length() < _1_3gram.getTitle().length()) {
							_1_3gram = _tmp1_3gram;
						}
					}
				}
			}
		}
		System.out.println(_1gram);
		System.out.println(_1_2gram);
		System.out.println(_1_3gram);
	}

	private class Detail {
		private final double fscore;
		private final double recall;
		private final double precision;
		private final double rmse;

		private final String title;

		public Detail(double fscore, double recall, double precision, double rmse, String title) {
			super();
			this.fscore = fscore;
			this.recall = recall;
			this.precision = precision;
			this.rmse = rmse;
			this.title = title;
		}

		/**
		 * @return the fscore
		 */
		public double getFscore() {
			return fscore;
		}

		/**
		 * @return the recall
		 */
		public double getRecall() {
			return recall;
		}

		/**
		 * @return the precision
		 */
		public double getPrecision() {
			return precision;
		}

		/**
		 * @return the rmse
		 */
		public double getRmse() {
			return rmse;
		}

		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

		public double getMean() {
			return 2 * ((fscore * (1 - rmse)) / (fscore + (1 - rmse)));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			long temp;
			temp = Double.doubleToLongBits(fscore);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(precision);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(recall);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(rmse);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Detail)) {
				return false;
			}
			Detail other = (Detail) obj;
			if (!getOuterType().equals(other.getOuterType())) {
				return false;
			}
			if (Double.doubleToLongBits(fscore) != Double.doubleToLongBits(other.fscore)) {
				return false;
			}
			if (Double.doubleToLongBits(precision) != Double.doubleToLongBits(other.precision)) {
				return false;
			}
			if (Double.doubleToLongBits(recall) != Double.doubleToLongBits(other.recall)) {
				return false;
			}
			if (Double.doubleToLongBits(rmse) != Double.doubleToLongBits(other.rmse)) {
				return false;
			}
			if (title == null) {
				if (other.title != null) {
					return false;
				}
			} else if (!title.equals(other.title)) {
				return false;
			}
			return true;
		}

		private TestesMestradoUfpeApplicationTests getOuterType() {
			return TestesMestradoUfpeApplicationTests.this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return String.format("Detail [fscore=%.4f, recall=%.4f, precision=%.4f, rmse=%.4f, mean=%.4f, title=%s]",
					fscore, recall, precision, rmse, getMean(), title);
		}

	}
}
