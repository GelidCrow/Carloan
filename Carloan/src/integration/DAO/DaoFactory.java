package integration.DAO;

public abstract class DaoFactory {
	public Access_daoFactory getDaoFactory(String nome){
		return new Access_daoFactory();
	}
}
