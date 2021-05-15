package tuning.performance.size.object;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import tuning.performance.size.object.model.Department;
import tuning.performance.size.object.model.Employee;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class ShallowSizeUnitTest {

    @Test
    void shouldReturnTheShallowSizeOfEmployeeClass() {
        String printableClassLayout = ClassLayout.parseClass(Employee.class).toPrintable();
        Pattern pattern = Pattern.compile("(Instance size: )(\\d+) (bytes)");
        Matcher matcher = pattern.matcher(printableClassLayout);

        assertThat(matcher.find(), is(true));
        assertThat(Integer.parseInt(matcher.group(2)), is(headerSizeInBytes() + referenceSizeInBytesOf(2) + 4));
    }

    @Test
    void shouldReturnTheShallowSizeOfDepartmentClass() {
        String printableClassLayout = ClassLayout.parseClass(Department.class).toPrintable();
        Pattern pattern = Pattern.compile("(Instance size: )(\\d+) (bytes)");
        Matcher matcher = pattern.matcher(printableClassLayout);

        assertThat(matcher.find(), is(true));
        assertThat(Integer.parseInt(matcher.group(2)), is(headerSizeInBytes() + referenceSizeInBytesOf(1)));
    }

    private int headerSizeInBytes() {
        return 12;
    }

    private int referenceSizeInBytesOf(int numberOfReferences) {
        return 4 * numberOfReferences;
    }
}
