package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.command.admin.*;
import ua.nure.hrynko.command.client.*;
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
		commands.put("goToWelcomePage", new GoToWelcomePageCommand());
		commands.put("login", new LoginCommand(new MySqlUserDAO()));
		commands.put("loginPage", new LoginPageCommand());
		commands.put("signUpPage", new SignUpPageCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("signUpUser", new SignUpCommand(new AllMethodsWithTransactions()));
		commands.put("goToWelcomeRegisteredUser", new GoToWelcomeRegisteredUserCommand());

		// client commands
		commands.put("clientPage", new ClientPageCommand(new MySqlCruiseDAO()));
		commands.put("addToBasket", new ClientPageAddToBasketCommand(new MySqlCruiseDAO()));
		commands.put("goToBasket", new ClientPageGoToBasketCommand());
		commands.put("removeOneUnitFromBasket", new ClientBasketRemoveOneUnitCommand(new MySqlCruiseDAO()));
		commands.put("addOneUnitInBasket", new ClientBasketAddOneUnitCommand(new MySqlCruiseDAO()));
		commands.put("removeOnePositionFromBasket",
				new ClientBasketRemoveOnePositionCommand(new MySqlCruiseDAO()));
		commands.put("clientBasketConfirmOrderOfSelectedUnits",
				new ClientBasketConfirmOrderOfSelectedUnitsCommand(new AllMethodsWithTransactions()));
		commands.put("cruiseFindByStartDate", new ClientPageCruiseFindByStartDateCommand(new MySqlCruiseDAO()));
		commands.put("cruiseFindByDuration", new ClientPageCruiseFindByDurationCommand(new MySqlCruiseDAO()));
		commands.put("clientPageGoToMyProfile", new ClientPageGoToMyProfileCommand(new MySqlAccountDAO()));
		commands.put("clientPageGoToTopUpYourAccount",
				new ClientPageGoToTopUpYourAccountCommand(new MySqlUserDAO(),new MySqlAccountDAO()));
		commands.put("clientPageGoToUpdatingAccountPage", new ClientPageGoToUpdatingAccountPageCommand());
		commands.put("clientUpdatingPageUpdateAccount", new ClientUpdatingPageUpdateAccountCommand(new MySqlAccountDAO()));
		commands.put("clientUpdatingPageUpdateYourProfile", new ClientUpdatingPageUpdateYourProfileCommand(new MySqlUserDAO()));
		commands.put("clientPageMyOrders", new ClientPageMyOrdersCommand(new MySqlOrderViewDAO()));
		commands.put("clientPageMyProfileFileUpload", new ClientPageMyProfileFileUploadCommand(new MySqlAccountDAO()));

		// admin commands
		commands.put("adminPage", new AdminPageCommand(new MySqlUserDAO()));
		commands.put("pageAdminCruises", new AdminPageCruisesCommand(new MySqlCruiseDAO()));
		commands.put("pageAdminOrders", new AdminPageOrderCommand(new MySqlOrderViewDAO()));
		commands.put("pageAdminChangeStatusWithWithdrawalFromDeposit",
				new AdminPageChangeStatusWithWithdrawalFromDepositCommand(new AllMethodsWithTransactions(),
						new MySqlOrderViewDAO()));
		commands.put("adminPageCruiseRemoveCruise", new AdminPageRemoveCruiseCommand(new MySqlCruiseDAO()));
		commands.put("adminGoToPageForUpdatingCruise",
				new AdminGoToPageForUpdatingCruiseCommand(new MySqlCruiseDAO()));
		commands.put("adminUpdatingPageUpdateCruise", new AdminUpdatingPageUpdateCruiseCommand(
				new MySqlCruiseDAO()));
		commands.put("adminGoToPageForAddingCruise", new AdminGoToPageForAddingCruiseCommand());
		commands.put("adminAddingPageAddCruise", new AdminAddingPageAddCruiseCommand(new MySqlCruiseDAO()));
		commands.put("adminPageRemoveUser", new AdminPageRemoveUserCommand(new MySqlUserDAO(),
				MySqlAccountDAO.getInstance()));
		commands.put("adminGoToUpdatingPage", new AdminGoToUpdatingPageUserCommand(new MySqlUserDAO()));
		commands.put("adminUpdatingPageUpdateUser", new AdminUpdatingPageUpdateUserCommand(new MySqlUserDAO()));
		commands.put("adminGoToPageAddingUser", new AdminGoToPageForAddingUserCommand());
		commands.put("adminAddingPageAddUser",
				new AdminAddingPageAddUserCommand(new  AllMethodsWithTransactions()));

		commands.put("pageAdminShips", new AdminPageShipsCommand(new MySqlShipDAO()));
		commands.put("adminGoToPageForRentShip", new AdminGoToPageForRentShipCommand());
		commands.put("adminRentingPageAddShipToCruiseHasShip",
				new AdminRentingPageAddShipToCruiseHasShipCommand(new MySqlCruiseHasShipDAO()));
		commands.put("adminPageShipsRemoveShip", new AdminPageShipsRemoveShipCommand(new MySqlShipDAO()));
		commands.put("adminGoToPageForAddingShip", new AdminGoToPageForAddingShipCommand());
		commands.put("adminAddingPageAddShip", new AdminAddingPageAddShipCommand(new MySqlShipDAO()));
		commands.put("adminGoToPageForUpdatingShip", new AdminGoToPageForUpdatingShipCommand(new MySqlShipDAO()));
		commands.put("adminUpdatingPageUpdateShip", new AdminUpdatingPageUpdateShipCommand(new MySqlShipDAO()));

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