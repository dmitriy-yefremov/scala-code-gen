package net.yefremov.sample.codegen.schema

/**
 * Represents a type definition.
 * @author Dmitriy Yefremov
 */
case class TypeSchema(name: TypeName, comment: String, fields: Seq[Field])
