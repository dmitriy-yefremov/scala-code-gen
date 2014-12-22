package net.yefremov.sample.codegen.ast

import net.yefremov.sample.codegen.Generator
import net.yefremov.sample.codegen.schema.TypeSchema
import treehugger.forest._
import treehuggerDSL._
import definitions._

/**
 * An AST generator based on the 'treehugger' livrary.
 * @author Dmitriy Yefremov
 */
class TreehuggerGenerator extends Generator {

  override def generate(schema: TypeSchema): String = {
    val classSymbol = RootClass.newClass(TermName(schema.name.shortName))

    val tree = BLOCK(
      CASECLASSDEF(classSymbol)
    ).inPackage(schema.name.packageName)

    treeToString(tree)
  }
}
