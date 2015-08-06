package integration.DAO;

public class Access_daoFactory {
  public DAO getDao(String nome){
	  return new DAOLogin();
  }
}
