package ie.lyit.ccr.model.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class UserCoursesTest {
    private static final String COURSE_NAME = "Test Course";
    private static final String SKILL_NAME = "Test Skill";
    private static final String COURSE_DESCRIPTION = "Test Description";

    UserCourses userCourses;

    @Before
    public void setUp() {
        userCourses = new UserCourses(COURSE_NAME, SKILL_NAME, COURSE_DESCRIPTION);
    }

    @Test
    public void testConstructor() {
        assertThat(userCourses).isNotNull();
        assertThat(userCourses)
                .extracting("courseName", "skillName", "courseDescription")
                .contains(COURSE_NAME, SKILL_NAME, COURSE_DESCRIPTION);
    }

}