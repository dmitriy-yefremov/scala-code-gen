package net.yefremov.sample.codegen.template

import net.yefremov.sample.codegen.schema.TypeSchema
import net.yefremov.sample.codegen.template.txt.CaseClass

/**
 * Lo-tech, Twirl templates based code generator. See the Twirl source code at
 * 'src/main/twirl/net.yefremov.sample.codegen.template/CaseClass.scala.txt'.
 * @author Dmitriy Yefremov
 */
class TwirlGenerator {

  def generate(schema: TypeSchema): String = CaseClass(schema).toString()

}
