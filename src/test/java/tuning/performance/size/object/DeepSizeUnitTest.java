package tuning.performance.size.object;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import tuning.performance.size.object.model.Department;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DeepSizeUnitTest {

    @Test
    void shouldReturnTheDeepSizeOfDepartmentInstance() {
        String departmentName = "Learning and Development";
        Department aDepartment = new Department(departmentName);

        int deepSize = shallowSizeOfDepartment(aDepartment)
                + shallowSizeOfDepartmentString(departmentName)
                + shallowSizeOfDepartmentStringByteArray(departmentName);

        assertThat(deepSize, is(80));
    }

    private int shallowSizeOfDepartment(Department aDepartment) {
        String printableClassLayout = ClassLayout.parseInstance(aDepartment).toPrintable();
        Pattern pattern = Pattern.compile("(Instance size: )(\\d+) (bytes)");
        Matcher matcher = pattern.matcher(printableClassLayout);

        matcher.find();
        return Integer.parseInt(matcher.group(2));
    }

    private int shallowSizeOfDepartmentString(String str) {
        String printableClassLayout = ClassLayout.parseInstance(str).toPrintable();
        Pattern pattern = Pattern.compile("(Instance size: )(\\d+) (bytes)");
        Matcher matcher = pattern.matcher(printableClassLayout);

        matcher.find();
        return Integer.parseInt(matcher.group(2));
    }

    private int shallowSizeOfDepartmentStringByteArray(String str) {
        String printableClassLayout = ClassLayout.parseInstance(str.getBytes(StandardCharsets.UTF_8)).toPrintable();
        Pattern pattern = Pattern.compile("(Instance size: )(\\d+) (bytes)");
        Matcher matcher = pattern.matcher(printableClassLayout);

        matcher.find();
        return Integer.parseInt(matcher.group(2));
    }
}
