package net.yefremov.sample.codegen.template

import net.yefremov.sample.codegen.schema.{FieldType, TypeSchema}
import net.yefremov.sample.codegen.schema.FieldType.FieldType
import net.yefremov.sample.codegen.template.txt.CaseClass

/**
 * Lo-tech, Twirl templates based code generator. See the Twirl source code at
 * 'src/main/twirl/net.yefremov.sample.codegen.template/CaseClass.scala.txt'.
 * @author Dmitriy Yefremov
 */
class TwirlGenerator {

  def generate(schema: TypeSchema): String = {
    CaseClass(schema).toString()
  }

}

/**
 * Helpers used by the Twirl template.
 */
object TwirlGenerator {

  def toType(fieldType: FieldType): String = {
    fieldType match {
      case FieldType.String => "String"
      case FieldType.Integer => "Int"
      case FieldType.Boolean => "Boolean"
    }
  }

}
