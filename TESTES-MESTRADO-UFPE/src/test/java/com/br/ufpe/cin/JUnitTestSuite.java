package com.br.ufpe.cin;

import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(ParallelSuite.class)
@SuiteClasses(FoldCrossValidation.class)
@IncludeCategory(JUnitTest.class)
public class JUnitTestSuite {

}