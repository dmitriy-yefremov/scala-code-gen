package net.yefremov.sample.codegen.template

import net.yefremov.sample.codegen.Generator
import net.yefremov.sample.codegen.schema.TypeSchema
import net.yefremov.sample.codegen.template.txt.CaseClass

/**
 * Lo-tech, Twirl templates based code generator.
 * See the Twirl source code at 'src/main/twirl/net.yefremov.sample.codegen.template/CaseClass.scala.txt'.
 * @author Dmitriy Yefremov
 */
class TwirlGenerator extends Generator {

  override def generate(schema: TypeSchema): String = CaseClass(schema).toString()

}
