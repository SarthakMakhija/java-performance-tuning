package tuning.performance.size.object;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.GraphLayout;
import tuning.performance.size.object.model.Department;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DeepSizeUsingGraphUnitTest {

    @Test
    void shouldReturnTheDeepSizeOfDepartmentInstance() {
        String departmentName = "Learning and Development";
        Department aDepartment = new Department(departmentName);

        long deepSize = GraphLayout.parseInstance(aDepartment).totalSize();
        assertThat(deepSize, is(80L));
    }
}
