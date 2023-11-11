package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private ICourseRepository courseRepository;

    @Mock
    private IRegistrationRepository registrationRepository;

    @InjectMocks
    private CourseServicesImpl courseService;

    @Test
    void testGetCourseById() {
        // Create a sample course
        Course sampleCourse = new Course();
        sampleCourse.setNumCourse(1L);
        sampleCourse.setLevel(1);
        sampleCourse.setTypeCourse(TypeCourse.COLLECTIVE_ADULT);
        sampleCourse.setSupport(Support.SNOWBOARD);
        sampleCourse.setPrice(100.0f);
        sampleCourse.setTimeSlot(60);

        // Mock the behavior of courseRepository.findById
        when(courseRepository.findById(1L)).thenReturn(java.util.Optional.of(sampleCourse));

        // Call the method from the service
        Course result = courseService.retrieveCourse(1L);

        // Verify that the repository method was called with the correct ID
        verify(courseRepository).findById(1L);

        // Assertions
        assertEquals(1L, result.getNumCourse());
        assertEquals(1, result.getLevel());
        assertEquals(TypeCourse.COLLECTIVE_ADULT, result.getTypeCourse());
        assertEquals(Support.SNOWBOARD, result.getSupport());
        assertEquals(100.0f, result.getPrice());
        assertEquals(60, result.getTimeSlot());
    }

    @Test
    void testGetRegistrationsForCourse() {
        // Create a sample course
        Course sampleCourse = new Course();
        sampleCourse.setNumCourse(1L);

        // Create sample registrations
        Registration reg1 = new Registration();
        reg1.setNumRegistration(1L);
        reg1.setCourse(sampleCourse);

        Registration reg2 = new Registration();
        reg2.setNumRegistration(2L);
        reg2.setCourse(sampleCourse);


    }

    @Test
    void testAddCourse() {
        // Create a sample course without explicitly setting numCourse (let it be null or use the default value)
        Course sampleCourse = new Course();
        sampleCourse.setLevel(2);
        sampleCourse.setTypeCourse(TypeCourse.COLLECTIVE_ADULT);
        sampleCourse.setSupport(Support.SNOWBOARD);
        sampleCourse.setPrice(150.0f);
        sampleCourse.setTimeSlot(90);

        // Mock the behavior of courseRepository.save
        when(courseRepository.save(sampleCourse)).thenReturn(sampleCourse);

        // Call the method from the service to add the course
        Course addedCourse = courseService.addCourse(sampleCourse);

        // Verify that the repository method was called with the correct course
        verify(courseRepository).save(sampleCourse);

        // Assertions
        assertEquals(sampleCourse, addedCourse);
    }


}
