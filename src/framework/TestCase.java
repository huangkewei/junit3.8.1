package framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class TestCase extends Assert implements Test{

    /**
     * 测试用例名
     */
    private String fName;

    /**
     * 此次构造方法并不在没有调用setName()调用后直接调用
     */
    private TestCase() {
        fName = null;
    }

    @Override
    public int CountTestCases() {
        return 1;
    }

    /**
     * 创建一个默认的TestResult
     */
    protected TestResult createResult() {
        return new TestResult();
    }

    /**
     *运行Test的一个简便方法，并且用默认的TestResult收集结果
     */
    public TestResult run() {
        TestResult result = createResult();
        run(result);
        return result;
    }

    /**
     *运行testCase，并在TestResult中收集结果
     */
    @Override
    public void run(TestResult result) {
        result.run(this);
    }

    /**
     * 运行裸测试序列
     * @exception抛出任何异常的Throwable
     */
    public void runBare() throws Throwable {
        setUp();
        try {
            runTest();
        }
        finally {
            tearDown();
        }
    }

    protected void runTest() throws Throwable {
        assertNotNull(fName);
        Method runMethod = null;
        try {
            // use getMethod to get all public inherited
            // methods. getDeclaredMethods returns all
            // methods of this class but excludes the
            // inherited ones.
            //使用getMethod获取所有公共继承
            // 方法。 getDeclaredMethods返回所有
            //这个类的方法，但不包括
            //继承的。
            runMethod = getClass().getMethod(fName, null);


        }catch (NoSuchMethodException e) {
            fail("Method \""+fName+"\"not fount");
        }

        if (!Modifier.isPublic(runMethod.getModifiers())) {
            fail("Method \""+fName+"\" should be public");
        }

        try {
            runMethod.invoke(this, new Class[0]);
        } catch (InvocationTargetException e) {
            e.fillInStackTrace();
            throw e.getTargetException();
        } catch (IllegalAccessException e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    /**
     *设置灯具，比如：打开网络连接
     * 这个方法在Test被执行前调用
     */
    protected void setUp() throws Exception {

    }

    protected void tearDown() throws Exception {

    }

    /**
     *返回testCase的字符串表现形式
     */
    public String toString() {
        return getName() + "(" +getClass().getName()+")";
    }

    public String getName() {
        return fName;
    }

    public void setName(String name) {
        fName = name;
    }
}
