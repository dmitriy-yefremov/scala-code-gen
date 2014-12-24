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

    annottees.map(_.tree) match {
      case List(q"class $name") =>

        // get schema file name from the type name
        val schemaName = name.toString + ".json"
        val schema = TypeSchema.fromJson(schemaName)

        c.Expr[Any](
          q"""
            case class $name() {

              def schema = ${schema.toString}

            }
          """
        )
    }
  }

}

class FromSchema extends StaticAnnotation {

  def macroTransform(annottees: Any*) = macro QuasiquotesGenerator.generate

}
