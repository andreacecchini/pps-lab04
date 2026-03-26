package it.unibo.pps.tasks.adts

import org.junit.Test
import org.junit.Assert.*
import it.unibo.pps.u03.extensionmethods.Sequences.Sequence, Sequence.*
import SchoolModel.*

class SchoolModelTest:
  val schoolModule: SchoolModule = BasicSchoolModule
  import schoolModule.*

  private val john = teacher("John")
  private val math = course("Math")
  private val physic = course("Physic")
  private val s1 = emptySchool.setTeacherToCourse(john, math)
  private val s2 = s1.setTeacherToCourse(john, physic)

  @Test def testEmptySchool(): Unit =
    assertEquals(Nil(), emptySchool.courses)
    assertEquals(Nil(), emptySchool.teachers)

  @Test def testAddTeacher(): Unit =
    assertTrue:
      s1.hasTeacher("John")
    assertEquals(Cons("John", Nil()), s1.teachers)

  @Test def testAddCourse(): Unit =
    assertTrue:
      s1.hasCourse("Math")
    assertEquals(Cons("Math", Nil()), s1.courses)

  @Test def testTeacherNotPresent(): Unit =
    assertFalse:
      emptySchool.hasTeacher("John")

  @Test def testCourseNotPresent(): Unit =
    assertFalse:
      emptySchool.hasCourse("Math")

  @Test def testNoDuplicatedTeachers(): Unit =
    assertEquals(Cons("John", Nil()), s2.teachers)

  @Test def testMultipleCoursesOnATeacher(): Unit =
    val expectedCourses = Cons(course("Math"), Cons(course("Physic"), Nil()))
    assertEquals(expectedCourses, s2.coursesOfATeacher(john))
    s2.coursesOfATeacher(john)