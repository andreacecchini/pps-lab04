package it.unibo.pps.tasks.adts

import org.junit.Test
import org.junit.Assert.*
import it.unibo.pps.u03.extensionmethods.Sequences.Sequence, Sequence.*
import SchoolModel.*

class SchoolModelTest:
  val schoolModule: SchoolModule = BasicSchoolModule
  import schoolModule.*

//  private val john = teacher("John")
//  private val math = course("Math")

  @Test def testEmptySchool(): Unit =
    assertEquals(Nil(), emptySchool.courses)
    assertEquals(Nil(), emptySchool.teachers)

  @Test def testAddTeacher(): Unit =
    val john = teacher("John")
    val math = course("Math")
    val s = emptySchool.setTeacherToCourse(john, math)
    assertTrue:
      s.hasTeacher("John")