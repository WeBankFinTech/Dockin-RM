package cn.webank.dockin.rm.utils;
import cn.webank.dockin.rm.web.bean.MonitorKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
public class StringFormatterTest {
    Logger logger = LoggerFactory.getLogger(StringFormatterTest.class);
    @Test
    public void test() {
        double d = StringFormatter.parseNumberFromPercentage("121%");
        assert d == 1.21d;
        d = StringFormatter.parseNumberFromPercentage("2.1%");
        assert d == 0.021d;
        assert d != 1.21d;
    }
    @Test
    public void testFormat() {
        String formatMonitorMsg = String.format(MonitorKeys.REQUEST_SUCCESS_TEMPLATE,
                "callerClassName", "callerMethodName", "null",
                "" + "responseTime", "resultFlag", null, null, null, null);
        System.out.println(formatMonitorMsg);
    }
}
