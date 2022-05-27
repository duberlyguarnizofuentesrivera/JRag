import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rag.DeadlockAnalyzer;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeadlockAnalyzerTest {
    @Test
    @DisplayName("Verify Matrix Initialization")
    void verifyMatrixConstruction() {
        DeadlockAnalyzer dla = new DeadlockAnalyzer("p1 r2\n" +
                "r1.1 p1\n" +
                "r2.1 p2\n" +
                "r2.2 p3\n" +
                "r1.2 p3\n" +
                "r1.3 p3\n" +
                "r1.4 p3\n" +
                "r1.5 p4\n" +
                "r1.6 p4\n" +
                "r2.3 p4\n" +
                "r3.1 p4\n" +
                "r3.2 p5\n" +
                "r3.3 p5\n" +
                "p2 r1.7\n" +
                "p2 r1\n" +
                "p2 r3.4\n" +
                "p2 r3.5\n" +
                "p4 r1\n" +
                "p5 r3.6\n" +
                "p5 r3\n",
                "3", "5", new String[]{"7", "2", "6"});

        assertAll(() -> assertTrue(
                dla.getAvailableVector()[0] == 7 &&
                        dla.getAvailableVector()[1] == 2 &&
                        dla.getAvailableVector()[2] == 6
        ), () -> assertTrue(
                dla.getAllocationMatrix()[0][0] == 0 &&
                        dla.getAllocationMatrix()[0][1] == 1 &&
                        dla.getAllocationMatrix()[0][2] == 0 &&
                        dla.getAllocationMatrix()[1][0] == 2 &&
                        dla.getAllocationMatrix()[1][1] == 0 &&
                        dla.getAllocationMatrix()[1][2] == 0 &&
                        dla.getAllocationMatrix()[2][0] == 3 &&
                        dla.getAllocationMatrix()[2][1] == 0 &&
                        dla.getAllocationMatrix()[2][2] == 3 &&
                        dla.getAllocationMatrix()[3][0] == 2 &&
                        dla.getAllocationMatrix()[3][1] == 1 &&
                        dla.getAllocationMatrix()[3][2] == 1 &&
                        dla.getAllocationMatrix()[4][0] == 0 &&
                        dla.getAllocationMatrix()[4][1] == 0 &&
                        dla.getAllocationMatrix()[4][2] == 2
        ), () -> assertTrue(
                dla.getRequestMatrix()[0][0] == 0 &&
                        dla.getRequestMatrix()[0][1] == 0 &&
                        dla.getRequestMatrix()[0][2] == 0 &&
                        dla.getRequestMatrix()[1][0] == 2 &&
                        dla.getRequestMatrix()[1][1] == 0 &&
                        dla.getRequestMatrix()[1][2] == 2 &&
                        dla.getRequestMatrix()[2][0] == 0 &&
                        dla.getRequestMatrix()[2][1] == 0 &&
                        dla.getRequestMatrix()[2][2] == 0 &&
                        dla.getRequestMatrix()[3][0] == 1 &&
                        dla.getRequestMatrix()[3][1] == 0 &&
                        dla.getRequestMatrix()[3][2] == 0 &&
                        dla.getRequestMatrix()[4][0] == 0 &&
                        dla.getRequestMatrix()[4][1] == 0 &&
                        dla.getRequestMatrix()[4][2] == 2));
    }

    //todo: test for cases with two onw resource to another
}
/*
demo data for testing:
Processes: 3
Resources: 4 (0,3,2 and 2 instances per resource, respectively)

p1 r2
p3 r4
r2.3 p3
p1 r3.1
r4.2 r3
p3 p2
 */