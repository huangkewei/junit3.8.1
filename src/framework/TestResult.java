package framework;

import java.util.Enumeration;
import java.util.Vector;

/**
 * TestResult是用来收集一个测试用例的结果，
 * 它是收集参数模式的一个实例
 * 测试框架区分了‘失败’和‘错误’，
 * ‘失败’是预期会发生的故障，并且用断言进行检查
 * ‘错误’是意外问题，比如：数组下标越界
 *
 * @see Test
 */
public class TestResult {

    protected Vector fFailures;
    protected Vector fErrors;
    protected Vector fListeners;
    protected int fRunTests;
    protected boolean fStop;

    public TestResult(){
        fFailures = new Vector();
        fErrors = new Vector();
        fListeners = new Vector();
        fRunTests = 0;
        fStop = false;
    }

    /**
     * 收集Error信息，通过异常产生的error
     */
    public synchronized void addError(Test test, Throwable t) {
        fErrors.addElement(new TestFailure(test, t));
        for (Enumeration e = cloneListeners().elements(); e.hasMoreElements();) {
            ((TestListener)e.nextElement()).addError(test, t);
        }
    }

    /**
     * 收集Failure信息，通过异常产生的failure
     */
    public synchronized void addFailure(Test test, AssertionFailedError t) {
        fFailures.addElement(new TestFailure(test, t));
        for (Enumeration e = cloneListeners().elements(); e.hasMoreElements();) {
            ((TestListener)e.nextElement()).addFailure(test, t);
        }

    }

    /**
     * 注册TestListener
     */
    public synchronized void addListener(TestListener listener) {
        fListeners.addElement(listener);
    }

    /**
     * 移除一个TestListener
     */
    public synchronized void removeListener(TestListener listener) {
        fListeners.remove(listener);
    }

    /**
     * 返回listeners的clone
     */
    private synchronized Vector cloneListeners() {
        return (Vector)fListeners.clone();
    }

    /**
     * 通知测试完成的结果
     */
    public void endTest(Test test) {
        for (Enumeration  e = cloneListeners().elements(); e.hasMoreElements();){
            ((TestListener)e.nextElement()).endTest(test);
        }
    }

    /**
     * 获取检测到的Error数
     */
    public synchronized int errorCount() {
        return fErrors.size();
    }

    /**
     * 返回Error的Enumeration
     */
    public synchronized Enumeration errors() {
        return fErrors.elements();
    }

    /**
     * 返回Failure数
     */
    public synchronized int failureCount() {
        return fFailures.size();
    }

    /**
     * 返回Failure的Enumeration
     */
    public synchronized Enumeration failures() {
        return fFailures.elements();
    }

    /**
     * 运行一个TestCase
     */
    protected void run(final TestCase test) {
        startTest(test);
        Protectable p = new Protectable() {
            public void protect() throws Throwable {
                test.runBare();
            }
        };
    }

    /**
     * 返回运行的Test 数
     */
    public synchronized int runCount() {
        return fRunTests;
    }

    /**
     * 运行一个TestCase
     */
    public void runProtected(final Test test, Protectable p){
        try {
            p.protect();
        } catch (AssertionFailedError e) {
            addFailure(test, e);
        } catch (ThreadDeath e) {//dont't catch ThreadDeath by accident
            throw e;
        } catch (Throwable e) {
            addError(test, e);
        }

    }

    /**
     * 检查运行的Test是否需要停止
     */
    public synchronized boolean shouldStop() {
        return fStop;
    }

    /**
     * 通知TestResult将要开始一个Test
     */
    public void startTest(Test test) {
        final int count = test.CountTestCases();
        synchronized (this) {
            fRunTests += count;
        }
        for (Enumeration e = cloneListeners().elements(); e.hasMoreElements();) {
            ((TestListener)e.nextElement()).startTest(test);
        }
    }

    /**
     * 标记运行的Test需要stop
     */
    public synchronized void stop() {
        fStop = true;
    }

    /**
     * 返回整个单元测试是否success
     */
    public synchronized boolean wasSuccessful() {
        return failureCount() == 0 && errorCount() == 0;
    }

}
