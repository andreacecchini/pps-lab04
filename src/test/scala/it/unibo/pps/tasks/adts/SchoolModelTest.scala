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
  private val s1 = emptySchool.setTeacherToCourse(john, math)

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