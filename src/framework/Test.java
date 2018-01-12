package framework;


/**
 * 测试类，用来运行和收集结果
 */
public interface Test {

    /**
     * 统计要运行的测试用例数
     */
    int  CountTestCases();

    /**
     *运行测试并收集结果在TestResult中
     */
    void run(TestResult result);

}
