# TODO

- work on `MessageBoardSensor` can take a while so let's implement [synesthesia](https://en.wikipedia.org/wiki/Synesthesia):
  - `MessageBoardParsingSensor` with a traditional NLP parser
  - `MessageBoardSensor` with a cognitive parser
- document the working memory processing
- find the reasoning bug using unit tests
- describe and test my ideas by introducing an algorithm that infers words from letters
  - signal volume: 0-255
  - signal variance: 0-255 (how much it differs from the average level)
  - duration (ttl) decreasing in time, can be increased on demand
- I want to have `quale: Quale[String]`
- what about using Drools?
- sensors should generate experiences and introduce them to the temporary memory where it can be refined and analyzed 
  by extracting ideas. Experiences are based on senses (senses are facets of reality)
- thought processes of heigher level should be able to throttle (some) reflexes
- complete baseform dictionary
- complete morphologic dictionary
- Virtual Assistant
- sensor managable resolution 
- textpseudovision sensor can learn about the input and recognize what functions have the elements of the input (which character is a
  separator, and such)
