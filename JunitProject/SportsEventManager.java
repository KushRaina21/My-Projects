import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class SportsEventManagerTest {
	
	public static void main(String[] args)
	{
		Result result=JUnitCore.runClasses(SportsEventManagerTest.class);
		List<Failure> faliures =result.getFailures();
		for (Failure failure : faliures) {
			System.out.println(failure);
		}
	}
	
	SportsEventManager sem= null;
	
	@Before
	public void setUp() {
		sem=new SportsEventManager();
		sem.gameCounter=0;
	}
	
	@Test
	public void testGetGameList() {
		
	}
	
	@Test
	public void testMaxPlayersLessThanZero() {
		assertEquals(100,sem.addGame("hockey", -11));
	}
	
	@Test
	public void testGameCounterGreaterThanZero() {
		sem.gameCounter=21;
		assertEquals(98,sem.addGame("hockey", 11));
	}

	@Test
	public void testNameIsNull() {
		assertEquals(99,sem.addGame(null, 11));
	}
	
	@Test
	public void testGameIsNotNull() {
		sem.gameCounter=1;
		sem.gameList[0]=new Game("hockey",1);
		assertEquals(101,sem.addGame("hockey", 11));
	}

	@Test
	public void testAddGameReturnsZero() {
		assertEquals(0,sem.addGame("hockey", 11));
	}
	
	@Test
	public void testSearchGameNotNull() {
		try{
		sem.gameCounter=2;
		Game game1 = new Game("hockey",1);
		Game game2 = new Game("football",1);
		sem.gameList[0]=game1;
		sem.gameList[1]=game2;
		assertEquals(game1,sem.searchGame("hockey"));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	
	@Test
	public void testSearchGameReturnsNullWhenNoGameCounterZero() {
	try{	
		assertEquals(null,sem.searchGame("hockey"));
	}
	catch(NullPointerException E)
	{
		fail();
	}
	}
	
	@Test
	public void testSearchGameReturnsNullWhenGameNotFoundInList() {
		try{
		sem.gameList[0]=new Game("hockey",1);
		sem.gameList[1]=new Game ("Soccer",3);
		sem.gameCounter=2;
		assertEquals(null,sem.searchGame("xyz"));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	@Test
	public void testAddPlayerWhenPlayerisFound() {
		try{
		
		sem.gameList[0]=new Game("hockey",1);
		sem.gameList[1]=new Game("football",1);
		String[] gameName={"hockey","football"};
		sem.playerList[0]=new Player("kush", sem.gameList);
		sem.playerCounter=1;
		assertEquals("kush  already exists",sem.addPlayer("kush", gameName));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	@Test
	public void testAddPlayerWhenGameisNotinTheList() {
		try{
		sem.gameList[0]=new Game("hockey",1);
		//sem.gameList[1]=new Game("football",1);
		sem.gameCounter=1;
		String[] gameName={"cricket"};
		sem.playerList[0]=new Player("kush", sem.gameList);
		sem.playerCounter=1;
		GameAssociation g1=new GameAssociation();
		sem.gameAssociationList[0]=g1;//.gamename="football";
		sem.gameAssociationList[0].gamename="hockey";
		sem.gameAssociationList[0].noofDays=1;
		sem.gameAssociationList[0].daynames=new String[2]; 
		sem.gameAssociationList[0].daynames[0]="monday";
		sem.gameAssociationList[0].noofPlayers=0;
		sem.gameAssociationList[0].playerNames=new String[2];
		sem.gameAssociationList[0].playerNames[0]="kush";
		assertEquals("Error you cannot be registered for cricket",sem.addPlayer("ravi", gameName));
		}
		catch(NullPointerException E)
		{
			fail();
		}
		
	}
	@Test
	public void testAddPlayerWhenGameinList() {
		try{
		sem.gameList[0]=new Game("Football",1);
		//sem.gameList[1]=new Game ("Football",2);
		//sem.gameList[1]=new Game("football",1);
		sem.gameCounter=2;
		String[] gameName={"Football"};
		sem.playerList[0]=new Player("kush", sem.gameList);
		sem.playerCounter=1;
		GameAssociation g1=new GameAssociation();
		sem.gameAssociationList[0]=g1;//.gamename="football";
		sem.gameAssociationList[0].gamename="Football";
		sem.gameAssociationList[0].noofDays=1;
		sem.gameAssociationList[0].daynames=new String[2]; 
		sem.gameAssociationList[0].daynames[0]="monday";
		sem.gameAssociationList[0].noofPlayers=0;
		sem.gameAssociationList[0].playerNames=new String[2];
		sem.gameAssociationList[0].playerNames[0]="kush";
		assertEquals("ravi added successfully",sem.addPlayer("ravi", gameName));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	
	
	@Test
	public void testAddSchedulerWhenDayIsAlreadyAdded() {
		try{
		sem.gameList[0]=new Game("hockey",1);
		sem.gameList[1]=new Game("football",1);
		String[] gameName={"hockey","football"};
		sem.scheduleList[0]=new DaySchedule("monday", sem.gameList);
		sem.scheduleCounter=2;
		assertEquals("monday  already scheduled",sem.addSchedule("monday", gameName));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	@Test
	public void testAddSchedulerWhenGameinNotinTheList() {
		try {
		sem.gameList[0]=new Game("football",1);
		String[] gameName={"cricket"};
		sem.scheduleList[0]=new DaySchedule("monday", sem.gameList);
		sem.scheduleCounter=1;
		assertEquals("Error you cannot be registered for cricket",sem.addSchedule("tuesday", gameName));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	@Test
	public void testAddSchedulerWhenGameinTheListDayNotScheduled() {
		try{
		sem.gameList[0]=new Game("football",1);
		String[] gameName={"football"};
		sem.scheduleList[0]=new DaySchedule("monday", sem.gameList);
		sem.scheduleCounter=1;
		sem.gameCounter=1;
		GameAssociation g1=new GameAssociation();
		sem.gameAssociationList[0]=g1;
		sem.gameAssociationList[0].gamename="football";
		sem.gameAssociationList[0].noofDays=1;
		sem.gameAssociationList[0].daynames=new String[2]; 
		sem.gameAssociationList[0].daynames[0]="monday";
		sem.gameAssociationList[0].noofPlayers=0;
		sem.gameAssociationList[0].playerNames=new String[2];
		sem.gameAssociationList[0].playerNames[0]="kush";
		assertEquals("tuesday added successfully",sem.addSchedule("tuesday", gameName));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	
	
	
	@Test
	public void testSearchPlayerWhenPlayerNameIsSame() {
		try{
		sem.playerCounter=1;
		sem.gameList[0]=new Game("hockey",1);
		sem.playerList[0]=new Player("kush", sem.gameList);
		assertEquals(sem.playerList[0],sem.searchPlayer("kush"));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	
	@Test
	public void testSearchPlayerWhenPlayerNameIsNotinPlayerList() {
		try{
		sem.playerCounter=1;
		sem.gameList[0]=new Game("hockey",1);
		sem.playerList[0]=new Player("kush", sem.gameList);
		assertEquals(null,sem.searchPlayer("anurag"));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	@Test
	public void testSearchPlayerWhenNoPlayerInTheList() {
		try{
		sem.playerCounter=0;
		assertEquals(null,sem.searchPlayer("kush"));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	
	@Test
	public void testSearchDayWhenDayisThereInSchedule() {
		try{
		//sem.playerCounter=1;
		sem.gameList[0]=new Game("football",1);
		sem.scheduleList[0]=new DaySchedule("monday", sem.gameList);
		sem.scheduleCounter=1;
		assertEquals(sem.scheduleList[0],sem.searchDay("monday"));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	
	@Test
	public void testSearchDayWhenNameIsNotInTheList() {
		try{
		//sem.playerCounter=1;
		sem.gameList[0]=new Game("football",1);
		sem.scheduleList[0]=new DaySchedule("monday", sem.gameList);
		sem.scheduleCounter=1;
		assertEquals(null,sem.searchDay("tuesday"));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	@Test
	public void testSearchDayWhenThereIsNoDayScheduleListCreated() {
		try{
		assertEquals(null,sem.searchDay("tuesday"));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	
	@Test
	public void testDisplayGameWiseScheduleWhenGameNameIsNotInTheList() {
		try{
		sem.gameCounter=1;
		sem.scheduleCounter=1;
		sem.gameList[0]=new Game("football",1);
		assertEquals("Error : This game is not valid",sem.displayGameWiseSchedule("cricket"));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	@Test
	public void testDisplayGameWiseScheduleWhenGameNameIsInTheList() {
		try{
		sem.gameCounter=1;
		//sem.scheduleCounter=1;
		sem.gameList[0]=new Game("football",1);
		GameAssociation g1=new GameAssociation();
		sem.gameAssociationList[0]=g1;
		sem.gameAssociationList[0].gamename="football";
		sem.gameAssociationList[0].noofDays=1;
		sem.gameAssociationList[0].daynames=new String[2]; 
		sem.gameAssociationList[0].daynames[0]="monday";
		sem.gameAssociationList[0].noofPlayers=0;
		sem.gameAssociationList[0].playerNames=new String[2];
		sem.gameAssociationList[0].playerNames[0]="kush";
		assertEquals("Players Names: kush\nDay Names: monday",sem.displayGameWiseSchedule("football"));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	@Test
	public void testDisplayDayWiseScheduleWhenDayIsNotInSchedule() {
		try{
		//sem.gameCounter=1;
		sem.scheduleCounter=1;
		sem.gameList[0]=new Game("football",1);
		sem.scheduleList[0]=new DaySchedule("monday", sem.gameList);
		assertEquals("Error : This day is not valid",sem.displayDayWiseSchedule("tuesday"));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	
	
	@Test
	public void testDisplayDayWiseScheduleWhenDayIsInTheList() {
		try{
		sem.scheduleCounter=1;
		sem.gameList[0]=new Game("football",1);
		sem.gameList[1]=new Game("football1",1);
		sem.gameList[2]=new Game("football2",1);
		sem.gameList[3]=new Game("football3",1);
		sem.gameList[4]=new Game("football4",1);
		sem.gameList[5]=new Game("football5",1);
		sem.gameList[6]=new Game("football6",1);
		sem.scheduleList[0]=new DaySchedule("monday", sem.gameList);
		GameAssociation g1=new GameAssociation();
		sem.gameAssociationList[0]=g1;
		sem.gameAssociationList[0].gamename="football";
		sem.gameAssociationList[0].noofDays=1;
		sem.gameAssociationList[0].daynames=new String[2]; 
		sem.gameAssociationList[0].daynames[0]="monday";
		sem.gameAssociationList[0].noofPlayers=0;
		sem.gameAssociationList[0].playerNames=new String[2];
		sem.gameAssociationList[0].playerNames[0]="kush";
		GameAssociation g2=new GameAssociation();
		sem.gameAssociationList[1]=g2;
		sem.gameAssociationList[1].gamename="football1";
		sem.gameAssociationList[1].noofDays=1;
		sem.gameAssociationList[1].daynames=new String[2]; 
		sem.gameAssociationList[1].daynames[0]="tuesday";
		sem.gameAssociationList[1].noofPlayers=0;
		sem.gameAssociationList[1].playerNames=new String[2];
		sem.gameAssociationList[1].playerNames[0]="kush";
		GameAssociation g3=new GameAssociation();
		sem.gameAssociationList[2]=g3;
		sem.gameAssociationList[2].gamename="football2";
		sem.gameAssociationList[2].noofDays=1;
		sem.gameAssociationList[2].daynames=new String[2]; 
		sem.gameAssociationList[2].daynames[0]="tuesday";
		sem.gameAssociationList[2].noofPlayers=0;
		sem.gameAssociationList[2].playerNames=new String[2];
		sem.gameAssociationList[2].playerNames[0]="kush";
		GameAssociation g4=new GameAssociation();
		sem.gameAssociationList[3]=g4;
		sem.gameAssociationList[3].gamename="football3";
		sem.gameAssociationList[3].noofDays=1;
		sem.gameAssociationList[3].daynames=new String[2]; 
		sem.gameAssociationList[3].daynames[0]="tuesday";
		sem.gameAssociationList[3].noofPlayers=0;
		sem.gameAssociationList[3].playerNames=new String[2];
		sem.gameAssociationList[3].playerNames[0]="kush";
		GameAssociation g5=new GameAssociation();
		sem.gameAssociationList[4]=g5;
		sem.gameAssociationList[4].gamename="football4";
		sem.gameAssociationList[4].noofDays=1;
		sem.gameAssociationList[4].daynames=new String[2]; 
		sem.gameAssociationList[4].daynames[0]="tuesday";
		sem.gameAssociationList[4].noofPlayers=0;
		sem.gameAssociationList[4].playerNames=new String[2];
		sem.gameAssociationList[4].playerNames[0]="kush";
		GameAssociation g6=new GameAssociation();
		sem.gameAssociationList[5]=g6;
		sem.gameAssociationList[5].gamename="football5";
		sem.gameAssociationList[5].noofDays=1;
		sem.gameAssociationList[5].daynames=new String[2]; 
		sem.gameAssociationList[5].daynames[0]="tuesday";
		sem.gameAssociationList[5].noofPlayers=0;
		sem.gameAssociationList[5].playerNames=new String[2];
		sem.gameAssociationList[5].playerNames[0]="kush";
		GameAssociation g7=new GameAssociation();
		sem.gameAssociationList[6]=g7;
		sem.gameAssociationList[6].gamename="football6";
		sem.gameAssociationList[6].noofDays=1;
		sem.gameAssociationList[6].daynames=new String[2]; 
		sem.gameAssociationList[6].daynames[0]="tuesday";
		sem.gameAssociationList[6].noofPlayers=0;
		sem.gameAssociationList[6].playerNames=new String[2];
		sem.gameAssociationList[6].playerNames[0]="kush";
		assertEquals("Game = football kush\nGame = football1 kush\nGame = football2 kush\nGame = football3 kush\nGame = football4 kush\nGame = football5 kush\nGame = football6 kush\n",sem.displayDayWiseSchedule("monday"));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	@Test
	public void testDisplayPlayerWiseScheduleWhenPlayerNotValid() {
		try{
			sem.gameList[0]=new Game("football",1);
		sem.playerList[0]=new Player("kush", sem.gameList);
		sem.playerCounter=1;
		assertEquals("Error : This player is not valid",sem.displayPlayerWiseSchedule("ravi"));
		}
		catch(NullPointerException E)
		{
			fail();
		}
	}
	@Test
	public void testDisplayPlayerWiseScheduleWhenPlayerIsValid() {
		try{
		sem.gameList[0]=new Game("football",1);
		sem.gameList[1]=new Game("football1",1);
		sem.gameList[2]=new Game("football2",1);
		sem.gameList[3]=new Game("football3",1);
		sem.gameList[4]=new Game("football4",1);
		sem.gameList[5]=new Game("football5",1);
		sem.gameList[6]=new Game("football6",1);
		sem.playerList[0]=new Player("kush", sem.gameList);
		GameAssociation g1=new GameAssociation();
		sem.gameAssociationList[0]=g1;
		sem.gameAssociationList[0].gamename="football";
		sem.gameAssociationList[0].daynames=new String[2]; 
		sem.gameAssociationList[0].daynames[0]="monday";
		GameAssociation g2=new GameAssociation();
		sem.gameAssociationList[1]=g2;
		sem.gameAssociationList[1].gamename="football1";
		sem.gameAssociationList[1].daynames=new String[2]; 
		sem.gameAssociationList[1].daynames[0]="tuesday";
		GameAssociation g3=new GameAssociation();
		sem.gameAssociationList[2]=g3;
		sem.gameAssociationList[2].gamename="football2";
		sem.gameAssociationList[2].daynames=new String[2]; 
		sem.gameAssociationList[2].daynames[0]="tuesday";
		GameAssociation g4=new GameAssociation();
		sem.gameAssociationList[3]=g4;
		sem.gameAssociationList[3].gamename="football3";
		sem.gameAssociationList[3].daynames=new String[2]; 
		sem.gameAssociationList[3].daynames[0]="tuesday";
		GameAssociation g5=new GameAssociation();
		sem.gameAssociationList[4]=g5;
		sem.gameAssociationList[4].gamename="football4";
		sem.gameAssociationList[4].daynames=new String[2]; 
		sem.gameAssociationList[4].daynames[0]="tuesday";
		GameAssociation g6=new GameAssociation();
		sem.gameAssociationList[5]=g6;
		sem.gameAssociationList[5].gamename="football5";
		sem.gameAssociationList[5].daynames=new String[2]; 
		sem.gameAssociationList[5].daynames[0]="tuesday";
		GameAssociation g7=new GameAssociation();
		sem.gameAssociationList[6]=g7;
		sem.gameAssociationList[6].gamename="football6";
		sem.gameAssociationList[6].daynames=new String[2]; 
		sem.gameAssociationList[6].daynames[0]="tuesday";
		sem.playerCounter=1;
		assertEquals("Game : football monday\nGame : football1 tuesday\nGame : football2 tuesday\nGame : football3 tuesday\nGame : football4 tuesday\nGame : football5 tuesday\nGame : football6 tuesday\n",sem.displayPlayerWiseSchedule("kush"));
		}
		catch(NullPointerException E)
		{
			fail();
		}
		}
		
}
