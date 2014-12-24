package net.yefremov.sample.codegen

import net.yefremov.sample.codegen.ast.TreehuggerGenerator
import net.yefremov.sample.codegen.macros.FromSchema
import net.yefremov.sample.codegen.schema.TypeSchema
import net.yefremov.sample.codegen.template.TwirlGenerator

object Main extends App {

  val schema = TypeSchema.fromJson("Foo.json")

  println((new TwirlGenerator).generate(schema))
  println((new TreehuggerGenerator).generate(schema))

  @FromSchema class Foo
}
