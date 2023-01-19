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
		commands.put("signUpPage", new SignUpPageCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("signUpUser", new SignUpCommand(MySqlUserDAO.getInstance(), MySqlAccountDAO.getInstance()));

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
		commands.put("ClientPageGoToMyProfile", new ClientPageGoToMyProfileCommand());
		commands.put("ClientPageGoToTopUpYourAccount",
				new ClientPageGoToTopUpYourAccountCommand(MySqlUserDAO.getInstance(),MySqlAccountDAO.getInstance()));
		commands.put("clientPageGoToUpdatingAccountPage", new ClientPageGoToUpdatingAccountPageCommand());
		commands.put("clientUpdatingPageUpdateAccount",
				new ClientUpdatingPageUpdateAccountCommand(MySqlAccountDAO.getInstance()));
		commands.put("clientUpdatingPageUpdateYourProfile",
				new ClientUpdatingPageUpdateYourProfileCommand(MySqlUserDAO.getInstance()));
		commands.put("clientPageMyOrders", new ClientPageMyOrdersCommand(MySqlOrderViewDAO.getInstance()));


		// admin commands
		commands.put("adminPage", new AdminPageCommand(MySqlUserDAO.getInstance()));

		commands.put("pageAdminCruises", new AdminPageCruisesCommand(MySqlCruiseDAO.getInstance()));
		commands.put("pageAdminOrders", new AdminPageOrderCommand(MySqlOrderViewDAO.getInstance()));
		commands.put("pageAdminChangeStatusWithWithdrawalFromDeposit",
				new AdminPageChangeStatusWithWithdrawalFromDepositCommand(new AllMethodsWithTransactions(),
						 MySqlOrderViewDAO.getInstance()));
		commands.put("adminPageCruiseRemoveCruise", new AdminPageRemoveCruiseCommand(MySqlCruiseDAO.getInstance()));
		commands.put("adminGoToPageForUpdatingCruise",
				new AdminGoToPageForUpdatingCruiseCommand(MySqlCruiseDAO.getInstance()));
		commands.put("adminUpdatingPageUpdateCruise", new AdminUpdatingPageUpdateCruiseCommand(
				MySqlCruiseDAO.getInstance()));
		commands.put("adminGoToPageForAddingCruise", new AdminGoToPageForAddingCruiseCommand());
		commands.put("adminAddingPageAddCruise", new AdminAddingPageAddCruiseCommand(MySqlCruiseDAO.getInstance()));
		commands.put("adminPageRemoveUser", new AdminPageRemoveUserCommand(MySqlUserDAO.getInstance(),
				MySqlAccountDAO.getInstance()));
		commands.put("adminGoToUpdatingPage", new AdminGoToUpdatingPageUserCommand(MySqlUserDAO.getInstance()));
		commands.put("adminUpdatingPageUpdateUser", new AdminUpdatingPageUpdateUserCommand(MySqlUserDAO.getInstance()));
		commands.put("adminGoToPageAddingUser", new AdminGoToPageForAddingUserCommand());
		commands.put("adminAddingPageAddUser",
				new AdminAddingPageAddUserCommand(MySqlUserDAO.getInstance(),MySqlAccountDAO.getInstance()));

		commands.put("pageAdminShips", new AdminPageShipsCommand(MySqlShipDAO.getInstance()));
		commands.put("adminGoToPageForRentShip", new AdminGoToPageForRentShipCommand());
		commands.put("adminRentingPageAddShipToCruiseHasShip",
				new AdminRentingPageAddShipToCruiseHasShipCommand(MySqlCruiseHasShipDAO.getInstance()));

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