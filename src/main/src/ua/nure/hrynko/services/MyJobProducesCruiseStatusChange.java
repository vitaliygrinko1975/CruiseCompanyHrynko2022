package ua.nure.hrynko.services;

import org.apache.log4j.Logger;
import org.quartz.*;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.dao.MySqlCruiseDAO;
import ua.nure.hrynko.dao.MySqlCruiseHasShipDAO;
import ua.nure.hrynko.models.Cruise;
import ua.nure.hrynko.models.CruiseHasShip;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyJobProducesCruiseStatusChange implements Job {

    private static final Logger LOG = Logger.getLogger(MyJobProducesCruiseStatusChange.class);


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.debug("MyJobProducesCruiseStatusChange starts");

        //START TRANSACTION
        MySqlCruiseDAO mySqlCruiseDAO = MySqlCruiseDAO.getInstance();
        MySqlCruiseHasShipDAO mySqlCruiseHasShipDAO = MySqlCruiseHasShipDAO.getInstance();
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);

            List<Cruise> listAllCruise = mySqlCruiseDAO.findAllCruises();
            List<CruiseHasShip> listCruiseHasShip = mySqlCruiseHasShipDAO.findAllItemsOfCruiseHasShip();

            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();
            long currentDateInMilliseconds = currentDate.getTime();
            LOG.trace("Current data --> " + currentDate);
            for (Cruise itemCruise : listAllCruise) {
                //дата начала
                long cruiseStartInMilliseconds = itemCruise.getStartOfCruise().getTime();
                LOG.trace("Start cruise data--> " + itemCruise.getStartOfCruise());
                //устанавливаем дату, с которой будет производить операции
                calendar.setTime(itemCruise.getStartOfCruise());
                // прибавляем кол дней = значению поля duration к установленной дате
                calendar.add(Calendar.DAY_OF_MONTH, itemCruise.getDuration());
                long cruiseFinishInMilliseconds = calendar.getTime().getTime();
                LOG.trace("Finish cruise data--> " + calendar.getTime());

                if (currentDateInMilliseconds <= cruiseStartInMilliseconds) {
                    itemCruise.setStatus("Не начался");
                    mySqlCruiseDAO.updateCruisesDb(con, itemCruise);
                    LOG.trace("Change status item on cruise DB--> " + "Не начался");
                }
                if (currentDateInMilliseconds >= cruiseStartInMilliseconds &&
                        currentDateInMilliseconds < cruiseFinishInMilliseconds) {
                    itemCruise.setStatus("Начался");
                    mySqlCruiseDAO.updateCruisesDb(con, itemCruise);
                    LOG.trace("Change status item on cruise DB--> " + "Начался");
                }
                if (currentDateInMilliseconds >= cruiseFinishInMilliseconds) {
                    itemCruise.setStatus("Закончился");
                    mySqlCruiseDAO.updateCruisesDb(con, itemCruise);
                    LOG.trace("Change status item on cruise DB--> " + "Закончился");
                }
            }
            for (CruiseHasShip itemCruiseHasShip : listCruiseHasShip) {
                long contractEndInMilliseconds = itemCruiseHasShip.getEndOfContract().getTime();
                LOG.trace("End contract data--> " + itemCruiseHasShip.getEndOfContract());
                if(currentDateInMilliseconds >= contractEndInMilliseconds){
                    itemCruiseHasShip.setStatus("Закончился");
                    mySqlCruiseHasShipDAO.updateCruiseHasShipDb(con, itemCruiseHasShip);
                    LOG.trace("Change status item on cruise_has_ship DB --> " + "Закончился");
                }
                if(currentDateInMilliseconds < contractEndInMilliseconds){
                    itemCruiseHasShip.setStatus("Действующий");
                    mySqlCruiseHasShipDAO.updateCruiseHasShipDb(con, itemCruiseHasShip);
                    LOG.trace("Change status item on cruise_has_ship DB --> " + "Действующий");
                }

            }
            con.commit();
            LOG.debug("MyJobProducesCruiseStatusChange finished");
        } catch (Exception ex) {
            DBManager.rollback(con);
            LOG.error("cannot change status cruise", ex);
        } finally {
            DBManager.close(con);
        }//END TRANSACTION
    }
}
