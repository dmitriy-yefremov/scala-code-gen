package net.yefremov.sample.codegen

import net.yefremov.sample.codegen.schema.{Field, TypeName, TypeSchema}
import net.yefremov.sample.codegen.template.TemplateGenerator
import scalariform.formatter.ScalaFormatter

object Main extends App {

  val schema = TypeSchema(
    name = TypeName("net.yefremov.sample.codegen.Foo"),
    fields = Seq(
      Field(
        name = "bar",
        valueType = classOf[String]
      ),
      Field(
        name = "baz",
        valueType = classOf[java.lang.Integer]
      )
    )
  )

  val generator = new TemplateGenerator
  val content = generator.generate(schema)

  println(content)
  println(ScalaFormatter.format(content))

}
