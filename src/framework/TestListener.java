package framework;

/**
 * 测试进程监听
 */
public interface TestListener {
    /**
     * 出现错误
     */
    void addError(Test test, Throwable  t);

    /**
     * 出现失败
     */
    void addFailure(Test test, AssertionFailedError t);

    /**
     * 测试结束
     */
    void endTest(Test test);


    /**
     * 测试开始
     */
    void startTest(Test test);
}
