package communication

abstract class MessageBoard[EntryType] {
  def contents: List[EntryType]
  def append(entry: EntryType): MessageBoard[EntryType]
}

class MessageBoardEntry(val sender: String, val message: String) {
  override def toString: String = s"$sender: $message"
}

class SimpleMessageBoard(val contents: List[MessageBoardEntry]) extends MessageBoard[MessageBoardEntry] {
  def append(entry: MessageBoardEntry): SimpleMessageBoard = new SimpleMessageBoard(entry :: contents)
}
