package it.unibo.pps.tasks.adts

import org.junit.Test
import org.junit.Assert.*
import it.unibo.pps.u03.extensionmethods.Sequences.Sequence, Sequence.*
import SchoolModel.*

class SchoolModelTest:
  val schoolModule: SchoolModule = BasicSchoolModule
  import schoolModule.*

  @Test def testEmptySchool(): Unit =
    assertEquals(Nil(), emptySchool.courses)
    assertEquals(Nil(), emptySchool.teachers)
