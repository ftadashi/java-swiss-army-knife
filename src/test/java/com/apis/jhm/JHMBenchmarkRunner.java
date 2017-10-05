package com.apis.jhm;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class JHMBenchmarkRunner {

    private JHMOperations jhmOperations;

    @Benchmark
    public void init() {
        this.jhmOperations = new JHMOperations();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1, warmups = 1)
    public void runnerBenchmarkAverageTime() {
        this.jhmOperations.opeation();
    }

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @Fork(value = 1, warmups = 1)
    public void runnerBenchmarkSampleTime() {
        this.jhmOperations.opeation();
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(value = 1, warmups = 1)
    public void runnerBenchmarkSingleShotTime() {
        this.jhmOperations.opeation();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 1)
    public void runnerBenchmarkThroughput() {
        this.jhmOperations.opeation();
    }

}
