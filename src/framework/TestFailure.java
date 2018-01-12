package framework;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * TestFailure用来收集一个failed test
 * 并且捕捉异常信息
 */
public class TestFailure {

    protected Test fFailedTest;
    protected Throwable fThrownException;

    public TestFailure(Test failedTest, Throwable thrownException ) {
        fFailedTest = failedTest;
        fThrownException = thrownException;
    }

    //get the failed Test
    public Test failedTest() {
        return fFailedTest;
    }

    public Throwable thrownException() {
        return fThrownException;
    }

    //toString
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(fFailedTest+": " + fThrownException.getMessage());
        return buffer.toString();
    }

    public String trace() {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        thrownException().printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }

    public String exceptionMessage() {
        return thrownException().getMessage();
    }

    public boolean isFailure() {
        return thrownException() instanceof AssertionFailedError;
    }

}
