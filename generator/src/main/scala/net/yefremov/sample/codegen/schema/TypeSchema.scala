package net.yefremov.sample.codegen.schema

import java.io.{FileInputStream, File}

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
    // TODO take care of the hardcoded path
    val file = new File("sample/src/main/resources", fileName)
    require(file.isFile, file.getAbsoluteFile)
    val inputStream = new FileInputStream(file)
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
