package net.yefremov.sample.codegen

import net.yefremov.sample.codegen.ast.TreehuggerGenerator
import net.yefremov.sample.codegen.schema.{Field, FieldType, TypeName, TypeSchema}
import net.yefremov.sample.codegen.template.TwirlGenerator

object Main extends App {

  val schema = TypeSchema(
    name = TypeName("net.yefremov.sample.codegen.Foo"),
    fields = Seq(
      Field(
        name = "bar",
        valueType = FieldType.String
      ),
      Field(
        name = "baz",
        valueType = FieldType.Int
      )
    )
  )

  val templateGenerator = new TwirlGenerator
  println(templateGenerator.generate(schema))

  val astGenerator = new TreehuggerGenerator
  println(astGenerator.generate(schema))

}
