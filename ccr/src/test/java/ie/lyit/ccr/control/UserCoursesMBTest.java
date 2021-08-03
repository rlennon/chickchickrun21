package ie.lyit.ccr.control;

import ie.lyit.ccr.model.entities.UserCourses;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class UserCoursesMBTest extends TestCase {

    private UserCoursesMB userCoursesMB;

    @Before
    public void setUp() {
        userCoursesMB = new UserCoursesMB();
    }

    @Test
    public void testGetUserCourses() {
        List<UserCourses> userCourses = userCoursesMB.getUserCourses();
        assertThat(userCourses).isNotNull();
        assertThat(userCourses).isNotEmpty();
        assertThat(userCourses).hasSize(2);
    }
}