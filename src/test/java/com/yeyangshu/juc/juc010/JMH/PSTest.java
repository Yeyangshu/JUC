package com.yeyangshu.juc.juc010.JMH;

import org.openjdk.jmh.annotations.*;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/7/5 10:10
 */
public class PSTest {

    @Benchmark
    @Warmup(iterations = 1, time = 3)
    @Fork(5)
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 1, time = 3)
    public void testForeach() {
        PS.foreach();
    }

    @Benchmark
    @Warmup(iterations = 1, time = 3)
    @Fork(5)
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 1, time = 3)
    public void testParallel() {
        PS.parallel();
    }

    /**
     * Benchmark                Mode  Cnt  Score   Error  Units
     * JMH.PSTest.testForeach  thrpt    5  0.591 ± 0.153  ops/s
     *
     *
     * Benchmark                Mode  Cnt  Score   Error  Units
     * JMH.PSTest.testParallel  thrpt    5  0.943 ± 0.255  ops/s
     */
}