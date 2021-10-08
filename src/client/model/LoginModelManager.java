package client.model;

import client.networking.Client;

import java.beans.PropertyChangeEvent;
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

//    client.addListener("login", this::onLoginResponse);
  }


  @Override public String processLogin(String username, String password)
  {
    return client.login(username, password);
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
