package client.model;

import client.networking.Client;
import shared.LoginObject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginModelManager implements LoginModel
{
  private Client client;
  private PropertyChangeSupport changeSupport;

  public LoginModelManager(Client c)
  {
    client = c;
    changeSupport = new PropertyChangeSupport(this);
  }


  @Override public void processLogin(LoginObject lo)
  {
    client.login(lo);
  }



  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    changeSupport.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    changeSupport.removePropertyChangeListener(listener);
  }
}
