package adapters.gateway.repositories.memory.zio.common

import adapters.Effect
import com.github.j5ik2o.dddbase.AggregateAllReader
import zio.ZIO

trait AggregateAllReadFeature extends AggregateAllReader[Effect] with AggregateBaseReadFeature {
  override def resolveAll: Effect[Seq[AggregateType]] =
    for {
      records    <- dao.getAll
      aggregates <- ZIO.collectAll(records.map(convertToAggregate))
    } yield aggregates
}
