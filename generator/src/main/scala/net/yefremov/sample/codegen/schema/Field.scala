package net.yefremov.sample.codegen.schema

import FieldType.FieldType

/**
 * Represents a single field of a type.
 * @author Dmitriy Yefremov
 */
case class Field(name: String, valueType: FieldType)
