package support

import org.scalatest._
import org.scalatest.concurrent.{Eventually, IntegrationPatience, ScalaFutures}

trait RichMatchers
  extends Matchers
    with DiagrammedAssertions
    with TryValues
    with EitherValues
    with OptionValues
    with AppendedClues
    with ScalaFutures
    with StreamlinedXml
    with Inside
    with Eventually
    with IntegrationPatience
