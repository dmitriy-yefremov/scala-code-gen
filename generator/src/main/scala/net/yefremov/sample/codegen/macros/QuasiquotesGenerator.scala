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

    val schemaPath = c.prefix.tree match {
      case Apply(_, List(Literal(Constant(x)))) => x.toString
      case _ => c.abort(c.enclosingPosition, "schema file path is not specified")
    }

    val schema = TypeSchema.fromJson(schemaPath)

    annottees.map(_.tree) match {
      case List(q"class $name") => c.Expr[Any](
        q"""
          case class $name() {

            def schema = ${schema.toString}

          }
        """
      )
    }
  }

}

class FromSchema(schemaFile: String) extends StaticAnnotation {

  def macroTransform(annottees: Any*) = macro QuasiquotesGenerator.generate

}
