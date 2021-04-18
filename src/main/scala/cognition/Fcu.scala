package cognition

import cognition.Idea._

case class Fcu(idea: Idea, quale: Quale, associations: List[Fcu])
