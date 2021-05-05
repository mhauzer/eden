# The Garden of Eden - Requirement Specification

The Garden of Eden is a console chatbot app.

User messages are read from the standard input and chatbot responses are printed to the standard output.

The user can communicate with the chatbot by reading and writing messages on a message board.

## Message Board

The message board is a communication medium where parties can store their messages.

The messages are FIFO ordered.

A message consists of:

- party id,
- content.

## Shell

The Shell is a service which represents an entity that can interact with the environment by multiple senses and
actuators. Senses provide information about environment. The Shell creates internal representation of information by
analysing input data.

The outer world cannot be a subject of direct experience. It is represented as a series of sensor-generated impulses
that constitutes qualia.

On the other hand, interaction with the outer world is also indirect. To impact the outer world, there must be another
series of impulses generated that are sent to an actuator. We can know that our intention has been realized in the outer
world by using sensors and finding correlations between posteriori qualia and apriori intentions.

Finding patterns in correlations between intentions and expected qualia is called learning.

## The Cognitive Process Model

1. A Sensor generates a series of basic Fundamental Code Units that is added to the Working Memory,
1. The Working Memory:
    1. interprets the data (and adds new FCUs by running simple categorization of the FCUs),
    1. Reflexes are fired,
    1. Reasoning,
1. A list of FCUs is sent to an Actuator
1. The Working Memory content is degrading in time,

### Quale

A sensation. A set of information originated from a sensor that is subject of experience.

We need qualia for pattern recognition that abstracts them into FCUs.

Quale properties:
- streamId,
- volume,
- density,
- variance,
- value,
- sense.

### Fundamental Code Unit (FCU)

FCU is a unit of cognition. It is a subject of the cognitive process (reasoning). It enables qualia processing by
providing meta information, cathegorization and relations between FCUs.

### The clock

### Sensors

Sensor resolution can be managable.

#### Message Board Sensor

It can produce letters or tokens.

### Actuators

#### Message Board Actuator

### Working Memory

The working memory is initialized with a list of FCUs generated by a sensor.

An empty working memory causes the clock to reset (when there's no experience, there's no time perception).

The working memory is a subject of the cognitive process.

### The Cognitive Process

The cognitive process is a function transforming a list of FCUs to another list of FCUs.

[[Miro](https://miro.com/app/board/o9J_lGklPoQ=/?moveToWidget=3074457358085512012&cot=14)]

1. qualia creation: Sensor generates qualia wrapped with a FCU of corresponding sense type, i.e. (TextPseudoVisionQuale(h), TextPseudoVisionQuale(e), TextPseudoVisionQuale(j))
2. taxonomization: First order qualia get recognized as letters (Letter(h), Letter(e), Letter(j))
3. taxonomization: Letters are grouped into words (Word(hej))
4. association: Words can have associated meaning (Greetings)
5. Meaning can trigger a reflex response - echo

FCU of any level can be a trigger

pattern recognition = taxonomization

abstraction is something different - it is a process of FCU creation that are not directly associated with qualia

The cognitive process is divided into the following steps:

1. sensor: raw ENTITIES (h, e, j) -> preprocessing: grouped ENTITIES (hej) -> quick association reflexes (GREETINGS) ->
   response reflexes (ECHO = intention: sense the same entity as a result of your action) -> cognition


1. First, the FCUs are subject to quick, predefined response called reflex.

WorkingMemory.reflex(
WorkingMemory.degrade(fcus) ::: fcus.filter(isReflexEligible).map(deriveQuickAssociations).filter(_.nonEmpty)
),

1. WorkingMemory.reason(
1. reflex

#### Quick Associations

#### Reflexes

A reflex is a quick, predefined response to a stimulus. A reflex produces the same output every time it detects a
triggering stimulus.

A two step process:

- interpreting the input,
- responding to the interpretation of the input.

A stimulus can trigger a reflex response if:

- it is eligible for the reflex process to happen, which means that:
    - the quale's volume has to be higher than
      `ReflexLevelThreshold`,
    - the quale has to be not older than `ReflexTtlThreshold`,
- it is recognized as a known trigger,
-

| Pattern | Association |
|---|---|
| ENTITY(TextPseudoVision: "hej") | GREETINGS |

| Trigger | Response |
|---|---|
| GREETINGS | GREETINGS |

#### Degradation

Every cognitive process causes FCU degradation. The FCU degradation means TTL values of a FCU and its corresponding
quale to decrease. If the FCU's TTL is 0 then the FCU is removed from the working memory (it is forgotten).

## Natural Language Processing

## Bibliography

1. Newton Howard, Amir Hussain - The Fundamental Code Unit of the Brain: Towards New Model for Cognitive
   Theory (https://link.springer.com/article/10.1007/s12559-017-9538-5)
1. https://en.wikipedia.org/wiki/Qualia
1. Taxonomization - https://blog.coreon.com/2021/01/11/keeping-your-sanity-with-machine-taxonomization/