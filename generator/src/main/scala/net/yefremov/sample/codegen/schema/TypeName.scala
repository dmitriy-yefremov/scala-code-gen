package net.yefremov.sample.codegen.schema

/**
 * Represents a fully qualified type name.
 * @author Dmitriy Yefremov
 */
case class TypeName(fullName: String) {

  private def lastDot = fullName.lastIndexOf('.')

  def packageName: String = fullName.take(lastDot)

  def shortName: String = fullName.drop(lastDot + 1)

}
