package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.dao.*;
import ua.nure.hrynko.services.AllMethodsWithTransactions;


import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.<br/>
 *
 * 
 */
public class CommandContainer {
	
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	private static final Map<String, Command> commands = new TreeMap<>();
	
	static {
		commands.put("login", new LoginCommand(MySqlUserDAO.getInstance()));
		commands.put("loginPage", new LoginPageCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		commands.put("noCommand", new NoCommand());
		
		// client commands
		commands.put("clientPage", new ClientPageCommand(MySqlCruiseDAO.getInstance()));
		commands.put("AddToBasket", new ClientPageAddToBasketCommand(MySqlCruiseDAO.getInstance()));
		commands.put("goToBasket", new ClientPageGoToBasketCommand());
		commands.put("RemoveOneUnitFromBasket", new ClientBasketRemoveOneUnitCommand(MySqlCruiseDAO.getInstance()));
		commands.put("AddOneUnitInBasket", new ClientBasketAddOneUnitCommand(MySqlCruiseDAO.getInstance()));
		commands.put("RemoveOnePositionFromBasket",
				new ClientBasketRemoveOnePositionCommand(MySqlCruiseDAO.getInstance()));
		commands.put("clientBasketConfirmOrderOfSelectedUnits",
				new ClientBasketConfirmOrderOfSelectedUnitsCommand(new AllMethodsWithTransactions()));
		commands.put("cruiseFindByStartDate", new ClientPageCruiseFindByStartDateCommand(MySqlCruiseDAO.getInstance()));
		commands.put("cruiseFindByDuration", new ClientPageCruiseFindByDurationCommand(MySqlCruiseDAO.getInstance()));
//		commands.put("signUpPage", new SignUpPageCommand());
//		commands.put("signUpUser", new SignUpCommand(MySqlUsersDAO.getInstance(), MySqlAccountsDAO.getInstance()));
//		commands.put("clientPageSortedDownByName", new ClientPageSortedDownByNameCommand(MySqlTariffsDAO.getInstance(),
//				MySqlUsersHasTariffsDAO.getInstance()));
//		commands.put("clientPageSortedUpByName", new ClientPageSortedUpByNameCommand(MySqlTariffsDAO.getInstance(),
//				MySqlUsersHasTariffsDAO.getInstance()));
//		commands.put("clientPageSortedUpByPrice", new ClientPageSortedUpByPriceCommand(MySqlTariffsDAO.getInstance(),
//				MySqlUsersHasTariffsDAO.getInstance()));
//		commands.put("clientPageSortedDownByPrice", new ClientPageSortedDownByPriceCommand(MySqlTariffsDAO.getInstance(),
//				MySqlUsersHasTariffsDAO.getInstance()));
//		commands.put("ClientPageGoToTopUpYourAccount",
//				new ClientPageGoToTopUpYourAccountCommand(MySqlUsersDAO.getInstance(),MySqlAccountsDAO.getInstance()));
//		commands.put("clientPageGoToUpdatingAccountPage", new ClientPageGoToUpdatingAccountPageCommand());
//		commands.put("clientUpdatingPageUpdateAccount",
//				new ClientUpdatingPageUpdateAccountCommand(MySqlAccountsDAO.getInstance()));
//		commands.put("clientPageToPay",
//				new ClientPageToPayCommand(MySqlUsersDAO.getInstance(),MySqlAccountsDAO.getInstance(),
//						MySqlTariffsDAO.getInstance(), MySqlUsersHasTariffsDAO.getInstance()));
//		commands.put("clientPageToGoPageTariffsForChooseTariffs",
//				new ClientPageGoToPageTariffsForChooseTariffCommand(MySqlTariffsDAO.getInstance(),
//				MySqlUsersHasTariffsDAO.getInstance()));

		// admin commands
		commands.put("adminPage", new AdminPageCommand(MySqlUserDAO.getInstance()));

		commands.put("pageAdminCruises", new AdminPageCruisesCommand(MySqlCruiseDAO.getInstance()));
		commands.put("pageAdminOrders", new AdminPageOrderCommand(MySqlUsersHasCruiseDAO.getInstance()));
//		commands.put("adminPageRemoveUser", new AdminPageRemoveUserCommand(MySqlUsersDAO.getInstance(),
//				MySqlAccountsDAO.getInstance()));
//		commands.put("adminGoToUpdatingPage", new AdminGoToUpdatingPageCommand(MySqlUsersDAO.getInstance()));
//		commands.put("adminUpdatingPageUpdateUser", new AdminUpdatingPageUpdateUserCommand(MySqlUsersDAO.getInstance()));
//		commands.put("adminGoToPageAddingUser", new AdminGoToPageForAddingUserCommand());
//		commands.put("adminAddingPageAddUser",
//				new AdminAddingPageAddUserCommand(MySqlUsersDAO.getInstance(),MySqlAccountsDAO.getInstance()));
//		commands.put("adminPageBlockingUser",new AdminPageBlockingUserCommand(MySqlUsersDAO.getInstance()));
//		commands.put("adminPageUnBlockingUser",new AdminPageUnBlockingUserCommand(MySqlUsersDAO.getInstance()));
//		commands.put("adminPageTariffsRemoveTariff", new AdminPageTariffsRemoveTariffCommand(MySqlTariffsDAO.getInstance()));
//		commands.put("adminGoToPageForAddingTariff", new AdminGoToPageForAddingTariffCommand());
//		commands.put("adminAddingPageAddTariff", new AdminAddingPageAddTariffCommand(MySqlTariffsDAO.getInstance()));
//		commands.put("adminGoToPageForUpdatingTariff",
//				new AdminGoToPageForUpdatingTariffCommand(MySqlTariffsDAO.getInstance()));
//		commands.put("adminUpdatingPageUpdateTariff", new AdminUpdatingPageUpdateTariffCommand(MySqlTariffsDAO.getInstance()));


		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
	
}