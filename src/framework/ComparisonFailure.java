package framework;

/**
 * 断言2个字符串相等失败就thrown
 */
public class ComparisonFailure extends AssertionFailedError{

    private String fExpected;
    private String fActual;


}
