package shared;

public class TransferObject
{
  private final String type;
  private final String contentClass;

  public TransferObject(String type, String content)
  {
    this.type = type;
    this.contentClass = content;
  }

  public String getType()
  {
    return type;
  }

  public String getContentClass()
  {
    return contentClass;
  }

  @Override public String toString()
  {
    return "TransferObject{" + "type='" + type + '\'' + ", contentClass='"
        + contentClass + '\'' + '}';
  }
}
