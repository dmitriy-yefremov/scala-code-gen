package net.yefremov.sample.codegen.ast

import net.yefremov.sample.codegen.schema.{TypeSchema, TypeName}

import treehugger.forest._
import treehuggerDSL._
import definitions._

/**
 * An AST generator based on the 'treehugger' livrary.
 * @author Dmitriy Yefremov
 */
class TreehuggerGenerator {

  def generate(schema: TypeSchema): String = {
    // register new type
    val classSymbol = RootClass.newClass(schema.name.shortName)

    // generate list of constructor parameters
    val params = schema.fields.map { field =>
      val fieldName = field.name
      val fieldType = toType(field.valueType)
      PARAM(fieldName, fieldType): ValDef
    }

    // generate class definition
    val tree = BLOCK(
      CASECLASSDEF(classSymbol).withParams(params).tree.withDoc(schema.comment):= BLOCK(
        DEF("schema", StringClass) := LIT(schema.toString)
      )
      ).inPackage(schema.name.packageName)

    // pretty print the tree
    treeToString(tree)
  }

  private def toType(fieldType: TypeName): Type = {
    fieldType.fullName match {
      case "String" => StringClass
      case "Int" => IntClass
      case "Boolean" => BooleanClass
    }
  }

}
