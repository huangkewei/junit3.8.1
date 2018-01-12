package framework;

/**
 * 一组Assert方法，只有在断言失败时显示信息
 */
public class Assert {

    /**
     * 受保护的类，因为它是一个静态的类
     */
    protected Assert() {}

    static public void assertTrue(String message, boolean condition) {
        if (!condition) fail(message);
    }


    static public void assertTrue(boolean condition) {
        assertTrue(null, condition);
    }

    static public void assertFalse(String messsage, boolean condition) {
        assertTrue(messsage,!condition);
    }

    static public void assertFalse(boolean condition) {
        assertFalse(null,condition);
    }

    /**
     *给定信息断言失败，停止程序执行
     */
    static public void fail(String message) {
        throw new AssertionFailedError(message);
    }

    /**
     *不用任何信息，停止程序执行
     */
    static public void fail() {
        fail(null);
    }

    /**
     * 断言两个对象相等，不相等就抛出AssertionFailedError
     */
    static public void assertEquals(String message, Object expected, Object actual) {
        if (expected == null && actual == null) {
            return;
        }
        if (expected != null && expected.equals(actual)) {
            return;
        }
        failNotEquals(message, expected, actual);
    }

    /**
     * 断言两个字符串相等
     */
    static public void assertEquals(String expected, String actual) {
        assertEquals(null,expected,actual);
    }

    /**
     * 如果expected为无穷，那么delta可以忽略
     */
    static public void assertEquals(String message, double expected, double actual, double delta) {
        //处理无穷数据因为减去无穷数据为NaN
        if (Double.isInfinite(expected)) {
            if (!(expected == actual)) {
                failNotEquals(message, new Double(expected), new Double(actual));
            }
        }else if (!(Math.abs(expected - actual) <= delta)) {
            //因为和NaN比较永远返回false
            failNotEquals(message, new Double(expected), new Double(actual));
        }
    }

    /**
     *如果actual为无穷，那么delta可以忽略
     */
    static public void assertEquals(double expected, double actual, double delta) {
        assertEquals(null,expected,actual,delta);
    }

    static public void assertEquals(String message, float expected, float actual, float delta)  {
        if (Float.isInfinite(expected)) {
            if (!(expected == actual)) {
                failNotEquals(message, new Float(expected), new Float(actual));
            }
        } else if (!(Math.abs(expected - actual) <= delta)) {
            failNotEquals(message, new Float(expected), new Float(actual));
        }
    }

    static public void assertEquals(float expected, float actual, float delta) {
        assertEquals(null, expected, actual, delta);
    }

    static public void assertEquals(String message, long expected, long actual) {
        assertEquals(message, new Long(expected), new Long(actual));
    }

    static public void assertEquals(long expected, long actual) {
        assertEquals(null, expected, actual);
    }

    static public void assertEquals(String message, boolean expected, boolean actual) {
        assertEquals(message, new Boolean(expected), new Boolean(actual));
    }

    static public void assertEquals(String message, byte expected, byte actual) {
        assertEquals(message, new Byte(expected), new Byte(actual));
    }

    static public void assertEquals(byte expected, byte actual) {
        assertEquals(null, new Byte(expected), new Byte(actual));
    }

    static public void assertEquals(String message, char expected, char actual) {
        assertEquals(message, expected, actual);
    }

    static public void assertEquals(char expected, char actual) {
        assertEquals(null, new Character(expected), new Character(actual));
    }

    static public void assertEquals(String message, short expected, short actual) {
        assertEquals(message, new Short(expected), new Short(actual));
    }

    static public void assertEquals(short expected, short actual) {
        assertEquals(null, new Short(expected), new Short(actual));
    }

    static public void assertEquals(String message ,int expected, int actual) {
        assertEquals(message, new Integer(expected), new Integer(actual));
    }

    static public void assertEquals(int expected, int actual) {
        assertEquals(null, new Integer(expected), new Integer(actual));
    }

    static public void assertNotNUll(Object object) {
        assertNotNull(null, object);
    }

    static public void assertNotNull(String messgae, Object object) {
        assertTrue(messgae, object != null);
    }

    static public void assertNotNull(Object object) {
        assertNotNull(null,object);
    }

    static public void assertNull(Object object) {
        assertNull(null, object);
    }
    static public void assertNull(String message, Object object) {
        assertTrue(message, object == null);
    }

    //断言两个对象应用相同的对象
    static public void assertSame(String message, Object expected, Object actual) {
        if(expected == actual) {
            return;
        }
        failNotSame(message, expected, actual);
    }

    static public void assertSame(Object expected, Object actual) {
        assertSame(null, expected,actual);
    }

    static public void assertNotSame(String message, Object expected, Object actual) {
        if (expected == actual) {
            failSame(message);
        }
    }

    static public void failNotSame(Object expected, Object actual) {
        failNotSame(null,expected,actual);
    }

    static private  void failSame(String message) {
        String formatted ="";
        if (message != null) {
            formatted = message + " ";
        }
        fail(formatted + "expected not same");
    }

    static private  void failNotSame(String message, Object expected, Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + "";
        }
        fail(formatted + "expected same:<" +expected+ "> was not:<"+actual+">");
    }

    static private void failNotEquals(String message, Object expected, Object actual){
        fail(format(message, expected, actual));
    }

    static String format(String message,Object expected, Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = message+" ";
        }
        return formatted+"expected:<"+expected+"> but was:<"+actual+">";
    }

}
