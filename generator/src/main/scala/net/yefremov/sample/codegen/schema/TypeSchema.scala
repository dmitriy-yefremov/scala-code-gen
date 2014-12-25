package net.yefremov.sample.codegen.schema

import java.io.FileInputStream

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
 * Represents a type definition.
 * @author Dmitriy Yefremov
 */
case class TypeSchema(name: TypeName, comment: String, fields: Seq[Field])

object TypeSchema {

  private val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  /**
   * Loads a schema from a JSON file.
   */
  def fromJson(fileName: String): TypeSchema = {
    val inputStream = new FileInputStream(fileName)
    try {
      mapper.readValue(inputStream, classOf[TypeSchema])
    } finally {
      inputStream.close()
    }
  }

  def toJson(schema: TypeSchema): String = {
    mapper.writeValueAsString(schema)
  }

}
