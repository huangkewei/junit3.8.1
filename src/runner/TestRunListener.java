package runner;

/**
 * 用于观察的侦听器界面
 * 执行测试运行，于TestListener不同，
 * 这个接口只使用原始对象做远程测试执行
 */
public interface TestRunListener {

    /**
     * 测试状态常数
     */
    int STATUS_ERROR = 1;
    int STATUS_FAILURE = 2;

    void testRunStarted(String var1, int var2);
    void testRunEnded(long var1);
    void testRunStopped(long var1);
    void testStarted(String var1);
    void testEnded(String var1);
    void testFailed(int var1, String var2, String var3);

}
