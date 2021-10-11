package shared;

public class TransferObject
{
  private String type;
  private String contentClass;

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
}
