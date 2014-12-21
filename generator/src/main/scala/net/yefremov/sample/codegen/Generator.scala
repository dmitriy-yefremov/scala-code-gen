package net.yefremov.sample.codegen

import net.yefremov.sample.codegen.schema.TypeSchema

/**
 * Code generator interface.
 * @author Dmitriy Yefremov
 */
trait Generator {

  def generate(schema: TypeSchema): String



}


