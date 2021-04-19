package communication

abstract class MessageBoard[EntryType] {
  def contents: List[EntryType]
  def append(entry: EntryType): MessageBoard[EntryType]
  def quit: Boolean
}

class MessageBoardEntry(val sender: String, val message: String) {
  override def toString: String = s"$sender: $message"
  def quit: Boolean = message == ":q"
  def nonQuit: Boolean = !quit
}

class SimpleMessageBoard(val contents: List[MessageBoardEntry]) extends MessageBoard[MessageBoardEntry] {
  def append(entry: MessageBoardEntry): SimpleMessageBoard = new SimpleMessageBoard(entry :: contents)
  def quit: Boolean = contents.head.quit
  def nonQuit: Boolean = contents.head.nonQuit
}
