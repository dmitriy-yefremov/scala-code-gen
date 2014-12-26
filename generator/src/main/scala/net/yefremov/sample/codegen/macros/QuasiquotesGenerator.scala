package net.yefremov.sample.codegen.macros

import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.Context

import net.yefremov.sample.codegen.schema.TypeSchema

/**
 * A macro generator based on quasiquates.
 * @author Dmitriy Yefremov
 */
object QuasiquotesGenerator {

  def generate(c: Context)(annottees: c.Expr[Any]*) = {

    import c.universe._

    // retrieve the schema path
    val schemaPath = c.prefix.tree match {
      case Apply(_, List(Literal(Constant(x)))) => x.toString
      case _ => c.abort(c.enclosingPosition, "schema file path is not specified")
    }

    // retrieve the annotate class name
    val className = annottees.map(_.tree) match {
      case List(q"class $name") => name
      case _ => c.abort(c.enclosingPosition, "the annotation can only be used with classes")
    }

    // load the schema from JSON
    val schema = TypeSchema.fromJson(schemaPath)

    // produce the list of constructor parameters (note the "val ..." syntax)
    val params = schema.fields.map { field =>
      val fieldName = newTermName(field.name)
      val fieldType = newTypeName(field.valueType.toString)
      q"val $fieldName: $fieldType"
    }

    // rewrite the class definition
    c.Expr(
      q"""
        case class $className(..$params) {

          def schema = ${schema.toString}

        }
      """
    )
  }

}

class FromSchema(schemaFile: String) extends StaticAnnotation {

  def macroTransform(annottees: Any*) = macro QuasiquotesGenerator.generate

}
