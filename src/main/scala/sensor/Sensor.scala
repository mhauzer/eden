package sensor

// Sensor is an internal representation of a sense. This is not a "real" sense. The real sense should be set before
// the sensor. Sensor generates experiences (qualia) that are subjects of cognition.
abstract class Sensor[SignalType] {
  def get(): List[SignalType]
}
