package com.br.ufpe.cin;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

public class ParallelSuite extends Suite {

	public ParallelSuite(Class<?> klass, RunnerBuilder builder) 
      throws InitializationError {
        super(klass, builder);
        setScheduler(new ParallelScheduler());
    }
}