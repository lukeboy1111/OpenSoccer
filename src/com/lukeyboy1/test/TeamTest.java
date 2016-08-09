package com.lukeyboy1.test;

import com.lukeyboy1.beans.TeamBean;
import com.lukeyboy1.service.ServiceLocator;
import com.lukeyboy1.service.iface.ITeamService;
import com.lukeyboy1.test.context.InitialContextFactoryForTest;

import junit.framework.TestCase;

import javax.naming.Context;
import javax.naming.InitialContext;
import static javax.rmi.PortableRemoteObject.narrow;
import javax.sql.DataSource;
import junit.framework.TestCase;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;


public class TeamTest extends TestCase {
	
	
	public void test1() {
		String teamId = "72";
		ITeamService teamService = ServiceLocator.getTeamService(true);
		TeamBean tb = teamService.getTeam(teamId);
		
	}
}
