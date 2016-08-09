package com.lukeyboy1.dao;

import com.lukeyboy1.dao.iface.IPlayerDao;
import com.lukeyboy1.dao.iface.ITeamDao;

public abstract class FitbaDAOFactory
{
	public static final Class FACTORY_CLASS = com.lukeyboy1.dao.FitbaLSDAOFactory.class;

	public static FitbaDAOFactory getFactory()
	{
		try
		{
			return (FitbaDAOFactory) FACTORY_CLASS.newInstance();
		} catch (Exception e)
		{
			throw new RuntimeException("Couldn't create DAOFactory!");
		}
	}
	
	public abstract ITeamDao getTeamDao(Boolean testMode);
	public abstract IPlayerDao getPlayerDao(Boolean testMode);

}
